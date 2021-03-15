package com.umpay.rms.gpd.internal.dto;


import java.io.Serializable;
import java.util.List;


/**  
* @ClassName: Msg  
* @Description: 统一前端以后台交互的方式  返回信息统一用这个
* @author liuhao
* @date 2019年12月27日
*    
*/  
public class Msg implements Serializable {
    private static final long serialVersionUID = -1L;

    private int code;//小于0是操作失败，等于大于0是操作成功
    private String message;//提示语
    private Object data;//操作结果

    public Msg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Msg() {
    }

    /**
     * 返回结果  该结果适用于所有类型
     * @param code 状态码
     * @param msg 返回描述
     * @param data 返回数据类型
     * @return
     * com.umpay.rms.gpd.fnp.util.Msg
     * @throws
     */
    public static Msg returnMsg(Integer code, String msg, Object data) {
       Msg msgs = new Msg();
        msgs.setCode(code);
        msgs.setMessage(msg);
        msgs.setData(data);
        return msgs;
    }
    /**
     * 请求成功返回结果
     * @param msg 返回描述信息
     * @param data 返回数据
     * @return
     * @throws
     */
    public static Msg returnSuccessMsg( String msg, Object data) {
        Msg msgs = new Msg();
        msgs.setCode(200);
        msgs.setMessage(msg);
        msgs.setData(data);
        return msgs;
    }

    public static Msg returnSuccessMsg(String msg) {
        Msg result = new Msg();;
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }

    /**
     * 请求失败返回结果
     * @param msg 返回描述信息
     * @param data 返回数据
     * @return
     * @throws
     */
    public static Msg returnErrorMsg(String msg, Object data) {
        Msg result = new Msg();;
        result.setCode(500);
        result.setMessage(msg);
        result.setData(data);

        return result;
    }
    public static Msg returnErrorMsg(String msg) {
        Msg result = new Msg();;
        result.setCode(500);
        result.setMessage(msg);
        return result;
    }
    public static Msg returnErrorMsg() {
        Msg result = new Msg();;
        result.setCode(500);
        result.setMessage("处理异常");
        return result;
    }
    /**
     * @Title: returnErrorMsg
     * @param msgList 报错内容
     * @return
     * @throws
     */
    public static Msg returnErrorMsg(List<String> msgList) {
        Msg result = new Msg();;
        result.setCode(500);
        result.setData(msgList);
        return result;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data=data;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
    public boolean isFail() {
        return this.code != 200;
    }
}
