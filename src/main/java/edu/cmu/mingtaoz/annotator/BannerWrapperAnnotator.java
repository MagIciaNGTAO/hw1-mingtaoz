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

import banner.BannerProperties;
import banner.Sentence;
import banner.tagging.CRFTagger;
import banner.tagging.Mention;
import banner.tokenization.Tokenizer;

import edu.cmu.mingtaoz.type.Banner;

/**
 * @author mingtaozhang
 * 
 */
public class BannerWrapperAnnotator extends JCasAnnotator_ImplBase {

  private String propertiesFilename;// banner.properties
  
  private String modelFilename; // model.bin

  private int sentenceLength;
  //private Chunker chunker;

  private BannerProperties properties;
  private Tokenizer tokenizer;
  private CRFTagger tagger;
  
  /**
   * @see AnalysisComponent#initialize(UimaContext)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    propertiesFilename = (String) aContext.getConfigParameterValue("propertiesFilename");
    modelFilename = (String) aContext.getConfigParameterValue("modelFilename");
    sentenceLength = (Integer)aContext.getConfigParameterValue("sentenceLength");
    try {
      properties = BannerProperties.load(propertiesFilename);
      tokenizer = properties.getTokenizer();
      tagger = CRFTagger.load(new File(modelFilename), properties.getLemmatiser(), properties.getPosTagger());
      //chunker = (Chunker) AbstractExternalizable.readObject(new File(featurePath));
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
    // Get the input text
    String line = aJCas.getDocumentText();
    String sentence = line.substring(line.indexOf(" ") + 1);
    if(sentence.length() <= sentenceLength){
      Sentence bsentence = new Sentence(null, sentence);
      tokenizer.tokenize(bsentence);
      tagger.tag(bsentence);
      String previous = "abc";
      for (Mention m : bsentence.getMentions()) {
        if(m.toString().contains("GENE")
                && !m.toString().contains(previous)){
          Banner annotation = new Banner(aJCas);
          String rawGene = m.toString();//sentence.substring(t.getToken().getStart(), t.getToken().getEnd());
          String gene = rawGene.substring(rawGene.indexOf(" ") + 1);
          int begin = sentence.indexOf(gene);
          int end = sentence.indexOf(gene) + gene.length();
          annotation.setBegin(begin- getPreviousNumOfSpace(sentence, begin));
          annotation.setEnd(end - getPreviousNumOfSpace(sentence, begin) - getNumOfSpace(gene) - 1);
          annotation.setBanner(gene);
          previous = gene;
          annotation.addToIndexes();
        }
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
