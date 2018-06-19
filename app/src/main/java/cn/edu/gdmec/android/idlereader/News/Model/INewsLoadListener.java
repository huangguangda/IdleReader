package cn.edu.gdmec.android.idlereader.News.Model;


import cn.edu.gdmec.android.idlereader.Bean.NewsBean;

public interface INewsLoadListener {
    void success(NewsBean newsBean);
    void fail(Throwable throwable);

    void loadMoreSuccess(NewsBean newsBean);
}
