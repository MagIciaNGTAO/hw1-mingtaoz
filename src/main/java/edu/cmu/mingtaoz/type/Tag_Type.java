
/* First created by JCasGen Sun Oct 07 21:57:33 EDT 2012 */
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
public class Tag_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Tag_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Tag_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Tag(addr, Tag_Type.this);
  			   Tag_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Tag(addr, Tag_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Tag.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.mingtaoz.type.Tag");
 
  /** @generated */
  final Feature casFeat_Tag;
  /** @generated */
  final int     casFeatCode_Tag;
  /** @generated */ 
  public String getTag(int addr) {
        if (featOkTst && casFeat_Tag == null)
      jcas.throwFeatMissing("Tag", "edu.cmu.mingtaoz.type.Tag");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Tag);
  }
  /** @generated */    
  public void setTag(int addr, String v) {
        if (featOkTst && casFeat_Tag == null)
      jcas.throwFeatMissing("Tag", "edu.cmu.mingtaoz.type.Tag");
    ll_cas.ll_setStringValue(addr, casFeatCode_Tag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Tag_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Tag = jcas.getRequiredFeatureDE(casType, "Tag", "uima.cas.String", featOkTst);
    casFeatCode_Tag  = (null == casFeat_Tag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Tag).getCode();

  }
}



    