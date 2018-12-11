package cn.xhlcode;

import cn.xhlcode.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class HtmlUtilTest {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtilTest.class);



    private static final String qqMusicUrl = "https://c.y.qq.com/rsc/fcgi-bin/fcg_user_created_diss?hostuin=347606601&sin=0&size=32&r=1512031330762&g_tk=345719309&jsonpCallback=MusicJsonCallback3561589509370917&loginUin=347606601&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8¬ice=0&platform=yqq&needNewCode=0";

    @Test
    public void HtmlUtilDemo() {

        // 新建一个模拟谷歌chrome 浏览器客户端对象
        final WebClient webClient= new WebClient(BrowserVersion.CHROME);
        // 当执行JS 执行出错时 是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 当HTTP 的状态非 200 时是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // 是否允许 ActiveXNative
        webClient.getOptions().setActiveXNative(false);
        // 是否允许CSS
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        // 设置 支持Ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());


        HtmlPage page = null;
        try {
           page =  webClient.getPage("https://c.y.qq.com/rsc/fcgi-bin/fcg_user_created_diss?hostuin=347606601&sin=0&size=32&r=1512031330762&g_tk=345719309&jsonpCallback=MusicJsonCallback3561589509370917&loginUin=347606601&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8¬ice=0&platform=yqq&needNewCode=0");
        } catch (Exception ex) {
            LOGGER.error(JSONObject.toJSONString(ex));
        } finally {
            webClient.close();
        }
        // 异步执行JS 需要时间  线程阻塞 30s  等待异步JS执行完成
        webClient.waitForBackgroundJavaScript(30000);

        String pageXml = page.asXml();

        System.out.println(pageXml);
    }




    @Test
    public void httpUtilsTest () {

        HttpUtils httpUtils = HttpUtils.getInstance();
        String result = "";
        try {
            result = httpUtils.getHtmlPageResponse("http://www.xhlcode.cn");
        } catch (Exception ex) {
            LOGGER.error(JSONObject.toJSONString(ex));
        }
        System.out.println(result);
    }


    /**
     * 模拟请求头
     */
    @Test
    public void htmlunitRequest() {
        try {
            String referer = "https://y.qq.com/portal/profile.html";

            URL url = new URL(qqMusicUrl);
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            WebRequest request = new WebRequest(url);
            request.setAdditionalHeader("Referer",referer);
            HtmlPage page = webClient.getPage(request);
            String pageXml = page.asXml();
            System.out.println(pageXml);
        } catch (Exception ex) {
            LOGGER.info(JSONObject.toJSONString(ex));
        }
    }
}
