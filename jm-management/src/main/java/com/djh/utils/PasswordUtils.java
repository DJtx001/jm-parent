package com.djh.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 密码加密工具类
 * <p>
 * 新密码统一使用 BCrypt：自带随机盐、可抵抗彩虹表、计算慢可抵御暴力破解。
 * 同时兼容系统早期用 MD5 + 固定盐 "djh" 存储的历史密码，保证老账号仍能正常登录，实现平滑迁移。
 */
public class PasswordUtils {

    /**
     * 历史遗留的 MD5 固定盐（仅用于校验老密码）
     */
    private static final String LEGACY_SALT = "djh";

    /**
     * BCrypt 强度，默认 10：值越大越安全但计算越慢
     */
    private static final int BCRYPT_STRENGTH = 10;

    /**
     * BCryptPasswordEncoder 线程安全，静态复用避免重复创建
     */
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(BCRYPT_STRENGTH);

    private PasswordUtils() {
    }

    /**
     * 加密密码（BCrypt）
     * <p>注意：同一个明文每次加密结果都不同，因为盐是随机生成的
     */
    public static String encode(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    /**
     * 校验密码
     * <p>BCrypt 密文按 BCrypt 规则校验；历史 MD5 密文按 MD5 规则校验
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null || encodedPassword.isEmpty()) {
            return false;
        }
        if (isBcrypt(encodedPassword)) {
            return ENCODER.matches(rawPassword, encodedPassword);
        }
        return legacyMd5(rawPassword).equalsIgnoreCase(encodedPassword);
    }

    /**
     * 判断密文是否为 BCrypt 格式（形如 $2a$10$...）
     */
    public static boolean isBcrypt(String encodedPassword) {
        return encodedPassword != null
                && (encodedPassword.startsWith("$2a$")
                || encodedPassword.startsWith("$2b$")
                || encodedPassword.startsWith("$2y$"));
    }

    /**
     * 历史 MD5 + 固定盐加密（仅用于兼容老密码，新密码不要再用）
     */
    public static String legacyMd5(String rawPassword) {
        return DigestUtils.md5DigestAsHex((rawPassword + LEGACY_SALT).getBytes(StandardCharsets.UTF_8));
    }
}
