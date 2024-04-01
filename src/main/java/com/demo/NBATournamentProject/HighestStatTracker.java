package com.demo.NBATournamentProject;

import java.util.*;

public class HighestStatTracker {
    private static Map<String, Number> highestPlayerStats;
    private static Map<String, List<String>> highestPlayerGameInfo;
    
    private static Map<String, Number> highestTeamStats;
    private static Map<String, List<String>> highestTeamGameInfo;
    private static Map<String, Map<String, Number>> playerTournamentHighs = new HashMap<>();
    private static Map<String, Map<String, List<String>>> playerTournamentHighsInfo = new HashMap<>();


    public static void initializeStats() {
        highestPlayerStats = new LinkedHashMap<>();
        highestPlayerGameInfo = new HashMap<>();

        
        highestTeamStats = new LinkedHashMap<>();
        highestTeamGameInfo = new HashMap<>();
        // Initialize all stats to zero
        highestPlayerStats.put("PTS", 0);
        highestPlayerStats.put("REB", 0);
        highestPlayerStats.put("AST", 0);
        highestPlayerStats.put("BLK", 0);
        highestPlayerStats.put("STL", 0);
        highestPlayerStats.put("TO", 0);
        highestPlayerStats.put("FGM", 0);
        highestPlayerStats.put("FGA", 0);
        highestPlayerStats.put("3PTM", 0);
        highestPlayerStats.put("3PTA", 0);
        highestPlayerStats.put("FTM", 0);
        highestPlayerStats.put("FTA", 0);
        highestPlayerStats.put("OR", 0);
        highestPlayerStats.put("DER", 0);
        highestPlayerStats.put("+/-", 0);
        highestPlayerStats.put("MIN", 0);
        highestPlayerStats.put("PRF", 0);
        highestPlayerStats.put("DNK", 0);
        highestPlayerStats.put("FPTS", 0);
        highestPlayerStats.put("GS", 0);
        
        
        highestTeamStats.put("PTS", 0);
        highestTeamStats.put("FGM", 0);
        highestTeamStats.put("FGA", 0);
        highestTeamStats.put("3PTM", 0);
        highestTeamStats.put("3PTA", 0);
        highestTeamStats.put("FTM", 0);
        highestTeamStats.put("FTA", 0);
        highestTeamStats.put("DNK", 0);
        highestTeamStats.put("FBPTS", 0);
        highestTeamStats.put("PTSiP", 0);
        highestTeamStats.put("SCPTS", 0);
        highestTeamStats.put("BPTS", 0);
        highestTeamStats.put("AST", 0);
        highestTeamStats.put("OREB", 0);
        highestTeamStats.put("DREB", 0);
        highestTeamStats.put("TREB", 0);
        highestTeamStats.put("STL", 0);
        highestTeamStats.put("BLK", 0);
        highestTeamStats.put("TO", 0);
        highestTeamStats.put("PTS Off", 0);
        highestTeamStats.put("POS", 0);
        highestTeamStats.put("MOV", 0);

        

        
        
        
        
        
        
    }
    
    public static void initializePlayerTournamentHighs(List<String> playerNames) {
        for (String playerName : playerNames) {
            Map<String, Number> playerHighs = new LinkedHashMap<>();
            // Initialize all relevant stats for the player
            playerHighs.put("PTS", 0);
            playerHighs.put("REB", 0);
            playerHighs.put("AST", 0);
            playerHighs.put("BLK", 0);
            playerHighs.put("STL", 0);
            playerHighs.put("TO", 0);
            playerHighs.put("FGM", 0);
            playerHighs.put("FGA", 0);
            playerHighs.put("3PTM", 0);
            playerHighs.put("3PTA", 0);
            playerHighs.put("FTM", 0);
            playerHighs.put("FTA", 0);
            playerHighs.put("OR", 0);
            playerHighs.put("DER", 0);
            playerHighs.put("+/-", 0);
            playerHighs.put("MIN", 0);
            playerHighs.put("PRF", 0);
            playerHighs.put("DNK", 0);
            playerHighs.put("FPTS", 0);
            playerHighs.put("GS", 0);
            playerTournamentHighs.put(playerName, playerHighs);
        }
    }

