package se77.rmtcalc;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersRestController {

    @Autowired
    private OrderService service;

    @GetMapping("/diffs/{itemId}")
    public ResultContainer getPriceDiffs(@PathVariable("itemId") Integer itemId) throws Exception {
        ResultContainer cont = new ResultContainer();
        for (Items item : Items.values()) {
            if (item.getItemId() == itemId) {
                cont.setTargetItem(item);
                Map<Systems, List<Double>> prices = service.getPriceDiffPercentMatrix(item,
                        Arrays.asList(Systems.values()));
                cont.setPrcices(prices);
            }
        }
        return cont;
    }
}
