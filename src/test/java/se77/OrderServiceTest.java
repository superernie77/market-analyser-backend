package se77;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private EveApiClient apiClient;
    
    @Autowired
    private ObjectMapper jackson;
        
    @Test
    public void testGetOrders() throws JsonParseException, JsonMappingException, IOException {
        List<Order> orders = apiClient.getOrdersByTypeAndSystem(Systems.JITA, Items.PLEX);
        
        assertThat(orders, notNullValue());
    }
    
    @Test
    public void testPriceDiffMatrix() throws Exception {
        List<Systems> systems = Arrays.asList(Systems.AMARR,Systems.DODIXIE,Systems.HEK, Systems.JITA, Systems.RENS);
        Map<Systems, List<Double>> result = orderService.getPriceDiffPercentMatrix(Items.PLEX, systems);
        
        assertThat(result.size(), is(5));
        assertThat(result.get(Systems.AMARR), notNullValue());
        assertThat(result.get(Systems.AMARR).size(), is(5));
    }
    
    @Test
    public void testOrderSorting() throws Exception  {
        Path path = Paths.get("src/test/resources/orders.json");
        List<Order> orders = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));

        double maxPrice = orders.stream().sorted().collect(Collectors.toList()).get(0).getPrice();
        assertThat(maxPrice, is(666999.0));
        
    }
    
    private List<Order> orders_jita;
    
    private List<Order> orders_hek;
    
    private List<Order> orders_amarr;
    
    private List<Order> orders_rens;
    
    private List<Order> orders_dodixie;
    
    @PostConstruct
    public void setup() throws Exception
    {
        Path path = Paths.get("src/test/resources/orders_jita.json");
        orders_jita = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));
        
        path = Paths.get("src/test/resources/orders_hek.json");
        orders_hek = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));
        
        path = Paths.get("src/test/resources/orders_amarr.json");
        orders_amarr = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));
        
        path = Paths.get("src/test/resources/orders_rens.json");
        orders_rens = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));
        
        path = Paths.get("src/test/resources/orders_dodixie.json");
        orders_dodixie = jackson.readValue(Files.newInputStream(path),
                jackson.getTypeFactory().constructCollectionType(List.class, Order.class));
        
        EveApiClient client =  Mockito.mock(EveApiClient.class);
        Mockito.when(client.getOrdersByTypeAndSystem(Systems.JITA, Items.PLEX)).thenReturn(orders_jita);
        Mockito.when(client.getOrdersByTypeAndSystem(Systems.AMARR, Items.PLEX)).thenReturn(orders_amarr);
        Mockito.when(client.getOrdersByTypeAndSystem(Systems.DODIXIE, Items.PLEX)).thenReturn(orders_dodixie);
        Mockito.when(client.getOrdersByTypeAndSystem(Systems.HEK, Items.PLEX)).thenReturn(orders_hek);
        Mockito.when(client.getOrdersByTypeAndSystem(Systems.RENS, Items.PLEX)).thenReturn(orders_rens);
        
        orderService.setApiClient(client);
    }
    

}
