package de.fernunihagen.d2l2.testdaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.dkpro.core.api.featurepath.FeaturePathException;
import org.dkpro.core.api.featurepath.FeaturePathFactory;
import org.lift.api.Feature;
import org.lift.api.FeatureType;
import org.lift.api.LiftFeatureExtrationException;
import org.lift.type.FeatureAnnotationNumeric;

import de.fernunihagen.d2l2.testdaf.io.FeatureCSVFileWriter;
import de.fernunihagen.d2l2.testdaf.io.FeatureSetBuilder;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.NC;

public class Analyzer extends JCasAnnotator_ImplBase {
	static StringBuilder sb;
	static String firstColumn;
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		
		super.initialize(context);
		sb = new StringBuilder();
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
		//writeCSVFile
		try {
			Set<Feature> fes = FeatureSetBuilder.buildFeatureSet(aJCas);
			firstColumn = writeFirstColumnNames(fes);
			sb.append(id);
			for (Feature feature : fes) {
				sb.append(",");
				sb.append(feature.getValue().toString());
			}
			sb.append("\n");
		} catch (LiftFeatureExtrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		try {
			writeCSVFile("D:\\HiWi\\LiFT\\output\\featureLV3.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}
		
	private static String writeFirstColumnNames(Set<Feature> features) {
		StringBuilder builder = new StringBuilder();
		builder.append("textId");
		for (Feature feature : features) {
			builder.append(",");
			builder.append(feature.getName());
		}
		return builder.toString();
		
	}
	public static void writeCSVFile(String filePath) throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		builder.append(firstColumn);
		builder.append("\n");
		builder.append(sb);
		PrintWriter pw = new PrintWriter(new File(filePath));
		pw.write(builder.toString());
		pw.close();
	}


}
