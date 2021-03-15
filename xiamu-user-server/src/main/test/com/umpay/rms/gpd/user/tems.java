package com.umpay.rms.gpd.user;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-11-02 16:21
 */
public class tems {
/*    public static void main(String[] args) {
        String content = "/all/in/one/u";
     String [] result = content.split("/");
        System.out.println(JSONUtil.toJsonStr(result));


        String value = "error=\"invalid_request\", error_description=\"短信验证码不正确\"";
        if(value != null && value. contains("error_description=")){
          String res = value.split("error_description=")[1];
            System.out.println(res);
        }else {
            System.out.println(">>>>>");
        }

        String decryptDataConfrim = "1995lh123";
        Boolean password = RandomUtil.checkPassword(decryptDataConfrim);
        System.out.println(password);
    }*/
/*
    public static void main(String[] args) {
        String newPassword = "123!@#qwe";
        String password = "$2a$10$H8LYBoZPRtwAiibx80mZV.p.7yl356Kh/T2N0YpPpPb34rE9hgQgS";
        Boolean result = BCrypt.checkpw(newPassword,password);
        System.out.println("result :"+result);
         newPassword = "123.!@#";



         result = RandomUtil.checkPassword(newPassword);
        System.out.println("result:"+result);

        Boolean rule = MobileUtil.checkMobile("13401091712");
        System.out.println("result : "+rule);
    }*/
    public static void main(String[] args) {
        String newPassword = "123";
        String password = "$2a$10$ekEgphEab83FW7teuU/4UuQ7j0me8B7aX97yIXK0gXC2dRzrPKBdm";
        Boolean result = BCrypt.checkpw(newPassword,password);
        System.out.println("result:"+result);

    }

}
