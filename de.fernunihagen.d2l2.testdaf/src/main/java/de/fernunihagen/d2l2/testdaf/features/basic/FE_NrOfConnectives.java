package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Set;

import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.featureSettings.FEL_AnnotationRatio;
import de.fernunihagen.d2l2.testdaf.featureSettings.FEL_GenericStructureCounter;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.fernunihagen.d2l2.testdaf.structures.SE_Connectives;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.NC;

/**
 * Extracts the total number of characters.
 */
@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token"})
public class FE_NrOfConnectives 
	extends FeatureExtractor_ImplBase
{	
	private FEL_GenericStructureCounter counter;
	
	public FE_NrOfConnectives() {
		counter = new FEL_GenericStructureCounter("Connectives");
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
