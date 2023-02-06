package eu.glowacki.utp.assignment01;

public class BankMoni extends BankFrame implements IAggregable<BankMoni, mType>, IDeeplyCloneable<BankMoni>{

    mType moni;

    public BankMoni(int m){INIT();moni = new mType(m);}
    BankMoni(int m, String t){time=t;moni = new mType(m);}

    public mType getMoni(){return moni;}

    @Override
    public mType aggregate(mType a) {
        if (a == null) {return moni;}
        moni = new mType(moni.getValue()+a.getValue());
        return moni;
    }

    @Override
    public BankMoni deepClone() {
        BankMoni tmp = new BankMoni(moni.getValue(),getTime());
        return tmp;
    }
}
