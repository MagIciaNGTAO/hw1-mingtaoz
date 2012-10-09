/**
 * 
 */
package edu.cmu.mingtaoz.annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.mingtaoz.type.Sentence;
import edu.cmu.mingtaoz.type.Tag;

/**
 * @author mingtaozhang
 *
 */
public class TagSentenceAnnotator extends JCasAnnotator_ImplBase {

  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // get document text
    String docText = aJCas.getDocumentText();
    String[] lines = docText.split("\n");
    
    for(String line : lines){
      Tag tagAnnotation = new Tag(aJCas);
      Sentence setenceAnnotation = new Sentence(aJCas);
      int beginOfline, tagEnd;
      tagEnd = line.indexOf(" ");
      
      String tag = line.substring(0, tagEnd);
      beginOfline = docText.indexOf(tag);
      
      tagAnnotation.setBegin(beginOfline);
      tagAnnotation.setEnd(beginOfline + tagEnd);
      tagAnnotation.setTag(line.substring(0, tagEnd));
      tagAnnotation.addToIndexes();
      
      setenceAnnotation.setBegin(beginOfline + tagEnd + 1);
      setenceAnnotation.setEnd(beginOfline + line.length());
      setenceAnnotation.setSentence(line.substring(tagEnd + 1));
      setenceAnnotation.addToIndexes();
    }
  }
}
