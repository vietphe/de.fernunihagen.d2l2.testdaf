package de.fernunihagen.d2l2.testdaf.features.basic;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.dkpro.core.tokit.BreakIteratorSegmenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Feature;

import de.fernunihagen.d2l2.features.util.FeatureTestUtil;
import de.fernunihagen.d2l2.testdaf.features.basic.FE_AvgNrOfTokensPerSentence;


public class FE_AvgNrOfTokensPerSentenceTest {

	@Test
	public void avgNrOfTokenPerSentenceTest() throws Exception {
		AnalysisEngine engine = createEngine(BreakIteratorSegmenter.class);
		
		JCas jcas = engine.newJCas();
        jcas.setDocumentLanguage("en");
        jcas.setDocumentText("This is a test. This is a test.");
        engine.process(jcas);

        FE_AvgNrOfTokensPerSentence extractor = new FE_AvgNrOfTokensPerSentence();
        Set<Feature> features = new HashSet<Feature>(extractor.extract(jcas));
        
        Assertions.assertAll(
        		() -> assertEquals(1, features.size()),
        		() -> FeatureTestUtil.assertFeature("FN_" + extractor.getInternalName(), 5.0, features.iterator().next(), 0.0001)
        );
	}
	
}
