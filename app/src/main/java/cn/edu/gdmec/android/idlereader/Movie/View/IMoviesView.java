package cn.edu.gdmec.android.idlereader.Movie.View;


import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;

public interface IMoviesView {
    void showNews(MoviesBean moviesBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
    //
    void showMoreMovie(MoviesBean moviesBean);
}
