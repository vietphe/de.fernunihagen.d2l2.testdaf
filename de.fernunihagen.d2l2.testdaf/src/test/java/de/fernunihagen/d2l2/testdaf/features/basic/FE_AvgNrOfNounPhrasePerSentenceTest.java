package de.fernunihagen.d2l2.testdaf.features.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.jcas.JCas;
import org.dkpro.core.io.xmi.XmiReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Feature;

import de.fernunihagen.d2l2.features.util.FeatureTestUtil;
import de.fernunihagen.d2l2.testdaf.features.basic.FE_AvgNrOfNounPhrasesPerSentence;


public class FE_AvgNrOfNounPhrasePerSentenceTest {

	@Test
	public void avgNrOfNounPhrasePerSentenceTest() throws Exception {
		
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
				XmiReader.class,
				XmiReader.PARAM_SOURCE_LOCATION, "src/test/resources/xmi/essay_en.txt.xmi"
		);
		JCas jcas = new JCasIterable(reader).iterator().next();
		
        FE_AvgNrOfNounPhrasesPerSentence extractor = new FE_AvgNrOfNounPhrasesPerSentence();
        Set<Feature> features = new HashSet<Feature>(extractor.extract(jcas));
        
        Assertions.assertAll(
        		() -> assertEquals(1, features.size()),
                () -> FeatureTestUtil.assertFeature("FN_" + extractor.getInternalName(), 6.6875, features.iterator().next(), 0.0001)
        );     
	}
	
}
