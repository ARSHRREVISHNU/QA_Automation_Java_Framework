package AmazonPages;

import CommonUtilities.CommonUtility;
import CommonUtilities.ExtentSparkReport;
import CommonUtilities.JSONUtility;
import Constants.FilePaths;
import WebUI.POM.amazonPages.AmazonLoginPage;
import lombok.SneakyThrows;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestUtils extends ExtentSparkReport {

    public AmazonLoginPage amazonLoginPage;
    public SoftAssert softAssert;

    public EmailAttachment attachment;
    public MultiPartEmail email;
    Properties emailProperties;
    public String path;

    @BeforeSuite(alwaysRun = true)
    public void preConditions(ITestContext suiteName){
         path = FilePaths.EXTENTHTML_REPORT_PATH+suiteName.getCurrentXmlTest().getSuite().getName()+" "+generateDynamicName()+".html";
        initialise(path, CommonUtility.readPropertyFile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("projectName"));
        amazonLoginPage = AmazonLoginPage.getInstance();
        amazonLoginPage.webDriverLoad();
        softAssert = new SoftAssert();
        attachment = new EmailAttachment();
        email = new MultiPartEmail();
        emailProperties = CommonUtility.readPropertyFile(FilePaths.Email_CONFIG_PROPERTIES);
    }

    public static String generateDynamicName(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s-ms");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static Object getValueFromJson(String path, String regex){

        return JSONUtility.readvalueFromJSON(path, "$."+regex);
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result){
        generateReport(result);
    }


    @SneakyThrows
    @AfterSuite(alwaysRun = true)
    public void sendEmail()
    {
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(emailProperties.getProperty("seleniumMailDescription"));
        attachment.setName(emailProperties.getProperty("seleniumMailName"));
        email.setHostName(emailProperties.getProperty("host"));
        email.setSmtpPort(Integer.parseInt(emailProperties.getProperty("port")));
        email.setAuthenticator(new DefaultAuthenticator(emailProperties.getProperty("mailId"), emailProperties.getProperty("password")));
        email.setSSLOnConnect(true);
        email.setFrom(emailProperties.getProperty("mailFrom"));
        email.setSubject(emailProperties.getProperty("seleniumSuiteSubject"));
        email.setMsg(emailProperties.getProperty("seleniumMailMsg"));
        email.addTo(emailProperties.getProperty("mailTo"));
        email.attach(attachment);
        email.send();
    }
}
