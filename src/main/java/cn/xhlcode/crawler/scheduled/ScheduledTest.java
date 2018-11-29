package cn.xhlcode.crawler.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTest {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Scheduled(fixedDelay = 5000)
    public void print() {
        System.out.println("当前时间： "+simpleDateFormat.format(new Date()));
    }

}
