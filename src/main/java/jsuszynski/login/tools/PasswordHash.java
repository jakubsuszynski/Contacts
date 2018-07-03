package jsuszynski.login.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PasswordHash {
    protected final static Logger log = LoggerFactory.getLogger(PasswordHash.class);

    private PasswordHash() {
    }

    public static String hashPassword(HttpServletRequest req) {
        return "";
    }

}
