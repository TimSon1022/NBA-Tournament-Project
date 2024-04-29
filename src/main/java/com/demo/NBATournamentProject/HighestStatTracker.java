package com.demo.NBATournamentProject;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

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
        highestPlayerStats.put("FGM",0);
        highestPlayerStats.put("FGA", 0);
        highestPlayerStats.put("3PTM", 0);
        highestPlayerStats.put("3PTA", 0);
        highestPlayerStats.put("FTM", 0);
        highestPlayerStats.put("FTA", 0);
        highestPlayerStats.put("OREB", 0);
        highestPlayerStats.put("DREB", 0);
        highestPlayerStats.put("+/-", -999);
        highestPlayerStats.put("MIN", 0);
        highestPlayerStats.put("PRF", 0);
        highestPlayerStats.put("DNK", 0);
        highestPlayerStats.put("FPTS", -999);
        highestPlayerStats.put("GS", -999);
        
        
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
            playerHighs.put("OREB", 0);
            playerHighs.put("DREB", 0);
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
            	if (statName.equals("OR")) {
            		statName = "OREB";
            	}
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
            	if (statName.equals("OR")) {
            		statName = "OREB";
            	}
                
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
        String statName = "DREB";
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
        String statName = "DREB";
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
    
    public static void printHeadToHeadGameInfo(int gameID, String teamName, String opponentName, int teamScore, int opponentScore) {
        Map<String, Number>teamStats = new LinkedHashMap<>();
        Map <String, Number> opponentStats = new LinkedHashMap<>(); 
        Map<String, List<String>> teamGameInfo = new LinkedHashMap<>();
        Map<String, List<String>> opponentGameInfo = new LinkedHashMap<>();
        Map<String,Number> teamStatLineMap = new LinkedHashMap<>();
        Map<String,Number> opponentStatLineMap = new LinkedHashMap<>();
        teamStatLineMap.put("MIN",0);
        opponentStatLineMap.put("MIN",0);
        for (Map.Entry<String, Number> entry : highestPlayerStats.entrySet()) {
        	teamStats.put(entry.getKey(), -999);
            opponentStats.put(entry.getKey(), -999);
            if (!entry.getKey().equals("PRF") && !entry.getKey().equals("GS")) {
                teamStatLineMap.put(entry.getKey(), 0);
                opponentStatLineMap.put(entry.getKey(), 0);
                if (entry.getKey().equals("DREB")) {
                	teamStatLineMap.put("FLS", 0);
                	opponentStatLineMap.put("FLS", 0);
                }
                if (entry.getKey().equals("+/-")) {
                	teamStatLineMap.put("FG%", 0);
                	teamStatLineMap.put("3PT%", 0);
                	teamStatLineMap.put("FT%", 0);
                    opponentStatLineMap.put("FG%", 0);
                    opponentStatLineMap.put("3PT%", 0);
                    opponentStatLineMap.put("FT%", 0);
                }
            }

        }
        teamStats.put("POG", 0);
        opponentStats.put("POG", 0);
        
        Map<String,Map<String,Number>> teamGameStats = new LinkedHashMap<>();
        Map<String,Map<String,Number>> opponentGameStats = new LinkedHashMap<>();
    	
        for (Entry<String, Map<Integer, Map<String, Number>>> entry : RankAnalyzer.allPlayerStats.entrySet()) {
            String playerName = entry.getKey();
            Map<Integer, Map<String,Number>> gameStats = new LinkedHashMap<>(entry.getValue());

            for (Entry<Integer,Map<String,Number>> gameEntry : gameStats.entrySet()) {
            	int gameNum = gameEntry.getKey();
            	if (gameNum == gameID && (RankAnalyzer.playerTeams.get(playerName).equals(teamName) || RankAnalyzer.playerTeams.get(playerName).equals(opponentName))) {
        			Map<String,Number> statMap = new LinkedHashMap<>();
            		boolean isTeam = RankAnalyzer.playerTeams.get(playerName).equals(teamName);	
            		Map<String,Number> game = new LinkedHashMap<>(gameEntry.getValue());
            		for (Entry<String,Number> statEntry : game.entrySet()) {

            		  String statName = statEntry.getKey();
                      double statValue = 0;
                      if (statEntry.getValue() != null) {
                    	  if (isTeam) {
                    		  if(teamStats.containsKey(statName)) {
                                  statValue = statEntry.getValue().doubleValue();

                                  if (statValue >= teamStats.get(statName).doubleValue()) {
                                      // If the current value is greater than or equal to the highest value,
                                      // update the highest value and add this player to the list of players achieving it.
                                      if (statValue > teamStats.get(statName).doubleValue()) {
                                    	  teamStats.put(statName, statValue);
                                          List<String> players = new LinkedList<>();
                                          players.add(playerName);
                                          teamGameInfo.put(statName, players);
                                      } else {
                                          List<String> players = teamGameInfo.get(statName);
                                          if (players == null) {
                                              players = new LinkedList<>();
                                              teamGameInfo.put(statName, players);
                                          }
                                          players.add(playerName);
                                      }
                                  }
                              }
                    	  }
                          else {
                        	  if(opponentStats.containsKey(statName)) {
                        		    statValue = statEntry.getValue().doubleValue();
                        		    if (statValue >= opponentStats.get(statName).doubleValue()) {
                        		        // If the current value is greater than or equal to the highest value,
                        		        // update the highest value and add this player to the list of players achieving it.
                        		        if (statValue > opponentStats.get(statName).doubleValue()) {
                        		            opponentStats.put(statName, statValue);
                        		            List<String> players = new LinkedList<>();
                        		            players.add(playerName);
                        		            opponentGameInfo.put(statName, players);
                        		        } else {
                        		            List<String> players = opponentGameInfo.get(statName);
                        		            if (players == null) {
                        		                players = new LinkedList<>();
                        		                opponentGameInfo.put(statName, players);
                        		            }
                        		            players.add(playerName);
                        		        }
                        		    }
                        		}
                    	  
                          }  
                          statMap.put(statEntry.getKey(), statEntry.getValue());
                      }
            		}
            		if (isTeam) {
            			teamGameStats.put(playerName, statMap);
            		}
            		else {
            			opponentGameStats.put(playerName, statMap);
            		}
            	}	
            	
            }
        }


        // Print header
        System.out.println("");
        
        int maxTeamOutputWidth = calculateMaxWidth();
        int maxOpponentOutputWidth = calculateMaxWidth();
        int maxStatNameWidth = calculateMaxScoreWidth();
        String headerFormat = String.format("%%-%ds %%-%ds %%-%ds%n", maxTeamOutputWidth+2, maxStatNameWidth+9, maxOpponentOutputWidth);
        System.out.printf(headerFormat, teamName + ": ", "\tMIN\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOREB\tDREB\tFLS\t+/-\tFG%\t3PT%\tFT%\tPRF\tDNK\tFPTS\tGS\tPOG", "");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        
        for (String playerName : teamGameStats.keySet()) {
        	Map<String, Number> playerStats = new LinkedHashMap<>(teamGameStats.get(playerName));
        	String statLine = "\t";
        	double fgm = 0.0;
        	double fga = 0.0;
        	double threeptm = 0.0;
        	double threepta = 0.0;
        	double ftm = 0.0;
        	double fta  = 0.0;
        	for (String stat : playerStats.keySet()) {
		        		Number playerStatValue = playerStats.get(stat);
		        		if(stat.equals("PTS")) {
		        			statLine += String.valueOf(playerStats.get("MIN")) + "\t";	
		        		}
		        		if (stat.equals("FGM")) {
		    				fgm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FGA")) {
		    				fga = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("3PTM")) {
		    				threeptm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("3PTA")) {
		    				threepta = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FTM")) {
		    				ftm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FTA")) {
		    				fta = playerStatValue.doubleValue();
		    			}
            			if (!stat.equals("FPTS") && !stat.equals("GS")) {
            				playerStatValue = playerStatValue.byteValue();
                        }
                        if (stat.equals("+/-")) {
                        	if (playerStatValue.doubleValue() >= 0) {
                        		statLine += "+";
                        	}
                        }
                        if (!stat.equals("POG")|| stat.equals("POG") && playerStatValue.doubleValue() > 0) {
                        	if(!stat.equals("MIN")){
                            	statLine += String.valueOf(playerStatValue) + "\t";
                        	}
                        	if (teamStatLineMap.containsKey(stat)) {
                        		if (!stat.equals("FPTS")) {
                        			int totalStatValue = teamStatLineMap.get(stat).intValue() + playerStatValue.intValue();
                            		teamStatLineMap.put(stat, totalStatValue);
                        		}
                        		else {
                        			double totalStatValue = teamStatLineMap.get(stat).doubleValue() + playerStatValue.doubleValue();
                            		teamStatLineMap.put(stat, totalStatValue);
                        		}
                        		
                        	}
                        } 
            			
            			if (stat.equals("+/-")) {
            				if (fga > 0) {
            					double fgPercentage = (double) fgm/fga;
            					statLine += new DecimalFormat("#0.0%").format(fgPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				if (threepta > 0) {
            					double threeptPercentage = (double) threeptm/threepta;
            					statLine += new DecimalFormat("#0.0%").format(threeptPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				if (fta > 0) {
            					double ftPercentage = (double) ftm/fta;
            					statLine += new DecimalFormat("#0.0%").format(ftPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				
            			}
            			
                	}

             System.out.printf(headerFormat, playerName, statLine , "");
        }
        double teamStatFGPercentage = teamStatLineMap.get("FGM").doubleValue()/teamStatLineMap.get("FGA").doubleValue();
        double teamStat3PTPercentage = teamStatLineMap.get("3PTM").doubleValue()/teamStatLineMap.get("3PTA").doubleValue();
        double teamStatFTPercentage = teamStatLineMap.get("FTM").doubleValue()/teamStatLineMap.get("FTA").doubleValue();
        teamStatLineMap.put("FG%",teamStatFGPercentage);
        teamStatLineMap.put("3PT%",teamStat3PTPercentage);
        teamStatLineMap.put("FT%",teamStatFTPercentage);
        String teamStatLine = "\t";
        for (String stat : teamStatLineMap.keySet()) {
        	Number teamStatValue = teamStatLineMap.get(stat);
            if (stat.equals("+/-") && teamStatValue.doubleValue() >= 0) {
            	teamStatLine += "+";
            }
        	if (!stat.equals("FG%") && !stat.equals("3PT%") && !stat.equals("FT%") && !stat.equals("FPTS")) {	
            	teamStatLine += String.valueOf(teamStatValue) + "\t";
        	}
        	else if (stat.equals("FPTS")) {
        		teamStatLine += new DecimalFormat("0.0").format(teamStatValue) + "\t";
        	}
        	else {
        		teamStatLine += new DecimalFormat("#0.0%").format(teamStatValue) + "\t";
        	}
        	if (stat.equals("FT%")) {
        		teamStatLine += "\t";
        	}
        	
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(headerFormat, "Total:", teamStatLine , "");
        System.out.printf(headerFormat, "", "\tMIN\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOREB\tDREB\tFLS\t+/-\tFG%\t3PT%\tFT%\t\tDNK\tFPTS", "");
        System.out.println("\n");
        System.out.printf(headerFormat, opponentName + ": ", "\tMIN\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOREB\tDREB\tFLS\t+/-\tFG%\t3PT%\tFT%\tPRF\tDNK\tFPTS\tGS\tPOG", "");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (String playerName : opponentGameStats.keySet()) {
        	Map<String, Number> playerStats = new LinkedHashMap<>(opponentGameStats.get(playerName));
        	String statLine = "\t";
        	double fgm = 0.0;
        	double fga = 0.0;
        	double threeptm = 0.0;
        	double threepta = 0.0;
        	double ftm = 0.0;
        	double fta  = 0.0;
        	for (String stat : playerStats.keySet()) {
		        		Number playerStatValue = playerStats.get(stat);
		        		if(stat.equals("PTS")) {
		        			statLine += String.valueOf(playerStats.get("MIN")) + "\t";	
		        		}
		        		
		        		if (stat.equals("FGM")) {
		    				fgm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FGA")) {
		    				fga = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("3PTM")) {
		    				threeptm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("3PTA")) {
		    				threepta = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FTM")) {
		    				ftm = playerStatValue.doubleValue();
		    			}
		    			if (stat.equals("FTA")) {
		    				fta = playerStatValue.doubleValue();
		    			}
            			if (!stat.equals("FPTS") && !stat.equals("GS")) {
            				playerStatValue = playerStatValue.byteValue();
                        }
                        if (stat.equals("+/-")) {
                        	if (playerStatValue.doubleValue() >= 0) {
                        		statLine += "+";
                        	}
                        }
                        if (!stat.equals("POG") || stat.equals("POG") && playerStatValue.doubleValue() > 0) {
                        	if (!stat.equals("MIN")) {
                            	statLine += String.valueOf(playerStatValue) + "\t";
                        	}
                        	if (opponentStatLineMap.containsKey(stat)) {
                        		if (!stat.equals("FPTS")) {
                        			int totalStatValue = opponentStatLineMap.get(stat).intValue() + playerStatValue.intValue();
                        			opponentStatLineMap.put(stat, totalStatValue);
                        		}
                        		else {
                        			double totalStatValue = opponentStatLineMap.get(stat).doubleValue() + playerStatValue.doubleValue();
                        			opponentStatLineMap.put(stat, totalStatValue);
                        		}
                        	}
                        } 
            			
            			if (stat.equals("+/-")) {
            				if (fga > 0) {
            					double fgPercentage = (double) fgm/fga;
            					statLine += new DecimalFormat("#0.0%").format(fgPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				if (threepta > 0) {
            					double threeptPercentage = (double) threeptm/threepta;
            					statLine += new DecimalFormat("#0.0%").format(threeptPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				if (fta > 0) {
            					double ftPercentage = (double) ftm/fta;
            					statLine += new DecimalFormat("#0.0%").format(ftPercentage)+"\t";
            				}
            				else {
            					statLine+= "-\t";
            				}
            				
            			}
            			
                	}
             System.out.printf(headerFormat, playerName, statLine , "");
        }
        
        double opponentStatFGPercentage = opponentStatLineMap.get("FGM").doubleValue()/opponentStatLineMap.get("FGA").doubleValue();
        double opponentStat3PTPercentage = opponentStatLineMap.get("3PTM").doubleValue()/opponentStatLineMap.get("3PTA").doubleValue();
        double opponentStatFTPercentage = opponentStatLineMap.get("FTM").doubleValue()/opponentStatLineMap.get("FTA").doubleValue();
        opponentStatLineMap.put("FG%",opponentStatFGPercentage);
        opponentStatLineMap.put("3PT%",opponentStat3PTPercentage);
        opponentStatLineMap.put("FT%",opponentStatFTPercentage);
        String opponentStatLine = "\t";
        for (String stat : opponentStatLineMap.keySet()) {
            Number opponentStatValue = opponentStatLineMap.get(stat);
            if (stat.equals("+/-") && opponentStatValue.doubleValue() >= 0) {
            	opponentStatLine += "+";
            }
            if (!stat.equals("FG%") && !stat.equals("3PT%") && !stat.equals("FT%") && !stat.equals("FPTS")) {   
                opponentStatLine += String.valueOf(opponentStatValue) + "\t";
            }
        	else if (stat.equals("FPTS")) {
        		opponentStatLine += new DecimalFormat("0.0").format(opponentStatValue) + "\t";
        	}
            else {
                opponentStatLine += new DecimalFormat("#0.0%").format(opponentStatValue) + "\t";
            }
            if (stat.equals("FT%")) {
                opponentStatLine += "\t";
            }
            
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(headerFormat, "Total:", opponentStatLine , "");
        System.out.printf(headerFormat, "", "\tMIN\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOREB\tDREB\tFLS\t+/-\tFG%\t3PT%\tFT%\t\tDNK\tFPTS", "");



        System.out.println("\n");

                System.out.printf(headerFormat, teamScore > opponentScore ? "{" + teamName + "}" : teamName, 
        		"[" +  teamScore + " : " + opponentScore + "]", opponentScore > teamScore ? "{" + opponentName + "}" : opponentName);
        System.out.println("");
        System.out.printf(headerFormat, "","Game Highs:","");
        maxStatNameWidth = calculateMaxStatNameWidth();

        // Sort stats for consistent ordering
        List<String> sortedStats = new LinkedList<>();
        sortedStats.add("PTS");
        sortedStats.add("REB");
        sortedStats.add("AST");
        sortedStats.add("BLK");
        sortedStats.add("STL");
        sortedStats.add("TO");
        sortedStats.add("FGM");
        sortedStats.add("FGA");
        sortedStats.add("3PTM");
        sortedStats.add("3PTA");
        sortedStats.add("FTM");
        sortedStats.add("FTA");
        sortedStats.add("OREB");
        sortedStats.add("DREB");
        sortedStats.add("+/-");
        sortedStats.add("MIN");
        sortedStats.add("PRF");
        sortedStats.add("DNK");
        sortedStats.add("FPTS");
        sortedStats.add("GS");
        sortedStats.add("POG");
        String POG = "";

        // Printing each stat side by side
        for (String stat : sortedStats) {
        	if (!stat.equals("POG")) {
        		
        		
                int maxListLength = teamGameInfo.get(stat).size() > opponentGameInfo.get(stat).size() ? teamGameInfo.get(stat).size() :  opponentGameInfo.get(stat).size();
                for (int i = 0; i < maxListLength; i++) {
                    String rowFormat = String.format("%%-%ds %%s %%-%ds%n", maxTeamOutputWidth, maxStatNameWidth, maxOpponentOutputWidth);
                    Number teamStatValue = teamStats.get(stat);
        			String teamStatString = "";
        			Number opponentStatValue = opponentStats.get(stat);
        			String opponentStatString = "";	
                    	try {

                			
                			if (!stat.equals("FPTS") && !stat.equals("GS")) {
                				teamStatValue = teamStatValue.byteValue();
                				opponentStatValue = opponentStatValue.byteValue();
                            }
                            if (stat.equals("+/-")) {
                            	if (teamStatValue.doubleValue() >= 0) {
                            		teamStatString += "+";
                            	}
                            	if (opponentStatValue.doubleValue() >= 0) {
                            		opponentStatString += "+";
                            	}
                                
                            }
                            
                			teamStatString += String.valueOf(teamStatValue);
                			opponentStatString += String.valueOf(opponentStatValue);
                			if (i == 0) {
                    			System.out.printf(rowFormat, ((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || stat.equals("DNK") && teamStatValue.doubleValue() > 0 || 
                						stat.equals("STL") && teamStatValue.doubleValue() > 0 || 
                						stat.equals("BLK") && teamStatValue.doubleValue() > 0 ? teamGameInfo.get(stat).get(i) : "N/A") + 
                    					(teamGameInfo.get(stat).size()-1 > i && teamStatValue.doubleValue() > 0 ? "," : ""), 
                    					teamStatString + "\t"  +  stat + "\t" +  opponentStatString + "\t", 
                    					((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || stat.equals("DNK") && opponentStatValue.doubleValue() > 0 || 
                        						stat.equals("STL") && opponentStatValue.doubleValue() > 0 || 
                        						stat.equals("BLK") && opponentStatValue.doubleValue() > 0 ? opponentGameInfo.get(stat).get(i) : "N/A") + 
                    					(opponentGameInfo.get(stat).size()-1 > i && opponentStatValue.doubleValue() > 0 ? "," : ""));
                			}
                			else {
                				rowFormat = String.format("%%-%ds %%s %%-%ds %n", maxTeamOutputWidth+22, maxStatNameWidth, maxOpponentOutputWidth);
                				System.out.printf(rowFormat, ((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || 
                						stat.equals("DNK") && teamStatValue.doubleValue() > 0 || 
                						stat.equals("STL") && teamStatValue.doubleValue() > 0 || 
                						stat.equals("BLK") && teamStatValue.doubleValue() > 0 ? teamGameInfo.get(stat).get(i) + 
                        						(teamGameInfo.get(stat).size()-1 > i ? "," : "") : "") , "", 
                						((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || stat.equals("DNK") && opponentStatValue.doubleValue() > 0 || 
                        						stat.equals("STL") && opponentStatValue.doubleValue() > 0 || 
                        						stat.equals("BLK") && opponentStatValue.doubleValue() > 0 ? opponentGameInfo.get(stat).get(i) + (opponentGameInfo.get(stat).size()-1 > i ? "," : "") : "") );
                			}

                        } catch (IndexOutOfBoundsException e) {
                        	if (teamGameInfo.get(stat).size()-1 < i  && ((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || 
                        			stat.equals("DNK") && opponentStatValue.doubleValue() > 0 || 
            						stat.equals("STL") && opponentStatValue.doubleValue() > 0 || 
            						stat.equals("BLK") && opponentStatValue.doubleValue() > 0)) {
                        		rowFormat = String.format("%%-%ds %%s %%-%ds%n", maxTeamOutputWidth+22, maxStatNameWidth, maxOpponentOutputWidth);
                        		System.out.printf(rowFormat, "", "", opponentGameInfo.get(stat).get(i) + (opponentGameInfo.get(stat).size()-1 > i ? "," : ""));
                        	}
                        	else if (opponentGameInfo.get(stat).size()-1 < i && ((!stat.equals("DNK") && !stat.equals("BLK") && !stat.equals("STL")) || 
            						stat.equals("DNK") && teamStatValue.doubleValue() > 0 || 
            						stat.equals("STL") && teamStatValue.doubleValue() > 0 || 
            						stat.equals("BLK") && teamStatValue.doubleValue() > 0)) {
                        		System.out.printf(rowFormat, teamGameInfo.get(stat).get(i)+ (teamGameInfo.get(stat).size()-1 > i ? "," : ""), "", "");
                        	}
                            // Handle the exception here, such as printing an error message or performing some other action.
                        }
                }
        	}
        	else if (stat.equals("POG")) {
        		if (teamStats.get(stat).byteValue() == 1) {
        			POG += teamGameInfo.get(stat).get(0);
        		}
        		else if (opponentStats.get(stat).byteValue() == 1) {
        			POG += opponentGameInfo.get(stat).get(0);
        		}
        	}
        }
        
        System.out.println("");
        maxStatNameWidth = calculateMaxScoreWidth();
        headerFormat = String.format("%%-%ds %%-%ds %%-%ds%n", maxTeamOutputWidth, maxStatNameWidth, maxOpponentOutputWidth);
        System.out.printf(headerFormat, "", "POG: " + POG, "");
        System.out.println("");
        
        
    }

    private static int calculateMaxWidth() {
        int maxWidth = 1;  // Starting with a reasonable default width

        		List<String> playerNames = new LinkedList<>(RankAnalyzer.playerNames);
        		for (String name : playerNames) {
					String player = name;
					String formatted = player + ",";
					maxWidth = Math.max(maxWidth, formatted.length());
        		}
        return maxWidth;
    }
    
    

    private static int calculateMaxStatNameWidth() {
    	String string = "000000000000000AAAA000000000000000";
    	int length = string.length();
        return length; 
    }
    private static int calculateMaxScoreWidth() {
    	String string = "[0000:0000]";
    	int length = string.length();
        return length; 
    }




}