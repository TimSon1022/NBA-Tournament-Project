package com.demo.NBATournamentProject;
import java.text.DecimalFormat;
import java.util.*;

class GameRecord {
	String team;
	String opponent;
    int teamScore;
    int opponentScore;
    boolean isHomeGame; // true for home games, false for away games

    public GameRecord(int teamScore, int opponentScore, boolean isHomeGame, String team, String opponent) {
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
        this.isHomeGame = isHomeGame;
        this.team = team;
        this.opponent = opponent;
   
    }

    @Override
    public String toString() {
        String location = isHomeGame ? "Home" : "Away";
        return  isHomeGame ?  ((teamScore > opponentScore) ? opponent :  "{" + opponent  + "}") + " [" +  opponentScore + " : " + teamScore + "] " 
        		+ (!(teamScore > opponentScore) ?   team :  "{" + team  + "}") +  " (" + location + ")\n" : 
        			(!(teamScore > opponentScore) ?   team :  "{" + team  + "}") + " [" + teamScore + " : " + opponentScore + "] " + ((teamScore > opponentScore) ? opponent :  "{" + opponent  + "}") +  " (" + location + ")\n";
    }
}

class HeadToHeadRecord {
    int wins;
    int losses;
    String team;
    String opponent;

    List<GameRecord> games = new ArrayList<>();

    public void recordWin(int teamScore, int opponentScore, boolean isHomeGame) {
        wins++;
        games.add(new GameRecord(teamScore, opponentScore, isHomeGame, team, opponent));
    }

    public void recordLoss(int teamScore, int opponentScore, boolean isHomeGame) {
        losses++;
        games.add(new GameRecord(teamScore, opponentScore, isHomeGame, team, opponent));
    }
    
    public int getWinLossDifference() {
        return wins - losses; // A positive number indicates more wins, negative indicates more losses.
    }

    public List<GameRecord> getGames() {
        return games;
    }
    
    public void printHeadToHeadRecord() {
        System.out.println("\nHead-to-Head Record vs " + opponent +  ": \n" + team + " " + wins +  " - " +  losses + " "  + opponent);
        System.out.println("\nGames:");
        for (GameRecord game : games) {
            System.out.println(game.toString());
        }
    }
}


public class TeamRecordAnalyzer {
    private static Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap;
    private static Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap;
    public static Map<String, String> teamNames = new HashMap<>();
    public List<Map<String,Object>> standings = new ArrayList<>();
    
    Map<String, Map<String, HeadToHeadRecord>> headToHeadRecords = new HashMap<>();

    public TeamRecordAnalyzer(Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap,
                              Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap) {
        TeamRecordAnalyzer.teamStatsMap = teamStatsMap;
        TeamRecordAnalyzer.opponentStatsMap = opponentStatsMap;
        String[] teamNamesArray = {
                "Team 3 Pointers", "Team 6th Man", "Team Africa", "Team Canada", "Team China",
                "Team Defense", "Team Dunk", "Team Europe", "Team France", "Team Improved",
                "Team Injuries", "Team Oceania", "Team Old", "Team Rookies", "Team Spain", "Team USA"
            };
        teamNames.put("3PT", teamNamesArray[0]);
        teamNames.put("6TH", teamNamesArray[1]);
        teamNames.put("AFR", teamNamesArray[2]);
        teamNames.put("CAN", teamNamesArray[3]);
        teamNames.put("CHI", teamNamesArray[4]);
        teamNames.put("DEF", teamNamesArray[5]);
        teamNames.put("DNK", teamNamesArray[6]);
        teamNames.put("EUR", teamNamesArray[7]);
        teamNames.put("FRA", teamNamesArray[8]);
        teamNames.put("IMP", teamNamesArray[9]);
        teamNames.put("INJ", teamNamesArray[10]);
        teamNames.put("OCE", teamNamesArray[11]);
        teamNames.put("OLD", teamNamesArray[12]);
        teamNames.put("RKS", teamNamesArray[13]);
        teamNames.put("SPA", teamNamesArray[14]);
        teamNames.put("USA", teamNamesArray[15]);
        
        standings = calculateStandings();
        
    }
    


    
    


