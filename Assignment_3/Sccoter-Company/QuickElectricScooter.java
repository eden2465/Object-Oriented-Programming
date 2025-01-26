public class QuickElectricScooter extends ElectricScooter {
    private double appVersion;


    public QuickElectricScooter(int serialNumber, int price, String model, int maxSpeed, double appVersion) {
        super(serialNumber,price,model,maxSpeed);
        this.appVersion = appVersion;
    }


    public void technicalTreatment(){
        System.out.println("Thank you for coming to us");
        System.out.println("model:" +this.model +"\n"+"max speed: "+this.maxSpeed);
        System.out.println("Hope not to see you again");
    }
}
