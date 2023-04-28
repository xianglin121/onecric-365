package com.onecric.CricketLive365.model;

/**
 * 开发公司：东莞市梦幻科技有限公司
 * 时间：2021/11/20
 */
public class LiveRoomInfoBean {

    /**
     * uid : 2
     * islive : 1
     * type : 1
     * viewers : 0
     * live_duration : 02:44
     * clicktime : 1636770692
     * match_id : 3596072
     * starttime : 1636707429
     * title : 测试
     * stream : 2_1636707429
     * thumb : http://baidu.com
     * pull : http://livepull.mhuan.shop/live/2_1636707429.m3u8
     * hotvotes : 0
     * ishot : 0
     * isrecommend : 0
     * is_home : 0
     * deletetime : 0
     * id : 1
     * typeName : 篮球
     * isliveName : 直播中
     * match_time : 2021-11-24 01:00
     * away_logo : https://cdn.sportnanoapi.com/basketball/team/821d1370da9d6010113bb0834aee2d6e.png
     * away_name : 法兰克斯顿蓝调女篮
     * home_logo : https://cdn.sportnanoapi.com/basketball/team/821d1370da9d6010113bb0834aee2d6e.png
     * home_name : 桑德林女篮
     * name : 澳南部女联
     */

    private int uid;
    private int islive;
    private int type;
    private int viewers;
    private String live_duration;
    private int clicktime;
    private int match_id;
    private int starttime;
    private String title;
    private String stream;
    private String thumb;
    private String pull;
    private int hotvotes;
    private int ishot;
    private int isrecommend;
    private int is_home;
    private int deletetime;
    private int id;
    private String typeName;
    private String isliveName;
    private String match_time;
    private String away_logo;
    private String away_name;
    private String home_logo;
    private String home_name;
    private String name;
    private QualityBean clarity;

    private int is_like;
    private int like_num;
    private int live_id;

    public int getLive_id() {
        return live_id;
    }

    public void setLive_id(int live_id) {
        this.live_id = live_id;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIslive() {
        return islive;
    }

    public void setIslive(int islive) {
        this.islive = islive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public String getLive_duration() {
        return live_duration;
    }

    public void setLive_duration(String live_duration) {
        this.live_duration = live_duration;
    }

    public int getClicktime() {
        return clicktime;
    }

    public void setClicktime(int clicktime) {
        this.clicktime = clicktime;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPull() {
        return pull;
    }

    public void setPull(String pull) {
        this.pull = pull;
    }

    public int getHotvotes() {
        return hotvotes;
    }

    public void setHotvotes(int hotvotes) {
        this.hotvotes = hotvotes;
    }

    public int getIshot() {
        return ishot;
    }

    public void setIshot(int ishot) {
        this.ishot = ishot;
    }

    public int getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(int isrecommend) {
        this.isrecommend = isrecommend;
    }

    public int getIs_home() {
        return is_home;
    }

    public void setIs_home(int is_home) {
        this.is_home = is_home;
    }

    public int getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(int deletetime) {
        this.deletetime = deletetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIsliveName() {
        return isliveName;
    }

    public void setIsliveName(String isliveName) {
        this.isliveName = isliveName;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getAway_logo() {
        return away_logo;
    }

    public void setAway_logo(String away_logo) {
        this.away_logo = away_logo;
    }

    public String getAway_name() {
        return away_name;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public String getHome_logo() {
        return home_logo;
    }

    public void setHome_logo(String home_logo) {
        this.home_logo = home_logo;
    }

    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QualityBean getClarity() {
        return clarity;
    }

    public void setClarity(QualityBean clarity) {
        this.clarity = clarity;
    }
}
