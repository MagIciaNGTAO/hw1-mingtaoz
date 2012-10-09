

/* First created by JCasGen Sun Oct 07 21:57:33 EDT 2012 */
package edu.cmu.mingtaoz.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 08 21:57:16 EDT 2012
 * XML source: /Users/mingtaozhang/Documents/workspace/hw1-mingtaoz/src/main/resources/descriptors/analysis_engine/TypeSystem.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
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
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentence(JCas jcas, int begin, int end) {
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
  //* Feature: Sentence

  /** getter for Sentence - gets Sentence in the single line
   * @generated */
  public String getSentence() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Sentence == null)
      jcasType.jcas.throwFeatMissing("Sentence", "edu.cmu.mingtaoz.type.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Sentence);}
    
  /** setter for Sentence - sets Sentence in the single line 
   * @generated */
  public void setSentence(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Sentence == null)
      jcasType.jcas.throwFeatMissing("Sentence", "edu.cmu.mingtaoz.type.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Sentence, v);}    
  }

    