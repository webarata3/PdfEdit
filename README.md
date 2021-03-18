# PdfEdit

パッケージ作成

```
./gradlew shadowJar
jlink --add-modules java.base,java.desktop --output build/jre
jpackage --name PdfEdit --input build --main-jar libs/PdfEdit-all.jar --runtime-image build/jre --win-menu --icon icon.ico
```
