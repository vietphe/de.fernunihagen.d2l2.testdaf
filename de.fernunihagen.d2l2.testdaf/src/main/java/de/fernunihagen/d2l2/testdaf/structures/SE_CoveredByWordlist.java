package de.fernunihagen.d2l2.testdaf.structures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.lift.type.Frequency;
import org.lift.type.Structure;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

/**
 * annotates for each token its frequency according to a provided dictionary
 */
@TypeCapability(inputs = {"de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token"})
public class SE_CoveredByWordlist
extends JCasAnnotator_ImplBase
{

	public static final String PARAM_LIST_FILE_PATH = "mapFilePath";
	@ConfigurationParameter(name = PARAM_LIST_FILE_PATH, mandatory = true)
	protected String mapFilePath;

	public static final String PARAM_USE_LEMMAS = "useLemmas";
	@ConfigurationParameter(name = PARAM_USE_LEMMAS, mandatory = true, defaultValue = "false")
	private boolean useLemmas;
	
	//TODO: Variable, ob alles gelowercased werden muss
	
	protected Map<String, Double> dict;
	
	public void initialize(UimaContext context)
			throws ResourceInitializationException
	{
		super.initialize(context);

		try {
			// if list file is not set, try to load default for language
			if (mapFilePath == "" || mapFilePath == null) {
				Path path = Paths.get(mapFilePath);
				if (Files.notExists(path)) {
					throw new ResourceInitializationException(new Throwable("Cannot load frequency dictionary"));
				}
				mapFilePath = path.toString();
			}

			dict = readMapping(mapFilePath);

		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
	}

	protected Map<String, Double> readMapping(String listFilePath) throws IOException {

		Map<String, Double> map = new HashMap<String, Double>();
		for (String line : FileUtils.readLines(new File(listFilePath), "UTF-8")) {
			String[] parts = line.split(";");
			map.put(parts[0], Double.parseDouble(parts[1]));
		}
		return map;
	}

	@Override
	public void process(JCas jcas) 
			throws AnalysisEngineProcessException
	{		
		if (useLemmas) {
			for (Token token: JCasUtil.select(jcas, Token.class)) {
				if(token.getPos().getCoarseValue()!= null) {
					//check only content words
					if(token.getPos().getCoarseValue().equals("NOUN")||token.getPos().getCoarseValue().equals("ADJ")||token.getPos().getCoarseValue().equals("VERB")) {
						Lemma lemma = token.getLemma();
						//because lemma values of actual lemmatization are only in lowercase
						String editedLemma = "";
						if(Character.isUpperCase(lemma.getCoveredText().charAt(0))) {
							editedLemma = lemma.getValue().substring(0, 1).toUpperCase()+lemma.getValue().substring(1);
						}else {
							editedLemma = lemma.getValue();
						}
//						System.out.println(editedLemma);
//						System.out.println(lemma.getValue());
						if (dict.containsKey(editedLemma)) {
							Structure s = new Structure(jcas,lemma.getBegin(),lemma.getEnd());
							s.setName("is in MC List");
							s.addToIndexes();
						}
					}
				}
			}
//			for (Lemma lemma : JCasUtil.select(jcas, Lemma.class)) {
//				//because lemma values of actual lemmatization are only in lowercase
//				String editedLemma = "";
//				if(Character.isUpperCase(lemma.getCoveredText().charAt(0))) {
//					editedLemma = lemma.getValue().substring(0, 1).toUpperCase()+lemma.getValue().substring(1);
//				}else {
//					editedLemma = lemma.getValue();
//				}
////				System.out.println(editedLemma);
////				System.out.println(lemma.getValue());
//				if (dict.containsKey(editedLemma)) {
//					Structure s = new Structure(jcas,lemma.getBegin(),lemma.getEnd());
//					s.setName("is in MC List");
//					s.addToIndexes();
//				}
//			}	
		}
		else {
			for (Token token : JCasUtil.select(jcas, Token.class)) {
				if (dict.containsKey(token.getCoveredText().toLowerCase())) {
					Structure s = new Structure(jcas,token.getBegin(),token.getEnd());
					s.setName("is in MC List");
					s.addToIndexes();
				}
			}			
		}
	}
}