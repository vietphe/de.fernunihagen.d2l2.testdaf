package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.core.readability.measure.ReadabilityMeasures;
import org.dkpro.core.readability.measure.WordSyllableCounter;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;

import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class FE_Lesbarkeitsindex 
	extends FeatureExtractor_ImplBase
	{
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> featureSet = new HashSet<>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		int punct = 0;
		int WoerterMitDreiOderMehrSilben = 0;
		int WoerterMit1Silbe = 0;
		int WoerterMit2Silbe = 0;
		int anzahlWoerter = 0;
		int WoerterMitMehrAls6Buchstaben = 0;
		ReadabilityMeasures readability = new ReadabilityMeasures();
		String documentLanguage = jcas.getDocumentLanguage();
		if (documentLanguage != null) {
			readability.setLanguage(documentLanguage);
		}
		WordSyllableCounter syllableCounter = new WordSyllableCounter(documentLanguage);
		for(Token t : tokens) {
			if(t.getPos().getCoarseValue()!=null) {
				if(t.getPos().getCoarseValue().equals("PUNCT")) {
					punct++;
				}
			}
			if(t.getPos().getCoarseValue()!=null) {
				if(!t.getPos().getCoarseValue().equals("PUNCT")) {
					anzahlWoerter++;
					if(syllableCounter.countSyllables(t.getCoveredText())>2) {
						System.out.println("WoerterMitDreiOderMehrSilben: "+t.getCoveredText());
						WoerterMitDreiOderMehrSilben++;
					}
					
					if(syllableCounter.countSyllables(t.getCoveredText())==1) {
						System.out.println("WoerterMit1Silbe: "+t.getCoveredText());
						WoerterMit1Silbe++;
					}
					if(syllableCounter.countSyllables(t.getCoveredText())==2) {
						System.out.println("WoerterMit2Silbe: "+t.getCoveredText());
						WoerterMit2Silbe++;
					}
					if(t.getCoveredText().length()>6) {
						System.out.println("WoerterMitMehrAls6Buchstaben: "+t.getCoveredText());
						WoerterMitMehrAls6Buchstaben++;
					}
				}
			}
		}
		System.out.println("WoerterMitDreiOderMehrSilben"+WoerterMitDreiOderMehrSilben);
		System.out.println("WoerterMit1Silbe"+WoerterMit1Silbe);
		System.out.println("WoerterMit2Silbe"+WoerterMit2Silbe);
		System.out.println("anzahlWoerter"+anzahlWoerter);
		System.out.println("WoerterMitMehrAls6Buchstaben"+WoerterMitMehrAls6Buchstaben);
		
		Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);
		int anzahlSentence = sentences.size();
		System.out.println("anzahlSentence"+anzahlSentence);
		double MS = 100*((double)WoerterMitDreiOderMehrSilben)/anzahlWoerter;
		double SL = (anzahlWoerter/anzahlSentence);
		double IW = 100*((double)WoerterMitMehrAls6Buchstaben)/anzahlWoerter;
		double ES = 100*((double)WoerterMit1Silbe)/anzahlWoerter;
		System.out.println(ES);
		double WSTF1 = 0.1935*MS + 0.1672*SL + 0.1297*IW - 0.0327*ES -0.875;			
		double WSTF2 = 0.2007 * MS + 0.1682 * SL + 0.1373 * IW - 2.779;
		double WSTF3 = 0.2963 * MS + 0.1905 * SL - 1.1144;
		double WSTF4 = 0.2744 * MS + 0.2656 * SL - 1.1693;
		featureSet.add( new Feature("WSTF1", (double)WSTF1, FeatureType.NUMERIC));
		featureSet.add( new Feature("WSTF2", (double)WSTF2, FeatureType.NUMERIC));
		featureSet.add( new Feature("WSTF3", (double)WSTF3, FeatureType.NUMERIC));
		featureSet.add( new Feature("WSTF4", (double)WSTF4, FeatureType.NUMERIC));
		
		return featureSet;
	}
	
	@Override
	public String getPublicName() {
		return "LesbarkeitsindexWienerSachtextformel";
	}
}
