package de.fernunihagen.d2l2.testdaf.structures;

import java.io.IOException;
import java.net.URISyntaxException;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.lift.api.StructureExtractor;

import de.fernunihagen.d2l2.testdaf.structure.ruta.RutaUtil;


public class SE_Comma 
	extends StructureExtractor
{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String rutaScript = 
		"IMPORT org.lift.type.Structure FROM desc.type.LiFT;" +
		"IMPORT de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token FROM desc.type.LexicalUnits;" +
		"Token{REGEXP(\",\")-> Structure};";

		try {
			Pair<AnalysisEngineDescription, TypeSystemDescription> descs = RutaUtil.initRutaFE(rutaScript);
			AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(descs.getKey());

			ae.process(jcas);			
		} catch (InvalidXMLException | ResourceInitializationException | IOException | RecognitionException
				| URISyntaxException e) {
			throw new AnalysisEngineProcessException();
		}
	}

	@Override
	public String getPublicStructureName() {
		return "Comma";
	}

}