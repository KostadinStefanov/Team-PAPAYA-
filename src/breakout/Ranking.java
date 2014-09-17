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
	private static ArrayList<String> results;
	
	public Ranking(TotalPoints total, Levels time){
		this.total = total.getPoints();
		this.time = time.getCurrentTime();
	}

	static void addPoint(int total, String time) throws IOException{
		 try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("../resources/ranking.txt", true)))) {
			 	String print = total + " " + time;
			    out.println(print);
			}
		 catch (IOException e) {
			 System.out.println("ranking.txt is missing");
			}
		 finally{
			 sortPoints();
		 }
	}
	
	@SuppressWarnings("null")
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
				String valueDate = putStrings[1];
			    records.put(keyTotal, valueDate);
			}
			ArrayList<String> results = null;
			results = null;
			for (Integer key : records.keySet()) {
				results.add(key + " " + results.get(key));
			}
			String firstBest = results.get(results.size());
			String secondBest = results.get(results.size() - 1);
			String thirdBest = results.get(results.size() - 2);
			//TODO test it!!!
			
		} 
		catch (Exception e) {
			System.out.println("ranking.txt is missing");
		}
		finally{
			in.close();
		}
	}
}
