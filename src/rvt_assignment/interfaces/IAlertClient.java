package rvt_assignment.interfaces;

import rvt_assignment.models.Alert;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface IAlertClient {
    public HttpResponse<JsonNode> postAlert(Alert alert) throws UnirestException;
    public HttpResponse<JsonNode> purgeAlerts() throws UnirestException;
    public HttpResponse<JsonNode> getEventsLog() throws UnirestException;
}
