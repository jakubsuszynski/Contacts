package jsuszynski.login.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordHash {
    protected final static Logger log = LoggerFactory.getLogger(PasswordHash.class);

    private PasswordHash() {
    }

    public static String hashPassword(String password) {
        String md5 = DigestUtils.md5Hex(password);
        return md5;

    }

}
