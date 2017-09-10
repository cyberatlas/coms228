package edu.iastate.cs228.hw1;

/*
 * @author
*/

public class GenomicDNASequence extends DNASequence
{
  public boolean[] iscoding; // made public instead of private for grading.

  public GenomicDNASequence(char[] gdnaarr)
  {
    // TODO
    super();
    for (i=0; i<gdnaarr.length; i++){
      if !(gdnaarr.isValidLetter()){
        throw IllegalArgumentArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.GeonomicDNASequence");
      }
    }
    //Creates Boolean array with the name isCoding of the same length as gdnaarr
    //By default all indeces are false
    boolean[] isCoding = new boolean[gdnaarr.length];
  }

  public void markCoding(int first, int last)
  {
    // TODO
    int slen = seqLength();
    //If first or last less than 0 throws exception
    if (first < 0 || last<0 || first >= slen || last >= slen ){
      throw IllegalArgumentException("Coding border is out of bound")
    }
    //if first greater than lasdt, obtain coding strand by alling reverseCompliment() and transform first and last  with slen -1 -x
    if (first > last){
      first = (slen-1-first);
      last = (slen-1-last);
      reverseComplement();
    }
    //if first !> last no reverse complimentation is needed
    //set boolean array is coding to true between first and last inclusive
    for (int i =0; i < iscoding.length; i++){
      iscoding[i] = (i >= first && i <= last) ? true : false;
    }
  }

  public char[] extractExons(int[] exonpos) {
    // TODO
    //Creating the arraylist that will be used later
    Arraylist<Character> concat = new ArrayList<>();
    //throws IllegalArgumentException if exonpos is 0 or odd
    if (exonpos.length() == 0 || exonpos.length % 2 != 0) {
      throw IllegalArgumentException("Empty array or odd number of elements");
    }
    for (int i =0; i < exonpos.length; i++){
      if (exonpos[i]<0 || exonpos[i] >= seqLength()){
        throw IllegalArgumentException("Exon position is out of bound");
      }
      if (i != exonpos.length && exonpos[i] > exonpos[i+1]){
        throw IllegalArgumentException("Exon positions are not in order");
      }
      //TODO
      //how to throw the illegal state exception?
      //The following code executes if i is 0 or even.
      if (i%2 == 0){
        //Creates a sub using the values of exonpos[i] and exonpos[i+1]
        char[] temp =  Array.CopyOfRange(seqarr, exonpos[i], exonpos[i+1]);
        //loops through that array and add those characters to the arraylist
        for (int j = exonpos[i]; j < exonpos[i+1]; j++){
          concat.add(te mp[j]);
        }
      }
    }
    //After everything is all said and done, the Arraylist is returned as an array
    return concat.toArray();
  }



}
