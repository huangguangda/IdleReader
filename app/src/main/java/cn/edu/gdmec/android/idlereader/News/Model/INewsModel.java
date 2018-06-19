package cn.edu.gdmec.android.idlereader.News.Model;



public interface INewsModel {
    void loadNews(String hostType,
                  int startPage,
                  String id,
                  INewsLoadListener iNewsLoadListener);
}
