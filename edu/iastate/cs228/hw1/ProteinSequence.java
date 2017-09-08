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
    }
  }

  @Override
  public boolean isValidLetter(char aa)
  {
    // TODO
  }
}
