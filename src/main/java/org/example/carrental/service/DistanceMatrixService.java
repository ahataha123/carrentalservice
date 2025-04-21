package org.example.carrental.service;

import org.example.carrental.dto.DistanceMatrixResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DistanceMatrixService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public DistanceMatrixResponse getDistance(String origin, String destination) {
        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                .queryParam("origins", origin)
                .queryParam("destinations", destination)
                .queryParam("key", apiKey)
                .toUriString();

        String jsonResponse = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray rows = jsonObject.getJSONArray("rows");
        JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);

        String distance = elements.getJSONObject("distance").getString("text");
        String duration = elements.getJSONObject("duration").getString("text");

        return new DistanceMatrixResponse(distance, duration);
    }
}
