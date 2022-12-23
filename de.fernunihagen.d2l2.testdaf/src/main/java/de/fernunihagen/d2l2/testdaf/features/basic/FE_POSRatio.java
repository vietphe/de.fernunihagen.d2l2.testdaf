package de.fernunihagen.d2l2.testdaf.features.basic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import de.fernunihagen.d2l2.testdaf.featureSettings.FeatureExtractor_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
/**
 * Counts the occurrence of the frequency bands.
 * 
 * @author Viet Phe
 */
public class FE_POSRatio extends FeatureExtractor_ImplBase{
		
	@Override
	public Set<Feature> extract(JCas jcas) throws LiftFeatureExtrationException {
		Set<Feature> POSFeatures = new HashSet<Feature>();
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		
		int NN = 0;
		int NE = 0;
		int ADJA = 0;
		int ADJD = 0;
		int CARD = 0;
		int VMFIN = 0;
		int VAFIN = 0;
		
		int VVFIN = 0;
		int VAIMP = 0;
		int VVIMP = 0;
		int VVINF = 0;
		int VAINF = 0;
		int VMINF = 0;
		int VVIZU = 0;
		
		int VVPP = 0;
		int VMPP = 0;
		int VAPP = 0;
		int ART = 0;
		int PPER = 0;
		int PRF = 0;
		int PPOSAT = 0;
		
		int PPOSS = 0;
		int PDAT = 0;
		int PDS = 0;
		int PIAT = 0;
		int PIDAT = 0;
		int PIS = 0;
		int PRELAT = 0;
		
		int PRELS = 0;
		int PWAT = 0;
		int PWS = 0;
		int PWAV = 0;
		int PAV = 0;
		int ADV = 0;
		int KOUI = 0;
		
		int KOUS = 0;
		int KON = 0;
		int KOKOM = 0;
		int APPR = 0;
		int APPRART = 0;
		int APPO = 0;
		int APZR = 0;
		
		int PTKZU = 0;
		int PTKNEG = 0;
		int PTKVZ = 0;
		int PTKA = 0;
		int PTKANT = 0;
		int ITJ = 0;
		int TRUNC = 0;
		int XY = 0;
		int FM = 0;
		int PROAV = 0;
		
		int PUNCT=0;
		for (Token t : tokens) {
//			System.out.println(t.getCoveredText()+" "+t.getPosValue()+" "+t.getPos().getCoarseValue());
			if(t.getPosValue().equals("NN")) {
				NN++;
			}
			if(t.getPosValue().equals("NE")) {
				NE++;
			}
			if(t.getPosValue().equals("ADJA")) {
				ADJA++;
			}
			if(t.getPosValue().equals("ADJD")) {
				ADJD++;
			}
			if(t.getPosValue().equals("CARD")) {
				CARD++;
			}
			if(t.getPosValue().equals("VMFIN")) {
				VMFIN++;
			}
			if(t.getPosValue().equals("VAFIN")) {
				VAFIN++;
			}
			if(t.getPosValue().equals("VVFIN")) {
				VVFIN++;
			}
			if(t.getPosValue().equals("VAIMP")) {
				VAIMP++;
			}
			if(t.getPosValue().equals("VVIMP")) {
				VVIMP++;
			}
			if(t.getPosValue().equals("VVINF")) {
				VVINF++;
			}
			if(t.getPosValue().equals("VAINF")) {
				VAINF++;
			}
			if(t.getPosValue().equals("VMINF")) {
				VMINF++;
			}
			if(t.getPosValue().equals("VVIZU")) {
				VVIZU++;
			}
			if(t.getPosValue().equals("VVPP")) {
				VVPP++;
			}
			if(t.getPosValue().equals("VMPP")) {
				VMPP++;
			}
			if(t.getPosValue().equals("VAPP")) {
				VAPP++;
			}
			if(t.getPosValue().equals("ART")) {
				ART++;
			}
			if(t.getPosValue().equals("PPER")) {
				PPER++;
			}
			if(t.getPosValue().equals("PRF")) {
				PRF++;
			}
			if(t.getPosValue().equals("PPOSAT")) {
				PPOSAT++;
			}
			if(t.getPosValue().equals("PPOSS")) {
				PPOSS++;
			}
			if(t.getPosValue().equals("PDAT")) {
				PDAT++;
			}
			if(t.getPosValue().equals("PDS")) {
				PDS++;
			}
			if(t.getPosValue().equals("PIAT")) {
				PIAT++;
			}
			if(t.getPosValue().equals("PIDAT")) {
				PIDAT++;
			}
			if(t.getPosValue().equals("PIS")) {
				PIS++;
			}
			if(t.getPosValue().equals("PRELAT")) {
				PRELAT++;
			}
			if(t.getPosValue().equals("PRELS")) {
				PRELS++;
			}
			if(t.getPosValue().equals("PWAT")) {
				PWAT++;
			}
			if(t.getPosValue().equals("PWS")) {
				PWS++;
			}
			if(t.getPosValue().equals("PWAV")) {
				PWAV++;
			}
			if(t.getPosValue().equals("PAV")) {
				PAV++;
			}
			if(t.getPosValue().equals("ADV")) {
				ADV++;
			}
			if(t.getPosValue().equals("KOUI")) {
				KOUI++;
			}
			if(t.getPosValue().equals("KOUS")) {
				KOUS++;
			}
			if(t.getPosValue().equals("KON")) {
				KON++;
			}
			if(t.getPosValue().equals("KOKOM")) {
				KOKOM++;
			}
			if(t.getPosValue().equals("APPR")) {
				APPR++;
			}
			if(t.getPosValue().equals("APPRART")) {
				APPRART++;
			}
			if(t.getPosValue().equals("APPO")) {
				APPO++;
			}
			if(t.getPosValue().equals("APZR")) {
				APZR++;
			}
			if(t.getPosValue().equals("PTKZU")) {
				PTKZU++;
			}
			if(t.getPosValue().equals("PTKNEG")) {
				PTKNEG++;
			}
			if(t.getPosValue().equals("PTKVZ")) {
				PTKVZ++;
			}
			if(t.getPosValue().equals("PTKA")) {
				PPOSAT++;
			}
			if(t.getPosValue().equals("PTKANT")) {
				PTKANT++;
			}
			if(t.getPosValue().equals("ITJ")) {
				ITJ++;
			}
			if(t.getPosValue().equals("TRUNC")) {
				TRUNC++;
			}
			if(t.getPosValue().equals("XY")) {
				XY++;
			}
			if(t.getPosValue().equals("FM")) {
				FM++;
			}
			if(t.getPosValue().equals("PROAV")) {
				PROAV++;
			}
			if(t.getPos().getCoarseValue()!=null && t.getPos().getCoarseValue().equals("PUNCT")) {
				PUNCT++;
			}
			
			
			
			
		}
		int sum = NN +NE +ADJA+ ADJD+ CARD +VMFIN+ VAFIN +VVFIN +VAIMP+ VVIMP+ VVINF +VAINF+VMINF +VVIZU +VMPP+ VAPP+ ART +PPER
				+PRF+PPOSAT+PPOSS+PDAT+PDS+PIAT+PIDAT+PIS+PRELAT+PRELS+PWAT+PWS+PWAV+PAV+ADV+KOUI+KOUS+KON+KOKOM+APPR+APPRART+APPO+APZR
				+PTKZU+PTKNEG+PTKVZ+PTKA+PTKANT+ITJ+TRUNC+XY+FM;
//		System.out.println("Noun: "+sum+" size: "+tokens.size()+"Punct: " +PUNCT );
		
		
		
		POSFeatures.add( new Feature("POS_NN", (double) NN/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_NE", (double) NE/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_ADJA", (double) ADJA/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_ADJD", (double) ADJD/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_CARD", (double) CARD/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VMFIN", (double) VMFIN/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VAFIN", (double) VAFIN/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VVFIN", (double) VVFIN/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VAIMP", (double) VAIMP/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VVIMP", (double) VVIMP/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VVINF", (double) VVINF/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VAINF", (double) VAINF/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VMINF", (double) VMINF/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VVIZU", (double) VVIZU/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VVPP", (double) VVPP/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VMPP", (double) VMPP/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_VAPP", (double) VAPP/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_ART", (double) ART/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PPER", (double) PPER/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PRF", (double) PRF/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PPOSAT", (double) PPOSAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PPOSS", (double) PPOSS/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PDAT", (double) PDAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PDS", (double) PDS/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PIAT", (double) PIAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PIDAT", (double) PIDAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PIS", (double) PIS/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PRELAT", (double) PRELAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PRELS", (double) PRELS/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PWAT", (double) PWAT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PWS", (double) PWS/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PWAV", (double) PWAV/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PAV", (double) PAV/tokens.size(), FeatureType.NUMERIC));		
		POSFeatures.add( new Feature("POS_ADV", (double) ADV/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_KOUI", (double) KOUI/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_KOUS", (double) KOUS/tokens.size(), FeatureType.NUMERIC));		
		POSFeatures.add( new Feature("POS_KON", (double) KON/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_KOKOM", (double) KOKOM/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_APPR", (double) APPR/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_APPRART", (double) APPRART/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_APPO", (double) APPO/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_APZR", (double) APZR/tokens.size(), FeatureType.NUMERIC));	
		POSFeatures.add( new Feature("POS_PTKZU", (double) PTKZU/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PTKNEG", (double) PTKNEG/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PTKVZ", (double) PTKVZ/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PTKA", (double) PTKA/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PTKANT", (double) PTKANT/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_ITJ", (double) ITJ/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_TRUNC", (double) TRUNC/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_XY", (double) XY/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_FM", (double) FM/tokens.size(), FeatureType.NUMERIC));
		POSFeatures.add( new Feature("POS_PROAV", (double) PROAV/tokens.size(), FeatureType.NUMERIC));
		
		return POSFeatures;
	}

	@Override
	public String getPublicName() {
		return "POS_Ratio";
	}

}
