package cn.xhlcode.crawler.util;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 超时时间
     */
    private int timeout = 20000;

    /**
     * 等待异步JS 执行时间
     */
    private int waitForBackgroundJavaScript = 30000;



    private static HttpUtils httpUtils;

    private HttpUtils(){}

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }
    public String getHtmlPageResponse(String url) throws Exception {
        final WebClient webClient= new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        webClient.getOptions().setTimeout(timeout);
        webClient.setJavaScriptTimeout(timeout);

        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
        } catch (Exception ex) {
            LOGGER.error("访问异常", JSONObject.toJSONString(ex));
            throw  ex;
        } finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);

        return page.asXml();
    }

    private Document parseHtmlToDoc(String html) throws Exception {
        return removeHtmlSpace(html);
    }

    /**
     * 移除文档中 空格
     * @param html
     * @return
     */
    private Document removeHtmlSpace(String html) {
        Document doc = Jsoup.parse(html);
        String result = doc.html().replace("&nbsp;","");
        return Jsoup.parse(result);
    }


    /**
     * 获取页面文档Document 对象（等待JS异步执行）
     * @param url
     * @return
     * @throws Exception
     */
    public Document getHtmlResponseAsDocument(String url) throws Exception {
        return parseHtmlToDoc(getHtmlPageResponse(url));
    }




    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getWaitForBackgroundJavaScript() {
        return waitForBackgroundJavaScript;
    }

    public void setWaitForBackgroundJavaScript(int waitForBackgroundJavaScript) {
        this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
    }
}
