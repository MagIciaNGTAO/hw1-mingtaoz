/**
 * 
 */
package edu.cmu.mingtaoz.obsolete;

import java.io.IOException;

import edu.stanford.nlp.ie.crf.CRFClassifier;

/**
 * @author mingtaozhang
 *
 */
public class NERDemo {

  static String test = "Cat\nfocal\nFak\nSrc\nDokR-specific\nendogenous\nEGF\nExpansins\nalpha\nB-Myb\nglutathionine\nAPOE\nDNA\nRecA\nclass\nCon\nhorseradish\nTraR\nelongin\nJAK\nSTAT\nSpfkh1\nTax\nMad2p\nMad1p\nhuman\n";
  
  public static void main(String[] args) throws IOException {
    String serializedClassifier = "src/main/resources/data/crffeature/gene-window1-ner-model.ser.gz";      
    CRFClassifier classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
    System.out.println(classifier.classifyToString(test));
  }
}
