package com.umpay.rms.gpd.user.security.exception;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-07-04 18:29
 */


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Description 定义异常MyOAuth2Exception的序列化
 * @Author wwz
 * @Date 2019/07/11
 * @Param
 * @Return
 */
public class ConsumOAuthExceptionJacksonSerializer extends StdSerializer<ConsumOAuth2Exception> {

    protected ConsumOAuthExceptionJacksonSerializer() {
        super(ConsumOAuth2Exception.class);
    }

    @Override
    public void serialize(ConsumOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        //jgen.writeObjectField("code", value.getHttpErrorCode());
        jgen.writeObjectField("code", value.getHttpErrorCode());
        jgen.writeStringField("message", value.getSummary());
        if(value.getSummary() != null && value.getSummary(). contains("error_description=")){
            jgen.writeStringField("data", value.getSummary().split("error_description=")[1].replace("\"",""));
        }else {
            jgen.writeStringField("data", value.getSummary());
        }
        jgen.writeEndObject();
    }
}