package edu.iastate.cs228.hw1;

/**
 * @author Alexander Stevenson
*/

public class GenomicDNASequence extends DNASequence
{
  /**
   *
   */
  public boolean[] iscoding; // made public instead of private for gradsing.

  public GenomicDNASequence(char[] gdnaarr)
  {

    super(gdnaarr);

    //Creates Boolean array with the name isCoding of the same length as gdnaarr
    //By default all indeces are false
    iscoding = new boolean[seqarr.length];
    }


  /**
   * If first number greater than last, call ReverseCompliment
   * @param first
   * @param last
   */
  public void markCoding(int first, int last) throws IllegalArgumentException
{
  int slen = seqLength();
  //If first or last less than 0 throws exceptio
  if (first < 0 || last<0 || first >= slen || last >= slen ){
    throw new  IllegalArgumentException("Coding border is out of bound");
  }
  //if first greater than last, obtain coding strand by alling reverseCompliment() and transform first and last  with slen -1 -x
  if (first > last){
	reverseComplement();
    first = (slen-1-first);
    last = (slen-1-last);
  }
  //if first !> last no reverse complimentation is needed
  //set boolean array is coding to true between first and last inclusive
  for (int i =first; i <= last; i++){
    iscoding[i] = true;
  }
  }

  /**
   *Iterates through exonpos finds the length required of the concatenated array then creates new array
   * @param exonpos
   * @throws IllegalArgumentException if the int array is out of bounds, has an odd number of characters, is not in orders, or non coding positions are found
   * @return the char array of exons
   */
  public char[] extractExons(int[] exonpos)throws IllegalArgumentException {

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
      if (exonpos[i] > exonpos[Math.min(exonpos.length-1,i + 1)]){
        throw new IllegalArgumentException("Exon positions are not in order");
      }
      if(iscoding[exonpos[i]] == false)
      {
    	  	throw new IllegalArgumentException("Noncoding position is found");
      }
    }
    
    for(int i = 0; i < exonpos.length; ++i)
    {

      //The following code executes if i is 0 or even.
      if (i%2 == 0){
        //Creates a sub using the values of exonpos[i] and exonpos[i+1]
        char[] temp =  charrayCopy(seqarr, exonpos[i], Math.min(exonpos[i+1], exonpos[exonpos.length-1]));
        //loops through that array and add those characters to the arraylist

        for (int j =0 ;  j < exonpos[i+1]-exonpos[i]+1; j++){
          concat += temp[j];
        }
      }
    }
    //After everything is all said and done, the String is returned as an array
    return concat.toCharArray();
  }



}
