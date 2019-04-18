package base.reporting;

import com.relevantcodes.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentManager {

    public static String environment = null;
    private static ExtentReports extent;
    private static ITestContext context;

    //Change your HostName UserName, Env
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
                    "Env").addSystemInfo("User Name", "UserName");
            extent.loadConfig(new File(System.getProperty("user.dir") + "/report-config.xml"));
        }
        return extent;
    }

    public static void setOutputDirectory(ITestContext context) {
        ExtentManager.context = context;
    }

}