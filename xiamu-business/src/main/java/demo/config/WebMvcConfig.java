/**
 * All rights Reserved, Designed By jxZhang
 *
 * @Title: WebMvcConfig.java
 * @Package com.sinosoft.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: jxZhang
 * @date: 2018年12月10日 下午5:54:08
 */
package demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import demo.config.interceptor.RequestInterceptor;
import demo.config.filter.HttpServletRequestReplacedFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName: WebMvcConfig
 * @Description:新增静态资源 因为 在访问Swagger2Config时 shiro会拦截Swagger；使用@ENableWebMvc进行配置，不会覆盖掉mvc默认的配置；
 * @Author: xiamu
 * @date: 2018年12月10日 下午5:54:08
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private RequestInterceptor requestInterceptor;

    /**
     * 解决前后端分离跨域问题
     * <p>Title: addCorsMappings</p>
     * <p>Description: </p>
     *
     * @param registry
     * @see WebMvcConfigurer#addCorsMappings(CorsRegistry)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //设置允许的方法
                .allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //跨域允许时间
                .maxAge(3600);
    }

    /**
     * 指定filter处理特定请求
     * 过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean httpServletRequestReplacedRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //请求参数解密过滤器
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/sign/**");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/static/*");
    }

    /**
     * 指定静态资源所在路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        //localeResolver.setCookieName("lang");
        //设置默认区域
        localeResolver.setDefaultLocale(new Locale("en", "US"));
        //设置cookie有效期.
        localeResolver.setCookieMaxAge(-1);
        return localeResolver;
    }

    /**
     * 请求指定编码格式
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

    /**
     * 自定义消息转换器
     *
     * @return
     */
    @Bean
    public HttpMessageConverter responseBodyConverter() {
        //字符串消息转换
        /*StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;*/
        //自定义fastjson 进行转换 https://www.cnblogs.com/xiaopotian/p/8654993.html
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /**
         * 　fastJson配置实体调用setSerializerFeatures方法可以配置多个过滤方式，常用的如下：
         * 　　1、WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * 　　2、WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * 　　3、DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * 　　4、WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * 　　5、WriteMapNullValue：是否输出值为null的字段,默认为false。
         */
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;

    }
}
