package People;

import java.util.Random;

public enum Nation {
    //Locale?
    //new Locale("th")
    //Collator.getInstance(new Locale("th")) -> "thailand"
    //
    PLN("Poland"),UKR("Ukrainian"),BLR("Belarussian"),
    SVK("Slovak"),LTU("Lithuania"),LVA("Latvian"),
    GBR("British"),IND("Indian"),CHN("Chinese"),VNM("Vietnamese");
    private final String name;
    Nation(String a){name=a;}
    public static Nation INIT_rand(){
        Nation[] out = Nation.values();
        int tmp = new Random().nextInt(out.length);
        return out[tmp];
    }
    public String toString(){return name;}
}
