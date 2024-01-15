package de.fernunihagen.d2l2.testdaf.features.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lift.api.Configuration.Language;
import org.lift.api.Feature;

import de.fernunihagen.d2l2.features.util.FeatureTestUtil;
import de.fernunihagen.d2l2.features.util.TestUtils;
import de.fernunihagen.d2l2.testdaf.PreprocessingConfiguration;
import de.fernunihagen.d2l2.testdaf.features.basic.FE_SyntaxTreeDepth;

public class FE_SyntaxTreeDepthTest {

	@Test
	public void syntaxTreeDepthFeatureExtractorTest_DE()
        throws Exception
	{
		String document = "Dies ist ein Satz, der immer verschachltelt und lanweilig zu lesen. Er ist dafür kurz.";
		JCas jcas = TestUtils.getJCasForString(document, new PreprocessingConfiguration(Language.German));

        FE_SyntaxTreeDepth extractor = new FE_SyntaxTreeDepth(Language.German);
        Set<Feature> features = extractor.extract(jcas);

        Assertions.assertAll(
        		() -> assertEquals(2, features.size()),
        		() -> FeatureTestUtil.assertFeatures(FE_SyntaxTreeDepth.AVG_SYNTAX_TREE_DEPTH, 3.0, features, 0.0001),
        		() -> FeatureTestUtil.assertFeatures(FE_SyntaxTreeDepth.TOTAL_SYNTAX_TREE_DEPTH, 6.0, features, 0.0001)
        );
    }
	
	@Test
	public void syntaxTreeDepthFeatureExtractorTest_EN()
        throws Exception
    {
		String document = "This is a sentence that is nested and boring to read. But it is short.";
		JCas jcas = TestUtils.getJCasForString(document, new PreprocessingConfiguration(Language.English));

        FE_SyntaxTreeDepth extractor = new FE_SyntaxTreeDepth(Language.English);
        Set<Feature> features = extractor.extract(jcas);

        Assertions.assertAll(
        		() -> assertEquals(2, features.size()),
        		() -> FeatureTestUtil.assertFeatures(FE_SyntaxTreeDepth.AVG_SYNTAX_TREE_DEPTH, 5.5, features, 0.0001),
        		() -> FeatureTestUtil.assertFeatures(FE_SyntaxTreeDepth.TOTAL_SYNTAX_TREE_DEPTH, 11.0, features, 0.0001)
        );
    }
}