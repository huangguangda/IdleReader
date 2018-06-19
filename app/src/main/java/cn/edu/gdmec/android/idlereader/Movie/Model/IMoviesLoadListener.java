package cn.edu.gdmec.android.idlereader.Movie.Model;


import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;

public interface IMoviesLoadListener {
    void success(MoviesBean moviesBean);
    void fail(Throwable throwable);
    void loadMoreSuccess(MoviesBean moviesBean);
}
