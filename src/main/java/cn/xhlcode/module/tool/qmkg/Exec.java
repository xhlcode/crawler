package cn.xhlcode.module.tool.qmkg;

import cn.xhlcode.module.tool.qmkg.bean.Detail;
import cn.xhlcode.util.HttpUtils;
import cn.xhlcode.util.SpiderUtil;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exec {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(Exec.class);

    private static  String testUrl =  "http://www.xhlcode.cn/qmkg_analysis?shareUrl=https%3A%2F%2Fkg3.qq.com%2Fnode%2Fplay%3Fs%3DllaX5Nlrw5zKOlBa%26shareuid%3D639c9b84202832%26topsource%3Da0_pn201001006_z1_u1161555_l0_t1503035944__";
    private static String testUrl1 = "https://kg3.qq.com/node/play?s=llaX5Nlrw5zKOlBa&shareuid=639c9b84202832&topsource=a0_pn201001006_z1_u1161555_l0_t1503035944__ ";
    private static String personalPre = "https://node.kg.qq.com/personal?uid=";
    private static String musicUrl = "https://node.kg.qq.com/play?s=#shareid#&g_f=personal";


    public static Detail analysis(String shareUrl) {
        String result ="";

        String contentText = SpiderUtil.getHtmlContext(shareUrl);
        JSONObject jsonObj = getJSONDate(contentText);
        Detail detail = handleJson(jsonObj);
        return detail;

    }


    /**
     * 获取JSON数据
     * @param contextText
     * @return
     */
    public static JSONObject  getJSONDate(String contextText) {
        String regEx = "(?<=window.__DATA__ = )([.\\S\\s]*)(?=; </script>)";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(contextText);

        String data = new String();

        while (m.find()) {
            data = m.group();
        }
        return JSONObject.parseObject(data);
    }


    /**
     * 处理json
     * @param dataJson
     */
    public static Detail handleJson(JSONObject dataJson) {
        Detail qmkg = new Detail();
        Boolean isMv =  dataJson.getBoolean("isMV");
        qmkg.setMv(isMv);

        //获取歌曲详细信息
        JSONObject detail = dataJson.getJSONObject("detail");
        //发布者的文字说明
        qmkg.setContent(detail.getString("content"));
        //封面
        qmkg.setCover(detail.getString("cover"));
        //发布者
        qmkg.setAuthor(detail.getString("nick"));
        //歌曲 或  MV 地址
        if(isMv){
            qmkg.setPlayUrl(detail.getString("playurl_video"));
        }else{
            qmkg.setPlayUrl(detail.getString("playurl"));
        }
        //原唱
        qmkg.setSinger(detail.getString("singer_name"));
        //曲名
        qmkg.setSong(detail.getString("song_name"));
        //上传设备
        qmkg.setTail(detail.getString("tail_name"));
        //转化时间戳
        String timeStamp = detail.getString("ctime");
        long parsingResult = Long.parseLong(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //发布时间
        qmkg.setTime(sdf.format(parsingResult*1000));
        //播放数量
        qmkg.setPlayNum(detail.getString("play_num"));
        //评论数量
        qmkg.setCommentNum(detail.getString("comment_num"));
        //分数
        qmkg.setScore(detail.getString("score"));
        //鲜花
        qmkg.setFlowerNum(detail.getString("flower_num"));
        //礼物
        qmkg.setGiftNum(detail.getString("gift_num"));

        qmkg.setPersonalUrl(dataJson.getString("personalUrl"));
        return qmkg;
    }
}
