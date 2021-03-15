package com.umpay.rms.gpd.user.controller;

import cn.hutool.core.lang.UUID;
import com.umpay.rms.gpd.internal.dto.Msg;
import com.umpay.rms.gpd.internal.util.RandomStringGenerator;
import com.umpay.rms.gpd.internal.util.moblie.MobileUtil;
import com.umpay.rms.gpd.user.api.entity.DbUser;
import com.umpay.rms.gpd.user.api.pojo.SmsSendInfo;
import com.umpay.rms.gpd.user.api.request.UserMobileLoginRequest;
import com.umpay.rms.gpd.user.constant.AccountConstants;
import com.umpay.rms.gpd.user.constant.CodeConstants;
import com.umpay.rms.gpd.user.service.DbUserService;
import com.umpay.rms.gpd.user.service.LoginService;
import com.umpay.rms.gpd.user.service.TbAccountBalanceDisposeService;
import com.umpay.rms.gpd.user.service.UserKeyService;
import com.umpay.rms.gpd.user.util.CommonMessageUtil;
import com.umpay.rms.gpd.user.util.CommonParamsUtil;
import com.umpay.rms.gpd.user.util.code.CodeUtil;
import com.umpay.rms.gpd.user.util.code.ImageCode;
import com.umpay.rms.gpd.user.util.requset.RequsetUtil;
import io.swagger.annotations.*;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: rms-gpd
 * @description: 登录
 * @author: xiamu
 * @create: 2020-06-05 10:16
 */
