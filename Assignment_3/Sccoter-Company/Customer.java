public class Customer implements Comparable, Profitable {
    private int ID;
    private String fname;
    private int age;
    private char gender;
    private boolean helmet;
    private boolean lock;
    private ElectricScooter scooter;
    private int registrationFee = 0;
    private double payments = 0; //sum of all payments' customer did for company

    public Customer(int ID, String fname, int age, char gender, boolean helmet, boolean lock, ElectricScooter scooter) {
        this.ID = ID;
        this.fname = fname;
        this.age = age;
        this.gender = gender;
        this.helmet = helmet;
        this.lock = lock;
        this.scooter = scooter;
    }

    public Customer(int ID, String fname, int age, char gender, boolean helmet, boolean lock) {
        this.ID = ID;
        this.fname = fname;
        this.age = age;
        this.gender = gender;
        this.helmet = helmet;
        this.lock = lock;
        this.scooter = null;
    }

    public double getProfit(){ //payments (price for scooter) is inside ServiceCall, not in Customer and so the cusomer pay only the registeration fee
        return this.registrationFee;
    }

    public void setRegistrationFee(){
        this.registrationFee=30;
        this.payments += this.registrationFee;
    }

    public void addPayment(double scooterPrice){
        this.payments += scooterPrice;
    }

    public int getId() {
        return this.ID;
    }

    public ElectricScooter getScooter() {
        return this.scooter;
    }

    public int compareTo(Object other) {
        if (this.payments > ((Customer)other).payments)
            return 1;
        else if (this.payments < ((Customer)other).payments)
            return -1;
        else
            return 0;
    }



    public int getMaxSpeed(){
        if(this.scooter==null)
            return 0;
        return this.scooter.getMaxSpeed();
    }
}
