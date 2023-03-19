package CommonUtilities;

import Constants.FilePaths;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentSparkReport {

    public static ExtentReports  extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test, extentLogger;


    public static void initialise(String path, String projectName)
    {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(path);
        setConfig(projectName);
    }

    public static void setConfig(String projectName){

        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Selenium_QA_Automation_Framework");
        spark.config().setReportName("AutomationReport_"+projectName);
        spark.config().setOfflineMode(true);
        extent.attachReporter(spark);
        setSystemInfo();
    }

    public static void setSystemInfo(){
        try{
            extent.setSystemInfo("SYSTEM NAME", InetAddress.getLocalHost().getHostName());
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }

    }

    public void setMethodName(Method m){
        test = extent.createTest(getClass().getSimpleName()+" : "+m.getName() + "()");
    }

    public static void generateReport(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Method is "+result.getMethod().getMethodName()+"is pass");
        }
        else if(result.getStatus() == ITestResult.SKIP)
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.YELLOW));
        }
        else if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, MarkupHelper.createCodeBlock(result.getThrowable().getMessage()));
            //test.addScreenCaptureFromBase64String(SeleniumUtilities.)
        }
        extent.flush();
    }

}

