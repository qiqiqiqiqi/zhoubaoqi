package study.qi.com.tantan;

/**
 * Created by feng on 2017/4/25.
 */

public class ADInfo {
    private String picUrl;
    private String videoUrl;
    private boolean video;
    private int date;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ADInfo{" +
                "picUrl='" + picUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", video=" + video +
                ", date=" + date +
                '}';
    }
}
