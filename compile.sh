find . -name "*.java" > sources.txt
javac @sources.txt
java com.company.avaj.sim.Simulator scenario.txt