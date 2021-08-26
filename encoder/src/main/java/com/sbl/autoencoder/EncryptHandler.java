package com.sbl.autoencoder;

import com.sbl.autoencoder.anno.EncryptField;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.sbl.autoencoder.enums.EncryptConstant.DECRYPT;
import static com.sbl.autoencoder.enums.EncryptConstant.ENCRYPT;

@Slf4j
@Aspect
@Component
public class EncryptHandler {

    @Autowired
    private StringEncryptor stringEncryptor;

    //该包下面的所有类的所有方法
//    @Pointcut("execution(* com.sbl.redis..*(..))")
//    public void pointCut() {
//    }


    //匹配指定注解的方法
    @Pointcut("@annotation(com.sbl.autoencoder.anno.EncryptMethod)")
    public void pointCutAnno() {
    }


    @Pointcut("execution(* com.sbl.redis.TestController.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        /**
         * 加密
         */
        Object encrypt = encrypt(joinPoint);
//        System.out.println("加密结果 "+encrypt);
        /**
         * 解密
         */
//        Object decrypt = decrypt(joinPoint);
        return encrypt;
    }


    // 加密入参这是
    public Object encrypt(ProceedingJoinPoint joinPoint) {

        Object obj = null;
        try {
            Object[] objects = joinPoint.getArgs();
            if (objects.length != 0) {
                for (Object o : objects) {
                    if (o instanceof String) {
                        obj = encryptValue(o);
                    } else {
                        obj = handler(o, ENCRYPT);
                    }
                    //TODO 其余类型自己看实际情况加
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  obj ;
    }

    //执行 切切入点的方法
    public Object decrypt(ProceedingJoinPoint joinPoint) {
//        Object obj = null ;
//        try {
//             obj = joinPoint.proceed();
//            System.out.println("hhhhhhhhhhh");
//            System.out.println("hhhhhhhhhhh");
//            System.out.println("hhhhhhhhhhh");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
//        //这个是目标方法的返回值
//        return  obj ;
        Object result = null;
        try {
            Object obj = joinPoint.proceed();
            System.out.println("类型...."+obj.getClass());
            if (obj != null) {
                if (obj instanceof String) {
                    decryptValue(obj);
                } else {
                    result = handler(obj, DECRYPT);
                }
                //TODO 其余类型自己看实际情况加
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    private Object handler(Object obj, String type) throws IllegalAccessException {

        if (Objects.isNull(obj)) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String realValue = (String) field.get(obj);
                String value;
                if (DECRYPT.equals(type)) {
                    System.out.println("解密参数  "+realValue   +"    开始解密");
                    value = stringEncryptor.decrypt(realValue);
                } else {
                    value = stringEncryptor.encrypt(realValue);
                }
                field.set(obj, value);
            }
        }
        return obj;
    }

    public String encryptValue(Object realValue) {
        String value = null;
        try {
            value = stringEncryptor.encrypt(String.valueOf(realValue));
            System.out.println("加密参数  "+realValue   +"    加密结果"+value);
        } catch (Exception ex) {
            return value;
        }
        return value;
    }

    public String decryptValue(Object realValue) {
        String value = String.valueOf(realValue);
        try {
            System.out.println("解密参数  "+realValue   +"    开始解密");
            value = stringEncryptor.decrypt(value);
//            System.out.println("解密参数  "+realValue   +"    界面结果"+value);
            System.out.println("    界面结果"+value);
        } catch (Exception ex) {
            return value;
        }
        return value;
    }
}