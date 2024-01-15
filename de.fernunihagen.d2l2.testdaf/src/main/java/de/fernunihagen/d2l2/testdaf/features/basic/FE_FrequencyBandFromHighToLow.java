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
public class FE_FrequencyBandFromHighToLow extends FeatureExtractor_ImplBase{
		
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> frequencyFeatures = new HashSet<Feature>();
		Collection<Frequency> frequencies = JCasUtil.select(jcas, Frequency.class);
		
		int Band1 = 0;
		int Band2 = 0;
		int Band3 = 0;
		int Band4 = 0;
		int Band5 = 0;
		int Band6 = 0;
		int Band7 = 0;
		for (Frequency f : frequencies) {
			if(f.getFrequencyBand().equals("Band 1")) {
				Band1++;
			}
			if(f.getFrequencyBand().equals("Band 2")) {
				Band2++;
			}
			if(f.getFrequencyBand().equals("Band 3")) {
				Band3++;
			}
			if(f.getFrequencyBand().equals("Band 4")) {
				Band4++;
			}
			if(f.getFrequencyBand().equals("Band 5")) {
				Band5++;
			}
			if(f.getFrequencyBand().equals("Band 6")) {
				Band6++;
			}
			if(f.getFrequencyBand().equals("Band 7")) {
				Band7++;
			}
		}
			//sort value
		int temp, size;
		int [] array = {Band1, Band2, Band3, Band4, Band5, Band6, Band7};
		size = array.length;
//		for (int i = 0; i < array.length; i++) {
//			System.out.print(array[i]+" ");
//		}
//		System.out.println();
		for(int i = 0; i<size; i++ ){
			for(int j = i+1; j<size; j++){
			
				if(array[i]>array[j]){
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
//		for (int i = 0; i < array.length; i++) {
//			System.out.print(array[i]+" ");
//		}
//		System.out.println();
//		
		if(array[size-1] == Band1) {
			frequencyFeatures.add( new Feature("1Position", "Band 1", FeatureType.STRING));
		}else if(array[size-1] == Band2) {
			frequencyFeatures.add( new Feature("1Position", "Band 2", FeatureType.STRING));
		}else if(array[size-1] == Band3) {
			frequencyFeatures.add( new Feature("1Position", "Band 3", FeatureType.STRING));
		}else if(array[size-1] == Band4) {
			frequencyFeatures.add( new Feature("1Position", "Band 4", FeatureType.STRING));
		}else if(array[size-1] == Band5) {
			frequencyFeatures.add( new Feature("1Position", "Band 5", FeatureType.STRING));
		}else if(array[size-1] == Band6) {
			frequencyFeatures.add( new Feature("1Position", "Band 6", FeatureType.STRING));
		}else {
			frequencyFeatures.add( new Feature("1Position", "Band 7", FeatureType.STRING));
		}
		//2.
		if(array[size-2] == Band1) {
			frequencyFeatures.add( new Feature("2Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-2] == Band2) {
			frequencyFeatures.add( new Feature("2Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-2] == Band3) {
			frequencyFeatures.add( new Feature("2Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-2] == Band4) {
			frequencyFeatures.add( new Feature("2Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-2] == Band5) {
			frequencyFeatures.add( new Feature("2Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-2] == Band6) {
			frequencyFeatures.add( new Feature("2Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-2] == Band7) {
			frequencyFeatures.add( new Feature("2Position", "Band 7", FeatureType.STRING));
		}
		
		//3.
		if(array[size-3] == Band1) {
			frequencyFeatures.add( new Feature("3Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-3] == Band2) {
			frequencyFeatures.add( new Feature("3Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-3] == Band3) {
			frequencyFeatures.add( new Feature("3Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-3] == Band4) {
			frequencyFeatures.add( new Feature("3Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-3] == Band5) {
			frequencyFeatures.add( new Feature("3Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-3] == Band6) {
			frequencyFeatures.add( new Feature("3Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-3] == Band7) {
			frequencyFeatures.add( new Feature("3Position", "Band 7", FeatureType.STRING));
		}
		
		//4.
		if(array[size-4] == Band1) {
			frequencyFeatures.add( new Feature("4Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-4] == Band2) {
			frequencyFeatures.add( new Feature("4Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-4] == Band3) {
			frequencyFeatures.add( new Feature("4Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-4] == Band4) {
			frequencyFeatures.add( new Feature("4Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-4] == Band5) {
			frequencyFeatures.add( new Feature("4Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-4] == Band6) {
			frequencyFeatures.add( new Feature("4Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-4] == Band7) {
			frequencyFeatures.add( new Feature("4Position", "Band 7", FeatureType.STRING));
		}
		
		//5.
		if(array[size-5] == Band1) {
			frequencyFeatures.add( new Feature("5Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-5] == Band2) {
			frequencyFeatures.add( new Feature("5Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-5] == Band3) {
			frequencyFeatures.add( new Feature("5Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-5] == Band4) {
			frequencyFeatures.add( new Feature("5Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-5] == Band5) {
			frequencyFeatures.add( new Feature("5Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-5] == Band6) {
			frequencyFeatures.add( new Feature("5Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-5] == Band7) {
			frequencyFeatures.add( new Feature("5Position", "Band 7", FeatureType.STRING));
		}
		
		//6.
		if(array[size-6] == Band1) {
			frequencyFeatures.add( new Feature("6Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-6] == Band2) {
			frequencyFeatures.add( new Feature("6Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-6] == Band3) {
			frequencyFeatures.add( new Feature("6Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-6] == Band4) {
			frequencyFeatures.add( new Feature("6Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-6] == Band5) {
			frequencyFeatures.add( new Feature("6Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-6] == Band6) {
			frequencyFeatures.add( new Feature("6Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-6] == Band7) {
			frequencyFeatures.add( new Feature("6Position", "Band 7", FeatureType.STRING));
		}
		
		//7.
		if(array[size-7] == Band1) {
			frequencyFeatures.add( new Feature("7Position", "Band 1", FeatureType.STRING));
		}
		if(array[size-7] == Band2) {
			frequencyFeatures.add( new Feature("7Position", "Band 2", FeatureType.STRING));
		}
		if(array[size-7] == Band3) {
			frequencyFeatures.add( new Feature("7Position", "Band 3", FeatureType.STRING));
		}
		if(array[size-7] == Band4) {
			frequencyFeatures.add( new Feature("7Position", "Band 4", FeatureType.STRING));
		}
		if(array[size-7] == Band5) {
			frequencyFeatures.add( new Feature("7Position", "Band 5", FeatureType.STRING));
		}
		if(array[size-7] == Band6) {
			frequencyFeatures.add( new Feature("7Position", "Band 6", FeatureType.STRING));
		}
		if(array[size-7] == Band7) {
			frequencyFeatures.add( new Feature("7Position", "Band 7", FeatureType.STRING));
		}
		
//		frequencyFeatures.add( new Feature("1.", ""+Band1, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("2.", ""+Band2, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("3.", ""+Band3, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("4.", ""+Band4, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("5.", ""+Band5, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("6.", ""+Band6, FeatureType.STRING));
//		frequencyFeatures.add( new Feature("7.", ""+Band7, FeatureType.STRING));
		return frequencyFeatures;
	}

	@Override
	public String getPublicName() {
		return "FrequencyBandFromHighToLow";
	}

}
