package cn.edu.gdmec.android.idlereader.Movie.Presenter;


import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;
import cn.edu.gdmec.android.idlereader.Movie.Model.IMoviesLoadListener;
import cn.edu.gdmec.android.idlereader.Movie.Model.IMoviesModel;
import cn.edu.gdmec.android.idlereader.Movie.Model.MoviesModel;
import cn.edu.gdmec.android.idlereader.Movie.View.IMoviesView;

public class MoviesPresenter implements IMoviesPresenter,IMoviesLoadListener {

    private IMoviesModel iMoviesModel;
    private IMoviesView iMoviesView;

    public MoviesPresenter(IMoviesView iMoviesView) {
        this.iMoviesView = iMoviesView;
        this.iMoviesModel =new MoviesModel();
    }


    @Override
    public void success(MoviesBean moviesBean) {
        iMoviesView.hideDialog();
        iMoviesView.showNews(moviesBean);
    }

    @Override
    public void fail(Throwable throwable) {
        iMoviesView.hideDialog();
        iMoviesView.showErrorMsg(throwable);
    }

    @Override
    public void loadMoreSuccess(MoviesBean moviesBean) {
        iMoviesView.hideDialog();
        iMoviesView.showMoreMovie(moviesBean);
    }


    @Override
    public void loadMovies(String total,int start) {
        if (start==0){
            iMoviesView.showDialog();
        }
        //iMoviesView.showDialog();
        iMoviesModel.loadMovies(total,start,this);
    }
}
