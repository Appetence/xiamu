package com.umpay.rms.gpd.user.util;

import java.util.Random;

/**
 * @program: rms-gpd
 * @description: 随机字符生成
 * @author: xiamu
 * @create: 2020-07-15 17:53
 */
public class RandomUtil {

    public static String createRandomString() {
        Random random = new Random();
        String newPassword = "";
        while (true) {
            StringBuffer sb = new StringBuffer();
            //获取长度为6的字符串
            for (int i = 0; i < 8; i++) {
                //获取范围在3之内的索引值
                int number = random.nextInt(3);
                int result = 0;
                switch (number) {
                    case 0:
                        //Math.random()*25+65成成65-90的int型的整型,强转小数只取整数部分
                        result = (int) (Math.random() * 25 + 65);  //对应A-Z 参考ASCII编码表
                        //将整型强转为char类型
                        sb.append((char) result);
                        break;
                    case 1:
                        result = (int) (Math.random() * 25 + 97);   //对应a-z
                        sb.append((char) result);
                        break;
                    case 2:
                        sb.append(String.valueOf(new Random().nextInt(10)));
                        break;
                }
            }
            newPassword = sb.toString();
            if (newPassword.matches("^.*[a-zA-Z]+.*$") && newPassword.matches("^.*[0-9]+.*$")) {
                break;
            }
        }
        return newPassword;
    }

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    /**
     * 指定长度的数字
     *
     * @param length
     * @return
     */
    public final static String createCode(int length) {
        return String.valueOf(((int) Math.random() * 9 + 1) * Math.pow(10, 5));
    }

    /*  public static void main(String[] args) {
          System.out.println(Math.random());
          System.out.println(Math.random()*9);
          System.out.println(Math.random()*9+1);
          System.out.println((int)(Math.random()*9+1));
          System.out.println(Math.pow(10,5));
          System.out.println((Math.random()*9+1)*Math.pow(10,5));
          System.out.println((int)((Math.random()*9+1)*Math.pow(10,5)));

      }*/
    public final static Boolean checkPassword(String newPassword) {
        if (newPassword.length() >= 6 && newPassword.length() <= 20) {
            if (newPassword.matches("^.*[a-zA-Z]+.*$") && newPassword.matches("^.*[0-9]+.*$")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
