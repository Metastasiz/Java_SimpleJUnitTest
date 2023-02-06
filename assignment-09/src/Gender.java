public enum Gender {
    M("Male"), F("Female");
    private final String gender;
    public static Gender randThis(){
        Gender[] out = Gender.values();
        return out[MyTools.myRandom(0,out.length-1)];
    }
    Gender(String gender){this.gender=gender;}
    @Override
    public String toString(){return gender;}
}
