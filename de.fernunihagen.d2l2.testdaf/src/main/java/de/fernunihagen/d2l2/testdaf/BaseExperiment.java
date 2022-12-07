package de.fernunihagen.d2l2.testdaf;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceInitializationException;
import org.dkpro.core.io.xmi.XmiWriter;
import org.lift.api.Configuration.Language;

import de.fernunihagen.d2l2.testdaf.io.TextReader;
import de.fernunihagen.d2l2.testdaf.structures.SE_Connectives;
import de.fernunihagen.d2l2.testdaf.structures.SE_DLTIntegrationCost;
import de.fernunihagen.d2l2.testdaf.structures.SE_DiscourseReferent;
import de.fernunihagen.d2l2.testdaf.structures.SE_FiniteVerb;
import de.fernunihagen.d2l2.testdaf.structures.SE_Frequencies;
import de.fernunihagen.d2l2.testdaf.structures.SE_FrequenciesGoogleWF;



public class BaseExperiment {

	public static void main(String[] args) 
			throws Exception
	{
		String inputPath = "D:\\HiWi\\LiFT\\testdaf\\LV3";
//		runTextExample("src/test/resources/txt/essay_en.txt", Language.English);
		runTextExample(inputPath, Language.German);
//		runTextExample("src/test/resources/txt/news_de.txt", Language.German);
	}
		
	private static void runTextExample(String inputPath, Language language) throws ResourceInitializationException, UIMAException, IOException {
		PreprocessingConfiguration config = new PreprocessingConfiguration(language);
				
//		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
//				TextReader.class,
//				TextReader.PARAM_LANGUAGE, language.code,
//				TextReader.PARAM_SOURCE_LOCATION, sourceDir
//		);
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(
				TextReader.class, TextReader.PARAM_INPUT_FILE,inputPath, TextReader.PARAM_LANGUAGE,"de" );

		AnalysisEngineDescription prepro = config.getUimaEngineDescription();
		AnalysisEngineDescription frequencies = createEngineDescription(SE_Frequencies.class, 
				SE_Frequencies.PARAM_LIST_FILE_PATH, "resources/frequencyLists/OpenSubtitlesDE_WF.csv");
		AnalysisEngineDescription frequenciesGoogleBooksWF = createEngineDescription(SE_FrequenciesGoogleWF.class, 
				SE_FrequenciesGoogleWF.PARAM_LIST_FILE_PATH, "resources/frequencyLists/Google00de_WF.csv");
		AnalysisEngineDescription connectivies = createEngineDescription(SE_Connectives.class, SE_Connectives.PARAM_LANGUAGE, "de",
				SE_Connectives.PARAM_USE_LEMMAS, false);
		AnalysisEngineDescription discourseReferents = createEngineDescription(SE_DiscourseReferent.class, SE_DiscourseReferent.PARAM_LANGUAGE, "de");
		AnalysisEngineDescription dLTIntegrationCosts = createEngineDescription(SE_DLTIntegrationCost.class, SE_DLTIntegrationCost.PARAM_LANGUAGE, "de");
		AnalysisEngineDescription finiteVerbs = createEngineDescription(SE_FiniteVerb.class, SE_FiniteVerb.PARAM_LANGUAGE, "de");
		
		AnalysisEngineDescription analyzer = createEngineDescription(Analyzer.class);
		AnalysisEngineDescription xmiWriter = createEngineDescription(
				XmiWriter.class, 
				XmiWriter.PARAM_OVERWRITE, true,
				XmiWriter.PARAM_TARGET_LOCATION, "target/cas"
		);
		
		SimplePipeline.runPipeline(reader, 
				prepro,
				frequencies,
//				frequenciesGoogleBooksWF,
				connectivies,
				discourseReferents,
				finiteVerbs,
				analyzer,
				xmiWriter
		);
	}
}