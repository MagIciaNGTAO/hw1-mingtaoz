/**
 * 
 */
package edu.cmu.mingtaoz.casconsumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

import edu.cmu.mingtaoz.type.POS;
import edu.cmu.mingtaoz.type.Regex;
import edu.cmu.mingtaoz.type.Sentence;
import edu.cmu.mingtaoz.type.Tag;

/**
 * @author mingtaozhang
 *
 */
public class SampleWriterCasConsumer extends CasConsumer_ImplBase {

  public static final String PARAM_OUTPUTDIR = "OutputDirectory";
  
  public static final String PARAM_OUTPUTFILE = "OutputFile";

  private File mOutputFile;

  public void initialize() throws ResourceInitializationException {
    mOutputFile = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR)+"/"+(String) getConfigParameterValue(PARAM_OUTPUTFILE));
    if (!mOutputFile.exists()) {
      try {
        mOutputFile.createNewFile();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    // write to file system
    try {
      writeSampleOutput(jcas.getCas(), mOutputFile);
    } catch (IOException e) {
      throw new ResourceProcessException(e);
    } catch (SAXException e) {
      throw new ResourceProcessException(e);
    } catch (CASRuntimeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CASException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private void writeSampleOutput(CAS aCas, File name) throws IOException, SAXException, CASRuntimeException, CASException {
    FileWriter out = null;
    try {
      // write XMI
      out = new FileWriter(name);
      FSIndex nnIndex = aCas.getJCas().getAnnotationIndex(POS.type);
      FSIndex sentenceIndex = aCas.getJCas().getAnnotationIndex(Sentence.type);
      FSIndex tagIndex = aCas.getJCas().getAnnotationIndex(Tag.type);

      Iterator tagIter = tagIndex.iterator();
      Iterator sentenceIter = sentenceIndex.iterator();
      
      Tag tag = (Tag) tagIter.next();
      Sentence sentence = (Sentence) sentenceIter.next();
      
      
      Iterator nnIter = nnIndex.iterator();
      // TODO empty case
      
      while (nnIter.hasNext()) {
        POS nn = (POS) nnIter.next();  
        while (nn.getBegin() > sentence.getEnd() && tagIter.hasNext()){
          tag = (Tag) tagIter.next();
          sentence = (Sentence) sentenceIter.next();
        }
        while(nn.getEnd() <= sentence.getEnd() && nn.getBegin() >= tag.getEnd() && nnIter.hasNext()) {
          // verify the logic
          int previousNumSpace = getPreviousNumOfSpace(sentence.getSentence(), nn.getPartOfSpeech());
          out.write(tag.getTag() + "|" + (nn.getBegin() - sentence.getBegin() - previousNumSpace) + " " 
                    + (1 + nn.getEnd() - sentence.getBegin() - previousNumSpace - (nn.getPartOfSpeech().split(" ").length) - 1) + "|" + nn.getPartOfSpeech()+"\n");
          nn = (POS) nnIter.next();
        }
      }
      
      
      FSIndex regexIndex = aCas.getJCas().getAnnotationIndex(Regex.type);
      Iterator regexIter = regexIndex.iterator();
      while (regexIter.hasNext()) {
        Regex regex = (Regex) regexIter.next();
        while (regex.getBegin() > sentence.getEnd() && tagIter.hasNext()){
          tag = (Tag) tagIter.next();
          sentence = (Sentence) sentenceIter.next();
        }
        while(regex.getEnd() <= sentence.getEnd() && regex.getBegin() >= tag.getEnd() && regexIter.hasNext()) {
          // verify the logic
          int previousNumSpace = getPreviousNumOfSpace(sentence.getSentence(), regex.getRegex());
          out.write(tag.getTag() + "|" + (regex.getBegin() - sentence.getBegin() - previousNumSpace) + " " 
                    + (1 + regex.getEnd() - sentence.getBegin() - previousNumSpace - (regex.getRegex().split(" ").length) - 1) + "|" + regex.getRegex()+"\n");
          regex = (Regex) regexIter.next();
        }
      }
      
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
  
  private int getPreviousNumOfSpace(String sentence, String nn){
    return sentence.substring(0, sentence.indexOf(nn)).split(" ").length;
  }
}
