
/* First created by JCasGen Mon Oct 08 21:57:16 EDT 2012 */
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
public class Regex_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Regex_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Regex_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Regex(addr, Regex_Type.this);
  			   Regex_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Regex(addr, Regex_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Regex.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.mingtaoz.type.Regex");
 
  /** @generated */
  final Feature casFeat_Regex;
  /** @generated */
  final int     casFeatCode_Regex;
  /** @generated */ 
  public String getRegex(int addr) {
        if (featOkTst && casFeat_Regex == null)
      jcas.throwFeatMissing("Regex", "edu.cmu.mingtaoz.type.Regex");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Regex);
  }
  /** @generated */    
  public void setRegex(int addr, String v) {
        if (featOkTst && casFeat_Regex == null)
      jcas.throwFeatMissing("Regex", "edu.cmu.mingtaoz.type.Regex");
    ll_cas.ll_setStringValue(addr, casFeatCode_Regex, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Regex_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Regex = jcas.getRequiredFeatureDE(casType, "Regex", "uima.cas.String", featOkTst);
    casFeatCode_Regex  = (null == casFeat_Regex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Regex).getCode();

  }
}



    