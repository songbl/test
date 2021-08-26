package com.songbl;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ESTest_Client {
    public static void main(String[] args) throws Exception {

        // 创建ES客户端
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.31.134", 9200, "http"))
//        );
//
//        // 关闭ES客户端
//        esClient.close();

//        int i = IdNOToAge("");
//        System.out.println(i);
        int i = lastToExpire("2021-06-21 17:30:47");
        System.out.println(i);

    }


    public static int lastToExpire(String s) {
        //设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.getTime());
        long time = date.getTime();
        long currentTime = System.currentTimeMillis();

        int lastToExpire = (int) ((time - currentTime) / 1000);

        return lastToExpire;
    }

}
