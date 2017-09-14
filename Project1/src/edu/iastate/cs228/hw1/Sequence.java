package edu.iastate.cs228.hw1;

/**
 * @author Alexander Stevenson
*/

public class Sequence
{
  public char[] seqarr; // made public instead of protected for grading.

    /**
     *Takes in char array parameter, which is copied into the global seqarr variable
     * Checks to make sure all characters are valid letters, otherwise throws IllegalArgumentException
      * @param sarr char array to be copied
     */
  public Sequence(char[] sarr) throws IllegalArgumentException
  {
    seqarr = new char[sarr.length];
    for (int i= 0; i <sarr.length; i++){

        if (isValidLetter(sarr[i])){
         seqarr[i] = sarr[i];
     } else{
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
    char[] copy = new char[seqarr.length];
    for (int i= 0; i<seqarr.length; i++){
        copy[i] = seqarr[i];
    }
    return copy;
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

     //Makes sure the object is not null then checks if the classes of the objects are equal
      if (obj!=null && obj.getClass().equals(this.getClass())){
          // returns true or false, checks if the strings are equal case insensitive
          return (this.getSeq().toString().toUpperCase().equals(obj.toString().toUpperCase()));
      }
      return false;
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

    /**
     * Copies a subsection of an array, like the get subarray method
     * @param arr the array that you want to copy from
     * @param start the starting index of the array you are copying from
     * @param end the ending index of the array you are copying from
     * @return a char[] array made up of the substring of the parameter array
     */
  public char[] charrayCopy(char[] arr, int start, int end){
      //Makes a char array of the size end - start +1, this accounts for the start character index
      char[] newarr = new char[end-start +1];
      for(int i = start, j = 0; i < end; i++, j++){
          newarr[j] = arr[i];
      }
      return newarr;
  }

}
