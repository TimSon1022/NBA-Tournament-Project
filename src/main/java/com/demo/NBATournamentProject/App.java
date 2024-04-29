package com.demo.NBATournamentProject;

import java.text.DecimalFormat;
import java.util.*;



public class App {
	static Map<String, List<Map<String, List<Map<String, String>>>>> playerMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();
    static Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();
    static Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();


    public static void main(String[] args) {
    	
        // Create the key-value map
                // Initialize the team names
        String[] teamNames = {
            "Team 3 Pointers", "Team 6th Man", "Team Africa", "Team Canada", "Team China",
            "Team Defense", "Team Dunk", "Team Europe", "Team France", "Team Improved",
            "Team Injuries", "Team Oceania", "Team Old", "Team Rookies", "Team Spain", "Team USA"
        };
        
        for (String teamName : teamNames) {
			playerMap.put(teamName, ExcelReader.readExcelPlayer(teamName + " Stats.xlsx"));
			teamStatsMap.put(teamName, ExcelReader.readExcelTeamStats(teamName + " Stats.xlsx"));
			opponentStatsMap.put(teamName, ExcelReader.readExcelOpponentStats(teamName + " Stats.xlsx"));
        }
        HighestStatTracker.initializeStats();
        RankAnalyzer.addPlayerNames(playerMap);
        RankAnalyzer.addAllPlayerStats(playerMap);
        RankAnalyzer.addAllTeamStats(teamStatsMap, true);
        RankAnalyzer.addAllTeamStats(opponentStatsMap, false);
        RankAnalyzer.addAllGames(teamStatsMap, opponentStatsMap);
        RankAnalyzer.addStatPercentages();
        RankAnalyzer.addPlayerPER();
        RankAnalyzer.addPlayerScore();
    
        TeamRecordAnalyzer analyzer = new TeamRecordAnalyzer(teamStatsMap, opponentStatsMap);
        RankAnalyzer.addOpponentMIN(playerMap);
        RankAnalyzer.addPlayerOffAndDefRatings();
        

        
        // Example data initialization
        PlayerOfTheRoundAnalyzer playerOfTheRoundAnalyzer = new PlayerOfTheRoundAnalyzer(playerMap);
        RankAnalyzer.addTournamentAwardsScore(analyzer.standings);
        analyzer.findUniqueGames();

        // Example usage

        
        
        // Note: You need to prepare 'playerStats' with your actual player statistics data including "POG" counts

        

        boolean isDone = false;
        System.out.println("Welcome to the NBA Tournament Database!");
        while (!isDone) {
        	System.out.println("[1] Check Rosters");
            System.out.println("[2] Search Player Stats");
            System.out.println("[3] Check Team Stats");
            System.out.println("[4] Check Player Rankings");
            System.out.println("[5] Check Team Offensive Rankings");
            System.out.println("[6] Check Team Defensive Rankings");
            System.out.println("[7] Check Game Highs of Players");
            System.out.println("[8] Search Player Game Highs");
            System.out.println("[9] Check Game Highs of Teams");
            System.out.println("[10] Check Team Standings");
            System.out.println("[11] Check Head to Head Records");
            System.out.println("[12] Check Player of the Rounds");
            System.out.println("[13] Check Best Players of the Rounds");
            System.out.println("[14] Check Game Records");
            System.out.println("[L] Leave");
            
            Scanner scan = new Scanner(System.in);
            String index = scan.nextLine();
            boolean validIndex = false;
            
            while (!validIndex) {
            	
                if (index.equals("1")) {
                	System.out.println("Which Team would you like to look at? (Type L to leave)");
                    for (int i = 0; i < teamNames.length; i++) {
                    	String space  = "  ";
                    	if (i + 1 >= 10) {
                    		space = " ";
                    	}
            			System.out.println("[" + (i + 1) + "]" + space +  teamNames[i]);
                    }
                    System.out.println("[L]  Leave");
                	String teamAnswer = scan.nextLine();
                	if (teamAnswer.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		boolean found = false;

                		for(String teamName : teamNames) {
                		    if(teamAnswer.toLowerCase().equals(teamName.toLowerCase())) {
                		    	teamAnswer = teamName;
                		        found = true;
                		        break;
                		    }
                		}
                		
                		if (isPositiveInteger(teamAnswer) || found) {
                			if (found) {
                				RankAnalyzer.findRoster(teamAnswer, scan);
                			}
                			
                			else if (Integer.parseInt(teamAnswer) >= 1 && Integer.parseInt(teamAnswer) <= teamNames.length) {
                				RankAnalyzer.findRoster(teamNames[Integer.parseInt(teamAnswer) - 1],scan);
                			}
                    		else {
                    			System.out.println("Not an option, please try again.");
                    		}
                		}
                		else {
                			System.out.println("Not an option, please try again.");
                		}

                	}
                
                }
            	 	
            	
                else if (index.equals("2")) {
                	System.out.println("What player or keyword would you like to search for? (Type L to leave)");
                	System.out.print("Player Name: ");
                	String playerName = scan.nextLine();
                	if (playerName.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		Map<Integer, String> choosePlayer = new HashMap<>();
                		if (playerName.length() >= 3) {
                			int playerIndex = 1;
                    		for (String player : RankAnalyzer.playerNames) {
                    			if (player.toLowerCase().contains(playerName.toLowerCase())) {
                    				choosePlayer.put(playerIndex, player);
                    				playerIndex++;
                    			}
                    		}
                    		if (!choosePlayer.isEmpty()) {
                    			boolean loop = false;
                    			while (!loop) {
                        			System.out.println("Please Choose which player you are looking for (press L to leave keyword search): ");
                        			for (int integer : choosePlayer.keySet()) {
                        				System.out.println("[" + integer + "]" + " " + choosePlayer.get(integer));
                        			}
                        			System.out.println("[L] Leave\n");
                        			String choosePlayerIndex = scan.nextLine();
                        			
                        			if (isPositiveInteger(choosePlayerIndex)  || containsValueIgnoreCase(choosePlayer,choosePlayerIndex)) {
                        				if (App.isPositiveInteger(choosePlayerIndex) && Integer.parseInt(choosePlayerIndex) > 0 && Integer.parseInt(choosePlayerIndex) <= choosePlayer.size()) {
                        					String choosePlayerName = choosePlayer.get(Integer.parseInt(choosePlayerIndex));
                        					StatsPrinter.printPlayerStats(choosePlayerName, playerMap, 0, 0, true, false);
                        					loop = true;
                        				}
                        				else if (containsValueIgnoreCase(choosePlayer,choosePlayerIndex)) {
                        					String choosePlayerName = findValueIgnoreCase(choosePlayer, choosePlayerIndex);
                        					StatsPrinter.printPlayerStats(choosePlayerName, playerMap, 0, 0, true, false);
                        					loop = true;
                        				}
                            			else {
                            				System.out.println("Not an answer above, please try again.");
                            			}
                        			}
                        			else if (choosePlayerIndex.toUpperCase().equals("L")) {
                        				loop = true;
                        			}
                        			else {
                        				System.out.println("Not an answer above, please try again.");
                        			}
                    			}	
                    		}
                    		else {
                    			System.out.println("Player(s) does not exist, please try again.");
                    		}
                		}
                		else {
                			System.out.println("player or keyword has to be at least 3 characters");
                		}

                		
                		
                		
                		
                	}
                }
                
                else if (index.equals("3")) {
                	System.out.println("Which Team would you like to look at? (Type L to leave)");
                    for (int i = 0; i < teamNames.length; i++) {
                    	String space  = "  ";
                    	if (i + 1 >= 10) {
                    		space = " ";
                    	}
            			System.out.println("[" + (i + 1) + "]" + space +  teamNames[i]);
                    }
                    System.out.println("[L]  Leave");
                	String teamAnswer = scan.nextLine();
                	if (teamAnswer.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		boolean found = false;

                		for(String teamName : teamNames) {
                		    if(teamAnswer.toLowerCase().equals(teamName.toLowerCase())) {
                		        found = true;
                		        teamAnswer = teamName;
                		        break;
                		    }
                		}
                		
                		if (isPositiveInteger(teamAnswer) || found) {
                			if (found) {
                				StatsPrinter.printTeamStats(teamAnswer, teamStatsMap, true);
                				StatsPrinter.printTeamStats(teamAnswer, opponentStatsMap, false);
                			}
                			
                			else if (Integer.parseInt(teamAnswer) >= 1 && Integer.parseInt(teamAnswer) <= teamNames.length) {
                				StatsPrinter.printTeamStats(teamNames[Integer.parseInt(teamAnswer) - 1], teamStatsMap, true);
                				StatsPrinter.printTeamStats(teamNames[Integer.parseInt(teamAnswer) - 1], opponentStatsMap, false);
                			}
                    		else {
                    			System.out.println("Not an option, please try again.");
                    		}
                		}

                	}
                }
                else if (index.equals("4")) {
                	System.out.println("Which Stat Ranking would you like to see?");
                    System.out.println("[1]  Points");
                    System.out.println("[2]  Rebounds");
                    System.out.println("[3]  Assists");
                    System.out.println("[4]  Blocks");
                    System.out.println("[5]  Steals");
                    System.out.println("[6]  Turnovers");
                    System.out.println("[7]  Field Goals Made");
                    System.out.println("[8]  Field Goals Attempted");
                    System.out.println("[9]  3 Pointers Made");
                    System.out.println("[10] 3 Pointers Attempted");
                    System.out.println("[11] Free Throws Made");
                    System.out.println("[12] Free Throws Attempted");
                    System.out.println("[13] Offensive Rebounds");
                    System.out.println("[14] Defensive Rebounds");
                    System.out.println("[15] Fouls");
                    System.out.println("[16] Plus Minus");
                    System.out.println("[17] Field Goal Percentage");
                    System.out.println("[18] 3 Pointer Percentage");
                    System.out.println("[19] Free Throw Percentage");
                    System.out.println("[20] Assist Percentage");
                    System.out.println("[21] Offensive Rebound Percentage");
                    System.out.println("[22] Defensive Rebound Percentage");
                    System.out.println("[23] Total Rebound Percentage");
                    System.out.println("[24] Minutes");
                    System.out.println("[25] Points Responsible For");
                    System.out.println("[26] Dunks");
                    System.out.println("[27] Player Efficiency Rating");
                    System.out.println("[28] Fantasy Points");
                    System.out.println("[29] Game Score");
                    System.out.println("[30] Player of the Game");
                    System.out.println("[31] Player Scores");
                    System.out.println("[32] Usage Rate");
                    System.out.println("[33] True Shooting Percentage");
                    System.out.println("[34] Points Scored Percentage");
                    System.out.println("[35] Points Assisted Percentage");
                    System.out.println("[36] Points Responsible For Percentage");
                    System.out.println("[37] Offensive Rating");
                    System.out.println("[38] Defensive Rating");
                    System.out.println("[39] Floor Percentage");
                    System.out.println("[40] Double Doubles");
                    System.out.println("[41] Triple Doubles");
                    System.out.println("[42] Assist to Turnover Ratio");
                    System.out.println("[43] MVP Ladder");
                    System.out.println("[44] DPOT Ladder");
                    System.out.println("[45] OPOT Ladder");
                    System.out.println("[46] 6MOT Ladder");
                    System.out.println("[L]  Leave");
                    String statNum = scan.nextLine();
                    

                    if (statNum.toUpperCase().equals("L")) {
                    	validIndex = true;
                    	break;
                    }
                    
                    if (isPositiveInteger(statNum)) {
                    	if (Integer.parseInt(statNum) >= 1 && Integer.parseInt(statNum) <= 46) {
                        	boolean validPlayers = false;
                        	while (!validPlayers) {
                            	System.out.println("How many players do you want to see in this stat ranking?");
                            	String totalPlayers = scan.nextLine();
                            	if (isPositiveInteger(totalPlayers)) {
                            		RankAnalyzer.getRankings(Integer.parseInt(statNum), Integer.parseInt(totalPlayers));
                            		validPlayers = true;
                            	}
                            	else {
                            		System.out.println("not an option, please try again");
                            	}
                        	}
       	
                        }
                        else {
                        	System.out.println("Not an option, please try again.");
                        }
                    }
                    else {
                    	System.out.println("Not an option, please try again.");
                    }	
                }
                else if (index.equals("5") || index.equals("6")) {
                	System.out.println("Which Stat Ranking would you like to see?");
                    System.out.println("[1]  Points");
                    System.out.println("[2]  Field Goals Made");
                    System.out.println("[3]  Field Goals Attempted");
                    System.out.println("[4]  3 Pointers Made");
                    System.out.println("[5]  3 Pointers Attempted");
                    System.out.println("[6]  Free Throws Made");
                    System.out.println("[7]  Free Throws Attempted");
                    System.out.println("[8]  Dunks");
                    System.out.println("[9]  Fast Break Points");
                    System.out.println("[10] Points in the Paint");
                    System.out.println("[11] Second Chance Points");
                    System.out.println("[12] Bench Points");
                    System.out.println("[13] Assists");
                    System.out.println("[14] Offensive Rebounds");
                    System.out.println("[15] Defensive Rebounds");
                    System.out.println("[16] Total Rebounds");
                    System.out.println("[17] Steals");
                    System.out.println("[18] Blocks");
                    System.out.println("[19] Turnovers");
                    System.out.println("[20] Points Off Turnovers");
                    System.out.println("[21] Team Fouls");
                    System.out.println("[22] Time of Possession");
                    System.out.println("[23] Field Goal Percentage");
                    System.out.println("[24] 3 Point Percentage");
                    System.out.println("[25] Free Throw Percentage");
                    System.out.println("[26] Possessions");
                    System.out.println( (index.equals("5")) ? "[27] Offensive Efficiency Rating" : "[27] Defensive Efficiency Rating");
                    System.out.println("[L]  Leave");
                    String statNum = scan.nextLine();
                    if (statNum.toUpperCase().equals("L")) {
                    	validIndex = true;
                    	break;
                    }
                	boolean isTeam = true;
                	if (index.equals("6")) {
                		isTeam = false;
                	}
                    
                    if (isPositiveInteger(statNum)) {

                    	if (Integer.parseInt(statNum) >= 1 && Integer.parseInt(statNum) <= 27) {
                        	RankAnalyzer.getTeamRankings(Integer.parseInt(statNum), isTeam);
       	
                        }
                        else {
                        	System.out.println("Not an option, please try again.");
                        }
                    }
                    else {
                    	System.out.println("Not an option, please try again.");
                    }	
                }
                
                else if (index.equals("7")) {
                	HighestStatTracker.printHighestPlayerStats();
                	validIndex = true;
                }
                
                else if (index.equals("8")) {
                	System.out.println("What player or keyword would you like to search for? (Type L to leave)");
                	System.out.print("Player Name: ");
                	String playerName = scan.nextLine();
                	if (playerName.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		
                		Map<Integer, String> choosePlayer = new HashMap<>();
                		if (playerName.length() >= 3) {
                			int playerIndex = 1;
                    		for (String player : RankAnalyzer.playerNames) {
                    			if (player.toLowerCase().contains(playerName.toLowerCase())) {
                    				choosePlayer.put(playerIndex, player);
                    				playerIndex++;
                    			}
                    		}
                    		if (!choosePlayer.isEmpty()) {
                    			boolean loop = false;
                    			while (!loop) {
                        			System.out.println("Please Choose which player you are looking for: ");
                        			for (int integer : choosePlayer.keySet()) {
                        				System.out.println("[" + integer + "]" + " " + choosePlayer.get(integer));
                        			}
                        			String choosePlayerIndex = scan.nextLine();
                        			
                        			if (isPositiveInteger(choosePlayerIndex)) {
                        				if (Integer.parseInt(choosePlayerIndex) > 0 && Integer.parseInt(choosePlayerIndex) <= choosePlayer.size()) {
                        					String choosePlayerName = choosePlayer.get(Integer.parseInt(choosePlayerIndex));
                        					HighestStatTracker.printPlayerTournamentHighStats(choosePlayerName);
                        					loop = true;
                        				}
                            			else {
                            				System.out.println("Not an answer above, please try again.");
                            			}
                        			}
                        			else {
                        				System.out.println("Not an answer above, please try again.");
                        			}
                    			}	
                    		}
                    		else {
                    			System.out.println("Player(s) does not exist, please try again.");
                    		}
                		}
                		else {
                			System.out.println("player or keyword has to be at least 3 characters");
                		}		
                	}
                }
                
                else if (index.equals("9")) {
                	HighestStatTracker.printHighestTeamStats();
                	validIndex = true;
                }
                else if (index.equals("10")) {
                	analyzer.printStandings();

                    validIndex = true;
                }
                
                else if (index.equals("11")) {
                	System.out.println("Which Team would you like to look at? (Type L to leave)");
                    for (int i = 0; i < teamNames.length; i++) {
                    	String space  = "  ";
                    	if (i + 1 >= 10) {
                    		space = " ";
                    	}
            			System.out.println("[" + (i + 1) + "]" + space +  teamNames[i]);
                    }
                    System.out.println("[L]  Leave");
                	String teamAnswer = scan.nextLine();
                	if (teamAnswer.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		boolean found = false;

                		for(String teamName : teamNames) {
                		    if(teamAnswer.toLowerCase().equals(teamName.toLowerCase())) {
                		        found = true;
                		        teamAnswer = teamName;
                		        break;
                		    }
                		}
                		
                		if (isPositiveInteger(teamAnswer) || found) {
                			if (found) {
                				analyzer.printHead2HeadRecord(teamAnswer);
                			}
                			
                			else if (Integer.parseInt(teamAnswer) >= 1 && Integer.parseInt(teamAnswer) <= teamNames.length) {
                				analyzer.printHead2HeadRecord(teamNames[Integer.parseInt(teamAnswer) - 1]);
                			}
                    		else {
                    			System.out.println("Not an option, please try again.");
                    		}
                		}

                	}
                
                }
                else if (index.equals("12")) {
                	System.out.println("------------------------------------------------------------------");
                	System.out.println("\t\t     Players of each Round:");
                	for (int i = 1; i <= 9; i++) {
                		String string = "\t\t  To Be Determined";
                		if (!playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).isEmpty()) {
                			string = playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).get(0);
                			
                		}
                		
                        	System.out.println("Round " + i + ": " + string + ((!playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).isEmpty()) ? ", POGs: " + 
                        		PlayerOfTheRoundAnalyzer.POTRPOGSMap.get(string) + ", " + RankAnalyzer.playerTeams.get(string) + ", Wins: " + 
                        		PlayerOfTheRoundAnalyzer.teamWinsForPOTRMap.get(string) + ", Player Score: " + new DecimalFormat("0.00").format(PlayerOfTheRoundAnalyzer.playerScoreForPOTRMap.get(string)) : ""));
                        	 if (!string.equals("\t\t  To Be Determined")) {
                        		 int gamesPerRound = 5;
                        	     int start = (i - 1) * gamesPerRound;
                        	     int end = start + gamesPerRound;
                        		 StatsPrinter.printPlayerStats(string,playerMap,start,end, false, true);
                        	 }
                        	
                	}
                	System.out.println("------------------------------------------------------------------");
                	validIndex = true;
                }
                
