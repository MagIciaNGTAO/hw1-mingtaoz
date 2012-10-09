/**
 * 
 */
package edu.cmu.mingtaoz.evaluation;

import java.io.File;
import java.io.IOException;

import org.apache.uima.util.FileUtils;

/**
 * @author mingtaozhang
 * 
 */
public class PosTagNamedEntityRecognizerAnnotatorEvaluator {
  public static void main(String[] args) throws IOException {
    String sample = FileUtils.file2String(new File("src/main/resources/data/sample.out"));
    String result = FileUtils.file2String(new File("src/main/resources/data/hw1-mingtaoz.out"));
    String[] sampleLines = sample.split("\n");
    String[] resultLines = result.split("\n");
    int count = 0;
    int tracer = 0;
    for (int i = 0; i < sampleLines.length; i++) {
      for (int j = tracer; j < resultLines.length; j++) {
        if(sampleLines[i].equals(resultLines[j])) {
          //System.out.println(sampleLines[i]);
          //System.out.println(resultLines[j]);
          count++;
          tracer++;
          break;
        } 
      }
    }
    System.out.println(count);
    System.out.println(resultLines.length);
    System.out.println(sampleLines.length);
    System.out.println("precesion: "+(double)count/resultLines.length);
    System.out.println("recall: "+(double)count/sampleLines.length);
  }
}
