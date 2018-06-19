package cn.edu.gdmec.android.idlereader.News.View;

import cn.edu.gdmec.android.idlereader.Bean.NewsBean;

public interface INewsView {
    void showNews(NewsBean newsBean);

    void showMoreNews(NewsBean newsBean);

    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
