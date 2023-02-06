package eu.glowacki.utp.assignment01;

import java.util.ArrayList;
import java.util.List;

public class MyList<A extends IAggregable<A, B> & IDeeplyCloneable<A>, B> implements IContainer{
    private final List<A> theList;
    private B b;

    public MyList(List<A> theList){
        this.theList = theList;
    }

    @Override
    public List<A> elements() {
        return theList;
    }

    @Override
    public B aggregateAllElements() {
        B tmp = null;
        for (A e : theList){
            tmp = e.aggregate(tmp);
        }
        return tmp;
    }

    @Override
    public A cloneElementAtIndex(int index) throws Exception {
        if (index < 0 || index >= theList.size()) {
            throw new IndexOutOfBoundsException("Out of bounds WOWOWOW");
        }
        return theList.get(index).deepClone();
    }


}
