import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class TestMyPESEL {
    private static final String[] validPESEL = {"97030112654"};
    private static final String[] nvalidPESEL = {"98030112664", "9a030112665", "970301126551"};
    private static final Date[] myDate = {MyTools.MyDate.getDate("1997-03-01")};
    private static final Gender[] myGender = {Gender.M};

    private static final Method _validateRegex;
    private static final Method _validateLastDigit;
    private static final Method _getBirthDateMethod;
    private static final Method _getSexMethod;

    static {
            _validateRegex = method(MyPESEL.class,"validateRegex", String.class, Boolean.TYPE);
            _validateLastDigit = method(MyPESEL.class,"validateLastDigit", String.class, Boolean.TYPE);
            _getBirthDateMethod = method(MyPESEL.class,"findDate", String.class);
            _getSexMethod = method(MyPESEL.class,"findGender", String.class);
    }

    private static <TType> Method method(Class<TType> cls, String name, Class<?> ... parameters) {
        try {
            var method = cls.getDeclaredMethod(name, parameters);
            method.setAccessible(true);
            return method;
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Test
    public void validateLastDigit() {
        try {
            for (String e : validPESEL) {
                Assert.assertTrue((boolean) _validateLastDigit.invoke(null, e,false));
            }
            Assert.assertFalse((boolean) _validateLastDigit.invoke(null, nvalidPESEL[0],false));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void validateREGEX() {
        try {
            for (String e : validPESEL) {
                Assert.assertTrue((boolean) _validateRegex.invoke(null, e,false));
            }
            Assert.assertFalse((boolean) _validateRegex.invoke(null, nvalidPESEL[1],false));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateDOB() {
        try {
            Assert.assertEquals(myDate[0], _getBirthDateMethod.invoke(null, validPESEL[0]));
            for (String e:nvalidPESEL){
                if (MyPESEL.validateRegex(e,false)){
                    Assert.assertNotEquals(myDate[0], _getBirthDateMethod.invoke(null, e));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (PESELexception peseLexception) {
            peseLexception.printStackTrace();
        }
    }

    @Test
    public void validateSex() {
        try {
            Assert.assertEquals(myGender[0], _getSexMethod.invoke(null, validPESEL[0]));
            Assert.assertNotEquals(myGender[0], _getSexMethod.invoke(null, nvalidPESEL[0]));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
