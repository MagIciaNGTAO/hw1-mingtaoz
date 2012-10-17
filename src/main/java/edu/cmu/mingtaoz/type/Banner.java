

/* First created by JCasGen Tue Oct 16 20:17:12 EDT 2012 */
package edu.cmu.mingtaoz.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 20:17:12 EDT 2012
 * XML source: /Users/mingtaozhang/Documents/workspace/hw1-mingtaoz/src/main/resources/descriptors/analysis_engine/TypeSystem.xml
 * @generated */
public class Banner extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Banner.class);
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
  protected Banner() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Banner(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Banner(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Banner(JCas jcas, int begin, int end) {
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
  //* Feature: Banner

  /** getter for Banner - gets Banner existed library
   * @generated */
  public String getBanner() {
    if (Banner_Type.featOkTst && ((Banner_Type)jcasType).casFeat_Banner == null)
      jcasType.jcas.throwFeatMissing("Banner", "edu.cmu.mingtaoz.type.Banner");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Banner_Type)jcasType).casFeatCode_Banner);}
    
  /** setter for Banner - sets Banner existed library 
   * @generated */
  public void setBanner(String v) {
    if (Banner_Type.featOkTst && ((Banner_Type)jcasType).casFeat_Banner == null)
      jcasType.jcas.throwFeatMissing("Banner", "edu.cmu.mingtaoz.type.Banner");
    jcasType.ll_cas.ll_setStringValue(addr, ((Banner_Type)jcasType).casFeatCode_Banner, v);}    
  }

    