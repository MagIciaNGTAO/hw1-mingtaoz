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
import org.apache.uima.util.FileUtils;
import org.xml.sax.SAXException;

import edu.cmu.mingtaoz.type.POS;
import edu.cmu.mingtaoz.type.Regex;
import edu.cmu.mingtaoz.type.Sentence;
import edu.cmu.mingtaoz.type.Tag;

/**
 * @author mingtaozhang
 *
 */
public class SampleWriterAndEvaluatorCasConsumer extends CasConsumer_ImplBase {

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
  
  /**
   * 
   * write out the output into hw1-mingtaoz.out file
   * 
   * @param aCas
   * @param name
   * @throws IOException
   * @throws SAXException
   * @throws CASRuntimeException
   * @throws CASException
   */
  private void writeSampleOutput(CAS aCas, File name) throws IOException, SAXException, CASRuntimeException, CASException {
    FileWriter out = null;
    StringBuilder outputResult = new StringBuilder();
    
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
          String result = tag.getTag() + "|" + (nn.getBegin() - sentence.getBegin() - previousNumSpace) + " " + (1 + nn.getEnd() - sentence.getBegin() - previousNumSpace - (nn.getPartOfSpeech().split(" ").length) - 1) + "|" + nn.getPartOfSpeech()+"\n";
          outputResult.append(result);   
          out.write(result);
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
          String result = tag.getTag() + "|" + (regex.getBegin() - sentence.getBegin() - previousNumSpace) + " " 
                  + (1 + regex.getEnd() - sentence.getBegin() - previousNumSpace - (regex.getRegex().split(" ").length) - 1) + "|" + regex.getRegex()+"\n";
          out.write(result);
          outputResult.append(result);
          regex = (Regex) regexIter.next();
        }
      }
      evaluateResult(outputResult.toString().split("\n"));
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
  
  /**
   * 
   * compared with sample.out, print out the recall/precession
   * 
   * a little bit hard code for Homework #1 purpose
   * 
   * @param resultLines
   * @throws IOException
   */
  private void evaluateResult(String[] resultLines) throws IOException{
    String sample = FileUtils.file2String(new File("src/main/resources/data/sample.out"));
    String[] sampleLines = sample.split("\n");
    int count = 0;
    int tracer = 0;
    for (int i = 0; i < sampleLines.length; i++) {
      for (int j = tracer; j < resultLines.length; j++) {
        if(sampleLines[i].equals(resultLines[j])) {
          count++;
          tracer++;
          break;
        }
      }
    }
    System.out.println();
    System.out.println(" ------------------ PRECESION REPORT ------------------");
    System.out.println("total result matching: "+count);
    System.out.println("total result lines: "+resultLines.length);
    System.out.println("sample result lines: "+sampleLines.length);
    System.out.println("precesion: "+(double)count/resultLines.length);
    System.out.println("recall: "+(double)count/sampleLines.length);
    System.out.println("-------------------------------------------------------");
    System.out.println();
  }
  
  //TODO
  private int getPreviousNumOfSpace(String sentence, String nn){
    if(sentence.indexOf(nn) != -1){
      return sentence.substring(0, sentence.indexOf(nn)).split(" ").length;
    } else {
      return 0;
    }
  }
}
