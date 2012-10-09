

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
public class Tag extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Tag.class);
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
  protected Tag() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Tag(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Tag(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Tag(JCas jcas, int begin, int end) {
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
  //* Feature: Tag

  /** getter for Tag - gets Tag in the single line
   * @generated */
  public String getTag() {
    if (Tag_Type.featOkTst && ((Tag_Type)jcasType).casFeat_Tag == null)
      jcasType.jcas.throwFeatMissing("Tag", "edu.cmu.mingtaoz.type.Tag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Tag_Type)jcasType).casFeatCode_Tag);}
    
  /** setter for Tag - sets Tag in the single line 
   * @generated */
  public void setTag(String v) {
    if (Tag_Type.featOkTst && ((Tag_Type)jcasType).casFeat_Tag == null)
      jcasType.jcas.throwFeatMissing("Tag", "edu.cmu.mingtaoz.type.Tag");
    jcasType.ll_cas.ll_setStringValue(addr, ((Tag_Type)jcasType).casFeatCode_Tag, v);}    
  }

    