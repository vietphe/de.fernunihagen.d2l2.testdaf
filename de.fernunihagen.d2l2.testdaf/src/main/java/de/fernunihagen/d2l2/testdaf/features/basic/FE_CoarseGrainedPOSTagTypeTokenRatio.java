package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
/**
 * 
 * 
 * @author Viet Phe
 */
public class FE_CoarseGrainedPOSTagTypeTokenRatio extends FeatureExtractor_ImplBase{
		
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> CoarseGrainedPOSTagRatioFeatures = new HashSet<Feature>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		Collection<POS> poses = JCasUtil.select(jcas, POS.class);
		Set<String> coarsePOS = new HashSet<>();	
		for (POS p: poses) {
			if(p.getCoarseValue() !=null) {
				coarsePOS.add(p.getCoarseValue());
			}			
		}		
		for(String c : coarsePOS) {
			List<String> words = new ArrayList<>();
			Set<String> wordsType = new HashSet<>();
			for(Token t : tokens) {
				if(t.getPos().getCoarseValue()!=null) {
					if(t.getPos().getCoarseValue().equals(c)) {
						words.add(t.getCoveredText());
						wordsType.add(t.getCoveredText());
					}
				}
				
			}
			CoarseGrainedPOSTagRatioFeatures.add( new Feature(c, (double) wordsType.size()/words.size(), FeatureType.NUMERIC));
		}
		//test
//		for(Token t : tokens) {
//			if(t.getPos().getCoarseValue()!=null) {
//				if(t.getPos().getCoarseValue().equals("PROPN")) {
//					System.out.println(t.getCoveredText());
//				}
//			}
//		}
		return CoarseGrainedPOSTagRatioFeatures;
	}

	@Override
	public String getPublicName() {
		return "CoarseGrainedPOSTagTypeTokenRatio";
	}

}
