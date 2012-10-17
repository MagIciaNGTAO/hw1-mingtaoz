
/* First created by JCasGen Tue Oct 16 20:17:12 EDT 2012 */
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
 * Updated by JCasGen Tue Oct 16 20:17:12 EDT 2012
 * @generated */
public class Banner_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Banner_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Banner_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Banner(addr, Banner_Type.this);
  			   Banner_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Banner(addr, Banner_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Banner.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.mingtaoz.type.Banner");
 
  /** @generated */
  final Feature casFeat_Banner;
  /** @generated */
  final int     casFeatCode_Banner;
  /** @generated */ 
  public String getBanner(int addr) {
        if (featOkTst && casFeat_Banner == null)
      jcas.throwFeatMissing("Banner", "edu.cmu.mingtaoz.type.Banner");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Banner);
  }
  /** @generated */    
  public void setBanner(int addr, String v) {
        if (featOkTst && casFeat_Banner == null)
      jcas.throwFeatMissing("Banner", "edu.cmu.mingtaoz.type.Banner");
    ll_cas.ll_setStringValue(addr, casFeatCode_Banner, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Banner_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Banner = jcas.getRequiredFeatureDE(casType, "Banner", "uima.cas.String", featOkTst);
    casFeatCode_Banner  = (null == casFeat_Banner) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Banner).getCode();

  }
}



    