package rvt_assignment.utils;

import rvt_assignment.Constants;
import rvt_assignment.interfaces.IAlertClient;
import rvt_assignment.models.Alert;

import com.google.gson.Gson;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MarketAlertClient implements IAlertClient {

    private final String baseURL = Constants.API_BASE_URL.value();
    Gson parser = new Gson();
    //userId should be parsed from an encrypted config file for security,
    //but that is beyond the scope of this assignment.
    private final String userId = Constants.USER_ID.value();
    @Override
    public HttpResponse<JsonNode> postAlert(Alert alert) throws UnirestException {
        //Unirest.config().defaultBaseUrl(baseURL);
        return Unirest.post(baseURL + "/Alert")
                .header("Content-Type", "application/json")
                .body(parser.toJson(alert))
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> purgeAlerts() throws UnirestException {
        //Unirest.config().defaultBaseUrl(baseURL);
        return Unirest.delete(baseURL + "/Alert?userId={userId}")
                .routeParam("userId", userId).asJson();
    }

    @Override
    public HttpResponse<JsonNode> getEventsLog() throws UnirestException {
        //Unirest.config().defaultBaseUrl(baseURL);
        String endpoint = String.format(baseURL + "/EventsLog/%s", Constants.USER_ID.value());
        return Unirest.get(endpoint)
                .asJson();
    }
}
