package de.fernunihagen.d2l2.testdaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;

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
		String text = "no text";
		if (JCasUtil.exists(aJCas, DocumentMetaData.class)){
			DocumentMetaData meta = JCasUtil.selectSingle(aJCas, DocumentMetaData.class);
			id = meta.getDocumentId();
			text = meta.getDocumentTitle();
		}
		System.out.println(id);
//		System.out.println(text);
	
//		try {
//			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
			//add Annotation type
//			for (Feature f : fes) {
//				String name = f.getName();
//				FeatureType featureType = f.getType();
//				Object value = f.getValue();
//				FeatureAnnotationNumeric fa = new FeatureAnnotationNumeric(aJCas, 0, 0);
//				fa.setName(name);
//				fa.setValue((double) value);
//				fa.addToIndexes();
//			}
		
//		} catch (LiftFeatureExtrationException e) {
//			
//			e.printStackTrace();
//		}
//		writeCSVFile
		try {
			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
			Map<String,String> featureMap = new HashMap<>();
//			Map<String,String> featureMap = new HashMap<>();
			featureMap.put("textId", id);
			for (Feature feature : fes) {
//				featureMap.put(feature.getName(), (String)feature.getValue()); // for Feature-Type is not NUMERIC
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
//		exportToTxtFile(sb.toString(),"D:\\HiWi\\LiFT\\output\\constituent");
		exportMultiHashMapListToCSV("D:\\HiWi\\LiFT\\output\\08-12_LV3.csv", featureList);
	}
	
	public static void exportMultiHashMapListToCSV(String filePath, List<Map<String, String>> hashMapList) {
        Set<String> allKeys = getAllKeys(hashMapList);

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the header with all keys
            for (String key : allKeys) {
                writer.write("," + key);
            }
            writer.write("\n");

            // Write the values
            for (Map<String, String> map : hashMapList) {
                for (String key : allKeys) {
                    String value = map.getOrDefault(key, "0");
                    writer.write("," + value);
                }
                writer.write("\n");
            }

            System.out.println("Data successfully exported to CSV file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    private static Set<String> getAllKeys(List<Map<String, String>> hashMapList) {
        Set<String> allKeys = new HashSet<>();
        for (Map<String, String> map : hashMapList) {
            allKeys.addAll(map.keySet());
        }
        return allKeys;
    }
}
