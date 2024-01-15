package de.fernunihagen.d2l2.testdaf.structures;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.jupiter.api.Test;
import org.lift.type.Structure;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SE_ConnectivesTest {

	@Test
	public void connectives_test()
		throws Exception
	{
		
		AnalysisEngine engine = createEngine(
				SE_Connectives.class,
				SE_Connectives.PARAM_LANGUAGE, "en");
		
        JCas jcas = engine.newJCas();
        engine.process(jcas);
        jcas.setDocumentText("after ,");

        Token t1 = new Token(jcas, 0, 4);
        t1.addToIndexes();
        
        Token t2 = new Token(jcas, 5, 6);
        t2.addToIndexes();
        

		engine.process(jcas);
		
		for (Structure s : JCasUtil.select(jcas, Structure.class)) {
			System.out.println(s);
		}
	}
}
