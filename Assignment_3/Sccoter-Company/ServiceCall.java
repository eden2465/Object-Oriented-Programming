public class ServiceCall implements Comparable,Profitable {
    private Customer customer;
    private Employee employee;
    private String serviceType;
    private ElectricScooter es;
    private double profit;

    public ServiceCall(Customer c, Employee e, String serviceType, ElectricScooter es) {
        this.customer = c;
        this.employee = e;
        this.serviceType = serviceType;
        this.es = es;
        this.profit = e.getCompanyProfit(es);
    }
    public ServiceCall(Customer c, Employee e, String serviceType, ElectricScooter es, boolean quick) {
        this.customer = c;
        this.employee = e;
        this.serviceType = serviceType;
        this.es = es;
        this.profit = e.getCompanyProfit(es);
    }

    public String getServiceType(){
        return this.serviceType;
    }

    public double getProfit(){
        return this.profit;
    }

    public ElectricScooter getElectricScooter(){
        return this.es;
    }


    public int compareTo(Object other) {
        if (this.profit > ((ServiceCall) other).profit)
            return 1;
        else if (this.profit < ((ServiceCall) other).profit)
            return -1;
        else
            return 0;
    }
}
