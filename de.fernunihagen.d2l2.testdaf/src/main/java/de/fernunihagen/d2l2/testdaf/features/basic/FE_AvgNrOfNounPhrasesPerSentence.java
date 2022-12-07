package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.featureSettings.FEL_AnnotationRatio;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.NC;

public class FE_AvgNrOfNounPhrasesPerSentence 
	extends FeatureExtractor_ImplBase
{

	private FEL_AnnotationRatio ratioExtractor;
	
	public FE_AvgNrOfNounPhrasesPerSentence() 
	{
		ratioExtractor = new FEL_AnnotationRatio(
			NC.class.getName(),
			Sentence.class.getName()
		);
	}
	
	@Override
	public Set<Feature> extract(JCas jcas) 
			throws LiftFeatureExtrationException
	{		
		return ratioExtractor.extract(jcas);
	}

	@Override
	public String getPublicName() {
		return ratioExtractor.getPublicName();
	}
	
	@Override
	public String getInternalName() {
		return ratioExtractor.getInternalName();
	}
}