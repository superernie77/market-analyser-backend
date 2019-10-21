package se77.rmtcalc;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private EveApiClient apiClient;

    public double getMaxPrice(Systems system, Items item) throws Exception {
        return apiClient.getOrdersByTypeAndSystem(system, item).get(0).getPrice();
    }
    
    public Map<Systems, List<Double>> getPriceDiffPercentMatrix(Items item, List<Systems> systems) throws Exception{
        Map<Systems, List<Double>> systemPriceDiffs = new HashMap<>();
        
        Map<Systems, Double> prices = new HashMap<Systems, Double>();
        
        // query max prices for all systems
        for (Systems system : systems) {
            double price = getMaxPrice(system, item);
            prices.put(system, price);
        }
        
        // calculat price diff in percentage for all systems in a matrix
        for (Systems system : systems) {
            List<Double> priceDiffs = new ArrayList<Double>();
            systemPriceDiffs.put(system, priceDiffs);
            for (Systems target : systems) {
                // get prices
                double maxPrice1 = prices.get(system);
                double maxPrice2 = prices.get(target);
                double diff = maxPrice2 - maxPrice1;
                
                // calc percentage
                double percentDiff = (diff / maxPrice1) * 100;
                priceDiffs.add(percentDiff);
            }
        }
        return systemPriceDiffs;
    }
    
    public EveApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(EveApiClient apiClient) {
        this.apiClient = apiClient;
    }
}