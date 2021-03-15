package demo.service.impl;

import demo.service.LuaScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-02-06 11:36
 */

@Service("luaScriptService")
public class LuaScriptServiceImpl implements LuaScriptService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private DefaultRedisScript<List> getRedisScript;

    @PostConstruct
    public void init(){
        getRedisScript = new DefaultRedisScript<List>();
        getRedisScript.setResultType(List.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/pre_decr.lua")));
    }

    @Override
    public void redisAddScriptExec(){
        /**
         * List设置lua的KEYS
         */
        List<String> keyList = new ArrayList();
        keyList.add("count");
        keyList.add("rate.limiting");

        /**
         * 用Mpa设置Lua的ARGV[1]
         */
        Map<String,Object> argvMap = new HashMap<String,Object>();
        argvMap.put("expire",10000);
        argvMap.put("times",10);

        /**
         * 调用脚本并执行
         */
        List result = redisTemplate.execute(getRedisScript,keyList, argvMap);
        logger.info("lua 脚本执行结果：{}",result);

    }
}