                else if (index.equals("13")) {
                	System.out.println("------------------------------------------------------------------");
                	System.out.println("\t\t     Best Players of each Round:");
                	int gamesPerRound = 5;
                	for (int i = 1; i <= 9; i++) {
                		String string = "\tTo Be Determined";
                		System.out.print("Round " + i  + ": ");
                		if (!playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).isEmpty()) {
                			System.out.print("\n");
                    		for (String playerName : playerOfTheRoundAnalyzer.topPlayersForRound.get(i).keySet()) {
                    			
                    			System.out.println(playerName  + ", Player Score: " + new DecimalFormat("0.00").format(playerOfTheRoundAnalyzer.topPlayersForRound.get(i).get(playerName)) +
                    					  ", "  + RankAnalyzer.playerTeams.get(playerName));
                    		 
                       	     int start = (i - 1) * gamesPerRound;
                       	     int end = start + gamesPerRound;
                       		 StatsPrinter.printPlayerStats(playerName,playerMap,start,end, false, true);
                    		}
                    		System.out.println("\n");
                		}
                		else {
                			System.out.print(string + "\n");
                		}
                	}
                	System.out.println("------------------------------------------------------------------");
                	validIndex = true;
                }
                else if (index.equals("14")) {
                    int week = 0;


                    for (GameRecord game : analyzer.sortedGames) {
                        int gameWeek = game.gameNumber; // Calculate week number
                        if (gameWeek > week) {
                        	week = gameWeek;
                        	System.out.println("[" + week  + "] Week " + week);
                        }
                    }
                    System.out.println("[L] Leave");
                    System.out.println("\nEnter the week number to view games (press L to leave): ");
                    String weekNumber = scan.nextLine();
                    
                    if (isPositiveInteger(weekNumber)) {
                    	if (Integer.parseInt(weekNumber) > 0 && Integer.parseInt(weekNumber) <= week) {
                    		Map <Integer, GameRecord> gamesForWeek = new HashMap<>(analyzer.gamesForWeek(Integer.parseInt(weekNumber)));
                    		boolean loop = false;
                    		while (!loop) {
                    			
                                System.out.println("\nGames for Week " + weekNumber + ":");
                                for (int i : gamesForWeek.keySet()) {
                                	GameRecord game = gamesForWeek.get(i);
                                    System.out.println("[" + i + "] " + (game.isHomeGame ? game.opponent + " vs " +  game.team :  game.team + " vs " +  game.opponent));
                                }
                                System.out.println("[L] Leave\n");
                                
                                System.out.println("\nWhat Game would you like to look at? (press L to leave):");
                                String game = scan.nextLine();
                                if(isPositiveInteger(game)) {
                                	if (Integer.parseInt(game) > 0 && Integer.parseInt(game) <= gamesForWeek.size()) {
                                		int gameNum = Integer.parseInt(game);
                                		GameRecord gameRecord = gamesForWeek.get(gameNum);
                                		boolean isHome = gameRecord.isHomeGame;
                                		String teamName = gameRecord.team;
                                		String opponentName = gameRecord.opponent;
                                		int teamScore = gameRecord.teamScore;
                                		int opponentScore = gameRecord.opponentScore;
                                		HighestStatTracker.printHeadToHeadGameInfo(Integer.parseInt(weekNumber), 
                                				isHome ? opponentName : teamName,
                                						isHome ? teamName : opponentName, 
                                						isHome ? opponentScore : teamScore, 
                                						isHome ? teamScore : opponentScore);
                                	}
                                	else {
                                		System.out.println("Not an option, please try again.");
                                	}
                                }
                                else if (game.toUpperCase().equals("L")) {
                                	loop = true;
                                }
                                else {
                                	System.out.println("Not an option, please try again.");
                                }
                                
                    		}
                            

                    	}
                    	else {
                    		System.out.println("Not an option, please try again.");
                    	}
                    	
                    }
                    else if (weekNumber.toUpperCase().equals("L")) {
                    	validIndex = true;
                    }
                    else {
                    	System.out.println("Not an option, please try again.");
                    }

                }
                
                else if (index.toUpperCase().equals("L")) {
                	isDone = true;
                	validIndex = true;
                    scan.close();
                    break;
                	
                }
                else {
                	System.out.println("Not an option, please try again.");
                	validIndex = true;
                }
            }

            
        } 
    
    }
    public static boolean isPositiveInteger(String s) {
        try {
        	int num = Integer.parseInt(s);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean containsValueIgnoreCase(Map<Integer, String> map, String valueToFind) {
        return map.values().stream()
                   .anyMatch(value -> value.equalsIgnoreCase(valueToFind));
    }
    public static String findValueIgnoreCase(Map<Integer, String> map, String valueToFind) {
        for (String value : map.values()) {
            if (value.equalsIgnoreCase(valueToFind)) {
                return value; // Return the actual value from the map
            }
        }
        return null; // Return null if no matching value is found
    }
}
