package com.umpay.rms.gpd.internal.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务接口地址
 *
 */
@Component
@ConfigurationProperties(prefix = "services", ignoreInvalidFields = true)
public class ServiceAddress {
    private List<Map<String, String>> address = new ArrayList<>();

    private static final String PAY_SERVER_URL = "pay";


    /**
     * 获取服务接口地址
     *
     * @param key 接口名
     * @return 地址
     */
    public String get(String key) {
        return address.stream().filter(m -> m.containsKey(key)).findFirst().orElse(new HashMap<>(0)).get(key);
    }

    /**
     * 获取支付服务接口地址
     *
     * @return 支付服务接口地址
     */
    public String getPayAddress() {
        return get(PAY_SERVER_URL);
    }

    public List<Map<String, String>> getAddress() {
        return address;
    }

    public void setAddress(List<Map<String, String>> address) {
        this.address = address;
    }

    public static String getPayServerUrl() {
        return PAY_SERVER_URL;
    }
}
