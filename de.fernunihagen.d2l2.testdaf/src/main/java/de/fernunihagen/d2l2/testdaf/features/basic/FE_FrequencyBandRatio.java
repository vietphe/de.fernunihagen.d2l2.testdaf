package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.Frequency;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
/**
 * Counts the occurrence of the frequency bands.
 * 
 * @author Viet Phe
 */
public class FE_FrequencyBandRatio extends FeatureExtractor_ImplBase{
		
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> frequencyFeatures = new HashSet<Feature>();
		Collection<Frequency> frequencies = JCasUtil.select(jcas, Frequency.class);
		
		int countBand1 = 0;
		int countBand2 = 0;
		int countBand3 = 0;
		int countBand4 = 0;
		int countBand5 = 0;
		int countBand6 = 0;
		int countBand7 = 0;
		for (Frequency f : frequencies) {
			if(f.getFrequencyBand().equals("Band 1")) {
				countBand1++;
			}
			if(f.getFrequencyBand().equals("Band 2")) {
				countBand2++;
			}
			if(f.getFrequencyBand().equals("Band 3")) {
				countBand3++;
			}
			if(f.getFrequencyBand().equals("Band 4")) {
				countBand4++;
			}
			if(f.getFrequencyBand().equals("Band 5")) {
				countBand5++;
			}
			if(f.getFrequencyBand().equals("Band 6")) {
				countBand6++;
			}
			if(f.getFrequencyBand().equals("Band 7")) {
				countBand7++;
			}
		}
		frequencyFeatures.add( new Feature("Band1Ratio", (double)countBand1/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band2Ratio", (double)countBand2/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band3Ratio", (double)countBand3/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band4Ratio", (double)countBand4/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band5Ratio", (double)countBand5/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band6Ratio", (double)countBand6/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band7Ratio", (double)countBand7/frequencies.size(), FeatureType.NUMERIC));
		return frequencyFeatures;
	}

	@Override
	public String getPublicName() {
		return "FrequencyBandRatio";
	}

}
