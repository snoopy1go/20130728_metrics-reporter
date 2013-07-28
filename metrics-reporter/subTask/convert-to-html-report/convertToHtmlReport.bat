cd /d %~dp0
set GROOVY_JAR="./lib/groovy-all-2.1.0.jar"
java -jar %GROOVY_JAR% -Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8 MetricsReportCreator.groovy
