import java.util.*;


public class Company {
    private static List<Customer> customersList = new ArrayList<>();
    private static List<Employee> employeesList = new ArrayList<>();
    private static List<ElectricScooter> scootersList = new ArrayList<>();
    private static List<ServiceCall> serviceCallsList = new ArrayList<>();
    //private static List<ElectricScooter> electricScootersList = new ArrayList<>();//double init
    //private static List<QuickElectricScooter> quickScootersList = new ArrayList<>();//double init


    public Company(String ES, String customers, String employees, String serviceCalls) {
        scootersList = fileReader.readObjectT(ES, ElectricScooter.class); //scooters must be first in order to customers to get their scooter
        customersList = fileReader.readObjectT(customers, Customer.class); //must be after scooter
        employeesList = fileReader.readObjectT(employees, Employee.class);
        serviceCallsList = fileReader.readObjectT(serviceCalls, ServiceCall.class); //must be last so we can call to customers, employees, scooters

    }

    public static ElectricScooter getScooter(int ES_number) {
        for (ElectricScooter es : scootersList)
            if (es.getSerialNumber() == ES_number)
                return es;
        return null;
    }

    public static Customer getCustomer(int customerID) {
        for (Customer c : customersList)
            if (c.getId() == customerID)
                return c;
        return null;
    }

    public static Employee getEmployee(int employeeID) {
        for (Employee e : employeesList)
            if (e.getID() == employeeID)
                return e;
        return null;

    }


    public void addCustomer(Customer c) {
        customersList.add(c);
        c.setRegistrationFee();
    }

    public void addElectricScooter(ElectricScooter es) {
        scootersList.add(es);
    }

    public void addEmployee(Employee e) {
        employeesList.add(e);

    }

    public boolean serviceForCustomer(int customerID, String serviceType, boolean quickES) {
        Customer c = checkId(customerID);
        if (c == null)
            return false;
        if (!serviceType.equals("Sales") && !serviceType.equals("Technical"))
            return false;
        Employee serviceGiver = getMinEmployeeCommRate(serviceType);
        serviceGiver.giveService(c.getScooter(), quickES);
        ServiceCall newCall = new ServiceCall(c, serviceGiver, serviceType, c.getScooter());
        c.addPayment(newCall.getProfit());
        serviceCallsList.add(newCall);
        return true;
    }

    public double purchasedQuickScooterRatio() {
        int quickScooterSum = 0;
        int scooterSalesSum = 0;
        for (ServiceCall sc : serviceCallsList) {
            if (sc.getServiceType().equals("Sales")) {
                scooterSalesSum += 1;
                if (sc.getElectricScooter() instanceof QuickElectricScooter)
                    quickScooterSum += 1;
            }
        }
        return quickScooterSum / scooterSalesSum;
    }

    public static double totalRevenues() {
        double sum = 0;
        for (ServiceCall sc : serviceCallsList)
            sum += sc.getProfit();
        for (Customer customer : customersList)
            sum += customer.getProfit();
        for (Employee employee : employeesList) //employee's Profit = company lost
            sum += employee.getProfit();
        return sum;
    }

    public static <T extends Comparable<? super T>> T getMin(List<T> list) {
        return Collections.min(list);
    }

    public static ElectricScooter getMinPriceElectricScooter(){
        return getMin(scootersList);
    }
    public static ElectricScooter getMinPriceElectricScooter(boolean quickES){
        scootersList.sort(ElectricScooter::compareTo);
        for (ElectricScooter es : scootersList)
            if (quickES)
                if (es instanceof QuickElectricScooter)
                    return es;
            else
                if (!(es instanceof QuickElectricScooter))
                    return es;
        return null;
    }

    public static int updatedRates(List<Updatable> listUpdatable) {
        int counter = 0;
        for (Updatable item : listUpdatable) {
            if (item.update())
                counter++;
        }
        return counter;
    }



    public static void removeFromList(ElectricScooter es) {
        scootersList.remove(es);
    }


    public static Customer checkId(int id) {
        for (Customer c : customersList) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    public static Employee getMinEmployeeCommRate(String serviceType) {
        double commRateMax = Double.MAX_VALUE;
        Employee employeeMinCommRate = null;
        for (Employee employee : employeesList) {
            if (employee.getCommRate() < commRateMax && serviceType.equals(employee.getServiceType())) {
                commRateMax = employee.getCommRate();
                employeeMinCommRate = employee;
            }
        }
        return employeeMinCommRate;
    }
}

