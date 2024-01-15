package de.fernunihagen.d2l2.testdaf.features.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Feature;

import de.fernunihagen.d2l2.features.util.FeatureTestUtil;
import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase;
import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase.ParserType;


public class FE_LesbarkeitsindexTest {
	@Test
	public void lesbarkeitsindexTest() throws Exception {
		AnalysisEngine engine = TestdafTestBase.getPreprocessingEngine("de",ParserType.noParser);
		
		JCas jcas = engine.newJCas();
        jcas.setDocumentLanguage("de");
        jcas.setDocumentText("Kaum eine Grünfläche in Deutschland entwickelt sich daher so ungestört "
        		+ "wie der Autobahnrand, der vielen Pflanzen und Tieren Schutz bietet.");
        engine.process(jcas);
        FE_Lesbarkeitsindex extractor = new FE_Lesbarkeitsindex();
        Set<Feature> features = new HashSet<Feature>(extractor.extract(jcas));       
        Assertions.assertAll(
        		() -> assertEquals(4, features.size()),
        		() -> FeatureTestUtil.assertFeature("WSTF2", 8.718, features.iterator().next(),0.0001)
        );
	}
	

}
