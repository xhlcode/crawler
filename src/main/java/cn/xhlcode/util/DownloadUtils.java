package cn.xhlcode.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DownloadUtils {



    public static void main(String[] args) {
        String str= "({{[{()}]}})";
        String[] strs = str.split("");
        Deque<String> queue = new LinkedList<String>();
        for(int i = 0;i<= strs.length;i++) {
            if (queue.size() == 0) {
                queue.push(strs[i]);
            } else {
                if(queue.peek().equals(strs[i])) {
                    queue.pop();
            } else {
                    queue.push(strs[i]);
                }
            }
        }

        if (queue.size() == 0) {
            System.out.println("不匹配");
        }

    }



}
