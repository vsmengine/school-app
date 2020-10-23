set jarpath=.\lib
java -jar %jarpath%\sqltool.jar --inlineRc=url=jdbc:hsqldb:hsql://localhost/school;shutdown=true db_create_sample_schema.sql
