import java.lang.Math;

public class TechnicalEmployee extends Employee {


    public TechnicalEmployee(int ID, String fName, int age, char gender, char shirtSize) {
        super(ID, fName, age, gender, shirtSize);
        this.commRate = 5;
        setFirstGrant();
        serviceType = "Technical";
    }

    public void setFirstGrant() {
        switch (shirtSize) {
            case 's':
                firstGrant = 30;
                break;
            case 'm':
            case 'l':
                firstGrant = 35;
                break;
            case 'x':
                firstGrant = 40;
                break;
        }
    }

    public double getCommRate() {
        return this.commRate;
    }

    public int getCompanyProfit(ElectricScooter es){
        return technicalService(es);
    }

    public ElectricScooter giveService(ElectricScooter es, boolean quickES){
        return es;
    }

    public int technicalService(ElectricScooter es) {
        int companyProfit=0;
        if (es == null)
            return 0;
        es.technicalTreatment();
        double p = Math.random();
        if (p < 0.3)
            companyProfit += 10;
        else if (p < 0.5)
            companyProfit += 30;
        else if (p < 0.9)
            companyProfit += 80;
        else
            companyProfit = 120;
        return companyProfit;
    }

    public boolean update() {
        if (commRate * 1.5 <= commRateSum) {
            this.commRate = this.commRateSum + 2;
            return true;
        }
        return false;
    }

}
