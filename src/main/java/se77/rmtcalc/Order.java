package se77.rmtcalc;

public class Order implements Comparable<Order>{
    
    private int duration;
    private boolean is_buy_order;
      
    private int location_id;
    private int min_volume;
    private long order_id;
    private double price;
    private String range;
    private int system_id;
    private int type_id;
    private int volume_remain;
    private int volume_total;
    
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean isIs_buy_order() {
        return is_buy_order;
    }
    public void setIs_buy_order(boolean is_buy_order) {
        this.is_buy_order = is_buy_order;
    }
    public int getLocation_id() {
        return location_id;
    }
    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }
    public int getMin_volume() {
        return min_volume;
    }
    public void setMin_volume(int min_volume) {
        this.min_volume = min_volume;
    }
    public long getOrder_id() {
        return order_id;
    }
    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }
    public int getSystem_id() {
        return system_id;
    }
    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }
    public int getType_id() {
        return type_id;
    }
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public int getVolume_remain() {
        return volume_remain;
    }
    public void setVolume_remain(int volume_remain) {
        this.volume_remain = volume_remain;
    }
    public int getVolume_total() {
        return volume_total;
    }
    public void setVolume_total(int volume_total) {
        this.volume_total = volume_total;
    }
    @Override
    public int compareTo(Order o) {
        if (this.getPrice()== o.getPrice()) {
            return 0;
        }
        return this.getPrice() < o.getPrice() ? 1 : -1;
    }

}
