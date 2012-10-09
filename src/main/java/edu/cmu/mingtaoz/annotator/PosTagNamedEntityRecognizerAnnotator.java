/**
 * 
 */
package edu.cmu.mingtaoz.annotator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.cmu.mingtaoz.type.POS;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * @author mingtaozhang
 * 
 */
public class PosTagNamedEntityRecognizerAnnotator extends JCasAnnotator_ImplBase {

  private StanfordCoreNLP pipeline;

  private int windowSize;

  /**
   * @see AnalysisComponent#initialize(UimaContext)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos");
    pipeline = new StanfordCoreNLP(props);
    windowSize = (Integer) aContext.getConfigParameterValue("windowSize");
  }

  class Position {
    String content;
    int begin;
    int end;

    public Position(String content, int begin, int end) {
      this.content = content;
      this.begin = begin;
      this.end = end;
    }
  }

  public Map<Integer, Position> getGeneSpans(String text) {
    Map<Integer, Position> begin2end = new HashMap<Integer, Position>();
    Annotation document = new Annotation(text);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      List<CoreLabel> candidate = new ArrayList<CoreLabel>();
      for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
        String pos = token.get(PartOfSpeechAnnotation.class);
        if (pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("FW")) {
          candidate.add(token);
        } else if (candidate.size() > 0) {
          int begin = candidate.get(0).beginPosition();
          int end = candidate.get(candidate.size() - 1).endPosition();
          
          StringBuilder temp = new StringBuilder();
          for (CoreLabel c : candidate) {
            temp.append(c.originalText()).append(" ");
          }
          String tempResult = temp.toString();
          begin2end.put(begin+end, new Position(tempResult.substring(0, tempResult.length() - 1), begin, end));
          candidate.clear();
        }
        
        if (pos.startsWith("NN")) {
          int begin = token.beginPosition();
          int end = token.endPosition();
          begin2end.put(begin+end, new Position(token.originalText(), begin, end));
        }
      }
      if (candidate.size() > 0) {
        int begin = candidate.get(0).beginPosition();
        int end = candidate.get(candidate.size() - 1).endPosition();
        StringBuilder temp = new StringBuilder();
        for (CoreLabel c : candidate) {
          temp.append(c.originalText()).append(" ");
        }
        String tempResult = temp.toString();
        begin2end.put(begin+end, new Position(tempResult.substring(0, tempResult.length() - 1), begin, end));
        candidate.clear();
      }
    }
    return begin2end;
  }
  
  /*
  public Map<Integer, Position> getGeneSpans(String text) {
    // Map<Integer, Integer> begin2end = new HashMap<Integer, Integer>();
    Map<Integer, Position> begin2end = new HashMap<Integer, Position>();
    Annotation document = new Annotation(text);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      for(int i=1; i< windowSize; i++){
        // we do this for different window sizes
        List<CoreLabel> candidate = new ArrayList<CoreLabel>();
        int count = 0;
        for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
          String pos = token.get(PartOfSpeechAnnotation.class);
          if (pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("FW") && count < i) {
            candidate.add(token);
            count++;
          } else if (candidate.size() > 0) {
            int begin = candidate.get(0).beginPosition();
            int end = candidate.get(candidate.size() - 1).endPosition();
            StringBuilder temp = new StringBuilder();
            for (CoreLabel c : candidate) {
              temp.append(c.originalText()).append(" ");
            }
            String tempResult = temp.toString();
            begin2end.put(begin + end, new Position(tempResult.substring(0, tempResult.length() - 1), begin, end));
            candidate.clear();
          }
        }
  
        if (candidate.size() > 0) {
          int begin = candidate.get(0).beginPosition();
          int end = candidate.get(candidate.size() - 1).endPosition();
          StringBuilder temp = new StringBuilder();
          for (CoreLabel c : candidate) {
            temp.append(c.originalText()).append(" ");
          }
          String tempResult = temp.toString();
          begin2end.put(begin + end, new Position(tempResult.substring(0, tempResult.length() - 1), begin, end));
          candidate.clear();
        }
      }
    }
    return begin2end;
  }
  */

  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // get document text
    String docText = aJCas.getDocumentText();
    Map<Integer, Position> result = getGeneSpans(docText);
    for (int sum : result.keySet()) {
      POS annotation = new POS(aJCas);
      annotation.setBegin(result.get(sum).begin);
      annotation.setEnd(result.get(sum).end);
      annotation.setPartOfSpeech(result.get(sum).content);
      // System.out.println(s);
      annotation.addToIndexes();
    }
  }
}
