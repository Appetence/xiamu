/*
package com.umpay.rms.gpd.user;

import cn.hutool.json.JSONUtil;
import com.umpay.rms.gpd.user.api.pojo.SmsSendInfo;
import com.umpay.rms.gpd.user.api.request.UserRegisterRequest;
import com.umpay.rms.gpd.user.constant.AccountConstants;
import com.umpay.rms.gpd.user.service.DbUserService;
import com.umpay.rms.gpd.user.service.PrepayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.websocket.SendResult;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

*/
/**
 * @program: rms-gpd
 * @description:
 * @author: xiamu
 * @create: 2020-09-07 11:48
 *//*

@Slf4j
@SpringBootTest
public class SpringBootApplicationTest {



    @Autowired
    PrepayService prepayService;
    @Autowired
    ProductServiceProxy productServiceProxy;

    @Test
    public void redistemplate() {
        ExecutorService executorService = Executors.newCachedThreadPool();//缓存线程池，有仍无进来，new，有空的
        System.out.println(executorService);

        long result = prepayService.charge("100", 10);
        System.out.println("  value  " + result);
        System.out.println(executorService.isTerminated());
        executorService.shutdown();
    }


    @Test
    public void rocketmq() {
        SmsSendInfo smsSendInfo = new SmsSendInfo();
        smsSendInfo.setAccountId(AccountConstants.getAccountId());
        smsSendInfo.setCode("123");
        smsSendInfo.setMobile("1834260040");
        smsSendInfo.setReqId(String.valueOf(System.currentTimeMillis()));
        // 异步发送短信
        SendResult sendResult = productServiceProxy.sendMessage(smsSendInfo);
        System.out.println("json result :" + JSONUtil.toJsonStr(sendResult));
    }

    @Autowired
    DbUserService dbUserService;

    @Test
    public void register() {
        UserRegisterRequest userInfoRequest = new UserRegisterRequest();
        userInfoRequest.setPassword("123412ll");
        userInfoRequest.setMobile("15987568874");
        dbUserService.onlineRegister(userInfoRequest);
    }
    @Autowired
    private TokenStore tokenStore;

    */
