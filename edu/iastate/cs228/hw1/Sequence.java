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
            throw new IllegalArgumentException("Invalid sequence letter for class " + this.getClass());
        }
    }
  }

    /**
     * This method returns the length of seqarr
     * @return length of the sequence
     */
  public int seqLength()
  {
    return seqarr.length;
  }

    /**
     * Returns a copy of the character array seqarr
     * @return copy of seqarr
     */
  public char[] getSeq()
  {
    char[] copy = char[seqarr.length];
    for (int i= 0; i<seqarr.length; i++){
        copy[i] = seqarr[i];
    }
  }

    /**
     * Create the string representation of seqarr
     * @return string representation of seqarr
     */
  public String toString()
  {
     String stringrep = new String(seqarr);
     return stringrep;
  }

    /**
     * Returns true if obj not null, same type as this obj so that both obj represent the same thing in case insesitive mode
     * Should be defined in a way so that it does not need to be redefined later
     * @param obj
     * @return
     */
  public boolean equals(Object obj)
  { 

      //can compare using the following obj1.getClass().equals(obj2.getClass())
      //Then I should check what each object contains
      if (obj!=null && obj.getClass().equals(this.getClass())){
          //returns true or false, checks if the strings are equal case insensitive
          return this.getSeq().toUpperCase().equals(obj.getSeq().toUpperCase());
      }
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
