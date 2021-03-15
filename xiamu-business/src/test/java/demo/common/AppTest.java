package demo.common;


import demo.base.BaseTest;
import demo.service.LuaScriptService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for simple App.
 */
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
*/
public class AppTest extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(AppTest.class);
    @Resource
    LuaScriptService luaScriptService;

    @Resource
    RedisTemplate<String, List> redisTemplate;
    @Resource
    RedisTemplate<String, String> redisTemplateString;
    @Test
    public void test() {
        logger.info(""+redisTemplate.opsForList().leftPush("listKey", build()));
    }

    LinkedList<User> build() {
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("name", String.valueOf(i)));
        }
        return users;
    }

    @Test
    public void get(){
        logger.info("key:"+redisTemplate.opsForList().size("key"));
        logger.info("listKey:"+redisTemplate.opsForList().size("listKey"));
        List<List> key = redisTemplate.opsForList().range("key", 0, -1);
        key.forEach(k->{
            logger.info("result :"+k);
        });

    }
    @Test
    public void test1(){
        redisTemplateString.opsForValue().set("key", "value");
        logger.info("result : "+redisTemplateString.opsForValue().get("key"));
    }

    @Test
    public void lua(){
        luaScriptService.redisAddScriptExec();
    }

}

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class User implements Serializable {
    private String name;
    private String age;
}