/**
     * 检查token
     *//*

    @Test
    public void checkToken(){
        String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2RlIjoyMDAsImxvZ2luX2tleSI6IjEwMDAwMTI2IiwidXNlcl9uYW1lIjoiMTMzMzUyNDc5OTAiLCJzaGFyZV9hY2NvdW50IjoieDZyR3JxUXQ2azdjQTVrYTZ6SyIsImF1dGhvcml0aWVzIjpbImNyZWF0ZU9yZGVyIiwidjEvc2VsZWN0VXNlckNoZWNrIiwiaW5zZXJ0VXNlckluZm8iLCJzbXNEYXlFY2hhcnRzIiwidW9kYXRlQWNjb3VudERlbGVnYXRlIiwidXBkYXRlVGVtcGxldCIsImNsaWVudFRlbXBsZXRMaXN0IiwiY291bnRBY2NvdW50VG9kYXlJbmZvQnlVc2VyTmFtZSIsInNlbGVjdFVzZXJJbmZvQnlVc2VyTmFtZSIsInVwZGF0ZUFjY291bnRDb2RlTnVtYmVyIiwidXBkYXRlTXNnQnlJZCIsImRlbGV0ZU1zZ0J5SWQiLCJzbXNEYXlUYWJsZSIsInNlbGVjdEFjY291bnRTZW5kSW5mb0J5VXNlck5hbWUiLCJnZXRWZWRpb0JhbGFuY2UiLCJzZWxlY3RVc2VyQ2hlY2siLCJkZWxldGVUZW1wbGV0Iiwic21zTW9udGhFY2hhcnRzIiwic2VsZWN0U3VuQWNjb3VudFNlbmRJbmZvIiwiY3JlYXRlU21zVGFzayIsImdldEFjY291bnRJbmZvIiwic21zVXBkYXRlU2lnbiIsInNlbEF1ZGl0VGVtcGxldExpc3QiLCJ1cGRhdGVBY2NvdW50U3RhdHVzIiwic21zTW9udGhUYWJsZSIsIk1vYmFuRG93biIsInRlbXBsYXRlIiwiY2xpZW50U2lnbkluZm9MaXN0IiwiZ2V0VXNlckxvZyIsInYxL3NlbGVjdGVUZW1wbGF0ZXNBZG1pbiIsInVwZGF0ZUFjY291bnRCYWxhbmNlIiwiaW5zZXJ0VGVtcGxhdGUiLCJjYW5jZWxUb1NlbmQiLCJjb21wYW55SW5kZXgiLCJmb3Jnb3RQYXNzd29yZCIsInNlbGVjdFNlbmRNc2dJbmZvIiwic2VsZWN0RFBTdW5BY2NvdW50UmVjb3JkIiwidXBkYXRldFVzZXJJbmZvIiwiZ2V0VXNlckluZm9CeVVzZXJuYW1lIiwidXBkYXRlQWNjb3VudEluZm8iLCJzZWxlY3RTZW5kTWFsbEJ5TWFuYWdlciIsInNlbGVjdFNlbmRNc2dJbmZvQnlVc2VyTmFtZSIsInNlbGVjdFN1bkFjY291bnRTZW5kSW5mb3MiLCJzZWxlY3RlVGVtcGxhdGVzIiwib25saW5lUmVnaXN0ZXIiLCJzbXNUZW1wbGV0QWRkIiwic21zQWRkU2lnbiIsInNlbGVjdGVUZW1wbGF0ZXNCeUxvd2VyTGV2ZWwiLCJTZW5kVGVtcGxhdGVzR2F0ZXdheSIsImNvdW50QWNjb3VudFRvZGF5SW5mbyIsInRpbWVUb1NlbmQiLCJzbXNEZWxldGVTaWduIiwidXBkYXRlVGVtcGxhdGUiLCJ2MS9zZWxlY3RVc2VyTWFuYWdlciIsInNlbGVjdFN1bkFjY291bnRSZWNvcmQiLCJnZXRQcm9kdWN0SW5mb3MiLCJzaWduSW5mbyIsImNoZWNrVGVtcGxhdGUiLCJkZWxldGVBY2NvdW50QmxhY2tsaXN0QnlJZCIsInNlbGVjdEFjY291bnRSZWNoYXJnZUluZm8iLCJjbGllbnRPdXRib3hEZXRhaWwiLCJzZWxlY3RVc2VyTWFuYWdlciIsInNlbFRlbXBsZXRCeUlkIiwic2VsZWN0ZVRlbXBsYXRlc0FkbWluIiwidXBkYXRlVGVtcGxhdGVTdGF0dXMiLCJhbGkiLCJnZXRBY2NvdW50QmFsYW5jZUluZm8iLCJzYXZlQ2FpeGluIiwiYXVkaXRTdWNjU2lnbkxpc3QiLCJnb1RvU2VuZCIsInNlbGVjdERlbGF5VGFzayIsImdldFNtc0JhbGFuY2UiLCJ1cGRhdGVGaWxlIiwiZXhjZWxCYXRjaEltcG9ydCIsImdldFVzZXJJbmZvcyIsInNlbGVjdFNlbmRNc2dJbmZvQnlNbXNJbmZvIiwiaW5zZXJ0QmxhY2tMaXN0Iiwic2VsZWN0ZVRlbXBsYXRlIiwic2VsZWN0U2VuZE1hbGwiLCJzZWxlY3RBY2NvdW50SW5mbyIsInBhcnNpbmdNb2JsaWUiLCJnZXRUZW1wbGV0SW5mbyIsIm1hbnVhbEFkZFBob25lIiwic2VsZWN0U3VuQWNjb3VudCIsImdldEFjY291bnRJbmZvQnlBY2NvdW50SWRzIiwiaW1wb3J0Tm9ybWFsQnlTdHJpbmcuYWpheCIsInNhdmVDb250ZW50IiwidXBkYXRldFVzZXJQYXNzd29yZCIsInNlbGVjdFN1bkFjY291bnRJbmZvIiwiZG93blNpZ25GaWxlIiwid3giLCJpbXBvcnROb3JtYWwiLCJzZWxlY3RBY2NvdW50QmxhY2tsaXN0Iiwic2F2ZUZpbGUiLCJ1cGRhdGVUZW1wbGF0ZUNoZWNrIiwidXBkYXRlQWNjb3VudFVzZXJTdGF0dXMiLCJjbGllbnRPdXRib3hMaXN0IiwidXBkYXRlQ29udGVudCIsInVwZGF0ZXRVc2VyQ29udGFjdEluZm8iXSwiY2xpZW50X2lkIjoiYWRtaW4iLCJhdWQiOlsicmVzMSJdLCJzY29wZSI6WyJhbGxzIl0sInNoYXJlX3JvbGUiOiIzOCIsImNvbXBhbnkiOiLogZTlsI_liqjnp5HmioDmnInpmZDlhazlj7giLCJzaGFyZV9zYWx0IjoiODUiLCJleHAiOjE2MDUxNTgzMjUsImp0aSI6IjFlNjU2NmNiLWRhMmMtNDU3Mi1iZTZkLWYxZWQ2NWRhMGM3MyIsInN0YXR1cyI6IjEifQ.be_3vsIrtRnvAMvUyw-AXO0zUWU113873vWVnmyi0ERs60nRr2a5H_dyp0MQL7LFJXsq4baWP7muiZpJjDsUcHACw54bF2auLK5JkEaaJZ6Yn870AP9IyQ929ghylI8hIxpkm_HU4BGZsTpYur8unIty00bFA6ZjjmUHMb81d9E";
        Map<String, Object> map = getExtraInfo(tokenStore.readAuthentication(accessToken));
        String username = tokenStore.readAuthentication(accessToken).getPrincipal().toString();
        String userId = (String) map.get("share_salt");
        String shareRole = (String) map.get("share_role");
        String shareAccount = (String) map.get("share_account");
        String company = (String) map.get("company");
        log.info("token info : {}", JSONUtil.toJsonStr(map));



    }


    @Test
    public void checkSalt(){
    //nimDA1016
        String password = "nimDA1016";
        String stal = "$2a$10$ekEgphEab83FW7teuU/4Uu";
        String bcrypt = BCrypt.hashpw(password, stal);//加盐加密
        System.out.println("result :"+bcrypt);
    }

    public Map<String, Object> getExtraInfo(Authentication auth) {
        Map<String, Object> map = (Map<String, Object>) auth.getDetails();
        return map;
    }

}
*/
