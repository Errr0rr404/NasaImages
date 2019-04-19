package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SetupFactory {
    //Extent Report Setup
    public static ExtentReports extent;
    public static String platform = null;
    public static WebDriver webDriver;
    public static SoftAssert softAssert = new SoftAssert();
    //screenshot
    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);
        String destination = null;
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + df.format(date) + ".png";
            FileUtils.copyFile(file, new File(destination));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return destination;
    }


    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentTestManager.setOutputDirectory(context);
        extent = ExtentTestManager.getInstance();
    }


    //Driver Initialization
    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "TEST CASE PASSED : " + result.getName());
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED : " + result.getName());
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST CASE SKIPPED : " + result.getName());
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(webDriver, result.getName());
        }
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @Parameters({"ExecutionPlatform", "url", "browserName", "os"})
    @BeforeMethod
    public void setUp(String ExecutionPlatform,
                      String url, String browserName,
                      String os) {
        platform = ExecutionPlatform;
        if (ExecutionPlatform.equalsIgnoreCase("Web")) {
            getLocalDriver(os, browserName);
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            webDriver.get(url);
            webDriver.manage().window().maximize();
        }


    }


    public WebDriver getLocalDriver(String OS, String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            if (OS.equalsIgnoreCase("Mac")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            }
            webDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("Mac")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            }
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    @AfterMethod
    public void quit() {
        if (platform.equalsIgnoreCase("web")) {
            webDriver.close();
            webDriver.quit();
        }
    }

}

