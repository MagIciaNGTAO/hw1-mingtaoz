/**
 * 
 */
package edu.cmu.mingtaoz.obsolete;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingtaozhang
 * 
 * were designed for CRF training, obsolete now
 */
public class DataWrapper {
  public static List<String> inputSentences = new ArrayList<String>();
  public static List<String> intputids = new ArrayList<String>();

  public static void initOutPut(String text) {
    String[] lines = text.split("\n");
    for(String line: lines){
      intputids.add(line.substring(0, line.indexOf(" ")));
      inputSentences.add(line.substring(line.indexOf(" ") + 1));
    }
  }
}
