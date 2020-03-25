package org.lzn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 工具
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/25 22:14
 */
public class MD5Util {

    /**
     * 将字符串进行 MD5 加密
     *
     * @param content 加密内容
     * @author LinZhenNan lin_hehe@qq.com 2020-03-25 22:19
     * @return java.lang.String
     */
    public static String toMd5(String content) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    content.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0, n = 32 - md5code.length(); i < n; i++) {
            md5code = "0".concat(md5code);
        }
        return md5code;
    }
}
