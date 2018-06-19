package cn.edu.gdmec.android.idlereader.Video.Model;


import java.util.List;

import cn.edu.gdmec.android.idlereader.Bean.TodayContentBean;
import cn.edu.gdmec.android.idlereader.Bean.VideoUrlBean;

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
    //void loadMoreSuccess(VideoUrlBean videoUrlBean);
}
