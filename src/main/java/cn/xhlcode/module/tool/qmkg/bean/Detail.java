package cn.xhlcode.module.tool.qmkg.bean;

public class Detail {
    private Boolean isMv; //是否是MV
    private String content; //发布者的文字说明
    private String cover; //封面
    private String author; //发布者
    private String playUrl; //歌曲 或  MV 地址
    private String singer; ////原唱
    private String song; //曲名
    private String tail; //上传设备
    private String time; //发布时间
    private String playNum; //播放数量
    private String commentNum; //评论数量
    private String score; //分数
    private String flowerNum; //鲜花
    private String giftNum; //礼物
    private String personalUrl; //个人中心主页地址


    public Boolean getMv() {
        return isMv;
    }

    public void setMv(Boolean mv) {
        isMv = mv;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFlowerNum() {
        return flowerNum;
    }

    public void setFlowerNum(String flowerNum) {
        this.flowerNum = flowerNum;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public String getPersonalUrl() {
        return personalUrl;
    }

    public void setPersonalUrl(String personalUrl) {
        this.personalUrl = personalUrl;
    }
}
