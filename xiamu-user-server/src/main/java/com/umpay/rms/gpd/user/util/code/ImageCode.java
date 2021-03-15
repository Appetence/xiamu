package com.umpay.rms.gpd.user.util.code;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-08-06 11:38
 */
public class ImageCode {
    private String jId;
    private byte[] data;
    private String key;
    private StringBuffer code;

    public StringBuffer getCode() {
        return code;
    }

    public void setCode(StringBuffer code) {
        this.code = code;
    }

    public String getjId() {
        return jId;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
