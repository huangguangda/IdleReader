package cn.edu.gdmec.android.idlereader.News.Model;


import cn.edu.gdmec.android.idlereader.Bean.NewsBean;
import cn.edu.gdmec.android.idlereader.Http.Api;
import cn.edu.gdmec.android.idlereader.Http.RetrofitHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsModel implements INewsModel {

    @Override
    public void loadNews(final String hostType, final int startPage, final String id,
                         final INewsLoadListener iNewsLoadListener) {
        RetrofitHelper retrofitHelper= new RetrofitHelper(Api.NEWS_HOST);
        retrofitHelper.getNews(hostType,id,startPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iNewsLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (startPage!=0){
                            iNewsLoadListener.loadMoreSuccess(newsBean);
                        }else {
                            iNewsLoadListener.success(newsBean);
                        }

                    }
                });
    }
}
