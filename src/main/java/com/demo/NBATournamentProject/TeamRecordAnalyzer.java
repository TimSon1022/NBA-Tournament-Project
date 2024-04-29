package com.demo.NBATournamentProject;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

class GameRecord {
    String team;
    String opponent;
    int teamScore;
    int opponentScore;
    boolean isHomeGame; // true for home games, false for away games
    int gameNumber; // Unique identifier for each game
    

    public GameRecord(int teamScore, int opponentScore, boolean isHomeGame, String team, String opponent, int gameNumber) {
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
        this.isHomeGame = isHomeGame;
        this.team = team;
        this.opponent = opponent;
        this.gameNumber = gameNumber;
    }

    @Override
    public String toString() {
        String location = isHomeGame ? "Home" : "Away";
        return "Game " + gameNumber  + ": " +  (isHomeGame ?  ((teamScore > opponentScore) ? opponent :  "{" + opponent  + "}") + " [" +  opponentScore + " : " + teamScore + "] " 
        		+ (!(teamScore > opponentScore) ?   team :  "{" + team  + "}") +  " (" + location + ")\n" : 
        			(!(teamScore > opponentScore) ?   team :  "{" + team  + "}") + " [" + teamScore + " : " + opponentScore + "] " + ((teamScore > opponentScore) ? opponent :  "{" + opponent  + "}") +  " (" + location + ")\n");
    }
}

class HeadToHeadRecord {
    int wins;
    int losses;
    int homeWins;
    int homeLosses;
    int awayWins;
    int awayLosses;
    String team;
    String opponent;

    List<GameRecord> games = new ArrayList<>();

    public void recordWin(int teamScore, int opponentScore, boolean isHomeGame, int gameNum) {
        wins++;
        games.add(new GameRecord(teamScore, opponentScore, isHomeGame, team, opponent, gameNum));
        if (isHomeGame) {
        	homeWins++;
        	
        }
        else {
        	awayWins++;
        }
    }

