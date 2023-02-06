public class MyTools {
    public static int myRandom(int lower, int upper){
        int out = (int)(Math.random()*(1+upper-lower))+lower;
        return out;
    }
}
