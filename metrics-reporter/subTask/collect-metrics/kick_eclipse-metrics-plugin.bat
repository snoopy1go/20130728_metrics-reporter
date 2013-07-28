@echo off
cd /d %~dp0
setlocal

set ECLIPSE_ROOT=C:\eclipse3.6_Cant_change_Japanese_All_in_one
set JAVAEXE=%ECLIPSE_ROOT%\eclipse\jre\bin\java.exe
set VMARGS="-Xmx512m"
set STARTUPJAR="%ECLIPSE_ROOT%\eclipse\plugins\org.eclipse.equinox.launcher_1.1.0.v20100507.jar"
set WORKSPACE=%ECLIPSE_ROOT%\workspace_20130715_withJenkins

echo calculating metrics ...
echo start time: %date% %time%
%JAVAEXE% %VMARGS% -cp %STARTUPJAR% org.eclipse.equinox.launcher.Main -noupdate -application org.eclipse.ant.core.antRunner -data %WORKSPACE% -file eclipse-metrics-plugin.xml %*
echo end time: %date% %time%
