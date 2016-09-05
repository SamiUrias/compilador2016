package Test;

import org.junit.Test;
import Automata.Subset;
import static org.junit.Assert.*;

/**
 * Created by Samuel on 05/09/2016.
 */
public class SubsetTest {


    @Test
    public void testEquals() throws Exception {
        //Se crean dos subsets para comparar el equals
        Subset subset1 = new Subset();
        Subset subset2 = new Subset();

        subset1.add(1);
        subset1.add(2);
        subset1.add(3);

        subset2.add(1);
        subset2.add(2);
        subset2.add(3);
        subset2.add(4);
        System.out.println(subset1.equals(subset2));

    }

    @Test
    public void testToString() throws Exception {
        //Se crean dos subsets para comparar el equals
        Subset subset1 = new Subset();

        subset1.add(1);
        subset1.add(2);
        subset1.add(3);
        System.out.println(subset1.toString());

    }
}