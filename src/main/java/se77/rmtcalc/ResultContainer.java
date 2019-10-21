package se77.rmtcalc;

import java.util.List;
import java.util.Map;

public class ResultContainer {
    
    private Items targetItem;
    
    private Map<Systems, List<Double>> prcices;

    public Items getTargetItem() {
        return targetItem;
    }

    public void setTargetItem(Items targetItem) {
        this.targetItem = targetItem;
    }

    public Map<Systems, List<Double>> getPrcices() {
        return prcices;
    }

    public void setPrcices(Map<Systems, List<Double>> prcices) {
        this.prcices = prcices;
    }

}
