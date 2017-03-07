package regionwriter;


import org.apache.geode.LogWriter;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.security.AuthInitialize;
import org.apache.geode.security.AuthenticationFailedException;

import java.util.Properties;


public class ClientAuthInitialize implements AuthInitialize {

    public static final String USER_NAME = "security-username";
    public static final String PASSWORD = "security-password";

    public static AuthInitialize create() {
        return new ClientAuthInitialize();
    }

    @Override
    public void close() {
    }

    @Override
    public Properties getCredentials(Properties arg0, DistributedMember arg1,
                                     boolean arg2) throws AuthenticationFailedException {
        Properties props = new Properties();

        String username = "operator";
        String password = "operator";
        props.put(USER_NAME, username);
        props.put(PASSWORD, password);
        return props;
    }

    @Override
    public void init(LogWriter arg0, LogWriter arg1)
            throws AuthenticationFailedException {
    }
}
