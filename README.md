# de.fernunihagen.d2l2.testdaf
This is an application to annotate and exploit text features on the Testdaf corpus.

The annotations can be viewed in the de.fernunihagen.d2l2.testdaf.structures package and the feature extraction classes are in the de.fernunihagen.d2l2.testdaf.features.basic and de.fernunihagen.d2l2.testdaf.features.fachsprache packages

How to use:

1. Adjust the path to the directory containing the essays in .txt format at "inputPath" in the class /de.fernunihagen.d2l2.testdaf/src/main/java/de/fernunihagen/d2l2/testdaf/BaseExperiment.java

2. Add the annotation classes you want to annotate in the pipeline then run the program at that class (BaseExperiment.java)

3. The generated .xmi files will be located in the /de.fernunihagen.d2l2.testdaf/target/cas directory
