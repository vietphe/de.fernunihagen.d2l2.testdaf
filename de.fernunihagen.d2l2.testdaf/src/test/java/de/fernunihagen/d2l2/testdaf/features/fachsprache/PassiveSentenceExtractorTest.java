package de.fernunihagen.d2l2.testdaf.features.fachsprache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase;
import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase.ParserType;

import java.util.HashSet;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Feature;
import de.fernunihagen.d2l2.features.util.FeatureTestUtil;


public class PassiveSentenceExtractorTest {
	@Test
	public void passiveSentenceExtractorTest() throws Exception {
		AnalysisEngine engine = TestdafTestBase.getPreprocessingEngine("de",ParserType.noParser);
		
		JCas jcas = engine.newJCas();
        jcas.setDocumentLanguage("de");
        jcas.setDocumentText("Die Aufgabe wurde gelöst.");
        engine.process(jcas);
        PassiveSentenceExtractor psExtractor = new PassiveSentenceExtractor();
        Set<Feature> features = new HashSet<Feature>(psExtractor.extract(jcas));
//        Iterator<Feature> it = features.iterator();
//        while(it.hasNext()) {
//        	System.out.println(it.next());
//        }
        
        Assertions.assertAll(
        		() -> assertEquals(7, features.size()),
        		() -> FeatureTestUtil.assertFeature("FrequencyOfTypicalPassiveSentence", 1.0, features.iterator().next())
        );
	}
	
}
