package cn.edu.gdmec.android.idlereader.Movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;
import cn.edu.gdmec.android.idlereader.Movie.Presenter.MoviesPresenter;
import cn.edu.gdmec.android.idlereader.Movie.View.IMoviesView;
import cn.edu.gdmec.android.idlereader.R;


public class FgMovieFragment extends Fragment implements IMoviesView {

    private MoviesPresenter moviesPresenter;
    private RecyclerView rv_movie_on;
    private SwipeRefreshLayout srl_movie;
    private ItemMovieOnAdapter movieOnAdapter;
    private ItemMovieTop250Adapter movieTop250Adapter;
    private RecyclerView rv_movie_top250;
    //
    private LinearLayoutManager layoutManager;
    private int start=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter = new MoviesPresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_on);
        rv_movie_top250=view.findViewById(R.id.rv_movie_top250);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieTop250Adapter = new ItemMovieTop250Adapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovies("in_theaters",0);
        moviesPresenter.loadMovies("top250",0);
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies("in_theaters",0);
                moviesPresenter.loadMovies("top250",0);
            }
        });

        layoutManager=new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_movie_on.setLayoutManager(layoutManager);

        rv_movie_on.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState==RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition()+1)==layoutManager.getItemCount()){
                    loadMovie();
                }
            }
        });
    }

    @Override
    public void showNews(MoviesBean moviesBean) {
        if (moviesBean.getTotal()==250){
            movieTop250Adapter.setData(moviesBean.getSubjects());
            rv_movie_top250.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,false));
            rv_movie_top250.setHorizontalScrollBarEnabled(true);
            rv_movie_top250.setAdapter(movieTop250Adapter);
        }else {
            movieOnAdapter.setData(moviesBean.getSubjects());
            rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_movie_on.setAdapter(movieOnAdapter);
        }
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        movieOnAdapter.notifyItemRemoved(movieOnAdapter.getItemCount());
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showMoreMovie(MoviesBean movieBean) {
        movieOnAdapter.addData(movieBean.getSubjects());
        rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie_on.setAdapter(movieOnAdapter);
        movieOnAdapter.notifyDataSetChanged();
    }

    private void loadMovie(){
        start+=20;
        moviesPresenter.loadMovies("in_theaters",start);
    }
}
