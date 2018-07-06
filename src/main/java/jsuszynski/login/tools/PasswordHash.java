package jsuszynski.login.tools;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {

    private PasswordHash() {
    }

    public static String hashPassword(String password) {
        return DigestUtils.md5Hex(password);

    }

}
