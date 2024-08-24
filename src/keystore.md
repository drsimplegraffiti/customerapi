keytool -genkeypair -alias abcodekeystore -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.jks -validity 3650
keytool -delete -alias abcodekeystore -keystore keystore.jks -storepass Bassguitar1#

keytool -export -alias abcodekeystore -file server.crt -keystore keystore.jks

keytool -import -alias mycert -file server.crt -keystore $JAVA_HOME/jre/lib/security/cacerts
