package cn.edu.gdmec.android.idlereader.Http;

import java.util.concurrent.TimeUnit;

import cn.edu.gdmec.android.idlereader.Bean.MoviesBean;
import cn.edu.gdmec.android.idlereader.Bean.NewsBean;
import cn.edu.gdmec.android.idlereader.Bean.TodayBean;
import cn.edu.gdmec.android.idlereader.Bean.VideoUrlBean;
import cn.edu.gdmec.android.idlereader.Bean.WeatherBean;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;



public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private RetrofitService retrofitService;

    public RetrofitHelper(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
         retrofitService = retrofit.create(RetrofitService.class);
    }

    public Observable<NewsBean> getNews(String type, String id, int startPage) {
        return retrofitService.getNews(type, id, startPage);
    }
    public Observable<MoviesBean> getMovies(String total,int start) {
        return retrofitService.getMovie(total,start);
    }
    public Observable<TodayBean> getToday(String category){
        return retrofitService.getToday(category);
    }
    public Observable<VideoUrlBean> getVideoUrl(String api){
        return retrofitService.getVideoUrl(api);
    }
    public Observable<WeatherBean> getWeather(int cityKey){
        return retrofitService.getWeather(cityKey);
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

        }
        return okHttpClient;
    }

}
