/**
 * 
 */
package edu.cmu.mingtaoz.annotator;

import java.io.File;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.mingtaoz.type.Lingpipe;

/**
 * @author mingtaozhang
 * 
 */
public class LingpipeWrapperAnnotator extends JCasAnnotator_ImplBase {

  private String featurePath;

  private Chunker chunker;

  private int sentenceLength;
  /**
   * @see AnalysisComponent#initialize(UimaContext)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    featurePath = (String) aContext.getConfigParameterValue("featurePath");
    sentenceLength = (Integer)aContext.getConfigParameterValue("sentenceLength");
    try {
      chunker = (Chunker) AbstractExternalizable.readObject(new File(featurePath));
    } catch (Exception e) {
      // don't case about different types of Exceptions
      e.printStackTrace();
    }
  }

  /**
   * 
   * process a cas (sentence), put annotation on the cas, the begin and end offset will ignore the spaces
   * 
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String line = aJCas.getDocumentText();
    String sentence = line.substring(line.indexOf(" ") + 1);
    if(sentence.length() > sentenceLength){
      Chunking chunking = chunker.chunk(sentence);
      for (Chunk c : chunking.chunkSet()) {
        Lingpipe annotation = new Lingpipe(aJCas);
        String gene = sentence.substring(c.start(), c.end());
        annotation.setBegin(c.start() - getPreviousNumOfSpace(sentence, c.start()));
        annotation.setEnd(c.end() - getPreviousNumOfSpace(sentence, c.start()) - getNumOfSpace(gene) - 1);
        annotation.setLingpipe(gene);
        annotation.addToIndexes();
      }
    }
  }

  /**
   * given a sentence and a end point, return how many spaces are there before end point
   * 
   * @param sentence
   * @param nn
   * @return
   */
  private int getPreviousNumOfSpace(String sentence, int end) {
    return getNumOfSpace(sentence.substring(0, end + 1));
  }
  
  /**
   * count spaces inside a String
   * 
   * @param sentence
   * @param nn
   * @return
   */
  private int getNumOfSpace(String s) {
    return s.split(" ").length - 1;
  }
}
