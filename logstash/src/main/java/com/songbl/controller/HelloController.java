package com.songbl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/app")
public class HelloController {

    @RequestMapping("getHello")
    public String getHello(){
        log.info("|8768|长期用药|"+stampToTime(System.currentTimeMillis()+""));
        log.info("|8768|普通浏览|"+stampToTime(System.currentTimeMillis()+1000+""));
        log.info("|8768|档案查询|"+stampToTime(System.currentTimeMillis()+2000+""));
        log.info("|8768|血费|"+stampToTime(System.currentTimeMillis()+3000+""));
        log.info("|8768|近期用药|"+stampToTime(System.currentTimeMillis()+3000+""));
        return "hello";
    }


    public  String stampToTime(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        //将时间戳转换为时间
        Date date = new Date(lt);
        //将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }
}
