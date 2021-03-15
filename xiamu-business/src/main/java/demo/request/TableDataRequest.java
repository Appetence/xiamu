package demo.request;

/**
 * @program: vue2.0
 * @description:
 * @Author: xiamu
 * @create: 2020-11-24 20:21
 */
public class TableDataRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TableDataRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
