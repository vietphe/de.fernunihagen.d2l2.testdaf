package de.fernunihagen.d2l2.testdaf.features.fachsprache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.lift.api.Feature;
import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase;
import de.fernunihagen.d2l2.testdaf.core.TestdafTestBase.ParserType;



public class SubstantivierungExtractorTest{
	@Test 
	public void substantivierungFeatureExtractorTest() throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaaa");
		AnalysisEngine engine = TestdafTestBase.getPreprocessingEngine("de",ParserType.noParser);

		JCas jcas = engine.newJCas();
		jcas.setDocumentLanguage("de");
		jcas.setDocumentText(
				"In seinem Kommentar in der Süddeutschen Zeitung über die Studiengebühren wirft Roland Preuß 2014 die Frage auf,"
				+ " ob der Staat und somit die Gesellschaft jedem Studierenden das Studium finanzieren sollte. "
				+ "Zu dem Zeitpunkt der Verfassung haben alle Bundesländer die Studiengebühren abgeschafft und Preuß "
				+ "beschreibt die Situation an deutschen Unis mit: Arm lebt und lernt neben Reich. "
				+ "Auch wenn der Autor äußert, dass die Studiengebühren auch die armen Menschen nicht von einem Studium "
				+ "abgehalten haben, wirft der Autor die zentrale These auf, dass Studiengebühren für Akademiker eingeführt "
				+ "werden sollten. Preuß fordert, dass die viel verdienenden Akademiker mittels zusätzlicher Steuern etwas "
				+ "zurückgeben sollten. Um seine These zu untermauen, vergleicht er das Leben eines Akademikers mit einem "
				+ "Nicht-Akademiker: Der Akademiker verdient auf der einen Seite mehr Geld und muss zum anderen dieselben "
				+ "allgemeinen Steuern zahlen. Zusätzlich bezahlen laut dem Autor beispielsweise die Menschen ihre "
				+ "Meisterausbildungen selbst, wohingegen ein Studium vom Staat finanziert wird. Deshalb schlägt der Autor "
				+ "ein Modell vor, wie es das beispielsweise bereits in Australien gibt: Wer später überdurchschnittlich "
				+ "verdient, zahlt einen kleinen Anteil seines Einkommens, bis die Summe [des Studiums] abgestottert ist. "
				+ "Das so zusätzlich eingenommene Geld könne somit laut Preuß in die Bildung investiert werden. Zusätzlich "
				+ "wirft der Autor in seinem Kommentar eine Aussage von Gegnern der Studiengebühren auf, die lautet: "
				+ "Die Gesellschaft profitiert von akademisch Gebildeten. Auf den ersten Blick könnte man durchaus "
				+ "verstehen, wenn Gegner dieser These erläutern, dass beispielsweise Menschen, die Philosophie oder "
				+ "Kunst studiert haben keinen Profit für die Gesellschaft darstellen. Für die These spricht hingegen, "
				+ "dass beispielsweise der Abschluss eines Medizinstudiums als ein absoluter Profit für die Gesellschaft "
				+ "angesehen wird. Doch auch akademisch Gebildete, die Naturwissenschaften studiert haben können als "
				+ "hilfreich für die Menschen angesehen werden. Diese Akademiker haben beispielsweise durch ihre "
				+ "Erfindungen und mathematisch gelegten Grundlagen die westliche Zivilisation geprägt. Auch Akademiker "
				+ "wie Lehrer und Psychologen stellen einen Profit für die Gesellschaft dar, da sie die Menschen und wie "
				+ "sie lernen erforschen. Diese Kette akademisch Gebildeter, die einen Mehrwert für die Gesellschaft "
				+ "darstellen, kann selbstverständlich noch viel weitergeführt werden. Meiner Meinung nach können aber "
				+ "auch Akademiker, die beispielsweise Philosophie oder Kunst studiert haben genau wie Mediziner, "
				+ "Naturwissenschaftler, Lehrer und Psychologen als Mehrwert für die Gesellschaft angesehen werden. "
				+ "Denn wenn jemand die Leistung dieses Akademikers in Anspruch nehmen möchte und dafür bezahlt, ist "
				+ "in einer liberalen Gesellschaft nichts gegen seinen akademischen Grad einzuwenden. Solange Menschen "
				+ "die Leistungen der Akademiker nutzen, muss meiner Meinung nach nicht nach Profit für die Allgemeinheit "
				+ "gefragt werden.");
		engine.process(jcas);
		
		
		SubstantivierungExtractor extractor = new SubstantivierungExtractor();
		
		Set<Feature> features = new HashSet<Feature>(extractor.extract(jcas));
	
		while(features.iterator().hasNext()) {
			System.out.println(features.iterator().next());
		}
		
		Assertions.assertAll(
	        		() -> assertEquals(12, features.size())
	    );
	}
}
