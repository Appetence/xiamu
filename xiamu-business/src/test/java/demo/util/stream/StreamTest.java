package demo.util.stream;

import demo.base.BaseTest;
import demo.entity.Payment;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: vue2x
 * @description:
 * @author: : xiamu
 * @create: 2021-03-02 11:35
 */
public class StreamTest extends BaseTest {

    @Test
    public void test() {
        LinkedList<Payment> build = build();
        /*
        anyMatch：判断的条件里，任意一个元素成功，返回true
        allMatch：判断条件里的元素，所有的都是，返回true
        noneMatch：与allMatch相反，判断条件里的元素，所有的都不是，返回true
        count方法，跟List接口中的 .size() 一样，返回的都是这个集合流的元素的长度，不同的是，流是集合的一个高级工厂，中间操作是工厂里的每一道工序，我们对这个流操作完成后，可以进行元素的数量的和；
        */
        //任意元素成功，则成功
        boolean anyMatch = build.stream().anyMatch(p -> {
            return filter(p);
        });
        boolean anyMatch2 = build.stream().anyMatch(p -> filter(p));
        System.out.println(anyMatch);
        System.out.println(anyMatch2);
        long count = build.stream().filter(p -> {
            return filter(p);
        }).count();
        System.out.println(count);
        List<Payment> collect = build.stream().filter(p -> {
            return filter(p);
        }).collect(Collectors.toList());
        List<Payment> collect2 = build.stream().filter(p -> filter(p)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect2);

    }


    public static LinkedList<Payment> build() {

        LinkedList<Payment> linkedList = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            Payment payment = new Payment();
            payment.setId(i);
            if (i % 2 == 1) {
                payment.setSerial("偶数");
            } else {
                payment.setSerial("基数");
            }
            linkedList.add(payment);
        }
        return linkedList;
    }

    private boolean filter(Payment p) {
        if (p.getSerial().equals("偶数")) {
            return true;
        } else {
            return false;
        }
    }

}
