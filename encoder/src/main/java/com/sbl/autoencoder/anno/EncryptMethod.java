package com.sbl.autoencoder.anno;

import java.lang.annotation.*;

import static com.sbl.autoencoder.enums.EncryptConstant.ENCRYPT;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptMethod {

    String type() default ENCRYPT;
}