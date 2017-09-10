package edu.iastate.cs228.hw1;

/*
 * @author
*/

public class CodingDNASequence extends DNASequence
{

  private char[] cdnaarr;

  public CodingDNASequence(char[] cdnaarr)
  {

    super();
    //Loop through cdnaarr and make sure all the letters are valid
    for(int i= 0; i < cdnaarr.length; i++){
      if!(isValidLetter(cndnaarr[i])){
        throw IllegalArgumentException("Invalid sequence letter for class" + this.getClass());
      }
    }
    this.cdnaarr = Arrays.copyOf(cdnaarr, 0, cdnaarr.length);
  }

  public boolean checkStartCodon()
  {

    if (seqarr.legth < 3){
      return false;
    }
    if (seqarr[0].toUpperCase() == 'A' && seqarr[1].toUpperCase() == 'T' && seqarr[2].toUpperCase() == 'G') {
      return true;
    }
    return false;
  }

  public char[] translate()
  {
    // TODO

    ArrayList<Character> protein = new ArrayList<>();

    if!(checkStartCodon()){
      throw RuntimeException("No start codon");
    }

    //Translating the coding sequence in seqarr to protien sequence
    //Loops through 3 at a time, creates a string out of the 3 selected characters.
    //TODO find a a way to deal with characters that are not of a length that is a percfect facor of 3
    //Finds the protein associated with that, adds it to the array list
    for (int i = 0; i < seqarr.length; i+=3){
      String codon = "" + seqarr[i] + seqarr[i+1] = seqarr[i+2];
      protein.add(getAminoAcid(codon));
    }
    return protein.toArray();
  }


  private char getAminoAcid(String codon)
  {
    if ( codon == null ) return '$';
    char aa = '$';
    switch ( codon.toUpperCase() )
    {
      case "AAA": aa = 'K'; break;
      case "AAC": aa = 'N'; break;
      case "AAG": aa = 'K'; break;
      case "AAT": aa = 'N'; break;

      case "ACA": aa = 'T'; break;
      case "ACC": aa = 'T'; break;
      case "ACG": aa = 'T'; break;
      case "ACT": aa = 'T'; break;

      case "AGA": aa = 'R'; break;
      case "AGC": aa = 'S'; break;
      case "AGG": aa = 'R'; break;
      case "AGT": aa = 'S'; break;

      case "ATA": aa = 'I'; break;
      case "ATC": aa = 'I'; break;
      case "ATG": aa = 'M'; break;
      case "ATT": aa = 'I'; break;

      case "CAA": aa = 'Q'; break;
      case "CAC": aa = 'H'; break;
      case "CAG": aa = 'Q'; break;
      case "CAT": aa = 'H'; break;

      case "CCA": aa = 'P'; break;
      case "CCC": aa = 'P'; break;
      case "CCG": aa = 'P'; break;
      case "CCT": aa = 'P'; break;

      case "CGA": aa = 'R'; break;
      case "CGC": aa = 'R'; break;
      case "CGG": aa = 'R'; break;
      case "CGT": aa = 'R'; break;

      case "CTA": aa = 'L'; break;
      case "CTC": aa = 'L'; break;
      case "CTG": aa = 'L'; break;
      case "CTT": aa = 'L'; break;

      case "GAA": aa = 'E'; break;
      case "GAC": aa = 'D'; break;
      case "GAG": aa = 'E'; break;
      case "GAT": aa = 'D'; break;

      case "GCA": aa = 'A'; break;
      case "GCC": aa = 'A'; break;
      case "GCG": aa = 'A'; break;
      case "GCT": aa = 'A'; break;

      case "GGA": aa = 'G'; break;
      case "GGC": aa = 'G'; break;
      case "GGG": aa = 'G'; break;
      case "GGT": aa = 'G'; break;

      case "GTA": aa = 'V'; break;
      case "GTC": aa = 'V'; break;
      case "GTG": aa = 'V'; break;
      case "GTT": aa = 'V'; break;

      case "TAA": aa = '$'; break;
      case "TAC": aa = 'Y'; break;
      case "TAG": aa = '$'; break;
      case "TAT": aa = 'Y'; break;

      case "TCA": aa = 'S'; break;
      case "TCC": aa = 'S'; break;
      case "TCG": aa = 'S'; break;
      case "TCT": aa = 'S'; break;

      case "TGA": aa = '$'; break;
      case "TGC": aa = 'C'; break;
      case "TGG": aa = 'W'; break;
      case "TGT": aa = 'C'; break;

      case "TTA": aa = 'L'; break;
      case "TTC": aa = 'F'; break;
      case "TTG": aa = 'L'; break;
      case "TTT": aa = 'F'; break;
      default:    aa = '$'; break;
    }
    return aa;
  }
}
