/**
 * 
 */
package edu.cmu.mingtaoz.obsolete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author mingtaozhang
 * 
 */
public class TrainingFileProvider {

  final static int totalLength = 15000;

  final static int trainingLength = 12000;

  /**
   * provide the training.in/out testing.in/out raw files
   * 
   */
  public static void generateSimpleTrainingTestingData() throws IOException {
    BufferedReader b1 = new BufferedReader(new FileReader("src/main/resources/data/crffeature/sample.in"));
    FileWriter trainingIn = new FileWriter(new File("src/main/resources/data/crffeature/training.in"));
    FileWriter trainingOut = new FileWriter(new File("src/main/resources/data/crffeature/training.out"));
    FileWriter testingIn = new FileWriter(new File("src/main/resources/data/crffeature/testing.in"));
    FileWriter testingOut = new FileWriter(new File("src/main/resources/data/crffeature/testing.out"));
    // training.in generate
    int i = 0;
    String line = "";
    while ((line = b1.readLine()) != null && i < trainingLength) {
      trainingIn.write(line + "\n");
      i++;
    }
    String startOfTestingId = line.substring(0, line.indexOf(" "));
    trainingIn.close();
    // testing.in generate
    do {
      testingIn.write(line + "\n");
    } while ((line = b1.readLine()) != null);
    testingIn.close();

    System.out.println(startOfTestingId);
    // training.out genenerate
    BufferedReader b2 = new BufferedReader(new FileReader("src/main/resources/data/sample.out"));
    int endOfTestingLine = 0;
    line = b2.readLine();
    while (line.substring(0, line.indexOf("|")).compareTo(startOfTestingId) < 0) {
      endOfTestingLine++;
      trainingOut.write(line + "\n");
      line = b2.readLine();
    }
    trainingOut.close();
    // testing.out genenerate
    do {
      testingOut.write(line + "\n");
    } while ((line = b2.readLine()) != null);
    testingOut.close();
  }

  public static void crfFormat() throws IOException {
    FileWriter f = new FileWriter(new File("src/main/resources/data/crffeature/testing.tsv"));
    List<String> ids = TrainingDataWrapper.intputids;
    for (int i = 0; i < ids.size(); i++) {
      String sentence = TrainingDataWrapper.inputSentences.get(i);
      String id = ids.get(i);
      String[] words = preprocess(sentence);// sentence.split(" ");
      List<String> answers = TrainingDataWrapper.outputs.get(id);
      for (String word : words) {
        // word = word.replaceAll("(,)|(\\.)", "");
        if (answers != null) {
          boolean sign = false;
          for(String temp: answers){
            sign |= temp.contains(word);
          }
          if(sign){
            f.write(word + "  " + "GENE" + "\n");
          } else {
            f.write(word + "  " + "O" + "\n");
          }
        } else {
          f.write(word + "  " + "O" + "\n");
        }
      }
    }
    f.close();
  }

  private static String[] preprocess(String s) {
    String[] words = s.split(" ");
    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].replaceAll(",", " ");
      words[i] = words[i].trim();
      if (words[i].lastIndexOf(".") == words[i].length() - 1) {
        //System.out.println();
        //System.out.println(words[i]);
        if(words[i].lastIndexOf(".") != -1){
          words[i] = words[i].substring(0, words[i].lastIndexOf("."));
        }
      }
      words[i] = words[i].replaceAll(" ", "");
    }
    return words;
  }

  /**
   * 
   * data preparation entry point
   * 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // generateSimpleTrainingTestingData();
    crfFormat();
  }
}
