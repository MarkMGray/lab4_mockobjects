javac -cp .:libs/* TestTempServlet.java TemperatureServlet.java
java -cp .:libs/* org.junit.runner.JUnitCore TestTempServlet
