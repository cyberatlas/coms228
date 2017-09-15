package edu.iastate.cs228.junit;
import edu.iastate.cs228.hw1.ProteinSequence;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProteinSequenceTest {




    @Test
    public void invalid() {
        char[] arr = {'b', 'j', 'o', 'u', 'x'};

        ProteinSequence seq = new ProteinSequence(arr);
    }

    @Test
    public void valid() {
        char[] arr = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence seq = new ProteinSequence(arr);
    }


    @Test
    public void seqLength() {
        char[] arr = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence seq = new ProteinSequence(arr);

        assertEquals(seq.seqLength(), 5);
    }

    @Test
    public void String() {
        char[] arr = {'a', 'a', 'a', 'a', 'a'};

        ProteinSequence seq = new ProteinSequence(arr);

        assertEquals(seq.toString(), "aaaaa");
    }

    @Test
    public void equals() {
        char[] arr1 = {'a', 't', 'c', 'd', 'e'};
        char[] arr2 = {'a', 't', 'c', 'd', 'e'};

        ProteinSequence sequence1 = new ProteinSequence(arr1);
        ProteinSequence sequence2 = new ProteinSequence(arr2);

        assertEquals(sequence1.equals(sequence2), true);
    }

    
}
