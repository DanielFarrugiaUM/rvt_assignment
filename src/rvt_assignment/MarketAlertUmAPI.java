package rvt_assignment;

import rvt_assignment.interfaces.IAlertClient;
import rvt_assignment.models.Alert;
import rvt_assignment.models.MarketAlertEvent;
import rvt_assignment.models.UploadAlertResponse;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
//import org.openqa.selenium.json.TypeToken;
import com.google.gson.reflect.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MarketAlertUmAPI {
    private IAlertClient client;

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
}
