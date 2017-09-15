package edu.iastate.cs228.hw1;

/**
 * @author Alexander Stevenson
*/

public class ProteinSequence extends Sequence
{
  /**
   *
   * @param psarr
   */
  public ProteinSequence(char[] psarr)
  {

    super(psarr);


}

  /**
   * Ensures char is valid
   * @param aa checked char
   * @return false if not !valid
   */
  @Override
  public boolean isValidLetter(char aa)
  {

    Character.toUpperCase(aa);
    //Returns true if character arrgument is equal to one of the letters not in the set
    if ((aa =='B' || aa=='J'||aa=='O'|| aa== 'U' || aa =='X' || aa=='Z')){
      return false;
    }
    return true;
  }
}
