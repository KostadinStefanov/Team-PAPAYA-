package breakout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

public class Ranking {
	public int total;
	public String time;
	
	public Ranking(TotalPoints total, Levels time){
		this.total = total.getPoints();
		this.time = time.getCurrentTime();
	}

	
	static void addPoint(int total, String time){
		//How can i call this method from Board.class
		 try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../resources/ranking.txt", true)))) {
			 	String print = total + " " + time;
			    out.println(print);
			}
		 catch (IOException e) {
			 //exception
			}
	}
	
	static void sortPoints() throws IOException{
		TreeMap<Integer, String> records = new TreeMap<>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("../resources/ranking.txt"));
			String line;
			while((line = in.readLine()) != null)
			{
				String[] putStrings = line.split(" ");
				int keyTotal = Integer.parseInt(putStrings[0]);
				String valueDateString = putStrings[1];
			    records.put(keyTotal, valueDateString);
			}
			ArrayList<String> results = null;
			//TODO get biggest results and print 3 biggest result
			for (Integer key : records.keySet()) {
				results.add(key + " " + results.get(key));
			}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			in.close();
		}
	}
}
