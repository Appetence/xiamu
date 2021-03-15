package com.umpay.rms.gpd.internal.util.moblie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-11-03 15:10
 */
public class MobileUtil {

    public static boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("^\\+?(\\(?0{0,2}(86)?\\)?)1(((\\d{2}|(44)\\d{2}|(65)\\d{2}|(0648)|(0647))\\d{8}))$");
        Matcher m = p.matcher(mobile);
        if(m.matches()){
            return true;
        }else{
            return false;
        }

    }

/*    public static void main(String[] args) {
        System.out.println(checkMobile("25834260040"));
    }*/

}
