package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.featureSettings.FEL_GenericStructureCounter;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;

public class FE_AvgNrOfCompoundPerToken extends FeatureExtractor_ImplBase{
	private FEL_GenericStructureCounter counter;
	public FE_AvgNrOfCompoundPerToken() {
		counter = new FEL_GenericStructureCounter("Compound");
	}
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		return counter.extract(jcas);
	}
	@Override
	public String getPublicName() {
		return counter.getPublicName();
	}
	
	@Override
	public String getInternalName() {
		return counter.getInternalName();
	}
}
