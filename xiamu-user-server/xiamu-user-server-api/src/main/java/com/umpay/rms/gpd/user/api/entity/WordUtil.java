package com.umpay.rms.gpd.user.api.entity;

import java.io.Serializable;

/**
 * @program: rms-gpd
 * @description: 短信模版文字内容
 * @author: xiamu
 * @create: 2020-06-09 15:52
 */
public class WordUtil implements Serializable {

    private String order;
    private String content;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
