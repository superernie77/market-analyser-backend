package se77.rmtcalc;

public enum Items {

    PLEX(44992),

    MPTC(34133),

    EXTR(40519),

    LINJECTOR(40520);

    private int itemId;

    Items(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

}
