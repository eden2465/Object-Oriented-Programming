package misc;

public class WorkDay {
    public volatile static boolean endDay = false;
    private volatile static boolean lastCustomer = false;

    public synchronized static boolean setLastCustomer( boolean lastCustomer ) {
        return WorkDay.lastCustomer = lastCustomer;
    }

    public static boolean isLastCustomer() {
        return lastCustomer;
    }
}
