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
public class FE_FineGrainedPOSTagTypeTokenRatio extends FeatureExtractor_ImplBase{
		
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> features = new HashSet<Feature>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		Collection<POS> poses = JCasUtil.select(jcas, POS.class);
		Set<String> POSValues = new HashSet<>();	
		for (POS p: poses) {
			if(p.getPosValue() !=null) {
				POSValues.add(p.getPosValue());
			}			
		}		
		for(String c : POSValues) {
			List<String> words = new ArrayList<>();
			Set<String> wordsType = new HashSet<>();
			for(Token t : tokens) {
				if(t.getPos().getPosValue()!=null) {
					if(t.getPos().getPosValue().equals(c)) {
						words.add(t.getCoveredText());
						wordsType.add(t.getCoveredText());
					}
				}
				
			}
			features.add( new Feature(c, (double) wordsType.size()/words.size(), FeatureType.NUMERIC));
		}
		//test
//		for(Token t : tokens) {
//			if(t.getPos().getPosValue()!=null) {
//				if(t.getPos().getPosValue().equals("PDAT")) {
//					System.out.println(t.getCoveredText());
//				}
//			}
//		}
		return features;
	}

	@Override
	public String getPublicName() {
		return "FineGrainedPOSTagTypeTokenRatio";
	}

}
