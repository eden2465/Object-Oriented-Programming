import java.util.Comparator;

public class CustomersByMaxSpeedComparator implements Comparator {
    public int compare(Object o1, Object o2){
        if (((Customer)o1).getMaxSpeed()>((Customer)o2).getMaxSpeed() )
            return 1;
        if (((Customer)o1).getMaxSpeed()<((Customer)o2).getMaxSpeed())
            return -1;
        else
            return 0;
    }
}
