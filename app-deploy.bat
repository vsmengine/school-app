echo on
set APACHE_FOLDER="C:\Program Files\Apache Software Foundation\Tomcat 9.0"
set WEBAPPS_FOLDER=%APACHE_FOLDER%\webapps
set DEPLOY_FOLDER=.\SchoolWeb\target
rem cd %APACHE_FOLDER%\bin
rem call shutdown.bat
copy %DEPLOY_FOLDER%\SchoolWeb-0.0.1-SNAPSHOT.war %WEBAPPS_FOLDER% 
rem call startup.bat