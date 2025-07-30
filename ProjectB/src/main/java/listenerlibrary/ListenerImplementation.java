package listenerlibrary;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import genericlibrary.BaseConfig;

public class ListenerImplementation  implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result)  {
		//provide the steps to capture the screenshot
		//perform typecasting
		TakesScreenshot tsobj = (TakesScreenshot)BaseConfig.staticDriver;
		
		//call the screenshot method and store it into temp path
		File src = tsobj.getScreenshotAs(OutputType.FILE);
		
		//create a permanent path and store it
		
		File dest = new File("./TakesScreenshot/SwagLabs.png");
		
		//copy the path from src to dest
		
		try {
			FileHandler.copy(src,dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}