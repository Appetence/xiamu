package demo.util;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @program: vue2x
 * @description: guava布隆过滤器做存在校验
 * @Author: xiamu
 * @create: 2021-02-26 20:12
 */
public class BloomFilterUtil {
    /**
     * filter应该是单例
     */
    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 10, 0.01);

    public static boolean isExist(String value) {
        /**
         * BloomFilter 使用位向量来表示元素，而不存储本身，这样极大压缩了元素的存储空间。其空间复杂度为O(m)，m是位向量的长度。而m与插入总数量n的关系如下公式(1)所示
         * https://zhuanlan.zhihu.com/p/85042394
         */
        // 创建一个BloomFilter，其预计插入的个数为10，误判率大约为0.01
        // 查询value是否存在
        if (bloomFilter.mightContain(value)) {
            return true;
        } else {
            // 将value
            bloomFilter.put(value);
            return false;
        }
    }

}
