package cn.xhlcode.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SpiderUtil {
    /**
     * 获取网页信息
     *
     * @param urlStr
     * @return
     */
    public static String getHtmlContext(String urlStr) {
        //http://www.avtb002.com/7198/
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            int sec_cont = 1000;
            URLConnection urlCon = url.openConnection();
//			HttpURLConnection urlCon = (HttpURLConnection) rulConnection;
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true, 默认情况下是false;
//			urlCon.setDoInput(true);
//			urlCon.setDoOutput(true);
//			urlCon.setUseCaches(false);
//			urlCon.setInstanceFollowRedirects(true);
//			urlCon.setRequestMethod("POST");
            //设置请求编码
            urlCon.setRequestProperty("Charsert", "UTF-8");
            urlCon.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
            urlCon.setRequestProperty("Content-Encoding", "gzip");
            urlCon.setRequestProperty("Cache-Control", "max-age=0");
            urlCon.setRequestProperty("connection", "keep-alive");
            urlCon.setRequestProperty("server","nginx/1.10.1");
            urlCon.setRequestProperty("transfer-encoding","chunked");
            urlCon.setRequestProperty("vary","Accept-Encoding");
            urlCon.setRequestProperty("x-powered-by","PHP/5.6.25");
            urlCon.setRequestProperty("Host","reflow.huoshan.com");

            // 设置连接主机超时（单位：毫秒）
            urlCon.setConnectTimeout(10 * sec_cont);
            urlCon.setReadTimeout(600000);
            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            urlCon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            String newUrl = urlCon.getHeaderField("Location");
            inputStream = urlCon.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return InputStreram2String(inputStream);
    }

    public static void main(String[] args) {
        String str = getHtmlContext("http://reflow.huoshan.com/share/item/6468850705569418510/?tag=10050");
        System.out.println(str);
    }



    /**
     * InputStreram to String
     *
     * @param istem
     * @return
     */
    public static String InputStreram2String(InputStream istem) {
        StringBuffer sBuff = new StringBuffer();
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(istem, "utf-8"));
            String line = "";
            while ((line = buff.readLine()) != null) {
                sBuff.append(line);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sBuff.toString();
    }

    /**
     * 保存图片至本地
     *
     * @param imageURL
     *            图片url
     * @param loaclPath
     *            保存路径
     */
    public static void saveIamgeToLocal(String imageURL, String loaclPath,String iamgeName) {
        try {
            File file = new File(loaclPath);
            if(!file.exists()){
                file.mkdirs();
            }

            String[] strs = imageURL.split("\\.");
            //获取图片格式
            String imageFormat =  strs[strs.length-1];

            URL url = new URL(imageURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式为"GET"
            conn.setRequestMethod("GET");
            // 超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(loaclPath+ File.separator+iamgeName+"."+imageFormat);
            // 创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            // 写入数据
            outStream.write(data);
            // 关闭输出流
            outStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取二进制数据
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream)  {
        try{
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存下载地址 至 txt
     * @param path
     * @param title
     * @param url
     */
    public static void saveDownloadRecord(String path,String title,String url){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path+"下载地址.txt",true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String msg =  title + " : \r\n" + url+"\r\n\r\n";
        try {
            fos.write(msg.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
