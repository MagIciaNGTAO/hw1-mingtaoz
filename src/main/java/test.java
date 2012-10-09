import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cmu.mingtaoz.type.Regex;

/**
 * 
 */

/**
 * @author mingtaozhang
 *
 */
public class test {
  //static Pattern mutant = Pattern.compile("[A-Za-z0-9]*((m|M)utan(t|ts))\b");
  //static Pattern mutant = Pattern.compile("[A-Za-z0-9]* [Mm]utan(t|ts)");
  static Pattern mutant = Pattern.compile("([A-Za-z0-9]+\\([A-Za-z0-9]*\\))|(\\([A-Za-z0-9]*\\)[A-Za-z0-9]+)"); //2
  
  
  public static void main(String[] args){
    //String docText = "aaa his273 mutant aaa";
    String docText = "(GGP)a";
    // search for Yorktown room numbers
    Matcher matcher = mutant.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      System.out.println(matcher.start() + "-" + matcher.end());
    }
  }
}
