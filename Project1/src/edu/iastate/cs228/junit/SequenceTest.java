package edu.iastate.cs228.junit;



import edu.iastate.cs228.hw1.Sequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceTest {
    @Test
    public void lowercases() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        Sequence sequence = new Sequence(arr);
    }

    @Test
    public void uppercass() {
        char[] arr = {'A', 'B', 'C', 'D', 'E'};

        Sequence sequence = new Sequence(arr);
    }

}