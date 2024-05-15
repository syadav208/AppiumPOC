package TestUtils;

import java.io.IOException;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.RSA.AppiumFrameworkDesign.utils.AppiumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	ExtentTest test;
	
 ExtentReports extent =  ExtentReporterNG.getReporterObject();
 AppiumDriver driver;
	
		   @Override
		    public void onTestStart(ITestResult result) {
		        // Executed when a test method starts
			  test = extent.createTest(result.getMethod().getMethodName());
		        System.out.println("Test started: " + result.getName());
		    }

		    @Override
		    public void onTestSuccess(ITestResult result) {
		        // Executed when a test method is successful
		    	test.log(Status.PASS, "Test Passed");
		        System.out.println("Test passed: " + result.getName());
		    }

		    @Override
		    public void onTestFailure(ITestResult result) {
		        // Executed when a test method fails
		    	test.fail(result.getThrowable());
		    	try {
		    	driver=(AppiumDriver) result.getTestClass().getRealClass().getField("driver")
		    	.get(result.getInstance());
		    	}catch(Exception e1) {
		    		e1.printStackTrace();
		    	}try {
		    		
		        test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		    }
		    	catch(IOException e) {
		    		e.printStackTrace();
		    	}
		    	}

		    @Override
		    public void onTestSkipped(ITestResult result) {
		        // Executed when a test method is skipped
		        System.out.println("Test skipped: " + result.getName());
		    }

		    @Override
		    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		        // Executed when a test method fails but is within success percentage
		        System.out.println("Test failed but within success percentage: " + result.getName());
		    }

		    @Override
		    public void onStart(ITestContext context) {
		        // Executed before the test suite starts
		        System.out.println("Test suite started: " + context.getName());
		    }

		    @Override
		    public void onFinish(ITestContext context) {
		        // Executed after the test suite finishes
		        System.out.println("Test suite finished: " + context.getName());
		        extent.flush();
		    }
}