@Api(value = "登录处理方法", tags = {"登录处理方法"})
//@CrossOrigin
@RestController
@RequestMapping("login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;
    @Resource
    private UserKeyService userKeyService;
    @Resource
    private DbUserService dbUserService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private TbAccountBalanceDisposeService tbAccountBalanceDisposeService;

    @Value("${all.in.one.register.count}")
    private int register;
    @Value("${all.in.one.forgot.count}")
    private int forgot;

    //校验验证码
    @ApiOperation(value = "校验验证码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "query", dataType = "String", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @RequestMapping(value = "checkCode", method = RequestMethod.GET)
    public Msg checkCode(HttpServletRequest httpServletRequest, @RequestParam(value = "code", required = true) String code) {
        //String JsonId = httpServletRequest.getSession().getId();
        String JsonId = this.getRememberMe(httpServletRequest, CommonParamsUtil.getJessionid());
        return loginService.checkCode(JsonId, code);

    }

    @ApiOperation(value = "生成验证码")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    // 生成验证码
    @RequestMapping(value = "/securityCode", method = RequestMethod.GET)
    public Msg securityCode(HttpServletRequest req, HttpServletResponse resp) {
        String JsonId = CommonMessageUtil.CODE_PRE + String.valueOf(UUID.randomUUID());//CommonMessageUtil.CODE_PRE + req.getSession().getId();
        Map<String, Object> map = CodeUtil.generateCodeAndPic();
        String code = map.get("code").toString();
        req.getSession().setAttribute("securityCode", code);
        //redis中存储
        Long diff = 5 * 60L;//有效期
        try {
            try {
                //因为Template中set值时会先调用序列化器将键和值都序列化为byte字节数组放入redis数据库中，在客户端除非get后的key为“keyTest”使用同样的序列化器序列化后的值，否则取不到zh值
                redisTemplate.opsForValue().set(JsonId, code, diff, TimeUnit.SECONDS);//
            } catch (Throwable throwable) {
                logger.error("短信验证码存放redis异常:{}", throwable);
            }
            //文件流写出
            resp.setContentType("image/png");// 必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = resp.getOutputStream(); // 获取文件输出流
            ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", os);
            os.flush();
            os.close();
            return Msg.returnSuccessMsg("", JsonId);
        } catch (IOException e) {
            logger.error("验证码写出异常：{}", e);
        } catch (Exception e) {
            logger.error("验证码生成异常：{}", e);
        }
        return Msg.returnSuccessMsg("验证码异常");

    }

    @ApiOperation(value = "生成验证码Enge")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    // 生成验证码
    @RequestMapping(value = "/v1/securityCodeEnge", method = RequestMethod.GET)
    public void securityCodeEnge(HttpServletRequest request, HttpServletResponse response) {
        //String JsonId = (String) RequsetUtil.getJessionId(req,CommonParamsUtil.getJessionid()).getData();
        String JsonId = CommonMessageUtil.CODE_PRE + request.getSession().getId();// this.getRememberMe(req,CommonParamsUtil.getJessionid());
        this.createImage(JsonId, request, response);
        // return Msg.returnSuccessMsg("获取验证码",this.createImage(JsonId,request,response));
    }

    private Map<String, Object> createImage(String JsonId, HttpServletRequest req, HttpServletResponse response) {
        Map<String, Object> map = CodeUtil.generateCodeAndPic();
        String code = map.get("code").toString();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage) map.get("codePic"), "PNG", byteArrayOutputStream);
            Long diff = 5 * 60L;//有效期
            try {
                //因为Template中set值时会先调用序列化器将键和值都序列化为byte字节数组放入redis数据库中，在客户端除非get后的key为“keyTest”使用同样的序列化器序列化后的值，否则取不到zh值
                redisTemplate.opsForValue().set(JsonId, code, diff, TimeUnit.SECONDS);//
            } catch (Throwable e) {
                logger.error("短信验证码存放redis异常：{}", e);
            }
            //无论何种形式,最终转为byte[]
            byte[] data = byteArrayOutputStream.toByteArray();

            MagicMatch match = null;

            match = Magic.getMagicMatch(data);
            String mimeType = match.getMimeType();

            // 设置响应的类型格式为图片格式
            response.setContentType(mimeType);
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            //bos.write(data);
            byteArrayOutputStream.close();
            bos.close();
            map.clear();
            map.put("image", data);
            map.put("key", JsonId);
            return map;
        } catch (Exception e) {
            logger.error("生成验证码异常{}", e);
        }
        return map;
    }

    private Map<String, Object> createImages(String JsonId) {
        Map<String, Object> map = CodeUtil.generateCodeAndPic();
        String code = map.get("code").toString();
        //ImageIO.write((RenderedImage) map.get("codePic"), "PNG", byteArrayOutputStream);
        Long diff = 5 * 60L;//有效期
        try {
            //因为Template中set值时会先调用序列化器将键和值都序列化为byte字节数组放入redis数据库中，在客户端除非get后的key为“keyTest”使用同样的序列化器序列化后的值，否则取不到zh值
            redisTemplate.opsForValue().set(JsonId, code, diff, TimeUnit.SECONDS);//
        } catch (Throwable e) {
            logger.error("短信验证码存放redis异常：{}", e);
        }
        //无论何种形式,最终转为byte[]
        //byte[] data = byteArrayOutputStream.toByteArray();

        map.put("image", map.get("codePic"));
        map.put("key", JsonId);
        return map;
    }

    private String getRememberMe(HttpServletRequest request, String key) {
        return (String) RequsetUtil.getJessionId(request, key).getData();
    }


    public Msg checkUserMobileLoginRequest(UserMobileLoginRequest userMobileLoginRequest) {
        if (StringUtils.isBlank(userMobileLoginRequest.getMobile())) {
            return Msg.returnErrorMsg("用户名不可以为空");
        }

        return null;

    }

    @ApiOperation(value = "获取publickey")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @RequestMapping(value = "/getPublicKey", method = RequestMethod.POST)
    public Msg getPublicKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // String JsonId = request.getSession().getId() ;//(String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();
        String JsonId = request.getSession().getId();// this.getRememberMe(request,CommonParamsUtil.getJessionid());
        Msg key = userKeyService.createKey(JsonId);
        ImageCode imageCode = new ImageCode();
        imageCode.setKey((String) key.getData());
        if (key.getCode() == 200) {
            key.setData(imageCode);
        }
        return key;
    }

    @ApiOperation(value = "获取publickey+图形验证码")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    @RequestMapping(value = "/getKey", method = RequestMethod.POST)
    public Msg getKey() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // String JsonId = request.getSession().getId() ;//(String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();
        String JsonId = request.getSession().getId();// this.getRememberMe(request,CommonParamsUtil.getJessionid());
        Map<String, Object> map = this.createImages(CommonMessageUtil.CODE_PRE + JsonId);
        logger.info("验证码：>>> {}", map.get("code"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Msg key = userKeyService.createKey(JsonId);
        ImageCode imageCode = new ImageCode();
        imageCode.setData(imageToBytes((RenderedImage) map.get("image"), byteArrayOutputStream));
        imageCode.setjId(JsonId);
        imageCode.setKey((String) key.getData());
        imageCode.setCode((StringBuffer) map.get("code"));
        logger.info("code is {}", map.get("code"));
        if (key.getCode() == 200) {
            //map.put("publicKey", key.getData());
            key.setData(imageCode);
        }
        return key;
    }


    private static byte[] imageToBytes(RenderedImage bImage, ByteArrayOutputStream byteArrayOutputStream) {
        try {
            ImageIO.write(bImage, "PNG", byteArrayOutputStream);
        } catch (IOException e) {
            logger.error("二维码流写出异常：{}", e);
        }
        return byteArrayOutputStream.toByteArray();
    }


    @ApiOperation(value = "发送sms验证码")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "后台异常"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "请求无权限"),
            @ApiResponse(code = 402, message = "请求未授权"),
            @ApiResponse(code = 403, message = "请求被禁止"),
            @ApiResponse(code = 404, message = "请求页面找不到"),
            @ApiResponse(code = 405, message = "请求方式不正确")})
    // 生成验证码
    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
    public Msg sendSmsCode(/*HttpServletRequest request,*/ @RequestBody UserMobileLoginRequest userMobileLoginRequest) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /*******************------------------check image code--------------------*********************/
        String sessionId = (String) RequsetUtil.getJessionId(request, CommonParamsUtil.getJessionid()).getData();

        Msg checkResult = loginService.checkCode(sessionId, userMobileLoginRequest.getImageCode());
        if (checkResult.isFail()) {
            return Msg.returnErrorMsg(checkResult.getMessage());
        }
        //次数统计前缀
        String pre = null;
        //号码前缀
        String codePre = null;
        String codeEffective = "1";
        Boolean flag = false;

        if (StringUtils.isBlank(userMobileLoginRequest.getMobile())) {
            return Msg.returnErrorMsg("手机号不可以为空");
        } else {
            if (!MobileUtil.checkMobile(userMobileLoginRequest.getMobile())) {
                return Msg.returnErrorMsg("手机号不符合规范");
            }
        }

        String username = userMobileLoginRequest.getMobile();
        DbUser dbUser = dbUserService.quareUserInfoByUsername(username);

        switch (userMobileLoginRequest.getBusi()) {
            case 1:
                //注册
                pre = CommonMessageUtil.SMS_CODE_REGISTER_COUNT_PRE;
                codePre = CommonMessageUtil.SMS_CODE_REGISTER_PRE;
                codeEffective = CodeConstants.getCodeRegisterSmsEffectiveIime();
                //校验注册时候验证码获取次数是否合法
                flag = loginService.checkSmsSendFlag(pre, register, username, "register");
                if (dbUser != null) {
                    return Msg.returnErrorMsg("手机号已注册");
                }
                break;
            case 2:
                //忘记密码时候验证码获取次数是否合法
                pre = CommonMessageUtil.SMS_CODE_FORGOT_COUNT_PRE;
                codePre = CommonMessageUtil.SMS_CODE_FORGOT_PRE;
                codeEffective = CodeConstants.getCodeForgotSmsEffectiveIime();
                if (dbUser == null) {
                    return Msg.returnErrorMsg("手机号不存在");
                }
                flag = loginService.checkSmsSendFlag(pre, forgot, username, "forgot");
                break;
            default:
                break;
        }
        if (!flag) {
            return Msg.returnErrorMsg("验证码获取次数太频繁，请过段时间后重试");
        }
        /*******************------------------ssm code send--------------------*********************/
        //key
        String JsonId = codePre + username;
        String code = RandomStringGenerator.getRandomStringByLength(6);
        try {
            //code 放到redis中
            Long diff = Long.parseLong(codeEffective);//有效期
            //因为Template中set值时会先调用序列化器将键和值都序列化为byte字节数组放入redis数据库中，在客户端除非get后的key为“keyTest”使用同样的序列化器序列化后的值，否则取不到zh值
            redisTemplate.opsForValue().set(JsonId, code, diff, TimeUnit.SECONDS);//
            //异步-调用短信发送服务 sms_code_send_template
            SmsSendInfo smsSendInfo = new SmsSendInfo();
            smsSendInfo.setAccountId(AccountConstants.getAccountId());
            smsSendInfo.setCode(code);
            smsSendInfo.setMobile(userMobileLoginRequest.getMobile());
            smsSendInfo.setReqId(String.valueOf(System.currentTimeMillis()));
            //TODO 异步发送短信
//            SendResult sendResult = productServiceProxy.sendMessage(smsSendInfo);
//            logger.info("短信发送结果：{}短信验证码：{}", JSONUtil.toJsonStr(sendResult), code);
            return Msg.returnSuccessMsg("");
        } catch (Throwable e) {
            logger.error("验证码生成异常：{}", e);
            return Msg.returnErrorMsg("生成验证码异常");
        }
    }


}
