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

import edu.cmu.mingtaoz.type.Banner;
import edu.cmu.mingtaoz.type.Lingpipe;

/**
 * @author mingtaozhang
 * 
 */
public class SampleWriterCasConsumer extends CasConsumer_ImplBase {

  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  public static final String PARAM_OUTPUTFILE = "OutputFile";

  //private static int i; count to monitor the process
  
  private File mOutputFile;
  
  private FileWriter out;
  
  /**
   * 
   * clean the exist file, set up two private members
   * 
   */
  public void initialize() throws ResourceInitializationException {
    mOutputFile = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR) + "/" + (String) getConfigParameterValue(PARAM_OUTPUTFILE));
    if (!mOutputFile.exists()) {
      try {
        mOutputFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      out = new FileWriter(mOutputFile); // delete the existed content
      out.close();
      out = new FileWriter(mOutputFile, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * transform CAS into JCas and call method to write result
   * 
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    writeOneLineSampleOutput(jcas);
  }

  /**
   * 
   * write out the output by appending to the end of the file
   * 
   * @param aCas
   * @param name
   * @throws IOException
   * @throws SAXException
   * @throws CASRuntimeException
   * @throws CASException
   */
  private void writeOneLineSampleOutput(JCas jcas) { 
    
    try {
      String sentence = jcas.getDocumentText();
      FSIndex index = jcas.getAnnotationIndex(Lingpipe.type);
      Iterator iterator = index.iterator();
      while(iterator.hasNext()){
        Lingpipe geneTag = (Lingpipe) iterator.next();
        out.write(sentence.substring(0, sentence.indexOf(" "))+"|"+geneTag.getBegin()+" "+geneTag.getEnd()+"|"+geneTag.getLingpipe()+"\n");
      }
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    
    
    try {
      String sentence = jcas.getDocumentText();
      FSIndex index = jcas.getAnnotationIndex(Banner.type);
      Iterator iterator = index.iterator();
      while(iterator.hasNext()){
        Banner geneTag = (Banner) iterator.next();
        out.write(sentence.substring(0, sentence.indexOf(" "))+"|"+geneTag.getBegin()+" "+geneTag.getEnd()+"|"+geneTag.getBanner()+"\n");
      }
      //System.out.println(i++);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * close the filewriter so that results can be written to file system
   * 
   */
  public void destroy(){
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
