package edu.iastate.cs228.junit;




import edu.iastate.cs228.hw1.DNASequence;
import edu.iastate.cs228.hw1.Sequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DNASequenceTest {
    @Test
    public void lowercasetest() {
        char[] arr = {'a', 'c', 'g', 't'};

        DNASequence seq = new DNASequence(arr);
    }


    @Test(expected = IllegalArgumentException.class)
    public void lowercaseAlphabeticCharacters() {
        char[] arr = {'a', 'b', 'c', 'd', 'e'};

        DNASequence seq = new DNASequence(arr);
    }


    @Test
    public void uppercasetest() {
        char[] arr = {'A', 'C', 'G', 'T'};

        DNASequence seq = new DNASequence(arr);
    }

}