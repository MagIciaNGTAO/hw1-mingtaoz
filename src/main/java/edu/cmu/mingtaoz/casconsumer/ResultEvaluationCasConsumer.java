/**
 * 
 */
package edu.cmu.mingtaoz.casconsumer;

import java.io.File;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.FileUtils;

/**
 * @author mingtaozhang
 * 
 */
public class ResultEvaluationCasConsumer extends CasConsumer_ImplBase {

  public static final String PARAM_SAMPLEPATH = "SamplePath";

  public static final String PARAM_RESULTPATH = "ResultPath";

  private File myFile;

  private File sampleFile;
  
  /**
   * if the two files are not existed (maybe due to Homework testing environment), set this sign to false
   * 
   */
  private boolean fileExistSign;

  /**
   * 
   * initialize by setup three private members
   * 
   */
  public void initialize() throws ResourceInitializationException {
    try{
      myFile = new File((String) getConfigParameterValue(PARAM_RESULTPATH));
      sampleFile = new File((String) getConfigParameterValue(PARAM_SAMPLEPATH));
      fileExistSign = true;
    } catch (Exception e) {
      fileExistSign = false;
    }
  }

  /**
   * empty for each cas
   * 
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {}
  
  /**
   * evaluate before quit, but if the condition can't provide enough evidence for evaluation, skip evaluation
   * 
   */
  public void destroy(){
    if(fileExistSign){
      evaluate();
    }
  }
  
  /**
   * compare similarity between sampleFile and myFile
   * 
   */
  private void evaluate(){
    try {
      String sample = FileUtils.file2String(sampleFile);
      String result = FileUtils.file2String(myFile);
      String[] sampleLines = sample.split("\n");
      String[] resultLines = result.split("\n");
      int count = 0;
      int tracer = 0;
      for (int i = 0; i < sampleLines.length; i++) {
        for (int j = tracer; j < resultLines.length; j++) {
          if (sampleLines[i].equals(resultLines[j])) {
            count++;
            tracer++;
            break;
          }
        }
      }
      System.out.println();
      System.out.println(" -------- PRECESION REPORT ---------------------------");
      System.out.println("total result matching: " + count);
      System.out.println("total result lines: " + resultLines.length);
      System.out.println("sample result lines: " + sampleLines.length);
      System.out.println("precesion: " + (double) count / resultLines.length);
      System.out.println("recall: " + (double) count / sampleLines.length);
      System.out.println("-------------------------------------------------------");
      System.out.println();
    } catch (Exception e) {
      System.out.println("evaluation is only available for sample.out file");
    }
  }
}
