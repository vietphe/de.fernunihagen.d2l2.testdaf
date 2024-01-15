package de.fernunihagen.d2l2.testdaf.features.basic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.Structure;

import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
/**
 * Counts the occurrence of the frequency bands.
 * 
 * @author Viet Phe
 */
public class FE_CoveredByWordlist extends FeatureExtractor_ImplBase{
	
	//TODO: adjust filepath;
	protected String mapFilePath = "resources/wordlists/MergedConcreteness.csv";
	
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> coveredByWordlistFeatures = new HashSet<Feature>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		//word
		ArrayList<String> words = new ArrayList<>();
		ArrayList<String> coveredWords = new ArrayList<>();
		Set<String> wordsTypeBased = new HashSet<>();
		Set<String> coveredWordsTypeBased = new HashSet<>();
		Map<String, Double> dict = new HashMap<>();
		try {
			dict = readMapping(mapFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Token token: tokens) {
			if(token.getPos().getCoarseValue()!= null) {
				if(!token.getPos().getCoarseValue().equals("PUNCT")) {
					String editedLemma = "";
					Lemma lemma = token.getLemma();
					//because lemma values of actual lemmatization are only in lowercase
					if(Character.isUpperCase(lemma.getCoveredText().charAt(0))) {
						editedLemma = lemma.getValue().substring(0, 1).toUpperCase()+lemma.getValue().substring(1);
					}else {
						editedLemma = lemma.getValue();
					}
					words.add(editedLemma);
					wordsTypeBased.add(editedLemma);			
				}			
			}			
		}
		for(String s : words) {
			if(dict.containsKey(s)) {
				coveredWords.add(s);
			}
		}
		for(String s : wordsTypeBased) {
			if(dict.containsKey(s)) {
				coveredWordsTypeBased.add(s);
			}
		}
//		System.out.println("allTypebasedLemmas:"+allTypebasedLemmas.size());
//		System.out.println("anzahl der covered word typebase:"+coveredByWordlist.size());
		coveredByWordlistFeatures.add( new Feature("CoveredWordType", (double)coveredWordsTypeBased.size()/wordsTypeBased.size(), FeatureType.NUMERIC));
		coveredByWordlistFeatures.add( new Feature("CoveredWord", (double)coveredWords.size()/words.size(), FeatureType.NUMERIC));
		
		//content word
		List<String> contentWords = new ArrayList<>();
		Set<String> contentWordsTypeBased = new HashSet<>();
		List<String> coveredContentWords = new ArrayList<>();
		Set<String> coveredContentWordsTypeBased = new HashSet<>();
		for (Token token: tokens) {
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
					contentWords.add(editedLemma);
					contentWordsTypeBased.add(editedLemma);
					System.out.println(editedLemma);
				}
			}
		}
		for(String s : contentWords) {
			if(dict.containsKey(s)) {
				coveredContentWords.add(s);
			}
		}
		for(String s : contentWordsTypeBased) {
			if(dict.containsKey(s)) {
				coveredContentWordsTypeBased.add(s);
			}
		}
		System.out.println("coveredContentWords "+ coveredContentWords.size());
		System.out.println("coveredContentWordsTypeBased "+ coveredContentWordsTypeBased.size());
		coveredByWordlistFeatures.add( new Feature("CoveredContentWordType", (double)coveredContentWordsTypeBased.size()/contentWordsTypeBased.size(), FeatureType.NUMERIC));
		coveredByWordlistFeatures.add( new Feature("CoveredContentWord", (double)coveredContentWords.size()/contentWords.size(), FeatureType.NUMERIC));
		
		return coveredByWordlistFeatures;
	}

	@Override
	public String getPublicName() {
		return "CoveredByWordlist";
	}
	
	protected Map<String, Double> readMapping(String listFilePath) throws IOException {

		Map<String, Double> map = new HashMap<String, Double>();
		for (String line : FileUtils.readLines(new File(listFilePath), "UTF-8")) {
			String[] parts = line.split(";");
			map.put(parts[0], Double.parseDouble(parts[1]));
		}
		return map;
	}
	

}
