
/* First created by JCasGen Sun Oct 07 19:39:06 EDT 2012 */
package edu.cmu.mingtaoz.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Oct 08 21:57:16 EDT 2012
 * @generated */
public class POS_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (POS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = POS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new POS(addr, POS_Type.this);
  			   POS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new POS(addr, POS_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = POS.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.mingtaoz.type.POS");
 
  /** @generated */
  final Feature casFeat_PartOfSpeech;
  /** @generated */
  final int     casFeatCode_PartOfSpeech;
  /** @generated */ 
  public String getPartOfSpeech(int addr) {
        if (featOkTst && casFeat_PartOfSpeech == null)
      jcas.throwFeatMissing("PartOfSpeech", "edu.cmu.mingtaoz.type.POS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_PartOfSpeech);
  }
  /** @generated */    
  public void setPartOfSpeech(int addr, String v) {
        if (featOkTst && casFeat_PartOfSpeech == null)
      jcas.throwFeatMissing("PartOfSpeech", "edu.cmu.mingtaoz.type.POS");
    ll_cas.ll_setStringValue(addr, casFeatCode_PartOfSpeech, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public POS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_PartOfSpeech = jcas.getRequiredFeatureDE(casType, "PartOfSpeech", "uima.cas.String", featOkTst);
    casFeatCode_PartOfSpeech  = (null == casFeat_PartOfSpeech) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PartOfSpeech).getCode();

  }
}



    