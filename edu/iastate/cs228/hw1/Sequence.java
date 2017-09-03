package edu.iastate.cs228.hw1;

/*
 * @author
*/

public class Sequence
{
  public char[] seqarr; // made public instead of protected for grading.

  public Sequence(char[] sarr)
  {
    // TODO seqarr = new char[] the length - sarr.length
    seqarr = new char[sarr.length];
    for (int i= 0; i <sarr.length; i++){

        //TODO If is false throw specified error, if true add character to seqarr
        if (isValidLetter(sarr[i])){
         seqarr(i) = sarr(1);
     } else{
            //TODO find out what class I need to throw from instad of the one I have defined
            throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.Sequence");
        }
    }
  }

    /**
     * This method returns the length of the squence
     * @return length of the sequence
     */
  public int seqLength()
  {
    // TODO
  }
  
  public char[] getSeq()
  {
    // TODO
  }

  public String toString()
  {
    // TODO
  }

  public boolean equals(Object obj)
  { 
    // TODO
  }

  /**
   *
   * @param let the character to validate
   * @return
   */
  public boolean isValidLetter(char let)
  {
    // returns true or false depending on if the character is uppercase or lowercase
   return Character.isLowerCase(let) || Character.isLowerCase(let);
  }

}
