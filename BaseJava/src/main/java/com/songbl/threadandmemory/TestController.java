package com.songbl.threadandmemory;


import com.songbl.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//********************************情况1*****************
// 1. 先执行线程1 创建对象；然后这个线程睡眠了，对象创建成功之后
// 2. 另外开一个线程2，打印对象，打印的结果是空（说明，这个线程将主存储的的person 打印出来，由于没赋值，所以结果是null ，但是有hash，内存开辟成功了）
// 3. 线程1睡醒了，执行赋值操作，再打印，就有值了
// 4. 线程3 再打印值，也有值，就是线程1 赋值的结果（是将主存堆中的数据拷贝到自己的线程中，执行完，再返回出去）（用到的时候，将主存中的对象拷贝到线程去，用完再写回去；
//     错乱的原因哈:当前的线程停止了，另外一个线程用到这个对象，框框的一顿干，干完写回去，这个线程又干，干完写回去，哪这个对象就有问题了，覆盖掉了，同一个引用）

//再创建，重新指向，原来的就废了，引用指向了新的地址



@RestController
@Slf4j
public class TestController {

    private Person person ;


    @PostMapping("/setData")
    public Object textData(@RequestParam("key") String key) {

        if ("1".equals(key)){
            test1();

            System.out.println("睡醒了  ");
        }else {
            test3();
        }

        System.out.println(person.toString()+"最后结果... hash code "+ person.hashCode());
        return person ;

    }

    @PostMapping("/setData1")
    public Object textData2(@RequestParam("key") String key) {

        test4() ;
        System.out.println(person.toString()+"setData1 ... hash code "+ person.hashCode());
        return person ;

    }

    @PostMapping("/setData3")
    public Object textData3(@RequestParam("key") String key) {

        test2() ;
        System.out.println(person.toString()+"打印当前对象 ... hash code "+ person.hashCode());
        return person ;

    }


    void test1(){
        person = new Person() ;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        person.setName("zhangsan");
        person.setAge(11);
        System.out.println(person.toString()+"test1"+"   "+person.hashCode());
    }


    void test2(){
        System.out.println(person.toString()+"test2"+person.hashCode());
    }
    void test3(){
        person = new Person() ;
        System.out.println(person.toString()+"test2"+person.hashCode());
    }
    void test4(){
        person = new Person() ;
        person.setName("李四");
        person.setAge(11);
        System.out.println(person.toString()+"test3"+person.hashCode());
    }



}
