package de.fernunihagen.d2l2.testdaf.structures;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.component.NoOpAnnotator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.core.corenlp.CoreNlpSegmenter;
import org.dkpro.core.opennlp.OpenNlpPosTagger;
import org.junit.jupiter.api.Test;
import org.lift.type.Frequency;
import org.lift.type.Structure;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class SE_FrequenciesTest {
	
	@Test
	public void sE_FrequenciesTest() throws Exception {
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
		
		for(Frequency s : JCasUtil.select(jcas, Frequency.class)) {
			System.out.println(s.toString());
		}
		
	}

}
