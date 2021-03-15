package demo.response;

import lombok.*;

/**
 * @program: vue2.0
 * @description:
 * @Author: xiamu
 * @create: 2020-11-24 20:49
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TableDataResponse {

    private int error = 0;
    private Object data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
