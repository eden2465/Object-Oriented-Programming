
public class SalesEmployee extends Employee{


    public SalesEmployee(int ID, String fName, int age, char gender, char shirtSize, double commRate) {
        super(ID, fName, age, gender, shirtSize);
        this.commRate = commRate;
        setFirstGrant();
        serviceType = "sales";
    }

    public void setFirstGrant() {
        switch (shirtSize) {
            case 's':
                firstGrant = 10;
                break;
            case 'm':
                firstGrant = 15;
                break;
            case 'l':
                firstGrant = 20;
                break;
            case 'x':
                firstGrant = 25;
                break;
        }
    }



    public ElectricScooter sellScooter(boolean QuickES) {
        ElectricScooter es = Company.getMinPriceElectricScooter(QuickES); //get the minimum price scooter
        Company.removeFromList(es); // remove it from the db
        this.commRateSum += commRate*es.getPrice();
        return es;
    }

    public ElectricScooter sellScooter() {
        ElectricScooter es = Company.getMinPriceElectricScooter();
        Company.removeFromList(es);
        this.profit += es.getPrice();
        this.commRateSum += commRate*es.price; //profit for SalesEmployee
        return es;
    }

    public ElectricScooter giveService(ElectricScooter es, boolean quickES){
        if (es == null)
            return sellScooter();
        return sellScooter(quickES);
    }

    public int getCompanyProfit(ElectricScooter es){
        return es.getPrice();
    }

    public double getCommRate() {
        return this.commRate;
    }

    public boolean update() {
        if (commRate * 2 <= commRateSum) {
            if (commRate * 1.02 < 30) {
                commRate = commRate * 1.02;
                return true;
            }
        }
        return false;
    }


}

