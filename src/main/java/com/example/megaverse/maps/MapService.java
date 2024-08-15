package com.example.megaverse.maps;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
    private final RestTemplate restTemplate;
    
    @Value("${base_url}")
    private String baseUrl;
    
    @Value("${candidate_id}")
    private String candidateId;

    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public AstralMap<String> getGoalMap() {
        String url = baseUrl + "/api/map/" + candidateId + "/goal";
        List<List<String>> matrix = Optional.ofNullable(restTemplate.getForObject(url, GoalMapResponse.class))
            .orElseThrow(() -> new RuntimeException("API returned null map"))
            .getGoal();
        return new AstralMap<String>(matrix);
    }

    public AstralMap<AstralObject> getCurrentMap() {
        String url = baseUrl + "/api/map/" + candidateId;        
        List<List<AstralObject>> matrix = Optional.ofNullable(restTemplate.getForObject(url, CurrentMapResponse.class))
            .orElseThrow(() -> new RuntimeException("API returned null map"))
            .getMap().getContent();
        return new AstralMap<AstralObject>(matrix);
    }
}
