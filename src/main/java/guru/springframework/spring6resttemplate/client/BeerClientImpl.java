package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String BEER_PATH = "/api/v1/beer";

    private final RestTemplateBuilder restTemplateBuilder;
    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> stringResponse = restTemplate.getForEntity(
                BASE_URL + BEER_PATH, String.class
        );

        ResponseEntity<Map> mapResponse = restTemplate.getForEntity(
                BASE_URL + BEER_PATH, Map.class
        );

        ResponseEntity<JsonNode> jsonResponse = restTemplate.getForEntity(
                BASE_URL + BEER_PATH, JsonNode.class
        );

        jsonResponse.getBody().findPath("_embedded").findPath("beer")
                .elements().forEachRemaining(
                        jsonNode -> {
                            System.out.println(jsonNode.get("beerName").asText());
                        });

        //System.out.println(stringResponse.getBody());
        return null;


    }
}
