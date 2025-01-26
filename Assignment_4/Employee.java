package employees;

import misc.WorkDay;

public abstract class Employee extends Thread {
    protected String ID;

    public Employee (String ID){
        this.ID=ID;
    }
}
