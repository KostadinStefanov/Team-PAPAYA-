package breakout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
	private int total;
	private Long time;
	private String username;
	private String workingDir = System.getProperty("user.dir");

	public Ranking(TotalPoints total, Levels time, String username) {
		this.total = total.getPoints();
		this.time = time.getCurrentTimeLong();
		this.username = username;
	}

	public void addPoint() {
		// How can i call this method from Board.class?
		List<RankingRows> records = new ArrayList<>();

		BufferedReader in = null;
		String filePath = workingDir + "/src/resources/ranking.txt";
		
		try {
			in = new BufferedReader(new FileReader(filePath));
			String line;
			Boolean oldUser = false;
			while ((line = in.readLine()) != null) {
				String[] putStrings = line.split(" ");
				String user = putStrings[0];
				int keyTotal = Integer.parseInt(putStrings[1]);
				long valueDate = Long.parseLong(putStrings[2]);

				//Check whether the user is available in the file and his records now are better
				if (user.equalsIgnoreCase(username)) {
					if (((keyTotal < total) || (keyTotal == total && time <= valueDate))) {
						RankingRows newRow = new RankingRows(username, total,time);
						records.add(newRow);
						oldUser = true;
					}
				} else {
					RankingRows newRow = new RankingRows(user, keyTotal,valueDate);
					records.add(newRow);
				}

			}
			in.close();
			
			if (! oldUser) {
				RankingRows newRow = new RankingRows(username, total,time);
				records.add(newRow);
			}
			
			Collections.sort(records);
			
			
		} catch (Exception e) {
			System.out.println("ranking.txt is missing");
		}

		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(filePath, false)))) {
			
			for (RankingRows rankingRows : records) {
				out.println(rankingRows.getUserName() + " " + rankingRows.getPoints() + " " + rankingRows.getTime());
			}
		} catch (IOException e) {
			System.out.println("ranking.txt is missing");
		}

	}

	static void sortPoints() throws IOException {

	}
}
