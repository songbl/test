package com.sbl.autoencoder.bean;

import com.sbl.autoencoder.anno.EncryptField;
import lombok.Data;

@Data
public class UserBean {

    private String id ;

    @EncryptField
    private String phoneNum ;

//    @EncryptField
    private String name ;
}
