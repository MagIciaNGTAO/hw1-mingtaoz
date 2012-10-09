/**
 * 
 */
package edu.cmu.mingtaoz.annotator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

    public PosTagNamedEntityRecognizerAnnotator() throws ResourceInitializationException {
      Properties props = new Properties();
      props.put("annotators", "tokenize, ssplit, pos");
      pipeline = new StanfordCoreNLP(props);
    }

    public Map<Integer, Integer> getGeneSpans(String text) {
      Map<Integer, Integer> begin2end = new HashMap<Integer, Integer>();
      Annotation document = new Annotation(text);
      pipeline.annotate(document);
      List<CoreMap> sentences = document.get(SentencesAnnotation.class);
      for (CoreMap sentence : sentences) {
        List<CoreLabel> candidate = new ArrayList<CoreLabel>();
        for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
          String pos = token.get(PartOfSpeechAnnotation.class);
          //if (!pos.startsWith("CC") && !pos.startsWith("VBD") && !pos.startsWith("RB")
          //    && !pos.startsWith("DT") && !pos.startsWith("IN") && !pos.startsWith("CD")) {
          if(pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("FW")){
            candidate.add(token);
          } else if (candidate.size() > 0) {
            int begin = candidate.get(0).beginPosition();
            int end = candidate.get(candidate.size() - 1).endPosition();
            begin2end.put(begin, end);
            candidate.clear();
          }
          if(pos.startsWith("NN")){
            //candidate.add(token);
            //} else if (candidate.size() > 0) {
            int begin = token.beginPosition();
            int end = token.endPosition();
            begin2end.put(begin, end);
            //candidate.clear();
          }
        }
        if (candidate.size() > 0) {
          int begin = candidate.get(0).beginPosition();
          int end = candidate.get(candidate.size() - 1).endPosition();
          begin2end.put(begin, end);
          candidate.clear();
        }
      }
      return begin2end;
    }

    public void process(JCas aJCas) throws AnalysisEngineProcessException {
      // get document text
      String docText = aJCas.getDocumentText();
      Map<Integer, Integer> result = getGeneSpans(docText);
      for(int i : result.keySet()){
        POS annotation = new POS(aJCas);
        annotation.setBegin(i);
        annotation.setEnd(result.get(i));
        annotation.setPartOfSpeech(docText.substring(i, result.get(i)));
        annotation.addToIndexes();
      }
    }
    
    /*
    public static void main(String[] args) throws ResourceInitializationException{
      String test = "When CSF [HCO3-] is shown as a function of CSF PCO2 the data of K-depleted rats are no longer displaced when compared to controls but still have a significantly greater slope (1.21 +/- 0.23 vs.";
      Map<Integer, Integer> m = new PosTagNamedEntityRecognizerAnnotator().getGeneSpans(test);
      for(int i:m.keySet()){
        //System.out.println(i + "-" + m.get(i));
        System.out.println(test.substring(i, m.get(i)));
      }
    }
    */
}
