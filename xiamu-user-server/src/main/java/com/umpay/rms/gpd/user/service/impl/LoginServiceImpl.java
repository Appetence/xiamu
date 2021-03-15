package com.umpay.rms.gpd.user.service.impl;



import com.umpay.rms.gpd.internal.constatnt.BalanceType;
import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.user.constant.CodeConstants;
import com.umpay.rms.gpd.user.service.LoginService;
import com.umpay.rms.gpd.user.service.PrepayService;
import com.umpay.rms.gpd.user.service.UserKeyService;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.CommonParamsUtil;
import com.umpay.rms.gpd.user.util.RandomUtil;
import com.umpay.rms.gpd.user.util.requset.RequsetUtil;
import com.umpay.rms.gpd.user.util.rsa.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @program: rms-gpd
 * @description: 登录业务
 * @author: xiamu
 * @create: 2020-06-05 11:13
 */
@Service

public class LoginServiceImpl implements LoginService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserKeyService userKeyService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg checkCode(String jsonId, String code) {
        logger.info("普通验证码登录");
        String codeTemplate = CommonMessageUtil.CODE_PRE + jsonId;
        try {
            String string = redisTemplate.opsForValue().get(codeTemplate);
            if (StringUtils.isNotBlank(string)) {
                if (string.equalsIgnoreCase(code)) {
                    redisTemplate.delete(codeTemplate);
                    //验证码正确
                    return Msg.returnSuccessMsg("验证码正确");
                } else {
                    return Msg.returnErrorMsg("验证码错误");
                }
            } else {
                return Msg.returnErrorMsg("验证码超时");
            }
        } catch (Throwable e) {
            logger.error("jessionId:{},从redis中获取验证码异常，{}", codeTemplate, e);
        }
        return Msg.returnErrorMsg("验证码获取错误");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg checkSmsCode(String username, String code) {
        String codeTemplate = CommonMessageUtil.CODE_PRE + username;
        try {
            String string = redisTemplate.opsForValue().get(codeTemplate);//
            if (StringUtils.isNotBlank(string)) {
                if (string.equalsIgnoreCase(code)) {
                    //验证码正确
                    return Msg.returnSuccessMsg("验证码正确");
                } else {
                    return Msg.returnErrorMsg("验证码错误");
                }
            } else {
                return Msg.returnErrorMsg("验证码超时");
            }
        } catch (Throwable e) {
            logger.error("JessionId:{},从redis中获取验证码异常，{}", codeTemplate, e);
        }
        return Msg.returnErrorMsg("验证码获取错误");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public Msg getPrivateKey(String jsonId) {

        return null;
    }

    @Override
    public Boolean checkSmsSendFlag(String pre, int rule, String username, String type) {
        pre = pre + username;
        String validaty = CommonMessageUtil.VALIDITY + pre;
        try {
            if (redisTemplate.hasKey(validaty)) {
                return false;
            } else {
                long count = redisTemplate.opsForValue().increment(pre, 1);
                if (rule >= count) {
                    //合法
                    return true;
                } else {
                    Long diff = 1L;
                    if ("register".equals(type)) {
                        diff = Long.parseLong(CodeConstants.getCodeRegisterEffectiveIime());//有效期
                    }
                    if ("forgot".equals(type)) {
                        diff = Long.parseLong(CodeConstants.getCodeForgotEffectiveIime());//有效期
                    }
                    //删除key 设置一个过期时间段
                    redisTemplate.delete(pre);
                    redisTemplate.opsForValue().set(validaty, "1", diff, TimeUnit.SECONDS);
                    logger.info(">>>>>>>>>:用户获取验证码超过次数限制：{}", validaty);
                    //不合法
                    return false;
                }
            }
        } catch (Throwable throwable) {
            logger.info("发送验证码短信异常：{}", throwable.getMessage());
            return false;
        }

    }

    @Override
    public Msg checkSmsCodeForgot(String username, String code) {
        String codeTemplate = CommonMessageUtil.SMS_CODE_FORGOT_PRE + username;
        try {
            String string = redisTemplate.opsForValue().get(codeTemplate);//
            if (StringUtils.isNotBlank(string)) {
                if (string.equalsIgnoreCase(code)) {
                    redisTemplate.delete(codeTemplate);
                    //验证码正确
                    return Msg.returnSuccessMsg("验证码正确");
                } else {
                    return Msg.returnErrorMsg("验证码错误");
                }
            } else {
                return Msg.returnErrorMsg("验证码超时");
            }
        } catch (Throwable e) {
            logger.error("JessionId:{},从redis中获取验证码异常，{}", codeTemplate, e);
        }
        return Msg.returnErrorMsg("验证码获取错误");
    }

    @Override
    public Msg getPasswordByPrivatekey(String JsionId, String password, String confrimPassword) {
        try {
            //从redis中拿出私钥
            Msg msg = userKeyService.getKey(CommonMessageUtil.PRIVATE + JsionId);
            if (msg.isSuccess()) {
                String privateKey = (String) msg.getData();
                String decryptData = RSAUtils.decrypt(password, RSAUtils.getPrivateKey(privateKey));
                String decryptDataConfrim = RSAUtils.decrypt(confrimPassword, RSAUtils.getPrivateKey(privateKey));
                logger.info("忘记密码，密码解密成功【】 ：{}", decryptDataConfrim);
                Boolean result = RandomUtil.checkPassword(decryptDataConfrim);
                logger.info("忘记密码，密码规则校验结果 ：{}", result);
                if (result) {
                    if (decryptData.equals(decryptDataConfrim)) {
                        return Msg.returnSuccessMsg(decryptData);
                    } else {
                        return Msg.returnErrorMsg("两次密码输入不一致");
                    }
                } else {
                    return Msg.returnErrorMsg("密码不符合规范");
                }
            } else {
                return msg;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("密码处理异常:{}", e.getMessage());
            return Msg.returnErrorMsg("密码处理异常");
        }
    }

    @Override
    public Msg decryptionPassword(HttpServletRequest request, String password) {
        String jsonId = (String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();
        try {
            //从redis中拿出私钥
            Msg msg = userKeyService.getKey(CommonMessageUtil.PRIVATE + jsonId);
            if (msg.isSuccess()) {
                String privateKey = (String) msg.getData();
                String decryptData = RSAUtils.decrypt(password, RSAUtils.getPrivateKey(privateKey));
                return Msg.returnSuccessMsg(decryptData);
            } else {
                return msg;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("密码处理异常:{}", e.getMessage());
            return Msg.returnErrorMsg("密码处理异常");
        }
    }

    @Override
    public Msg checkSmsCodeRegister(String mobile, String smsCode) {
        String codeTemplate = CommonMessageUtil.SMS_CODE_REGISTER_PRE + mobile;
        try {
            String string = redisTemplate.opsForValue().get(codeTemplate);//
            if (StringUtils.isNotBlank(string)) {
                if (string.equalsIgnoreCase(smsCode)) {
                    redisTemplate.delete(codeTemplate);
                    //验证码正确
                    return Msg.returnSuccessMsg("验证码正确");
                } else {
                    return Msg.returnErrorMsg("验证码错误");
                }
            } else {
                return Msg.returnErrorMsg("验证码超时");
            }
        } catch (Throwable e) {
            logger.error("JessionId:{},从redis中获取验证码异常，{}", codeTemplate, e);
        }
        return Msg.returnErrorMsg("验证码获取错误");
    }
    @Value("${all.in.one.account.id}")
    private String accountId;
    @Autowired
    PrepayService prepayService;
    @Override
    public void setKey() {
        long result =  prepayService.charge("{k}"+ BalanceType.getSmsBalance() + accountId, 1);
        System.out.println("result :"+result);
    }


}
