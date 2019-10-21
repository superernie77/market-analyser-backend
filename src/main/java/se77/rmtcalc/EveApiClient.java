package se77.rmtcalc;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EveApiClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    public ObjectMapper jackson;
   
    public List<Order> getOrdersByTypeAndSystem(Systems system, Items item)
            throws JsonParseException, JsonMappingException, IOException {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https").host("esi.evetech.net")
                .path("latest/markets").pathSegment(Integer.toString(system.getRegionId())).pathSegment("orders")
                .queryParam("datasource", "tranquility").queryParam("order_type", "sell")
                .queryParam("type_id", Integer.toString(item.getItemId())).build();
        RequestEntity<List<Order>> request = new RequestEntity<List<Order>>(HttpMethod.GET,
                uriComponents.encode().toUri());
//         ResponseEntity<List<Order>> response = restTemplate.exchange(request,
//         new ParameterizedTypeReference<List<Order>>() {
//         });

        Path path = Paths.get("src/main/resources/orders.json");
        List<Order> orders = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));

        return orders;// response.getBody().stream().filter( o -> o.getSystem_id() ==
                      // system.getSystemId()).sorted().collect(Collectors.toList());
    }

}
