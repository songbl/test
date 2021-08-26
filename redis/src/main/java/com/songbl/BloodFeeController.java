package com.songbl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BloodFeeController {

    @Autowired
    private RedisUtils redisUtils;

    /**传递
     * 血费列表
     */
    @PostMapping("/setData")
    public String textData(@RequestParam("key") String key,
                                  @RequestParam("value") String value) {

        redisUtils.hPut(key,key,value,-1);
        return (String) redisUtils.hGet(key,key);
    }


}
