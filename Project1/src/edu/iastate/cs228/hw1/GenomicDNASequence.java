package edu.iastate.cs228.hw1;

/*
 * @author Alexander Stevenson
*/

public class GenomicDNASequence extends DNASequence
{
  public boolean[] iscoding; // made public instead of private for gradsing.

  public GenomicDNASequence(char[] gdnaarr)
  {

    super(gdnaarr);
//    for (int i=0; i<gdnaarr.length; i++){
//      if (!(isValidLetter(gdnaarr[i]))){
//        throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.GeonomicDNASequence");
//      }
    iscoding = new boolean[seqarr.length];
    }
    //Creates Boolean array with the name isCoding of the same length as gdnaarr
    //By default all indeces are false
    
 // }

  public void markCoding(int first, int last)
{
  int slen = seqLength();
  //If first or last less than 0 throws exceptio
  if (first < 0 || last<0 || first >= slen || last >= slen ){
    throw new  IllegalArgumentException("Coding border is out of bound");
  }
  //if first greater than last, obtain coding strand by alling reverseCompliment() and transform first and last  with slen -1 -x
  if (first > last){
    first = (slen-1-first);
    last = (slen-1-last);
    reverseComplement();
  }
  //if first !> last no reverse complimentation is needed
  //set boolean array is coding to true between first and last inclusive
  for (int i =first; i <= last; i++){
//    iscoding[i] = (i >= first && i <= last) ? true : false;
    iscoding[i] = true;
  }
  }

  public char[] extractExons(int[] exonpos) {
    // TODO
    //concat is basically used as an Arraylist, holding
    String concat = "";
    //throws IllegalArgumentException if exonpos is 0 or odd
    if (exonpos.length == 0 || exonpos.length % 2 != 0) {
      throw new IllegalArgumentException("Empty array or odd number of elements");
    }
    for (int i =0; i < exonpos.length; i++){
      if (exonpos[i]<0 || exonpos[i] >= seqLength()){
        throw new IllegalArgumentException("Exon position is out of bound");
      }
      if (i != exonpos.length && exonpos[i] > exonpos[i+1]){
        throw new IllegalArgumentException("Exon positions are not in order");
      }

      //TODO find out how many instances count them and go through the array again. TA will clarify and get back to me

      //how to throw new the illegal state exception?
      //The following code executes if i is 0 or even.
      if (i%2 == 0){
        //Creates a sub using the values of exonpos[i] and exonpos[i+1]
        char[] temp =  charrayCopy(seqarr, exonpos[i], exonpos[i+1]);
        //loops through that array and add those characters to the arraylist
        //TODO Fix this damn loop!
        for (int j =0 ;  j < exonpos[i+1]-exonpos[i]; j++){
          concat += temp[j];
        }
      }
    }
    //After everything is all said and done, the Arraylist is returned as an array
    return concat.toCharArray();
  }



}
