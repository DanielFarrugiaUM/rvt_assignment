package rvt_assignment;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", Constants.WEBDRIVER_PATH.value());
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://www.google.com");

	}

}
