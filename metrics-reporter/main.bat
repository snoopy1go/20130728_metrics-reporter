cd /d %~dp0
call ./subTask/collect-metrics/kick_eclipse-metrics-plugin.bat

cd /d %~dp0
call ./subTask/convert-to-html-report/convertToHtmlReport.bat
