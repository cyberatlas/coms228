package edu.iastate.cs228.hw1;

/*
 * @author Alexander Stevenson
*/

public class DNASequence extends Sequence
{



  public DNASequence(char[] dnaarr) throws IllegalArgumentException
  {
    super(dnaarr);
    //Iterate through and make sure all the characters in the array are valid
    for (int i = 0; i < dnaarr.length; i++){
      if (!(isValidLetter(dnaarr[i]))) {
        throw new IllegalArgumentException("Invalid sequence for class " + this.getClass());
      }
    }
    //TODO
    // "The constructor saves a copy of the character array argument in the field of it's superclass"
    //Should be copying dnarr and saving it as char[] copy
    //char[] copy = charrayCopy(dnaarr, 0, dnaarr.length);
  }

  /**
   * Checks to make sure only valid DNA characters are used
   * @param let is the character that is being compared
   * @return true if the character is acceptable
   */
  @Override
  public boolean isValidLetter(char let)
  {
    //Created a temperorary character which is the capital version of let for ease of comparison
    char temp = Character.toUpperCase(let);
    //Should return true if one of the characters is A C G or T or their lowercase counterparts
    return (temp == 'A' || temp == 'C' || temp == 'G' || temp == 'T');
  }

  /**
   * Outputs a character array that is the reverse of seqarr and all of the letters are complliments
   * @return reverse compliment char[] of seqarr
   */
  public char[] getReverseCompSeq()
  {

    //Thinking it might be a good idea to throw a valid letter check in for good measure
    //reverse the sequence and instead of savind the original value, it saves the compliment of the original value
    char[] reverse =  new char[seqarr.length];
    for (int i = 0; i < seqarr.length; i++){
      reverse[i] = compliment(seqarr[(seqarr.length-i-1)]);
    }
    return reverse;
  }

  /**
   *  Uses getReverseCompSeq() to get the reverse compliment of seqarr
   *  Saves the output to a temporary array
   *  Assigns that seqarray so seqarray is the reverse compliment of what it already was
   */
  public void reverseComplement()
  {

    char[] temp = charrayCopy(getReverseCompSeq(), 0, getReverseCompSeq().length);
    for (int i =0; i < seqarr.length; i++){
      seqarr[i] = temp[i];
    }
  }

  /**
   * Outputs the compliment of the character provided, otherwise returns error
   * @param check character to check
   * @return the compliment character
   */
  private char compliment(char check){
    switch(check) {
      case 'A': return 'T';
      case 'C': return 'G';
      case 'G': return 'C';
      case 'T': return 'A';

      case 'a': return 't';
      case 'c': return 'g';
      case 'g': return 'c';
      case 't': return 'a';
      //should not get to this point. Returning $ proves that something is wrong
      default: return '$';
    }
  }
}
