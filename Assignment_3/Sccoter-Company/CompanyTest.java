import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private Company company;

    @BeforeEach
    void createCompany(){
        company = new Company("src\\inputFiles\\ES.txt","src\\inputFiles\\Customers.txt","src\\inputFiles\\Employees.txt","src\\inputFiles\\SC.txt");
    }

    @Test
    void addCustomer(){
        Customer customer = new Customer(19191919, "Chen", 24, 'm', false, false);
        company.addCustomer(customer);
    }

    @Test
    void getMinEmployeeCommRate(){
        Employee employee = company.getMinEmployeeCommRate();
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employee.getCommRate(),0.011634076 );
    }
    @Test
    void readingCustomersInputFile(){
    }
    @Test
    void totalRevenues() {

    }

    @Test
    void getMin() {
    }

}