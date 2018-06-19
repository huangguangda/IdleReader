package cn.edu.gdmec.android.idlereader.Movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.idlereader.ADetailActivity;
import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;
import cn.edu.gdmec.android.idlereader.R;


public class ItemMovieTop250Adapter extends RecyclerView.Adapter<ItemMovieTop250Adapter.ViewHolder> {

    private List<MoviesBean.SubjectsBean> objects = new ArrayList<MoviesBean.SubjectsBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemMovieTop250Adapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void setData(List<MoviesBean.SubjectsBean> objects){
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_top250, parent, false);
        return new ItemMovieTop250Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MoviesBean.SubjectsBean bean=objects.get(position);
        if (bean==null){
            return;
        }
        Glide.with(context)
                .load(bean.getImages().getSmall())
                .into(holder.ivMovieTop);
        holder.tvMovieTopTitle.setText(bean.getTitle());
        holder.rlMovieOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra("url",bean.getAlt());
                intent.putExtra("title", bean.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout rlMovieOn;
        private ImageView ivMovieTop;
        private TextView tvMovieTopTitle;

        public ViewHolder(View view) {
            super(view);
            rlMovieOn = (LinearLayout) view.findViewById(R.id.rl_movie_on);
            ivMovieTop = (ImageView) view.findViewById(R.id.iv_movie_top);
            tvMovieTopTitle = (TextView) view.findViewById(R.id.tv_movie_top_title);
        }
    }
}
