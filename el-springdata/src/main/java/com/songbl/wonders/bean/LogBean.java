package com.songbl.wonders.bean;

import com.songbl.wonders.Common.Constants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *  埋点的业务bean
 */

@Data
@Document(indexName = Constants.INDEX_NAME_LOGBEAN,shards = 1,replicas = 0)
public class LogBean {

    @Id
    private String userId ;

    // 证件类型
    @Field(type = FieldType.Keyword)
    private String cardType ;

    // 证件号码
    @Field(type = FieldType.Keyword)
    private String cardNum ;


    //服务的名字
    @Field(type = FieldType.Keyword)
    private String  appName;


    //业务名称
    @Field(type = FieldType.Keyword)
    private String  businessName;

    // 记录产生的时间
    @Field(type = FieldType.Keyword)
    private String createTime ;


    // 业务持续时间
    @Field(type = FieldType.Keyword)
    private String businessLastTime ;









}
