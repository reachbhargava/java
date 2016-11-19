start java -jar RMIServer.jar
ping -n 3 -w 1000 127.0.0.1 > nul
start java -jar RMIClient.jar