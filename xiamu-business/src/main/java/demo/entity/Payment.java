package demo.entity;

import lombok.ToString;

import java.io.Serializable;

/**
 * (Payment)实体类
 *
 * @author makejava
 * @since 2020-05-26 13:25:09
 */
@ToString
public class Payment implements Serializable {
    private static final long serialVersionUID = 820043067964485034L;
    
    private Integer id;
    
    private String serial;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}