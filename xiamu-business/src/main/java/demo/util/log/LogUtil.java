package demo.util.log;

import lombok.Data;
import lombok.ToString;

/**
 * @program:
 * @description: 日志
 * @Author: xiamu
 * @create: 2020-08-03 14:25
 */
@Data
@ToString
public class LogUtil {

    private String uuid;
    private String date;
    private String httpUri;
    private String httpMethod;
    private String classMethod;
    private Object params;
    private String time;
    private Object result;

}
