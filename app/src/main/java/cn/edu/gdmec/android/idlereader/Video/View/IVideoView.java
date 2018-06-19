package cn.edu.gdmec.android.idlereader.Video.View;

import java.util.List;

import cn.edu.gdmec.android.idlereader.Bean.TodayContentBean;

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
