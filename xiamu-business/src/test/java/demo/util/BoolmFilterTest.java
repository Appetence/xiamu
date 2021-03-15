package demo.util;

import demo.base.BaseTest;
import org.junit.Test;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-03-01 15:18
 */
public class BoolmFilterTest extends BaseTest {

    @Test
    public void boolmFilter(){
        System.out.println(demo.util.BloomFilterUtil.isExist("a"));
        System.out.println(demo.util.BloomFilterUtil.isExist("a"));
    }
}
