gradle clean build -x test
service tomcat stop
rm -rf /opt/tomcat/webapps/*
cp -v build/libs/MediNetServices.war /opt/tomcat/webapps/ROOT.war
service tomcat start