package rvt_assignment;

import rvt_assignment.interfaces.IAlertClient;
import rvt_assignment.models.Alert;
import rvt_assignment.models.AlertType;
import rvt_assignment.models.MarketAlertEvent;
import rvt_assignment.models.UploadAlertResponse;
import rvt_assignment.utils.MarketAlertClient;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
//import org.openqa.selenium.json.TypeToken;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MarketAlertUmAPI {
    private IAlertClient client;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public MarketAlertUmAPI(IAlertClient client){
        this.client = client;
    }

    public IAlertClient getClient() {
        return client;
    }

    public void setClient(IAlertClient client) {
        this.client = client;
    }

    public UploadAlertResponse postAlert(Alert alert) throws UnirestException{
        HttpResponse<JsonNode> response = client.postAlert(alert);

        Gson parser = new Gson();
        String jsonString = response.getBody().toString();
        return parser.fromJson(jsonString, UploadAlertResponse.class);
    }

    public boolean purgeAlerts() throws UnirestException{
        HttpResponse<JsonNode> response = client.purgeAlerts();
        return response.getStatus() == 200;
    }
    
    public ArrayList<MarketAlertEvent> getEventsLog() throws UnirestException{
        HttpResponse<JsonNode> response = client.getEventsLog();

        Gson parser = new Gson();
        Type eventsListType = new TypeToken<ArrayList<MarketAlertEvent>>(){}.getType();
        String jsonString = response.getBody().toString();
        return parser.fromJson(jsonString, eventsListType);
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
	
    public void menuLoop() throws UnirestException{
    	boolean run = true;
    	while(run){
			System.out.println("****MAIN MENU****");
			System.out.println("1. Upload an Alert.");
			System.out.println("2. Purge Alerts.");
			System.out.println("3. Terminate testing.");
			
			switch(read())
			{
			case 1: postAlert(
					new Alert(
							AlertType.ELECTRONICS.value(),
							"Jumper Windows 11 Laptop",
							"Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD",
							"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth",
							"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg",
							Constants.USER_ID.value(),
							24999)
					); 
					break;
			case 2: purgeAlerts(); break;
			case 3:run = false; break;
			}
    	}
    }
    
	public static void main(String[] args) {
		try{
			MarketAlertUmAPI api = new MarketAlertUmAPI(new MarketAlertClient());
			//Clean the system before starting
			api.purgeAlerts();
			// Clear out the events log from events that did not occur during testing
			//(example the previous purge)
			api.getEventsLog();
			api.menuLoop();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
