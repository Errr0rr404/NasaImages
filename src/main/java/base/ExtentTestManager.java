package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
/**
 * I'm not the author of this class. Took help of the extent report to complete designing this class :)
 */
public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    private static ExtentReports extent;
    private static ITestContext context;

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }


    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    public static void log(final String message, Class clas) {
        Logger logger = Logger.getLogger(clas);
        logger.info(message);
        Reporter.log(message, true);
        ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");
    }

    public static void log(final StringUtils message) {
        Reporter.log(message + "<br>", true);
        ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");
    }

    public synchronized static ExtentReports getInstance() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(cal.getTime());

        if (extent == null) {
            File outputDirectory = new File(context.getOutputDirectory());
            File resultDirectory = new File(outputDirectory.getParentFile(), "html");
            extent = new ExtentReports(System.getProperty("user.dir") + "/Execution_Report/Report_" + date + ".html", true);
            Reporter.log("Extent Report Directory" + resultDirectory, true);
            extent.addSystemInfo("Host Name", "HostName").addSystemInfo("Environment",
                    "QA").addSystemInfo("User Name", "NASA");
            extent.loadConfig(new File(System.getProperty("user.dir") + "/report-config.xml"));
        }
        return extent;
    }

    public static void setOutputDirectory(ITestContext context) {
        ExtentTestManager.context = context;
    }

}