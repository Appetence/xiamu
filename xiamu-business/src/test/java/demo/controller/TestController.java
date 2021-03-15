package demo.controller;

import com.alibaba.fastjson.JSONObject;
import demo.base.BaseTest;
import demo.request.TableDataRequestAdd;
import org.junit.Test;

/**
 * @program: vue2x
 * @description:
 * @author: : xiamu
 * @create: 2021-03-01 18:48
 */
public class TestController extends BaseTest {
    @Test
    public void testAdd(){

        System.out.println(build());
    }

    private String build() {
        TableDataRequestAdd tableDataRequestAdd = new TableDataRequestAdd();
        tableDataRequestAdd.setAddress("123x");
        tableDataRequestAdd.setAge("11");
        tableDataRequestAdd.setName("测试");
        return JSONObject.toJSONString(tableDataRequestAdd);
    }
}
