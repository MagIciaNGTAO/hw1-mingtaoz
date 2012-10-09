

/* First created by JCasGen Mon Oct 08 21:57:16 EDT 2012 */
package edu.cmu.mingtaoz.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 08 21:57:16 EDT 2012
 * XML source: /Users/mingtaozhang/Documents/workspace/hw1-mingtaoz/src/main/resources/descriptors/analysis_engine/TypeSystem.xml
 * @generated */
public class Regex extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Regex.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Regex() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Regex(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Regex(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Regex(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Regex

  /** getter for Regex - gets human made regular expression
   * @generated */
  public String getRegex() {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Regex == null)
      jcasType.jcas.throwFeatMissing("Regex", "edu.cmu.mingtaoz.type.Regex");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Regex_Type)jcasType).casFeatCode_Regex);}
    
  /** setter for Regex - sets human made regular expression 
   * @generated */
  public void setRegex(String v) {
    if (Regex_Type.featOkTst && ((Regex_Type)jcasType).casFeat_Regex == null)
      jcasType.jcas.throwFeatMissing("Regex", "edu.cmu.mingtaoz.type.Regex");
    jcasType.ll_cas.ll_setStringValue(addr, ((Regex_Type)jcasType).casFeatCode_Regex, v);}    
  }

    