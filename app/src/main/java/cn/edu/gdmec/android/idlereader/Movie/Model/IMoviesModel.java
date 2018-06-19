package cn.edu.gdmec.android.idlereader.Movie.Model;


public interface IMoviesModel {
    void loadMovies(String total,int start, IMoviesLoadListener iMoviesLoadListener);
}
