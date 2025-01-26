public abstract class Employee implements Updatable, Comparable, Profitable {
    protected int ID;
    protected String fName;
    protected int age;
    protected char gender;
    protected char shirtSize;
    protected int firstGrant;
    protected double commRate;
    protected double commRateSum;
    protected double profit;
    protected String serviceType;


    public Employee(int ID, String fName, int age, char gender, char shirtSize) {
        this.ID = ID;
        this.fName = fName;
        this.age = age;
        if (gender == 'f' || gender == 'u' || gender == 'm')
            this.gender = gender;
        else
            throw new ImpossibleGenderException("Please insert 'f','m' or 'u' ");
        if (shirtSize == 's' || shirtSize == 'm' || shirtSize == 'l' || shirtSize == 'x')
            this.shirtSize = shirtSize;
        else
            throw new ImpossibleShirtSizeException("Please insert 's','m', 'l' or 'x' ");
    }

    //public abstract boolean update();
    public abstract int getCompanyProfit(ElectricScooter es);

    public abstract ElectricScooter giveService(ElectricScooter es, boolean quickES);

    public double getProfit() { //company's profit, not employee's profit
        return -this.commRateSum - this.firstGrant;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public abstract double getCommRate();

    public int compareTo(Object other) {
        if (this.commRateSum > ((Employee) other).commRateSum)
            return 1;
        else if (this.commRateSum < ((Employee) other).commRateSum)
            return -1;
        else
            return 0;
    }

    public int getID() {
        return this.ID;
    }
}