    public static void updateHighestPlayerStats(String playerName, Map<String, String> gameStats) {
        String gameName = "";
        for (Map.Entry<String, String> entry : gameStats.entrySet()) {
            String statName = entry.getKey();
            double statValue = 0;
            if (statName.equals("Games")) {
                gameName = entry.getValue();
            } 
            else {
                if (entry.getValue() != null && highestPlayerStats.containsKey(statName)) {
                    statValue = Double.parseDouble(entry.getValue());
                    if (statName.equals("REB") && gameStats.containsKey("OR")) {
                        double offensiveRebounds = Double.parseDouble(gameStats.get("OR"));
                        double defensiveRebounds = statValue - offensiveRebounds;
                        // Update DER entry with defensive rebounds

                        updateDEREntry(playerName, gameName, defensiveRebounds);
                    }
                    if (statValue >= highestPlayerStats.get(statName).doubleValue()) {
                        // If the current value is greater than or equal to the highest value,
                        // update the highest value and add this player to the list of players achieving it.
                        if (statValue > highestPlayerStats.get(statName).doubleValue()) {
                            highestPlayerStats.put(statName, statValue);
                            List<String> players = new ArrayList<>();
                            players.add(playerName + " " + gameName);
                            highestPlayerGameInfo.put(statName, players);
                        } else {
                            List<String> players = highestPlayerGameInfo.get(statName);
                            if (players == null) {
                                players = new ArrayList<>();
                                highestPlayerGameInfo.put(statName, players);
                            }
                            players.add(playerName + " " + gameName);
                        }
                    }
                }
            }
        }
    }
    
    
    
    public static void updateHighestTeamStats(String teamName, Map<String, String> gameStats, boolean isTeam) {
    	
    	if (isTeam) {
            String gameName = "";
            for (Map.Entry<String, String> entry : gameStats.entrySet()) {
                String statName = entry.getKey();
                double statValue = 0;
                if (statName.equals("Games")) {
                    gameName = entry.getValue();
                    
                } 
                else {
                    if (entry.getValue() != null && highestTeamStats.containsKey(statName)) {
                        statValue = Double.parseDouble(entry.getValue());
                        if (statValue >= highestTeamStats.get(statName).doubleValue()) {
                            // If the current value is greater than or equal to the highest value,
                            // update the highest value and add this player to the list of players achieving it.
                            if (statValue > highestTeamStats.get(statName).doubleValue()) {
                                highestTeamStats.put(statName, statValue);
                                List<String> teams = new ArrayList<>();
                                teams.add(teamName + " " + gameName);
                                
                                highestTeamGameInfo.put(statName, teams);
                                
                            } 
                            else {
                                List<String> teams = highestTeamGameInfo.get(statName);
                                if (teams == null) {
                                	teams = new ArrayList<>();
                                    highestTeamGameInfo.put(statName, teams);
                                }
                                
                                teams.add(teamName + " " + gameName);
                            }
                        }
                    }
                }
            }
    	}

    }
    public static void updateMOV(Map<String, Map<String, List<List<Integer>>>> gamesMap) {
        // Iterate over the outer map
        for (Map.Entry<String, Map<String, List<List<Integer>>>> gameEntry : gamesMap.entrySet()) {
            // Get the nested map from the current entry
            Map<String, List<List<Integer>>> nestedMap = gameEntry.getValue();
            String teamName = gameEntry.getKey();      
            // Iterate over the nested map
            for (Map.Entry<String, List<List<Integer>>> nestedEntry : nestedMap.entrySet()) {
                // Get the list of lists from the current entry
                List<List<Integer>> listOfLists = nestedEntry.getValue();
                String gameName = nestedEntry.getKey();
                
                // Iterate over the list of lists
                for (List<Integer> list : listOfLists) {
                	double statValue = (double)list.get(0) - list.get(1);
                    // Print the current list
                    if (statValue >= highestTeamStats.get("MOV").doubleValue()) {
                        if (statValue > highestTeamStats.get("MOV").doubleValue()) {
                            highestTeamStats.put("MOV", statValue);
                            List<String> teams = new ArrayList<>();
                            teams.add(teamName + " " + gameName);
                            
                            highestTeamGameInfo.put("MOV", teams);
                            
                        } 
                        else {
                            List<String> teams = highestTeamGameInfo.get("MOV");
                            if (teams == null) {
                            	teams = new ArrayList<>();
                                highestTeamGameInfo.put("MOV", teams);
                            }
                            
                            teams.add(teamName + " " + gameName);
                        }
                    	
                    }
                }
            }
        }
    }
    
    
    public static void updatePlayerTournamentHighs(String playerName, Map<String, String> gameStats) {
    	
    	
    	
        Map<String, Number> currentHighs = playerTournamentHighs.getOrDefault(playerName, new HashMap<>());
        String gameName = "";
        Map<String, List<String>> statMap = new HashMap<>();
        if (playerTournamentHighsInfo.containsKey(playerName)) {
        	statMap = new HashMap<>(playerTournamentHighsInfo.get(playerName));
        }
        for (Map.Entry<String, String> entry : gameStats.entrySet()) {
            String statName = entry.getKey();
            double statValue = 0;
            if (statName.equals("Games")) {
            	gameName = entry.getValue();
            }
            else {
                
                if (entry.getValue() != null && currentHighs.containsKey(statName)) {
                	
                	 statValue = Double.parseDouble(entry.getValue());
                     if (statName.equals("REB") && gameStats.containsKey("OR") && statValue > 0) {
                         double offensiveRebounds = Double.parseDouble(gameStats.get("OR"));
                         double defensiveRebounds = statValue - offensiveRebounds;
                         // Update DER entry with defensive rebounds
                         
                         statMap = updatePlayerDEREntry(playerName, gameName, defensiveRebounds, statMap);
                     }
                    if (statValue >= currentHighs.get(statName).doubleValue()) {
                        // If the current value is greater than or equal to the highest value,
                        // update the highest value and add this player to the list of players achieving it.
                    	
                    	if (statValue > 0) {
                            if (statValue > currentHighs.get(statName).doubleValue()) {
                            	currentHighs.put(statName, statValue);
                                List<String> players = new ArrayList<>();
                                players.add(" " + gameName);  
                                statMap.put(statName, players);
                                
                            } 
                            else {
                                List<String> players = statMap.get(statName);
                                if (players == null) {
                                	players = new ArrayList<>();
                                	statMap.put(statName, players);
                                }
                                
                                players.add(" " + gameName);
                            	
                            }
                    	}
                    	else {
                    		List<String> players = new ArrayList<>();
                    		players.add(" N/A");
                    		statMap.put(statName, players);
                    	}

                    }
                }
                
            }
            

        }

        playerTournamentHighs.put(playerName, currentHighs); // Update the record
        playerTournamentHighsInfo.put(playerName, statMap);
    }

