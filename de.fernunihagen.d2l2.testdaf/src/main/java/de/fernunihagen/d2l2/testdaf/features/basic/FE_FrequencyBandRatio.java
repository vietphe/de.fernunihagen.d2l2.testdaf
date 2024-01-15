package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.ArrayList;
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
		int bandsValue = 0;
		
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
				bandsValue += 1;
			}
			if(f.getFrequencyBand().equals("Band 2")) {
				countBand2++;
				bandsValue += 2;
			}
			if(f.getFrequencyBand().equals("Band 3")) {
				countBand3++;
				bandsValue += 3;
			}
			if(f.getFrequencyBand().equals("Band 4")) {
				countBand4++;
				bandsValue += 4;
			}
			if(f.getFrequencyBand().equals("Band 5")) {
				countBand5++;
				bandsValue += 5;
			}
			if(f.getFrequencyBand().equals("Band 6")) {
				countBand6++;
				bandsValue += 6;
			}
			if(f.getFrequencyBand().equals("Band 7")) {
				countBand7++;
				bandsValue += 7;
			}
		}
		double avgBand = (double)bandsValue/frequencies.size();
		double tempBand = 0;
		
		for (Frequency f : frequencies) {
			double tempAdd = 0;
			if(f.getFrequencyBand().equals("Band 1")) {
				tempAdd = 1 - avgBand;
				tempBand += Math.pow(tempAdd,2);				
			}
			if(f.getFrequencyBand().equals("Band 2")) {
				tempAdd = 2 - avgBand;
				tempBand += Math.pow(tempAdd,2);
				
			}
			if(f.getFrequencyBand().equals("Band 3")) {
				tempAdd = 3 - avgBand;
				tempBand += Math.pow(tempAdd,2);
			}
			if(f.getFrequencyBand().equals("Band 4")) {
				tempAdd = 4 - avgBand;
				tempBand += Math.pow(tempAdd,2);
			}
			if(f.getFrequencyBand().equals("Band 5")) {
				tempAdd = 5 - avgBand;
				tempBand += Math.pow(tempAdd,2);
			}
			if(f.getFrequencyBand().equals("Band 6")) {
				tempAdd = 6 - avgBand;
				tempBand += Math.pow(tempAdd,2);
			}
			if(f.getFrequencyBand().equals("Band 7")) {
				tempAdd = 7 - avgBand;
				tempBand += Math.pow(tempAdd,2);
			}
		}
		
		double stndDeviation = Math.sqrt(tempBand / frequencies.size());
		frequencyFeatures.add( new Feature("Band1Ratio", (double)countBand1/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band2Ratio", (double)countBand2/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band3Ratio", (double)countBand3/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band4Ratio", (double)countBand4/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band5Ratio", (double)countBand5/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band6Ratio", (double)countBand6/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band7Ratio", (double)countBand7/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Avg_Band", (double)bandsValue/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("STANDARD_DEVIATION_OF_FREQUENCY_BAND", stndDeviation, FeatureType.NUMERIC));

		return frequencyFeatures;
	}

	@Override
	public String getPublicName() {
		return "FrequencyBandRatio";
	}

}
