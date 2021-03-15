package demo.aspect;

import demo.util.log.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @Author: xiamu
 * @create: 2020-06-19 12:26
 * @quoted https://www.cnblogs.com/liaojie970/p/7883687.html
 */
@Aspect
@Component
public class WebMethodAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebMethodAspect.class);

    /**
     * 切入点，用于匹配是连接点的执行方法
     * <p>
     * 指定 controller 包下的注解
     * execution（）	表达式的主体；
     * 第一个”*“符号	表示返回值的类型任意；
     * com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“	表示当前包及子包
     * 第二个”*“	表示类名，*即所有类。此处可以自定义，下文有举例
     * .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Pointcut("execution( * demo.controller..*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void methodPointCut() {

    }

    /**
     * 前置方法
     * <p>
     * 指定当前执行方法在methodPointCut之前执行
     */
    @Before("methodPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
    }

    /**
     * 指定在方法返回后调用
     */
    @AfterReturning(returning = "returning", pointcut = "methodPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object returning) throws Throwable {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
    }

    /**
     * 后置方法，通知方法会在目标方法返回或异常后调用
     * @After
     */
    @After("methodPointCut()")
    public void doAfter(){

    }
    /**
     * 通知方法返回
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("methodPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LogUtil logUtil = new LogUtil();
        logUtil.setUuid(String.valueOf(UUID.randomUUID()));
        logUtil.setDate("");
        logUtil.setHttpUri(request.getRequestURL().toString());
        logUtil.setHttpMethod(request.getMethod());
        //获取访问路径: 项目名+servlet
        String requestURI = request.getRequestURI();
        Object[] args = pjp.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        logUtil.setParams(arguments);
        logUtil.setClassMethod(requestURI);
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        logUtil.setResult(ob);
        logger.info("Request Info : ->> {}", logUtil);
        return ob;
    }
}