    // Method to print or access player highs
    public static void printPlayerTournamentHighs(String playerName) {
        Map<String, Number> highs = playerTournamentHighs.get(playerName);
        if (highs != null) {
            System.out.println("Tournament Highs for " + playerName + ":");
            for (Map.Entry<String, Number> high : highs.entrySet()) {
                System.out.println(high.getKey() + ": " + high.getValue());
            }
        } else {
            System.out.println("No data for " + playerName);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

    private static void updateDEREntry(String playerName, String gameName, double defensiveRebounds) {
        String statName = "DER";
        if (defensiveRebounds >= highestPlayerStats.getOrDefault(statName, 0).doubleValue()) {
            // If the current value is greater than or equal to the highest value,
            // update the highest value and add this player to the list of players achieving it.
            if (defensiveRebounds > highestPlayerStats.getOrDefault(statName, 0).doubleValue()) {
                highestPlayerStats.put(statName, defensiveRebounds);
                List<String> players = new ArrayList<>();
                players.add(playerName + " " + gameName);
                highestPlayerGameInfo.put(statName, players);
            } else {
                List<String> players = highestPlayerGameInfo.get(statName);
                if (players == null) {
                    players = new ArrayList<>();
                    highestPlayerGameInfo.put(statName, players);
                }
                players.add(playerName + " " + gameName);
            }
        }
    }
    
    private static Map<String, List<String>> updatePlayerDEREntry(String playerName, String gameName, double defensiveRebounds, Map<String, List<String>> oldMap) {
        String statName = "DER";
        Map<String, List<String>> statMap = new HashMap<>(oldMap);
        	

        
        if (defensiveRebounds >= playerTournamentHighs.get(playerName).getOrDefault(statName, 0).doubleValue()) {
            // If the current value is greater than or equal to the highest value,
            // update the highest value and add this player to the list of players achieving it.
            if (defensiveRebounds > playerTournamentHighs.get(playerName).getOrDefault(statName, 0).doubleValue()) {
            	playerTournamentHighs.get(playerName).put(statName, defensiveRebounds);
                List<String> players = new ArrayList<>();
                players.add(" " + gameName);
                statMap.put(statName, players);
            } else {
                List<String> players = statMap.get(statName);
                if (players == null) {
                    players = new ArrayList<>();    
                    statMap.put(statName, players);
                }
                players.add(" " + gameName);
                
                statMap.put(statName, players);
                
            }
        }
        playerTournamentHighsInfo.put(playerName, statMap);
        return statMap;
        
    }


    public Map<String, Number> getHighestStats() {
        return highestPlayerStats;
    }

    public Map<String, List<String>> getHighestGameInfo() {
        return highestPlayerGameInfo;
    }

    public static void printHighestPlayerStats() {
        System.out.println("Tournament Game Highs:");
        for (Map.Entry<String, Number> entry : highestPlayerStats.entrySet()) {
            String statName = entry.getKey();
            Number statValue = entry.getValue();
            String stat = "";
            List<String> players = highestPlayerGameInfo.get(statName);
            StringBuilder playerNames = new StringBuilder();
            if (players != null) {
                for (String player : players) {
                    playerNames.append(player).append(", ");
                }
                playerNames.delete(playerNames.length() - 2, playerNames.length()); // Remove the last comma and space
            }
            if (!statName.equals("FPTS") && !statName.equals("GS")) {
                statValue = statValue.byteValue();
            }
            if (statName.equals("+/-")&& statValue.doubleValue() > 0) {
                stat += "+";
            }
            stat += String.valueOf(statValue);
            System.out.printf("%-4s: %-5s %-30s%n", statName, stat, playerNames);
        }
    }
    
    
    public static void printPlayerTournamentHighStats(String playerName) {
    	  boolean isFound = searchForKeyIgnoreCase(playerTournamentHighs, playerName);
    	if (isFound) {
    		playerName = searchStringForKeyIgnoreCase(playerName);
            System.out.println(playerName + "'s Tournament Game Highs:");
            
            
            for (Map.Entry<String, Number> entry : playerTournamentHighs.get(playerName).entrySet()) {
                String statName = entry.getKey();
                Number statValue = entry.getValue();
                String stat = "";
                List<String> players = playerTournamentHighsInfo.get(playerName).get(statName);
                StringBuilder playerNames = new StringBuilder();
                if (players != null) {
                    for (String player : players) {
                        playerNames.append(player).append(", ");
                    }
                    playerNames.delete(playerNames.length() - 2, playerNames.length()); // Remove the last comma and space
                }
                if (!statName.equals("FPTS") && !statName.equals("GS")) {
                    statValue = statValue.byteValue();
                }
                if (statName.equals("+/-") && statValue.doubleValue() > 0) {
                    stat += "+";
                }
                stat += String.valueOf(statValue);
                System.out.printf("%-4s: %-5s %-30s%n", statName, stat, playerNames);
            }
    	}
        else {
        	System.out.println("Player not found.");
        }
    	
    	

    }
    
    public static boolean searchForKeyIgnoreCase(Map<String, Map<String, Number>> map, String searchKey) {
        for (String key : map.keySet()) {
            if (key.equalsIgnoreCase(searchKey)) {
                return true;
            }
        }
        return false;
    }
    
    public static String searchStringForKeyIgnoreCase(String searchKey) {
        for (String key : playerTournamentHighs.keySet()) {
            if (key.equalsIgnoreCase(searchKey)) {
                return key;
            }
        }
        return "";
    }
    

    
    
    
    
    
    public static void printHighestTeamStats() {
        System.out.println("Tournament Game Highs:");
        int maxStatNameLength = 0;
        // First, calculate the maximum length of the stat names
        for (String statName : highestTeamStats.keySet()) {
            maxStatNameLength = Math.max(maxStatNameLength, statName.length() + 1); // +1 for the colon
        }
        // Calculate the format specifier for alignment, considering both the stat name and stat value
        String formatSpecifier = "%-" + maxStatNameLength + "s %-6s %-50s%n";

        for (Map.Entry<String, Number> entry : highestTeamStats.entrySet()) {
            String statName = entry.getKey() + ":";
            Number statValue = entry.getValue();
            List<String> teams = highestTeamGameInfo.get(statName.replace(":", ""));
            StringBuilder teamNames = new StringBuilder();
            if (teams != null) {
                for (String team : teams) {
                    teamNames.append(team).append(", ");
                }
                // Remove the last comma and space
                if (teamNames.length() > 0) {
                    teamNames.delete(teamNames.length() - 2, teamNames.length());
                }
            }
            // Round the stat value, except for 'POS'
            if (!statName.equals("POS:")) {
                statValue = Math.round(statValue.doubleValue());
            }
            String stat = String.valueOf(statValue);
            // Adjust the format specifier based on the maxStatNameLength
            System.out.printf(formatSpecifier, statName, stat, teamNames.toString());
        }
    }




}