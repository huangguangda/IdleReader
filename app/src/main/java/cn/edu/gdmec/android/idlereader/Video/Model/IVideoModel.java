package cn.edu.gdmec.android.idlereader.Video.Model;

public interface IVideoModel {
    void loadVideo(String category, IVideoLoadListener iVideoLoadListener);
    //void loadMovies(String total,int start, IMoviesLoadListener iMoviesLoadListener);
}
