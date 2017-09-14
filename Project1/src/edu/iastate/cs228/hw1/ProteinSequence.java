package edu.iastate.cs228.hw1;

/*
 * @author
*/

public class ProteinSequence extends Sequence
{
  /**
   *
   * @param psarr
   */
  public ProteinSequence(char[] psarr)
  {
    // TODO
    super(psarr);

    for (int i =0; i < psarr.length; i++){
      if(!(isValidLetter(psarr[i]))){
        throw new IllegalArgumentException("Invalid sequence letter for class" + this.getClass());
      }
      //
      //TODO I have no idea if this is right or not, I am assuming that they want the psarr to be instead of seqarr if this is true
      seqarr[i]=psarr[i];

    }
  }

  @Override
  public boolean isValidLetter(char aa)
  {

    Character.toUpperCase(aa);
    //Returns true if character arrgument is equal to one of the letters not in the set
    if ((aa =='B' || aa=='J'||aa=='O'|| aa== 'U' || aa =='X' || aa=='Z')){
      return true;
    }
    return false;
  }
}