    public void recordLoss(int teamScore, int opponentScore, boolean isHomeGame, int gameNum) {
        losses++;
        games.add(new GameRecord(teamScore, opponentScore, isHomeGame, team, opponent, gameNum++));
        if (isHomeGame) {
        	homeLosses++;
        }
        else {
        	awayLosses++;
        }
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
    static String[] teamNamesArray = {
            "Team 3 Pointers", "Team 6th Man", "Team Africa", "Team Canada", "Team China",
            "Team Defense", "Team Dunk", "Team Europe", "Team France", "Team Improved",
            "Team Injuries", "Team Oceania", "Team Old", "Team Rookies", "Team Spain", "Team USA"
        };
    private static Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap;
    private static Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap;
    List<GameRecord> sortedGames = new ArrayList<>();
    // Assuming this exists inside the TeamRecordAnalyzer class
    private Map<String, Integer> winStreaks = new HashMap<>();
    private Map<String, Integer> loseStreaks = new HashMap<>();

    public static Map<String, String> teamNames = new HashMap<>();
    public List<Map<String,Object>> standings = new ArrayList<>();
    
    Map<String, Map<String, HeadToHeadRecord>> headToHeadRecords = new HashMap<>();

    public TeamRecordAnalyzer(Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap,
                              Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap) {
        TeamRecordAnalyzer.teamStatsMap = teamStatsMap;
        TeamRecordAnalyzer.opponentStatsMap = opponentStatsMap;

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
                    h2h.recordWin(teamPoints, opponentPoints, (opponentGames.get(i).get("Games").contains("vs") ? true : false), games+1);
                    updateStreaks(teamName,true);
                } else {
                    losses++;
                    h2h.recordLoss(teamPoints, opponentPoints, (opponentGames.get(i).get("Games").contains("vs") ? true : false), games+1);
                    updateStreaks(teamName, false);
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

     // First, group teams by PCT
        Map<Double, List<Map<String, Object>>> pctGroups = standings.stream()
                .collect(Collectors.groupingBy(team -> (Double) team.get("PCT")));
        Map<Double, List<Map<String, Object>>> sortedPctGroups = new TreeMap<>(Comparator.reverseOrder());
        sortedPctGroups.putAll(pctGroups);


        List<Map<String, Object>> sortedStandings = new LinkedList<>();

        for (Map.Entry<Double, List<Map<String, Object>>> entry : sortedPctGroups.entrySet()) {
            List<Map<String, Object>> tiedTeams = entry.getValue();

                // For two-way ties, sort based on head-to-head record, then PD
                tiedTeams.sort((a, b) -> {
                    HeadToHeadRecord h2hA = getHeadToHeadRecord((String) a.get("Team"), (String) b.get("Team"));
                    HeadToHeadRecord h2hB = getHeadToHeadRecord((String) b.get("Team"), (String) a.get("Team"));
                    int h2hResult = Integer.compare(h2hB.getWinLossDifference(), h2hA.getWinLossDifference());
                    if (h2hResult != 0) {
                    	return h2hResult;
                    }
                    if (Integer.compare((int) b.get("PD"), (int) a.get("PD")) != 0) {
                    	return Integer.compare((int) b.get("PD"), (int) a.get("PD"));
                    }
                    	

                    // If head-to-head is the same, compare by PD
                    return Integer.compare((int) a.get("PA"), (int) b.get("PA"));
                });

            sortedStandings.addAll(tiedTeams);
        }

        // Finally, rank the sortedStandings based on the resolved ties
        return sortedStandings;
    }

    
    public void printStandings() {
    	int i = 1;
        System.out.println("#\tTeam\t\t\tGP\tW\tL\tPCT\tPF\tPA\tPD\tStrk");
        for (Map<String, Object> objects: standings) {
        	int winStreak = winStreaks.get(objects.get("Team"));
            int loseStreak = loseStreaks.get(objects.get("Team"));
            String streak = (winStreak > 0) ? (winStreak + "W") : (loseStreak + "L");
        	System.out.println(i  + "\t" +  objects.get("Team") + "\t\t" +  objects.get("GP") + "\t" +  objects.get("W")+ "\t" +  
        objects.get("L")+ "\t" +  new DecimalFormat("#0.0%").format(objects.get("PCT"))+ "\t" +  objects.get("PF") + "\t" +  objects.get("PA")+ "\t" +  objects.get("PD")
        + "\t" + streak);
        i++;
        }
    }
    
    public void printHead2HeadRecord(String teamName) {
        Set<String> printedOpponents = new HashSet<>();
        List<Map<String, String>> opponentGames = opponentStatsMap.get(teamName).get(0).get("Opponent Stats");
        int homeWins = 0;
        int homeLosses = 0;
        int awayWins = 0;
        int awayLosses = 0;

        for (Map<String, String> game : opponentGames) {
            String opponentTeamAbbreviation = game.get("Games").startsWith("@") ? game.get("Games").substring(1).trim() : game.get("Games").substring(3).trim();
            String opponentTeamName = teamNames.get(opponentTeamAbbreviation);
            
            if (!printedOpponents.contains(opponentTeamName)) {
                HeadToHeadRecord h2h = getHeadToHeadRecord(teamName, opponentTeamName);
                h2h.printHeadToHeadRecord();
                printedOpponents.add(opponentTeamName);
                homeWins += h2h.homeWins;
                homeLosses += h2h.homeLosses;
                awayWins += h2h.awayWins;
                awayLosses += h2h.awayLosses;
            }
        }
        System.out.println("Home Record: " + homeWins + " - " + homeLosses);
        System.out.println("Away Record: " + awayWins + " - " + awayLosses+"\n");
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
    

    private void updateStreaks(String teamName, boolean win) {
        // Initialize streaks if not present
        winStreaks.putIfAbsent(teamName, 0);
        loseStreaks.putIfAbsent(teamName, 0);

        if (win) {
            // If the team won, increment win streak and reset lose streak
            winStreaks.put(teamName, winStreaks.get(teamName) + 1);
            loseStreaks.put(teamName, 0); // Reset lose streak
        } else {
            // If the team lost, increment lose streak and reset win streak
            loseStreaks.put(teamName, loseStreaks.get(teamName) + 1);
            winStreaks.put(teamName, 0); // Reset win streak
        }
    }

    public void processGameResult(String teamName, int teamPoints, int opponentPoints) {
        // Example method to process a game result and update streaks
        boolean win = teamPoints > opponentPoints;
        updateStreaks(teamName, win);
    }

    public void printCurrentStreaks() {
        // Example method to print current win/lose streaks for all teams
        for (String team : winStreaks.keySet()) {
            int winStreak = winStreaks.get(team);
            int loseStreak = loseStreaks.get(team);
            String streak = (winStreak > 0) ? ("Win Streak: " + winStreak) : ("Lose Streak: " + loseStreak);
            System.out.println(team + " - " + streak);
        }
    }
    
    public void findUniqueGames() {
        Map<String, GameRecord> uniqueGames = new HashMap<>();

        // Iterate over all teams' head-to-head records
        for (Map.Entry<String, Map<String, HeadToHeadRecord>> teamEntry : headToHeadRecords.entrySet()) {
            for (Map.Entry<String, HeadToHeadRecord> opponentEntry : teamEntry.getValue().entrySet()) {
                List<GameRecord> games = opponentEntry.getValue().getGames();

                // Add each game if not already present, using a unique key
                for (GameRecord game : games) {
                    String teamsKey = game.team.compareTo(game.opponent) < 0 ?
                            game.team + " vs " + game.opponent :
                            game.opponent + " vs " + game.team;
                    String uniqueKey = game.gameNumber + "-" + teamsKey; // Unique identifier based on game number and teams

                    if (!uniqueGames.containsKey(uniqueKey)) {
                        uniqueGames.put(uniqueKey, game);
                    }
                }
            }
        }

        // Extract the games and sort them by game number
        sortedGames = new ArrayList<>(uniqueGames.values());
        sortedGames.sort(Comparator.comparingInt(g -> g.gameNumber));


    }
    
    public Map<Integer, GameRecord> gamesForWeek(int weekNumber) {
        Map<Integer, GameRecord> gamesForWeek = new HashMap<>();


        int gameNum = 0;
        for (GameRecord game : sortedGames) {
            int gameWeek = game.gameNumber; // Calculate week number
            if (gameWeek == weekNumber) {
            	gameNum++;
                gamesForWeek.put(gameNum,game);
            }
        }



        // Print the games for the specified week

        return gamesForWeek;

    }

    
    
    
    
    

}