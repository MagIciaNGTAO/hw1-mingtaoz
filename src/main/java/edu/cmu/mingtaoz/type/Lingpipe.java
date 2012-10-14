

/* First created by JCasGen Sat Oct 13 20:19:49 EDT 2012 */
package edu.cmu.mingtaoz.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Oct 13 20:19:49 EDT 2012
 * XML source: /Users/mingtaozhang/Documents/workspace/hw1-mingtaoz/src/main/resources/descriptors/analysis_engine/TypeSystem.xml
 * @generated */
public class Lingpipe extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Lingpipe.class);
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
  protected Lingpipe() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Lingpipe(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Lingpipe(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Lingpipe(JCas jcas, int begin, int end) {
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
  //* Feature: Lingpipe

  /** getter for Lingpipe - gets Lingpipe existed library
   * @generated */
  public String getLingpipe() {
    if (Lingpipe_Type.featOkTst && ((Lingpipe_Type)jcasType).casFeat_Lingpipe == null)
      jcasType.jcas.throwFeatMissing("Lingpipe", "edu.cmu.mingtaoz.type.Lingpipe");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Lingpipe_Type)jcasType).casFeatCode_Lingpipe);}
    
  /** setter for Lingpipe - sets Lingpipe existed library 
   * @generated */
  public void setLingpipe(String v) {
    if (Lingpipe_Type.featOkTst && ((Lingpipe_Type)jcasType).casFeat_Lingpipe == null)
      jcasType.jcas.throwFeatMissing("Lingpipe", "edu.cmu.mingtaoz.type.Lingpipe");
    jcasType.ll_cas.ll_setStringValue(addr, ((Lingpipe_Type)jcasType).casFeatCode_Lingpipe, v);}    
  }

    