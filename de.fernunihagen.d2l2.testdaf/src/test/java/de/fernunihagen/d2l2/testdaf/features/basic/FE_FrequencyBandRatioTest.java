package de.fernunihagen.d2l2.testdaf.features.basic;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Feature;

import de.fernunihagen.d2l2.features.util.FeatureTestUtil;
import de.fernunihagen.d2l2.testdaf.structures.SE_Frequencies;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class FE_FrequencyBandRatioTest {
	
	@Test
	public void frequencyBandRatioTest()
		throws Exception
	{
		
		AnalysisEngine engine = AnalysisEngineFactory.createEngine(SE_Frequencies.class, SE_Frequencies.PARAM_LIST_FILE_PATH, "resources/frequencyLists/OpenSubtitlesDE_WF.csv");
		JCas jcas = engine.newJCas();
		jcas.setDocumentLanguage("de");
		jcas.setDocumentText("Das Beispiel war schlecht");
		
		
		Token t1 = new Token(jcas,0,3);
		t1.addToIndexes();
		Token t2 = new Token(jcas,4,12);
		t2.addToIndexes();
		Token t3 = new Token(jcas,13,16);
		t3.addToIndexes();
		Token t4 = new Token(jcas,17,25);
		t4.addToIndexes();
		
		engine.process(jcas);
		
		FE_FrequencyBandRatio fe = new FE_FrequencyBandRatio();
		Set<Feature> features = fe.extract(jcas);
		Assertions.assertAll(
		        () -> assertEquals(9, features.size()),
		        () -> FeatureTestUtil.assertFeature("Band4Ratio", 0.0, features.iterator().next(), 0.00001)
				);
	}
}
