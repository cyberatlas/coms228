package edu.iastate.cs228.junit;
import edu.iastate.cs228.hw1.GenomicDNASequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenomicDNASequenceTest{

    @Test
    public void extract() {
        String dna = new String("AATGCCAGTCAGCATAGCGTAGACT");

        int[] ardemo = {1, 5, 8, 10, 13, 16};

        GenomicDNASequence gdemo = new GenomicDNASequence(dna.toCharArray());

        gdemo.markCoding(1, 16);

        edu.iastate.cs228.hw1.CodingDNASequence cdna = new edu.iastate.cs228.hw1.CodingDNASequence(gdemo.extractExons(ardemo));

        assertEquals(cdna.toString(), "ATGCCTCAATAG");
    }
}