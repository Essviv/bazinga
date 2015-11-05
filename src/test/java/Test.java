import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by sunyiwei on 2015/11/3.
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info("My first shiro application...");

        //set up
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //get current login subject
        Subject subject = SecurityUtils.getSubject();

        //session operation
        String key = "age";
        Session session = subject.getSession();
        session.setAttribute(key, 27);
        logger.info("Current user is {} years old.", session.getAttribute(key));

        //login
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            subject.login(token);
        }

        //output principal
        logger.info("Hello, {}. Now is {}", "patrick", new Date());
        logger.info("User[{}] logged in successfully.", subject.getPrincipal());

        //check role, permission
        if (subject.hasRole("schwartz")) {
            logger.info("May the schwartz be with you.");
        } else {
            logger.info("Hello, mere mortal.");
        }

        //check permission
        if (subject.isPermitted("lightsaber:weild")) {
            logger.info("You may use a lightsaber ring. Use it wisely.");
        } else {
            logger.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        if (subject.isPermitted("winnebago:drive:eagle5")) {
            logger.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            logger.info("Sorry, you're not allowed to drive the 'eagle5' winnerbago.");
        }

        subject.logout();
        System.exit(0);
    }
}
