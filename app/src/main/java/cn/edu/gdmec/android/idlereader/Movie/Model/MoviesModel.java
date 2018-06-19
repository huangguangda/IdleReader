package cn.edu.gdmec.android.idlereader.Movie.Model;


import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;
import cn.edu.gdmec.android.idlereader.Http.Api;
import cn.edu.gdmec.android.idlereader.Http.RetrofitHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesModel implements IMoviesModel {

    @Override
    public void loadMovies( String total,final int start, final IMoviesLoadListener iMoviesLoadListener) {
        RetrofitHelper retrofitHelper= new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovies(total,start)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMoviesLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        if (start!=0){
                            iMoviesLoadListener.loadMoreSuccess(moviesBean);
                        }else {
                            iMoviesLoadListener.success(moviesBean);
                        }

                    }
                });
    }

}
