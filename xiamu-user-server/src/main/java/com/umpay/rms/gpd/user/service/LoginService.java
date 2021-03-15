package com.umpay.rms.gpd.user.service;

import com.umpay.rms.gpd.internal.dto.Msg;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-06-05 11:13
 */
public interface LoginService {
    Msg checkCode(String jsonId, String code);

    Msg checkSmsCode(String username, String code);

    Msg getPrivateKey(String jsonId);

    Boolean checkSmsSendFlag(String pre, int count, String usesrname,String type);

    Msg checkSmsCodeForgot(String username, String code);

    Msg getPasswordByPrivatekey(String JsionId, String password,String confrimPassword);

    Msg decryptionPassword(HttpServletRequest request, String password);

    Msg checkSmsCodeRegister(String mobile, String smsCode);

    void setKey();
}
