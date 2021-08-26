package com.songbl;

import java.util.concurrent.ThreadPoolExecutor;

public class Stack {
    private Person person ;


    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }) ;

        int a = 3 ;
        int b = 3 ;
        a=4 ;
        System.out.println(b);
        System.out.println(a);

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1);
    }


    void test1(){
        person = new Person() ;
        person.setName("zhangsan");
        person.setAge(11);
        System.out.println(person.toString()+"test1");
    }


    void test2(){
        person = new Person() ;
        person.setName("李四");
        person.setAge(11);
        System.out.println(person.toString()+"test2");
    }



}
