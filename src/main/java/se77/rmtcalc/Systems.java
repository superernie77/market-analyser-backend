package se77.rmtcalc;

public enum Systems {

    JITA(30000142,10000002),

    DODIXIE(30002659,10000032),

    RENS(30002510,10000030 ),

    HEK(30002053, 10000042),

    AMARR(30002187,10000043 );

    private int systemId;
    
    private int regionId;

    Systems(int systemId, int regionId) {
        this.systemId = systemId;
        this.regionId = regionId;
    }

    public int getSystemId() {
        return systemId;
    }
    
    public int getRegionId() {
        return regionId;
    }

}
