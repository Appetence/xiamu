package com.umpay.rms.gpd.user.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("api文档")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //请求头中的参数非必填
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf()).select()
                .apis(RequestHandlerSelectors.basePackage("com.umpay.rms.gpd.user.controller"))
                .paths(PathSelectors.any()).build().globalOperationParameters(pars);
    }
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("融合消息平台 RestAPI Docs")
                //.termsOfServiceUrl("")
                .version("1.0")
                //描述
                .description("API 描述")
                .build();

    }
}
