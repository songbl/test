package com.sbl.redis;

import com.alibaba.fastjson.JSON;
import com.sbl.autoencoder.anno.EncryptField;
import com.sbl.autoencoder.anno.EncryptMethod;
import com.sbl.autoencoder.bean.UserBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisUtils redisUtils;

    /**传递
     * 血费列表
     */
    @PostMapping("/setData")
    @EncryptMethod
    public Object textData(@RequestParam("key") String key,
                           @EncryptField @RequestParam("value") String value) {

        UserBean userBean = new UserBean() ;
        userBean.setPhoneNum(value);
        userBean.setName("hhhhh");

        return userBean ;
    }






}
