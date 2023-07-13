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
public class FE_FrequencyBandDeReWo extends FeatureExtractor_ImplBase{
		
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
		int countBand8 = 0;
		int countBand9 = 0;
		int countBand10 = 0;
		int countBand11 = 0;
		int countBand12 = 0;
		int countBand13 = 0;
		int countBand14 = 0;
		int countBand15 = 0;
		int countBand16 = 0;
		int countBand17 = 0;
		int countBand18 = 0;
		int countBand19 = 0;
		int countBand20 = 0;
		
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
			if(f.getFrequencyBand().equals("Band 8")) {
				countBand8++;
			}
			if(f.getFrequencyBand().equals("Band 9")) {
				countBand9++;
			}
			if(f.getFrequencyBand().equals("Band 10")) {
				countBand10++;
			}
			if(f.getFrequencyBand().equals("Band 11")) {
				countBand11++;
			}
			if(f.getFrequencyBand().equals("Band 12")) {
				countBand12++;
			}
			if(f.getFrequencyBand().equals("Band 13")) {
				countBand13++;
			}
			if(f.getFrequencyBand().equals("Band 14")) {
				countBand14++;
			}
			if(f.getFrequencyBand().equals("Band 15")) {
				countBand15++;
			}
			if(f.getFrequencyBand().equals("Band 16")) {
				countBand16++;
			}
			if(f.getFrequencyBand().equals("Band 17")) {
				countBand17++;
			}
			if(f.getFrequencyBand().equals("Band 18")) {
				countBand18++;
			}
			if(f.getFrequencyBand().equals("Band 19")) {
				countBand19++;
			}
			if(f.getFrequencyBand().equals("Band 20")) {
				countBand20++;
			}			
		}
		frequencyFeatures.add( new Feature("Band1Ratio", (double)countBand1/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band2Ratio", (double)countBand2/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band3Ratio", (double)countBand3/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band4Ratio", (double)countBand4/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band5Ratio", (double)countBand5/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band6Ratio", (double)countBand6/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band7Ratio", (double)countBand7/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band8Ratio", (double)countBand8/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band9Ratio", (double)countBand9/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band10Ratio", (double)countBand10/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band11Ratio", (double)countBand11/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band12Ratio", (double)countBand12/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band13Ratio", (double)countBand13/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band14Ratio", (double)countBand14/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band15Ratio", (double)countBand15/frequencies.size(), FeatureType.NUMERIC));
		
		frequencyFeatures.add( new Feature("Band16Ratio", (double)countBand16/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band17Ratio", (double)countBand17/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band18Ratio", (double)countBand18/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band19Ratio", (double)countBand19/frequencies.size(), FeatureType.NUMERIC));
		frequencyFeatures.add( new Feature("Band20Ratio", (double)countBand20/frequencies.size(), FeatureType.NUMERIC));
		
		return frequencyFeatures;
	}

	@Override
	public String getPublicName() {
		return "FrequencyBandRatio";
	}

}
