package de.fernunihagen.d2l2.testdaf.featureSettings;

import java.util.Set;

import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.Structure;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

/**
 * Counts structures
 */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token"})
public class FEL_GenericStructureCounter 
	extends FeatureExtractor_ImplBase
{
	
	private final String structureName;

	// TODO call generic counter instead of duplicating code ...
	public FEL_GenericStructureCounter(String structureName) {
		this.structureName = structureName;
	}
	
	@Override
	public Set<Feature> extract(JCas jcas) 
			throws LiftFeatureExtrationException
	{		
		// TODO parameterize normalization?
		int overallCount = JCasUtil.select(jcas, Token.class).size();
		
		int nrOfFeature = 0;
    	for (Structure s : JCasUtil.select(jcas, Structure.class)) {
    		if (s.getName().equals(structureName)) {
    			nrOfFeature++;
    		}
        }

		//Normalization on total count of words
		double ratio = (double) nrOfFeature / overallCount;
		
		return new Feature("FN_"+ getInternalName(), ratio, FeatureType.NUMERIC).asSet();
	}

	@Override
	public String getPublicName() {
		return this.cleanName("NR_OF_"+structureName + "_PER_NR_OF_TOKEN");
	}
	@Override
	public String getInternalName() {
		return "StructureCounter_" + getPublicName();
	}
	
}