    public List<Map<String, Object>> calculateStandings() {
        List<Map<String, Object>> standings = new ArrayList<>();

        for (String teamName : teamStatsMap.keySet()) {
            Map<String, Object> teamRecord = new HashMap<>();
            int wins = 0;
            int losses = 0;
            int PD = 0;
            int games = 0;
            int PF = 0;
            int PA = 0;
            List<Map<String, String>> teamGames = teamStatsMap.get(teamName).get(0).get("Team Stats");
            List<Map<String, String>> opponentGames = opponentStatsMap.get(teamName).get(0).get("Opponent Stats");

            for (int i = 0; i < teamGames.size(); i++) {
                int teamPoints = Integer.parseInt(teamGames.get(i).get("PTS"));
                int opponentPoints = Integer.parseInt(opponentGames.get(i).get("PTS"));
                PF += teamPoints;
                PA += opponentPoints;
                
                PD += teamPoints - opponentPoints;
                String opponentTeamAbbreviation = opponentGames.get(i).get("Games");
                opponentTeamAbbreviation = opponentTeamAbbreviation.startsWith("@") ? opponentTeamAbbreviation.substring(1).trim() : opponentTeamAbbreviation.substring(3).trim();
                String opponentTeamName = teamNames.get(opponentTeamAbbreviation); 
                HeadToHeadRecord h2h = getHeadToHeadRecord(teamName, opponentTeamName);
                h2h.team = teamName;
                h2h.opponent = teamNames.get(opponentTeamAbbreviation);
                if (teamPoints > opponentPoints) {
                    wins++;
                    h2h.recordWin(teamPoints, opponentPoints, (opponentGames.get(i).get("Games").contains("vs") ? true : false));
                } else {
                    losses++;
                    h2h.recordLoss(teamPoints, opponentPoints, (opponentGames.get(i).get("Games").contains("vs") ? true : false));
                }
                games++;
                
                // No need to put the record back in the map since the object is already updated by reference
            }
            
            teamRecord.put("Team", teamName);
            teamRecord.put("GP", games);
            teamRecord.put("W", wins);
            teamRecord.put("L", losses);
            teamRecord.put("PF", PF);
            teamRecord.put("PA", PA);
            teamRecord.put("PD", PD);
            teamRecord.put("PCT", wins / (double) (wins + losses));
            standings.add(teamRecord);
        }

        standings.sort((a, b) -> {
            // First, compare by win-loss percentage (PCT), descending order.
        	
            int result = Double.compare((double) b.get("PCT"), (double) a.get("PCT"));
            if (result == 0) {
                // If PCT is the same, compare head-to-head record.

                HeadToHeadRecord h2hA = getHeadToHeadRecord((String) a.get("Team"), (String) b.get("Team"));
                HeadToHeadRecord h2hB = getHeadToHeadRecord((String) b.get("Team"), (String) a.get("Team"));
                
                
                
                result = Integer.compare(h2hB.wins - h2hB.losses, h2hA.wins - h2hA.losses); // Compare net head-to-head wins
                if (result == 0) {
                    // If head-to-head is also tied, compare by point differential (PD), descending order.
                    result = Integer.compare((int) b.get("PD"), (int) a.get("PD"));
                }
            }
            return result; // Returning -1 means 'a' should be placed after 'b' if 'a' is less than 'b' based on comparison logic
        });
        return standings;
    }
    
    public void printStandings() {
    	int i = 1;
        System.out.println("#\tTeam\t\t\tGP\tW\tL\tPCT\tPF\tPA\tPD");
        for (Map<String, Object> objects: standings) {
        	System.out.println(i  + "\t" +  objects.get("Team") + "\t\t" +  objects.get("GP") + "\t" +  objects.get("W")+ "\t" +  
        objects.get("L")+ "\t" +  new DecimalFormat("#0.0%").format(objects.get("PCT"))+ "\t" +  objects.get("PF") + "\t" +  objects.get("PA")+ "\t" +  objects.get("PD"));
        i++;
        }
    }
    
    public void printHead2HeadRecord(String teamName) {
        Set<String> printedOpponents = new HashSet<>();
        List<Map<String, String>> opponentGames = opponentStatsMap.get(teamName).get(0).get("Opponent Stats");

        for (Map<String, String> game : opponentGames) {
            String opponentTeamAbbreviation = game.get("Games").startsWith("@") ? game.get("Games").substring(1).trim() : game.get("Games").substring(3).trim();
            String opponentTeamName = teamNames.get(opponentTeamAbbreviation);
            
            if (!printedOpponents.contains(opponentTeamName)) {
                HeadToHeadRecord h2h = getHeadToHeadRecord(teamName, opponentTeamName);
                h2h.printHeadToHeadRecord();
                printedOpponents.add(opponentTeamName);
            }
        }
    }

    
    public HeadToHeadRecord getHeadToHeadRecord(String teamName, String opponentTeamName) {
        Map<String, HeadToHeadRecord> opponents = headToHeadRecords.computeIfAbsent(teamName, k -> new HashMap<>());
        return opponents.computeIfAbsent(opponentTeamName, k -> new HeadToHeadRecord());
    }
    
 // Method to calculate team wins per round
    public static Map<String, Integer> calculateTeamWinsPerRound(int round, int gamesPerRound) {
        Map<String, Integer> teamWins = new HashMap<>();

        // Iterate through each team's game records
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> entry : teamStatsMap.entrySet()) {
            String teamName = entry.getKey();
            int wins = 0;

            List<Map<String, String>> teamGames = entry.getValue().get(0).get("Team Stats");
            List<Map<String, String>> opponentGames = opponentStatsMap.get(teamName).get(0).get("Opponent Stats");

            int startGameIndex = (round - 1) * gamesPerRound;
            int endGameIndex = startGameIndex + gamesPerRound;

            // Make sure we don't exceed the list size
            endGameIndex = Math.min(endGameIndex, teamGames.size());

            for (int i = startGameIndex; i < endGameIndex; i++) {
                int teamPoints = Integer.parseInt(teamGames.get(i).get("PTS"));
                int opponentPoints = Integer.parseInt(opponentGames.get(i).get("PTS"));

                if (teamPoints > opponentPoints) {
                    wins++;
                }
            }

            teamWins.put(teamName, wins);
        }

        return teamWins;
    }
    
    
    

}
