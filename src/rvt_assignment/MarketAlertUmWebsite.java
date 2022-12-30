package rvt_assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rvt_assignment.Constants;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;

public class MarketAlertUmWebsite {

    private final WebDriver webDriver;
    private final String baseUrl = Constants.WEBSITE_BASE_URL.value();
    private final String goodUserId = Constants.USER_ID.value();
    private final String wannabeUserId = "Fejk Juwzer";
    private final String loginPageUrl;
    private final String logoutUrl;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public String getAlertsUrl() {
        return alertsUrl;
    }

    private final String alertsUrl;

    public MarketAlertUmWebsite(WebDriver wd){
        this.webDriver = wd;
        webDriver.manage().timeouts().pageLoadTimeout(8, TimeUnit.SECONDS);
        this.loginPageUrl = String.format("%s/%s", baseUrl, "Alerts/Login");
        this.logoutUrl = String.format("%s/%s", baseUrl, "Home/Logout");
        this.alertsUrl = String.format("%s/%s", baseUrl, "Alerts/List");
        webDriver.get(baseUrl);
    }

    public void loginPage(){
        webDriver.get(loginPageUrl);

    }

    public void goodLogin(){
    	try{
	        WebDriverWait wait = new WebDriverWait(webDriver, 5);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserId"))).sendKeys(goodUserId);
	        WebElement submitBtn = webDriver.findElement(By.xpath("//input[@type='submit']"));
	        submitBtn.click();
    	}catch(TimeoutException ex){
    		System.out.println("Could not locate login text box. Are you sure you are in the login page?");
    	}
    }
    
    public void badLogin(){
    	try{
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserId"))).sendKeys(wannabeUserId);
            WebElement submitBtn = webDriver.findElement(By.xpath("//input[@type='submit']"));
            submitBtn.click();
    	}catch(TimeoutException ex){
    		System.out.println("Could not locate login text box. Are you sure you are in the login page?");
    	}

    }

    public void logout(){
        webDriver.get(logoutUrl);
    }

    public void alerts(){
        webDriver.get(alertsUrl);
    }

    public void home(){
        webDriver.get(baseUrl);
    }
    
	public static int read()
	{
		try{
		return Integer.parseInt(br.readLine());
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		return -1;
	}
	
    public void menuLoop(){
    	boolean run = true;
    	while(run){
			System.out.println("****MAIN MENU****");
			System.out.println("1. Go to home page.");
			System.out.println("2. Go to login page.");
			System.out.println("3. Go to alerts page.");
			System.out.println("4. Try a valid login");
			System.out.println("5. Try an invalid login");
			System.out.println("6. Logout");
			System.out.println("7. Terminate testing.");
			
			switch(read())
			{
			case 1: home(); break;
			case 2: loginPage(); break;
			case 3: alerts(); break;
			case 4: goodLogin(); break;
			case 5: badLogin(); break;
			case 6: logout(); break;
			case 7:run = false; break;
			}
    	}
    }
    
	public static void main(String[] args) {
		try{
			System.setProperty("webdriver.chrome.driver", Constants.WEBDRIVER_PATH.value());
			WebDriver webDriver = new ChromeDriver();
			new MarketAlertUmWebsite(webDriver).menuLoop();
			webDriver.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
