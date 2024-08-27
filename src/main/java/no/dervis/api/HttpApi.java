package no.dervis.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpApi {


    public <T> List<T> getList(Class<T> clazz, String url) {
        try(HttpClient client = HttpClient.newHttpClient()) {

            // Create HttpRequest instance
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Send a GET request and get HttpResponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Get String response body
            String responseBody = response.body();

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert JSON string to List of User objects
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
