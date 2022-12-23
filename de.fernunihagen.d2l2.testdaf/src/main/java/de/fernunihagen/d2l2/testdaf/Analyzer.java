package de.fernunihagen.d2l2.testdaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.pipeline.JCasIterator;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.dkpro.core.api.featurepath.FeaturePathException;
import org.dkpro.core.api.featurepath.FeaturePathFactory;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.FeatureAnnotationNumeric;

import de.fernunihagen.d2l2.testdaf.io.CasFeatureFileWriter;
import de.fernunihagen.d2l2.testdaf.io.FeatureCSVFileWriter;
import de.fernunihagen.d2l2.testdaf.io.FeatureSetBuilder;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.NC;

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
//		int size = 0;
//		try {
//			for (Entry<AnnotationFS, String> entry : FeaturePathFactory.select(aJCas.getCas(),Token.class.getName())) {
//				// if feature is null it means we can count any entry
//				if(null == null || entry.getValue().equals(null)) {
//					size++;
//				}
//			}
//		} catch (FeaturePathException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println("Size: "+size);
		Collection<NC> ncs = JCasUtil.select(aJCas, NC.class);
		for (NC nc : ncs) {
			System.out.println("Nominal Phrase: "+nc.getChunkValue()+" "+ nc.getCoveredText());
		}
		
//		Collection<Token> tokens = JCasUtil.select(aJCas, Token.class);
//		for (Token t : tokens) {
//			System.out.println(t.getCoveredText()+"- "+t.getLemma().getValue()+"- "+t.getPosValue() +"- "+ t.getPos().getCoarseValue());
//		}
//		System.out.println(tokens.size());
		
		Map<String, Set<Feature>> idFeatureMap = new HashMap<>();
		try {
			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
			//add Annotation type
			for (Feature f : fes) {
				String name = f.getName();
				FeatureType featureType = f.getType();
				Object value = f.getValue();
				FeatureAnnotationNumeric fa = new FeatureAnnotationNumeric(aJCas, 0, 0);
				fa.setName(name);
				fa.setValue((double) value);
				fa.addToIndexes();
			}
//			
		} catch (LiftFeatureExtrationException e) {
			
			e.printStackTrace();
		}
				
//		//writeCSVFile for every essay
//		try {
//			StringBuilder sb1 = new StringBuilder();
//			String path = "D:\\HiWi\\LiFT\\outcome\\" + id +".csv";
//			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
//			String first = writeFirstColumnNames(fes);
//			sb1.append(first);
//			sb1.append("\n");
//			sb1.append(id);
//			for (Feature feature : fes) {
//				sb1.append(",");
//				sb1.append(feature.getValue().toString());
//			}
//			sb1.append("\n");
//			
//			PrintWriter pw = new PrintWriter(new File(path));
//			pw.write(sb1.toString());
//			pw.close();
//		} catch (LiftFeatureExtrationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//writeCSVFile
		try {
			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
			Map<String,String> featureMap = new HashMap<>();
			featureMap.put("textId", id);
			for (Feature feature : fes) {
				featureMap.put(feature.getName(), Double.toString((double) feature.getValue())); //TODO: check casting type
			}
			featureList.add(featureMap);
		} catch (LiftFeatureExtrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void destroy() {
		
		try {
			exportCSVFile("D:\\HiWi\\LiFT\\output\\LV2_POS.csv", featureList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
