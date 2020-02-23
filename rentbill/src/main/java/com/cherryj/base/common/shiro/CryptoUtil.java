package com.cherryj.base.common.shiro;


import com.cherryj.base.domain.UserAccount;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public final class CryptoUtil {


    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    public static void encryptPassword(UserAccount userAccount) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        userAccount.setPasswordSalt(randomNumberGenerator.nextBytes().toHex());
        String hashedPassword = new SimpleHash(ALGORITHM_NAME, userAccount.getPassword(),
                ByteSource.Util.bytes(userAccount.getPasswordSalt()), HASH_ITERATIONS).toHex();
        userAccount.setPasswordHash(hashedPassword);
    }

    public static boolean validatePassword(String passwordHash, String password, String passwordSalt) {
        String hashedPassword = new SimpleHash(ALGORITHM_NAME, password,
                ByteSource.Util.bytes(passwordSalt), HASH_ITERATIONS).toHex();
        return passwordHash.equals(hashedPassword);
    }
}
