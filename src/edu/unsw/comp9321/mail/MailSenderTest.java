package edu.unsw.comp9321.mail;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.mail.URLName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.mail.MailSender;
import edu.unsw.comp9321.mail.MailSenderException;

import junit.framework.TestCase;

public class MailSenderTest {

	static Logger logger = Logger.getLogger(MailSenderTest.class.getName());
	protected void setUp() throws Exception {
		// To test JNDI outside Tomcat, we need to set these
		 // values manually ... (just for testing purposes)
		 System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
            "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, 
            "org.apache.naming");            

        // Create InitialContext with java:/comp/env/mail
        InitialContext ic = new InitialContext();

        ic.createSubcontext("java:");
        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.createSubcontext("java:comp/env/mail");
       
        //Construct a naming reference
        Reference ref = new Reference("javax.mail.Session", "org.apache.naming.factory.MailSessionFactory", null);
        ref.add(new StringRefAddr("auth", "Container"));
        //You can also use other smtp providers
        ref.add(new StringRefAddr("mail.smtp.host","smtp.gmail.com"));
        ref.add(new StringRefAddr("mail.smtp.auth","true"));
        //Replace as necessary
        ref.add(new StringRefAddr("mail.smtp.user","blackandgarcias"));
        ref.add(new StringRefAddr("mail.smtp.password","garciasandblack"));
        //Turn this off to avoid username and password appearing in log files.
        ref.add(new StringRefAddr("mail.debug","false"));
        ref.add(new StringRefAddr("mail.smtp.starttls.enable","true"));
        
        ic.bind("java:comp/env/mail/Session", ref);
	}
	
	public void testSession(){
		MailSender sender = null;
		try {
			sender = MailSender.getMailSender();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (MailSenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSendMessage(){
		MailSender sender = null;
		try{
			sender = MailSender.getMailSender();
			//Replace as necessary
			String fromAddress = "garciasandblack@gmail.com";
			String toAddress = "matthew.c.saxby@gmail.com";
			String subject = "test";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Dear Mailer, Why you email me?!!");
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
 		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		InitialContext ic = new InitialContext();
		Context subContext = (Context) ic.lookup("java:comp");
		logger.info("Found sub-context");
		subContext.destroySubcontext("/env/mail");
		ic.destroySubcontext("java:comp");
		ic.destroySubcontext("java:");
	}

	public void sendConfirmationEmail(String username, String email) {
		MailSender sender = null;
		try{
			sender = MailSender.getMailSender();
			//Replace as necessary
			String fromAddress = "garciasandblack@gmail.com";
			String toAddress = email;
			String subject = "Confirmation for SMDB";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Hi, \nThanks for signing up to the Sydney Movie Database. A world of movies and showtimes awaits you. \nTo Finalise your membership simply click on the link below: \nhttp://localhost:8080/BlackAndGarcias/controller?action=confirmSignup&username=" + username);
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
 		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
