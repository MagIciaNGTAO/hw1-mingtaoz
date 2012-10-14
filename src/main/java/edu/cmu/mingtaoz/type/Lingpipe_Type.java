
/* First created by JCasGen Sat Oct 13 20:19:49 EDT 2012 */
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
 * Updated by JCasGen Sat Oct 13 20:19:49 EDT 2012
 * @generated */
public class Lingpipe_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Lingpipe_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Lingpipe_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Lingpipe(addr, Lingpipe_Type.this);
  			   Lingpipe_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Lingpipe(addr, Lingpipe_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Lingpipe.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.mingtaoz.type.Lingpipe");
 
  /** @generated */
  final Feature casFeat_Lingpipe;
  /** @generated */
  final int     casFeatCode_Lingpipe;
  /** @generated */ 
  public String getLingpipe(int addr) {
        if (featOkTst && casFeat_Lingpipe == null)
      jcas.throwFeatMissing("Lingpipe", "edu.cmu.mingtaoz.type.Lingpipe");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Lingpipe);
  }
  /** @generated */    
  public void setLingpipe(int addr, String v) {
        if (featOkTst && casFeat_Lingpipe == null)
      jcas.throwFeatMissing("Lingpipe", "edu.cmu.mingtaoz.type.Lingpipe");
    ll_cas.ll_setStringValue(addr, casFeatCode_Lingpipe, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Lingpipe_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Lingpipe = jcas.getRequiredFeatureDE(casType, "Lingpipe", "uima.cas.String", featOkTst);
    casFeatCode_Lingpipe  = (null == casFeat_Lingpipe) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Lingpipe).getCode();

  }
}



    