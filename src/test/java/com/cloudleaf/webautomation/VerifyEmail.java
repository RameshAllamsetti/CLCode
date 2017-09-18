package com.cloudleaf.webautomation;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.apache.commons.lang3.StringUtils;
 
/**
* The Class VerifyEmail.
*
 * @author Kiran Kumar Cherukuri
*/
public class VerifyEmail {
 
       public static String getVerificationCode(String emailId, String password, String browser, String os, String userid) {
             
              if(browser.equalsIgnoreCase("safari"))
            {
             browser = "Safari";
            }
            else if(browser.equalsIgnoreCase("chrome"))
            {
             browser = "Chrome";
            }
            else if(browser.equalsIgnoreCase("firefox"))
            {
             browser = "Firefox";
            }
            else if(browser.equalsIgnoreCase("ie"))
            {
             browser = "IE";
            }
              String verificationCode = null;
              System.out.println("\nVerify your identity in Salesforce Email verification Started");
              try {
                     OUTERLOOP: for (int counter = 1; counter <= 10; counter++) {
                           try {
                                  Thread.sleep(15000);
                           } catch (InterruptedException e) {
                           }
                           System.out.println("\nVerify your identity in Salesforce Email verification in progress........");
                           Properties props = System.getProperties();
                           props.setProperty("mail.store.protocol", "pop");
                           Session session = Session.getDefaultInstance(props, null);
                           Store store = session.getStore("pop3s");
                           String[] emailIdSplit = emailId.split("@");
                           String emailDomain = emailIdSplit[1];
                           if (emailDomain.equalsIgnoreCase("innominds.com")) {
                                  store.connect("outlook.office365.com", 995, emailId, password);
                           } else {
                                  System.out.println("Email id is NOT Valid");
                           }
                           Folder folder = store.getFolder("Inbox");
                           folder.open(Folder.READ_WRITE);
                           FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.RECENT), true);
                           ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                           Message[] messages = folder.search(ft);
                           for (int i = 1; i <= messages.length; i++) {
                                  Message message2 = messages[messages.length - i];
                                  Object content = null;
                                  try {
                                         content = message2.getContent();
                                  } catch (IOException e1) {
                                         e1.printStackTrace();
                                  }
                                  if (message2.getSubject().equalsIgnoreCase("FW: Sandbox: Verify your identity in Salesforce")/* && message2.getReceivedDate().after(new Date())*/) {
                                         if (content.toString().trim().contains("Verification Code") && content.toString().trim().contains(userid) && content.toString().trim().contains(browser) && content.toString().trim().contains(os))
                                         {
                                                System.out.println("Verify your identity in Salesforce Email Received and Verified!!!");
                                                //message2.setFlag(Flags.Flag.SEEN,true);
                                                folder.setFlags(new Message[] {message2}, new Flags(Flags.Flag.SEEN), true);
                                                message2.setFlag(Flag.SEEN, true);
                                                String contentStr = content.toString().trim();
                                                verificationCode = getCode(contentStr);
                                                boolean isNumber = isInteger(verificationCode);
                                                break OUTERLOOP;
                                         }
                                  }
                           }
                           folder.close(true);
                           store.close();
                           if (counter == 10) {
                                  System.out.println("\n\"Verify your identity\" Email NOT Received even after waitig for 10 secs");
                           }
                     }
              } catch (MessagingException e) {
                     System.out.println("Error: " + e);
                     e.printStackTrace();
              }
              return verificationCode;
       }
      
       public static String getCode(String bodycontent) {
              String code = null, finalCode = null;
              try {
                     String start = "Verification Code: ";
                     String end = "If you didn't recently log";
                     if (bodycontent.contains(start)) {
                           code = StringUtils.substringBetween(bodycontent, start, end);
                     }
                     code = code.trim();
                     finalCode = code.replaceAll("<[^>]*>", "").trim();
                     finalCode = finalCode.replaceAll("&nbsp;", "");
                     System.out.println("Latest Verification Code: "+finalCode.trim());
                     return finalCode;
              } catch (Exception e) {
                     e.printStackTrace();
              }
              return finalCode;
       }
      
       public static boolean isInteger(String s) {
           try {
               Integer.parseInt(s);
           } catch(NumberFormatException e) {
               return false;
           } catch(NullPointerException e) {
               return false;
           }
           return true;
       }
}
