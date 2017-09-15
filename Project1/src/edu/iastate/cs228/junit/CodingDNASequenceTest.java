package edu.iastate.cs228.junit;



import edu.iastate.cs228.hw1.GenomicDNASequence;
import edu.iastate.cs228.hw1.ProteinSequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodingDNASequenceTest {
   
	@Test
    public void StartCodonCheck() {
        String dna = new String("AATGCCAGTCAGCATAGCGTAGACT");

        int[] ardemo = {1, 5, 8, 10, 13, 16};

        GenomicDNASequence gdna = new GenomicDNASequence(dna.toCharArray());

        gdna.markCoding(1, 16);

        edu.iastate.cs228.hw1.CodingDNASequence cdna = new edu.iastate.cs228.hw1.CodingDNASequence(gdna.extractExons(ardemo));

        assertEquals(cdna.checkStartCodon(), true);
    }
}