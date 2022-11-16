package de.fernunihagen.d2l2.testdaf.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.lift.api.Configuration.Language;
import org.lift.api.Feature;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.features.FE_AvgNrOfCharsPerSentence;
import de.fernunihagen.d2l2.testdaf.features.FE_AvgNrOfCharsPerToken;
import de.fernunihagen.d2l2.testdaf.features.FE_AvgNrOfNounPhrasesPerSentence;
import de.fernunihagen.d2l2.testdaf.features.FE_AvgNrOfTokensPerSentence;
import de.fernunihagen.d2l2.testdaf.features.FE_CommaRatio;
import de.fernunihagen.d2l2.testdaf.features.FE_LexicalDensity;
import de.fernunihagen.d2l2.testdaf.features.FE_LexicalVariation;
import de.fernunihagen.d2l2.testdaf.features.FE_NrOfChars;
import de.fernunihagen.d2l2.testdaf.features.FE_SyntacticVariability;
import de.fernunihagen.d2l2.testdaf.features.FE_SyntaxTreeDepth;
import de.fernunihagen.d2l2.testdaf.features.FE_TraditionalReadabilityMeasures;
import de.fernunihagen.d2l2.testdaf.features.FE_TrigramCounter;
import de.fernunihagen.d2l2.testdaf.features.FE_TypeTokenRatio;
import de.fernunihagen.d2l2.testdaf.features.ReadabilityConfiguration;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.NC;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.PC;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.VC;
import edu.stanford.nlp.patterns.surface.Token;

public class FeatureSetBuilder {

	public static Set<Feature> buildFeatureSet(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> featureSet = new LinkedHashSet<Feature>();
		featureSet.addAll(getAvgNrOfCharsPerSentence(jcas));
		featureSet.addAll(getAvgNrOfCharsPerToken(jcas));
		featureSet.addAll(getNrOfChars(jcas));
		featureSet.addAll(getLexicalDensity(jcas));
		featureSet.addAll(getLexicalVariation(jcas));
		featureSet.addAll(getTypeTokenRatio(jcas));
		featureSet.addAll(getTraditionalReadabilityMeasures(jcas));
		featureSet.addAll(getSyntaxTreeDepth(jcas));
		featureSet.addAll(getAvgNrOfNounPhrasesPerSentence(jcas));
		featureSet.addAll(getAvgNrOfTokensPerSentence(jcas));
		featureSet.addAll(getCommaRatio(jcas));
		featureSet.addAll(getSyntacticVariability(jcas));
		
		
		
		return featureSet;
	}
	
	private static Set<Feature> getAvgNrOfCharsPerSentence(JCas jcas) throws LiftFeatureExtrationException {
		FE_AvgNrOfCharsPerSentence extractor = new FE_AvgNrOfCharsPerSentence();
		return extractor.extract(jcas);
	}
	
	private static Set<Feature> getAvgNrOfCharsPerToken(JCas jcas) throws LiftFeatureExtrationException {
		FE_AvgNrOfCharsPerToken extractor = new FE_AvgNrOfCharsPerToken();
		return extractor.extract(jcas);
	}
	private static Set<Feature> getAvgNrOfNounPhrasesPerSentence(JCas jcas) throws LiftFeatureExtrationException {
		FE_AvgNrOfNounPhrasesPerSentence extractor = new FE_AvgNrOfNounPhrasesPerSentence();
		return extractor.extract(jcas);		
	}
	private static Set<Feature> getAvgNrOfTokensPerSentence(JCas jcas) throws LiftFeatureExtrationException {
		FE_AvgNrOfTokensPerSentence extractor = new FE_AvgNrOfTokensPerSentence();
		return extractor.extract(jcas);		
	}
	private static Set<Feature> getCommaRatio(JCas jcas) throws LiftFeatureExtrationException {
		FE_CommaRatio extractor = new FE_CommaRatio();
		return extractor.extract(jcas);		
	}
	
	
	private static Set<Feature> getLexicalDensity(JCas jcas) throws LiftFeatureExtrationException {
		FE_LexicalDensity extractor = new FE_LexicalDensity();
		return extractor.extract(jcas);
	}	
	private static Set<Feature> getLexicalVariation(JCas jcas) throws LiftFeatureExtrationException {
		FE_LexicalVariation extractor = new FE_LexicalVariation();
		return extractor.extract(jcas);
	}
	private static Set<Feature> getNrOfChars(JCas jcas) throws LiftFeatureExtrationException {
		FE_NrOfChars extractor = new FE_NrOfChars();
		return extractor.extract(jcas);
	}
	
	private static Set<Feature> getSyntacticVariability(JCas jcas) throws LiftFeatureExtrationException {
		FE_SyntacticVariability extractor = new FE_SyntacticVariability();
		return extractor.extract(jcas);		
	}
	private static Set<Feature> getSyntaxTreeDepth(JCas jcas) throws LiftFeatureExtrationException {
		FE_SyntaxTreeDepth extractor = new FE_SyntaxTreeDepth(Language.German);
		return extractor.extract(jcas);		
	}
	
	static List<ReadabilityConfiguration> readabilityConfigurations = new ArrayList<ReadabilityConfiguration>(Arrays.asList(ReadabilityConfiguration.values()));
	
	private static Set<Feature> getTraditionalReadabilityMeasures(JCas jcas) throws LiftFeatureExtrationException {
		FE_TraditionalReadabilityMeasures extractor = new FE_TraditionalReadabilityMeasures(readabilityConfigurations);
		return extractor.extract(jcas);		
	}
	
	private static Set<Feature> getTypeTokenRatio(JCas jcas) throws LiftFeatureExtrationException {
		FE_TypeTokenRatio extractor = new FE_TypeTokenRatio();
		return extractor.extract(jcas);		
	}
	
	
}
