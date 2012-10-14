/**
 * 
 */
package edu.cmu.mingtaoz.obsolete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mingtaozhang
 *
 * were designed for CRF training, obsolete now
 */
public class TrainingDataWrapper {
  public static List<String> inputSentences = new ArrayList<String>();
  public static List<String> intputids = new ArrayList<String>();
  public static Map<String, List<String>> outputs = new HashMap<String, List<String>>();; // inputids(if exist) => all results
  
  static {
    initOutPut();
    initInputStructure();
  }

  public static void initOutPut(){
    try{
      BufferedReader b1 = new BufferedReader(new FileReader("src/main/resources/data/testing.in"));
      String line = "";
      while((line = b1.readLine()) != null){
        intputids.add(line.substring(0, line.indexOf(" ")));
        inputSentences.add(line.substring(line.indexOf(" ") + 1));
      }
    } catch (Exception e){}
  }
  
  public static void initInputStructure(){
    try{
      BufferedReader b2 = new BufferedReader(new FileReader("src/main/resources/data/testing.out"));
      String line = b2.readLine();
      String id = line.substring(0, line.indexOf("|"));
      List<String> tempResult = new ArrayList<String>();
      System.out.println(line.substring(line.lastIndexOf("|")+1));
      tempResult.add(line.substring(line.lastIndexOf("|")+1));
      while((line = b2.readLine()) != null){
        String tempId = line.substring(0, line.indexOf("|"));
        if(id.equals(tempId)){
          tempResult.add(line.substring(line.lastIndexOf("|")+1));
        } else {
          outputs.put(id, tempResult);
          id = tempId;
          tempResult = new ArrayList<String>();
          tempResult.add(line.substring(line.lastIndexOf("|")+1));
        }
      }
    } catch (Exception e){}
  }
}
