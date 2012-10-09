/**
 * 
 */
package edu.cmu.mingtaoz.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import edu.cmu.mingtaoz.type.Regex;

/**
 * @author mingtaozhang
 *
 */
public class RegularExpressionBasedAnnotator extends JCasAnnotator_ImplBase {
  private Pattern mutant = Pattern.compile("[A-Za-z0-9]* [Mm]utan(t|ts)"); //1
  private Pattern parens = Pattern.compile("([A-Za-z0-9]+\\([A-Za-z0-9]*\\))|(\\([A-Za-z0-9]*\\)[A-Za-z0-9]+)"); //2
  private Pattern fusionProteins = Pattern.compile("[A-Za-z0-9]* [A-Za-z0-9]* fusion"); //5

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    // get document text
    String docText = aJCas.getDocumentText();

    Matcher matcher = mutant.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      Regex annotation = new Regex(aJCas);
      annotation.setBegin(matcher.start());
      annotation.setEnd(matcher.end());
      annotation.setRegex(docText.substring(matcher.start(), matcher.end()));
      annotation.addToIndexes();
    }

    matcher = parens.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      Regex annotation = new Regex(aJCas);
      annotation.setBegin(matcher.start());
      annotation.setEnd(matcher.end());
      annotation.setRegex(docText.substring(matcher.start(), matcher.end()));
      annotation.addToIndexes();
    }
    
    matcher = fusionProteins.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      Regex annotation = new Regex(aJCas);
      annotation.setBegin(matcher.start());
      annotation.setEnd(matcher.end());
      annotation.setRegex(docText.substring(matcher.start(), matcher.end()));
      annotation.addToIndexes();
    }
  }
}
