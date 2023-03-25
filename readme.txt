1. Sposób uruchomienia programu
mvn clean compile assembly:single
java -cp core/target/core-1.0-SNAPSHOT.jar parser.Parser <type of file (xlsx|csv)> <name of output file >   <-- komenda do uruchomenia programu z dwoma argumentami. Pierwszy to typ pliku (csv lub xlsx), a drugi to nazwa pliku wyjściowego.

java -cp core/target/core-1.0-SNAPSHOT.jar parser.Parser csv outputFile.xml   <-- Uruchomi program z plikiem wyjsciowym outputFile.xml

Aby wygenerować pokrycie testami jednostkowymi:
mvn clean install
firefox ./testing/target/site/jacoco-aggregate/index.html              <-- otwarcie raportu pokrycia jednostkowego projektu w przeglądarce firefox

**Nie udało mi się wygenerować raportu bezpośrednio w Sonarqub-ie. Raport generowałem za pomocą jacoco, a następnie importowałem go do sonarqube.
Aby podejrzeć raport w sonarqub-ie uruchamiałem komendy:
mvn clean install
mvn sonar:sonar   -Dsonar.projectKey=XmlParser   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=5d70a34912f5e685936f42ecbf3ceb246278f95a

