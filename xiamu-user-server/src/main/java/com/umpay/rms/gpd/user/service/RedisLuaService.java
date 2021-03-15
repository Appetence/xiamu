package com.umpay.rms.gpd.user.service;


import cn.hutool.core.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-10-26 23:19
 */
public class RedisLuaService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger( RedisLuaService.class );

    private final Map<String/* resName */, String/* resContent */> map = new HashMap<>( );

    public static final RedisLuaService getInstance( ) {
        return InstanceHolder.instance;
    }

    private RedisLuaService( ) {}


    public synchronized String regist( String luaResName ) {
        try {
            String content = map.get( luaResName );
            if( content != null ) {
                return content;
            }
            content =
                    StreamUtils.copyToString( this.getClass( ).getClassLoader( ).getResourceAsStream( luaResName ), Charset.forName( CharsetUtil.UTF_8 ) );
            map.put( luaResName, content );
            return content;
        } catch( IOException e ) {
            logger.error( "regist lua {} exception, error = {}", luaResName, e.getMessage( ) );
            return null;
        }
    }

    private static final class InstanceHolder {
        public static final RedisLuaService instance = new RedisLuaService( );
    }
}
