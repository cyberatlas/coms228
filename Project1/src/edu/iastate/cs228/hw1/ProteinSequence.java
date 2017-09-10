package edu.iastate.cs228.hw1;

/*
 * @author
*/

public class ProteinSequence extends Sequence
{
  public ProteinSequence(char[] psarr)
  {
    // TODO
    super();
    for (int i =0; i < psarr.length; i++){
      if!(isValidLetter(psarr[i])){
        throw IllegalArgumentException("Invalid sequence letter for class" + this.getClass());
      }
      //
      //TODO I have no idea if this is right or not, I am assuming that they want the psarr to be instead of seqarr if this is true
      seqarr[i]=psarr[i];

    }
  }

  @Override
  public boolean isValidLetter(char aa)
  {

    aa.toUpperCase();
    //Returns true if character arrgument is equal to one of the letters not in the set
    if !(aa =='B' || aa=='J'||aa=='O'|| aa== 'U' || aa =='X' || a=='Z'){
      return true;
    }
    return false;
  }
}
