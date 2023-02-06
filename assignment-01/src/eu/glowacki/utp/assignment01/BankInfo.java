package eu.glowacki.utp.assignment01;

import org.junit.Assert;

public class BankInfo extends BankFrame implements IAggregable<BankInfo, String>, IDeeplyCloneable<BankInfo>{

    String name;

    public BankInfo(String n){INIT();name=new String(n);}
    BankInfo(String n, String t){time=t;name=new String(n);}

    public String getName(){return name;}


    @Override
    public String aggregate(String a) {
        if (a == null)return name;
        name += " "+a;
        return name;
    }

    @Override
    public BankInfo deepClone() {
        BankInfo tmp = new BankInfo(getName(),getTime());
        return tmp;
    }
}