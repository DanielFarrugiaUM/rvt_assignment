package rvt_assignment.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import rvt_assignment.interfaces.IAlertClient;
import rvt_assignment.models.MarketAlertEvent;

public class ApiChecker {
	private IAlertClient client;
	
    public ApiChecker(IAlertClient client){
        this.client = client;
    }
    
    public ArrayList<MarketAlertEvent> getEventsLog() throws UnirestException{
        HttpResponse<JsonNode> response = client.getEventsLog();

        Gson parser = new Gson();
        Type eventsListType = new TypeToken<ArrayList<MarketAlertEvent>>(){}.getType();
        String jsonString = response.getBody().toString();
        return parser.fromJson(jsonString, eventsListType);
    }
}
