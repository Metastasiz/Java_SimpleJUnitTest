package People;

import java.util.Random;

public enum AcademicDeg {
    Prof("Professor. "),Dr("Doctor. "),TA("Teacher Assistant ");
    private final String name;
    AcademicDeg(String a){name=a;}
    public static AcademicDeg INIT_rand(){
        AcademicDeg[] out = AcademicDeg.values();
        int tmp = new Random().nextInt(out.length);
        return out[tmp];
    }
    public String toString(){return name;}
}
