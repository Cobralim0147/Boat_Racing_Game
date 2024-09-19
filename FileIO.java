package final_oop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;

public class FileIO {

    public static class PlayerScore {
        private String playerName;
        private int score;

        public PlayerScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }
    }

    public static List<PlayerScore> loadScores() {
        List<PlayerScore> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("scores.txt"))) {
            // Skip the first two lines (header lines)
            reader.readLine(); // "TOP Scores"
            reader.readLine(); // "Username,Number of Turns"

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    String scoreStr = parts[1].trim();
                    try {
                        int score = Integer.parseInt(scoreStr);
                        scores.add(new PlayerScore(playerName, score));
                    } catch (NumberFormatException e) {
                        // If the score is not a valid integer, skip this line
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // If the file doesn't exist yet or is empty, return an empty list of scores
            return scores;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

	    
	 // Method to save top 5 scores to a file
    public static void saveScores(List<PlayerScore> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"))) {
            writer.write("TOP Scores (Ranked from User who took the least steps to win the game.)");
            writer.newLine();
            writer.newLine();
            writer.write("Username, Number of Turns");
            writer.newLine();

            // Sort the scores in ascending order based on the score value
            scores.sort(Comparator.comparingInt(PlayerScore::getScore));

            for (PlayerScore score : scores) {
                writer.write(score.getPlayerName() + "," + score.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	    // Method to update the top 5 scores and save to a file
	    public static void updateTopScores(PlayerScore newScore) {
	        List<PlayerScore> scores = loadScores();
	        scores.add(newScore);
	        scores.sort(Comparator.comparingInt(PlayerScore::getScore)); // Sort in ascending order of score
	        if (scores.size() > 5) {
	            scores = scores.subList(0, 5);
	        }
	        saveScores(scores);
	    }


	    // Method to create the file if it doesn't exist
	    public static void createFileIfNotExists() {
	        try {
	            File file = new File("scores.txt");
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void saveScoresToFile() {
	        List<PlayerScore> scores = loadScores();
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"))) {
	            for (PlayerScore score : scores) {
	                writer.write(score.getPlayerName() + "," + score.getScore());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void updateTopScores(List<PlayerScore> scores) {
	        scores.sort(Collections.reverseOrder(Comparator.comparingInt(PlayerScore::getScore))); // Sort in descending order of score
	        if (scores.size() > 5) {
	            scores = scores.subList(0, 5);
	        }
	        saveScores(scores);
	    }

}