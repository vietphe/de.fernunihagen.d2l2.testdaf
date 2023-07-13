package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.Structure;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Compound;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class FE_AvgNrOfCompoundPerToken extends FeatureExtractor_ImplBase{
	public static String NR_OF_COMPOUND_PER_NR_OF_NOUN = "NrOfCompoundPerNrOfNoun";
	public static String NR_OF_TYPE_BASED_COMPOUND_PER_NR_OF_NOUN = "NrOfTypeBasedCompoundPerNrOfNoun";
	
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> features = new HashSet<>();
		List<Integer> nouns = new ArrayList<>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		for (Token token : tokens) {
			if (token.getPos().getCoarseValue() != null && token.getPos().getCoarseValue().equals("NOUN"))
			nouns.add(token.getBegin());
		}
		int nrOfCompound = 0;
		for (Structure s : JCasUtil.select(jcas, Structure.class)) {
    		if (s.getName().equals("Compound")) {
    			nrOfCompound++;
    		}
        }
		
		double ratio = (double) nrOfCompound/nouns.size();		
		features.add(new Feature(NR_OF_COMPOUND_PER_NR_OF_NOUN, ratio, FeatureType.NUMERIC));
		
		//!!! here for type-based	
		Map<String,Integer> typeBasedNoun = new HashMap<>();
		//to remove tokens with the same covered text
		for (Token token : tokens) {
			if (token.getPos().getCoarseValue() != null && token.getPos().getCoarseValue().equals("NOUN"))
				typeBasedNoun.put(token.getCoveredText().toLowerCase(), token.getBegin());
		}
		ArrayList<Integer> uniqueNoun = new ArrayList<>();
		Iterator<Map.Entry<String, Integer>> it = typeBasedNoun.entrySet().iterator();		
		while (it.hasNext()) {
			Map.Entry<String, Integer> c = it.next();
			uniqueNoun.add(c.getValue());
		}
		Collection<Compound> compounds = JCasUtil.select(jcas, Compound.class);
		int countUniqueCompound = 0;
		for (Compound c : compounds) {
			if (uniqueNoun.contains(c.getBegin())) {
				countUniqueCompound++;
			}
		}	
		double ratio2 = (double) countUniqueCompound/uniqueNoun.size();
		features.add(new Feature(NR_OF_TYPE_BASED_COMPOUND_PER_NR_OF_NOUN, ratio2, FeatureType.NUMERIC));
		return features;
	}
	@Override
	public String getPublicName() {
		return "CompoundRatio";
	}
	
}
