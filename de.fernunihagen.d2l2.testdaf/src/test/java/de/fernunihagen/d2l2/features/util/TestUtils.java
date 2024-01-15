package de.fernunihagen.d2l2.features.util;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.fernunihagen.d2l2.testdaf.PreprocessingConfiguration;

public class TestUtils {

	public static JCas getJCasForString(String document, PreprocessingConfiguration config) 
			throws ResourceInitializationException, AnalysisEngineProcessException 
	{
		AnalysisEngineDescription desc = config.getUimaEngineDescription();
		
		// TODO creating the engine is expensive, should probably cache in some way
		AnalysisEngine engine = createEngine(desc);

        JCas jcas = engine.newJCas();
        jcas.setDocumentLanguage(config.getLanguage().code);
        jcas.setDocumentText(document);
        engine.process(jcas);
        
        return jcas;
	}
}
