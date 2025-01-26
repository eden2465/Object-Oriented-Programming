package misc;

public class SummaryDetails {
    private int priceForService;
    private String typeOfService;
    private String helpingEmployee;


    public SummaryDetails(int priceForService, String typeOfService, String helpingEmployee) {
        this.priceForService = priceForService;
        this.typeOfService = typeOfService;
        this.helpingEmployee = helpingEmployee;

    }

    @Override
    public String toString() {
        return ("price for service :" + priceForService
                +" type of service :" +typeOfService +
                " helping employee :"+ helpingEmployee );
    }

    public int getPriceForService(){
        return this.priceForService;
    }

    public String getHelpingEmployee(){
        return helpingEmployee;
    }

    public String getTypeOfService(){
        return this.typeOfService;
    }
}
