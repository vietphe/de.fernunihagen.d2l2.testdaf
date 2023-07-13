package de.fernunihagen.d2l2.testdaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.FeatureAnnotationNumeric;
import de.fernunihagen.d2l2.testdaf.io.FeatureSetBuilder;
import de.tudarmstadt.ukp.dkpro.core.api.anomaly.type.GrammarAnomaly;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class Analyzer extends JCasAnnotator_ImplBase {
	static StringBuilder sb;
	static String firstColumn;
	ArrayList<Map<String,String>> featureList;
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		
		super.initialize(context);
		sb = new StringBuilder();
		featureList = new ArrayList<>();
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {	
		String id = "no Id";
		if (JCasUtil.exists(aJCas, DocumentMetaData.class)){
			DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
			id = meta.getDocumentId();
		}
		System.out.println(id);
		Map<String, String> grammarAnomaly = new HashMap<>();
		Collection<GrammarAnomaly> gas = JCasUtil.select(aJCas, GrammarAnomaly.class);
		for (GrammarAnomaly g : gas) {
			System.out.println(g.getBegin()+": "+g.getCoveredText());
			grammarAnomaly.put(g.getBegin()+"-"+g.getEnd(), g.getDescription());
			
		}
		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
		
		int grammaticalError = 0;
		int spellingMistake = 0;
		for (Map.Entry<String, String> entry : grammarAnomaly.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			System.out.println(key+": "+val);
			if (val.contains("<suggestion>")||val.contains("requires the verb's base form")) {
				grammaticalError++;
			}
			if (val.contains("Possible spelling mistake found")) {
				spellingMistake++;
			}
		}
		
		System.out.println("Grammatischer Fehler: "+grammaticalError);
		System.out.println("Spelling Fehler: "+spellingMistake);
		

//		try {
//			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
//			//add Annotation type
//			for (Feature f : fes) {
//				String name = f.getName();
//				FeatureType featureType = f.getType();
//				Object value = f.getValue();
//				FeatureAnnotationNumeric fa = new FeatureAnnotationNumeric(aJCas, 0, 0);
//				fa.setName(name);
//				fa.setValue((double) value);
//				fa.addToIndexes();
//			}
//		
//		} catch (LiftFeatureExtrationException e) {
//			
//			e.printStackTrace();
//		}
////		writeCSVFile
//		try {
//			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
//			Map<String,String> featureMap = new HashMap<>();
//			featureMap.put("textId", id);
//			for (Feature feature : fes) {
//				featureMap.put(feature.getName(), Double.toString((double) feature.getValue())); //TODO: check casting type
//			}
//			featureList.add(featureMap);
//		} catch (LiftFeatureExtrationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	@Override
	public void destroy() {
		
//		try {
//			exportCSVFile("D:\\HiWi\\LiFT\\output\\LV3Derewo.csv", featureList);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
		
	
	public static void exportCSVFile(String filePath, ArrayList<Map<String,String>> list) throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		StringBuilder label = new StringBuilder();
		ArrayList<String> arrLabel = new ArrayList<>();
		
		Map<String,String> map1 = list.get(0);
		for (Map.Entry<String, String> entry : map1.entrySet()) {
			String key = entry.getKey();
//			System.out.println(key);
			label.append(key);
			label.append(",");
			arrLabel.add(key);
		}
		builder.append(label.toString());
		builder.append("\n");
		for (Map<String,String> map: list) {
			for (String s : arrLabel) {
				builder.append(map.get(s));
				builder.append(",");
			}
			builder.append("\n");
			
		}
		PrintWriter pw = new PrintWriter(new File(filePath));
		pw.write(builder.toString());
		pw.close();
		
	}


}
