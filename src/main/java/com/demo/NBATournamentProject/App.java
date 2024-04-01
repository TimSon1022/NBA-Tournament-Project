package com.demo.NBATournamentProject;

import java.text.DecimalFormat;
import java.util.*;


public class App {
    public static void main(String[] args) {
    	
        // Create the key-value map
        Map<String, List<Map<String, List<Map<String, String>>>>> playerMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();
        Map<String, List<Map<String, List<Map<String, String>>>>> teamStatsMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();
        Map<String, List<Map<String, List<Map<String, String>>>>> opponentStatsMap = new HashMap<String, List<Map<String, List<Map<String, String>>>>>();

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
                				RankAnalyzer.findRoster(teamAnswer);
                			}
                			
                			else if (Integer.parseInt(teamAnswer) >= 1 && Integer.parseInt(teamAnswer) <= teamNames.length) {
                				RankAnalyzer.findRoster(teamNames[Integer.parseInt(teamAnswer) - 1]);
                			}
                    		else {
                    			System.out.println("Not an option, please try again.");
                    		}
                		}

                	}
                
                }
            	
            	
            	
            	
            	
            	
            	
                else if (index.equals("2")) {
                	System.out.println("What Player would you like to search for? (Type L to leave)");
                	System.out.print("Player Name: ");
                	String playerName = scan.nextLine();
                	if (playerName.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		StatsPrinter.printPlayerStats(playerName, playerMap);
                		
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
                    System.out.println("[34] Offensive Rating");
                    System.out.println("[35] Defensive Rating");
                    System.out.println("[36] Floor Percentage");
                    System.out.println("[37] MVP Ladder");
                    System.out.println("[38] DPOT Ladder");
                    System.out.println("[L]  Leave");
                    String statNum = scan.nextLine();
                    

                    if (statNum.toUpperCase().equals("L")) {
                    	validIndex = true;
                    	break;
                    }
                    
                    if (isPositiveInteger(statNum)) {
                    	if (Integer.parseInt(statNum) >= 1 && Integer.parseInt(statNum) <= 38) {
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
                	System.out.println("What Player would you like to search for? (Type L to leave)");
                	System.out.print("Player Name: ");
                	String playerName = scan.nextLine();
                	if (playerName.toUpperCase().equals("L")) {
                		validIndex = true;
                		break;
                	}
                	else {
                		HighestStatTracker.printPlayerTournamentHighStats(playerName);
                		
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
                		String string = "To Be Determined";
                		if (!playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).isEmpty()) {
                			string = playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).get(0);
                			
                		}
                		
                        	System.out.println("Round " + i + ": " + string + ((!playerOfTheRoundAnalyzer.calculatePlayerOfTheRound(i).isEmpty()) ? ", POGs: " + 
                        		PlayerOfTheRoundAnalyzer.POTRPOGSMap.get(string) + ", Team Wins: " + 
                        		PlayerOfTheRoundAnalyzer.teamWinsForPOTRMap.get(string) + ", Player Score: " + new DecimalFormat("0.00").format(PlayerOfTheRoundAnalyzer.playerScoreForPOTRMap.get(string)) : ""));

                	}
                	System.out.println("------------------------------------------------------------------");
                	validIndex = true;
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
        	System.out.println(num);
        	
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
