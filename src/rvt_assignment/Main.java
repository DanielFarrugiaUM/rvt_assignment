package rvt_assignment;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.chrome.ChromeDriver;

import com.mashape.unirest.http.exceptions.UnirestException;

import rvt_assignment.models.AlertType;
import rvt_assignment.models.Alert;
import rvt_assignment.models.UploadAlertResponse;
import rvt_assignment.utils.MarketAlertClient;

public class Main {

	public static void main(String[] args) throws UnirestException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", Constants.WEBDRIVER_PATH.value());
		//WebDriver driver = new ChromeDriver();
		
		//driver.navigate().to("http://www.google.com");
		
        MarketAlertUmAPI sys = new MarketAlertUmAPI(new MarketAlertClient());

        Alert alert = new Alert();
        alert.setAlertType(AlertType.ELECTRONICS.value());
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPostedBy(Constants.USER_ID.value());
        alert.setPriceInCents(24999);

        @SuppressWarnings("unused")
		UploadAlertResponse response = sys.postAlert(alert);

        System.out.println();

	}

}
