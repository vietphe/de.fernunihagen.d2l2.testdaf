# de.fernunihagen.d2l2.testdaf
<h1>Testdaf Text Annotation and Feature Extraction Application</h1>
<p>This application is designed to annotate and extract text features from the Testdaf corpus.</p>

<h2>Project Structure</h2>
<p>The annotations can be viewed in the following packages:</p>
<b>'de.fernunihagen.d2l2.testdaf.structures'</b> <br>
<p>The feature extraction classes are available in the following packages:</p>
<b>'de.fernunihagen.d2l2.testdaf.features.basic'</b> <br>
<b>'de.fernunihagen.d2l2.testdaf.features.fachsprache'</b>
<h2>How to Use</h2>
1. Adjust the path to the directory containing the essays in .txt format at <b>'inputPath'</b> in the class
<b>'/de.fernunihagen.d2l2.testdaf/src/main/java/de/fernunihagen/d2l2/testdaf/BaseExperiment.java.'</b> <br>
2. Add the annotation classes you want to use in the pipeline. <br>
3. Run the program at the <b>BaseExperiment.java</b> class. <br>
4. The generated .xmi files will be located in the <b>'/de.fernunihagen.d2l2.testdaf/target/cas'</b> directory. 
