package com.demo.NBATournamentProject;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class RankAnalyzer {
	
	 	static Map<String, Integer> totalPlayerPTSMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerREBMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerASTMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerBLKMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerSTLMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerTOMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerFGMMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerFGAMap = new HashMap<>();
	    static Map<String, Integer> totalPlayer3PTMMap = new HashMap<>();
	    static Map<String, Integer> totalPlayer3PTAMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerFTMMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerFTAMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerOREBMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerDREBMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerFLSMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerPlusMinusMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerPOGMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerMINMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerPRFMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerDNKMap = new HashMap<>();
	    static Map<String, Double> totalPlayerFPTSMap = new HashMap<>();
	    static Map<String, Double> totalPlayerGSMap = new HashMap<>();
	    static Map<String, Double> totalPlayerPERMap = new HashMap<>();
	    static Map<String, Double> totalPlayerDRBPMap = new HashMap<>();    
	    static Map<String, Double> totalPlayerFGPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayer3PTPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerFTPercentageMap = new HashMap<>();
	    static Map<String, Integer> totalPlayerGamesMap = new HashMap<>();
	    static Map<String, Integer> totalTeamMINMap = new HashMap<>();
	    static Map<String, String> playerTeams = new LinkedHashMap<>();
	    static Map<String, Double> playerScoreMap= new HashMap<>();
	    static Map<String, Double> playerOffRatingMap= new HashMap<>();
	    static Map<String, Double> playerDefRatingMap= new HashMap<>();
	    static Map<String, Double> playerFloorPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerASTTORatioMap = new HashMap<>();
	    
	    
	    static Map<String, Double> playerMVPScoreMap = new HashMap<>();
	    static Map<String, Double> playerDPOTScoreMap = new HashMap<>();
	    static Map<String, Double> playerOPOTScoreMap = new HashMap<>();
	    static Map<String, Double> player6MOTScoreMap = new HashMap<>();
	    static Map<String, Double> playerPTSScoredPercentageMap = new HashMap<>();
	    static Map<String, Double> playerPTSAssistedPercentageMap = new HashMap<>();
	    static Map<String, Double> playerPRFPercentageMap = new HashMap<>();
	    static int totalPlayers = 0;
	    static int totalPlayerGames = 0;
	    static int totalPlayerMIN = 0;
	    static int totalPlayerFGA = 0;
	    static int totalPlayer3PTA = 0;
	    static int totalPlayerFTA = 0;
	    static double avgPlayerMIN = 0;
	    static double avgPlayerFGA = 0;
	    static double avgPlayer3PTA = 0;
	    static double avgPlayerFTA = 0;

	    
	    
	    static Map<String, Integer> totalTeamPTSMap = new HashMap<>();
	    static Map<String, Integer> totalTeamFGMMap = new HashMap<>();
	    static Map<String, Integer> totalTeamFGAMap = new HashMap<>();
	    static Map<String, Integer> totalTeam3PTMMap = new HashMap<>();
	    static Map<String, Integer> totalTeam3PTAMap = new HashMap<>();
	    static Map<String, Integer> totalTeamFTMMap = new HashMap<>();
	    static Map<String, Integer> totalTeamFTAMap = new HashMap<>();
	    static Map<String, Integer> totalTeamDNKMap = new HashMap<>();
	    static Map<String, Integer> totalTeamFBPTSMap = new HashMap<>();
	    static Map<String, Integer> totalTeamPTSiPMap = new HashMap<>();
	    static Map<String, Integer> totalTeamSCPTSMap = new HashMap<>();
	    static Map<String, Integer> totalTeamBPTSMap = new HashMap<>();
	    static Map<String, Integer> totalTeamASTMap = new HashMap<>();
	    static Map<String, Integer> totalTeamOREBMap = new HashMap<>();
	    static Map<String, Integer> totalTeamDREBMap = new HashMap<>();
	    static Map<String, Integer> totalTeamTREBMap = new HashMap<>();
	    static Map<String, Integer> totalTeamSTLMap = new HashMap<>();
	    static Map<String, Integer> totalTeamBLKMap = new HashMap<>();
	    static Map<String, Integer> totalTeamTOMap = new HashMap<>();
	    static Map<String, Integer> totalTeamPTSOffMap = new HashMap<>();
	    static Map<String, Integer> totalTeamTFMap = new HashMap<>();
	    static Map<String, Integer> totalTeamTOPMap = new HashMap<>();
	    
	    static Map<String, Double> totalTeamFGPercentageMap = new HashMap<>();
	    static Map<String, Double> totalTeam3PTPercentageMap = new HashMap<>();
	    static Map<String, Double> totalTeamFTPercentageMap = new HashMap<>(); 
	    static Map<String, Double> totalTeamPOSMap = new HashMap<>();
	    static Map<String, Integer> totalTeamGamesMap = new HashMap<>();
	    static Map<String, String> teamTeams = new HashMap<>(); // Note: This might need renaming based on its use
	    static Map<String, Double> teamGradeMap= new HashMap<>();
	    
	    
	    static Map<String, Double> totalTeamPPPMap = new HashMap<>();
	    static Map<String, Double> totalTeamOERMap = new HashMap<>();
	    static Map<String, Double> totalOpponentPPPMap = new HashMap<>();
	    static Map<String, Double> totalTeamDERMap = new HashMap<>();
	    

	    
	    
	    static Map<String, Double> totalPlayerOREBPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerDREBPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerTREBPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerASTPercentageMap = new HashMap<>();
	    static Map<String, Double> totalPlayerUsageRateMap = new HashMap<>();
	    static Map<String, Double> totalPlayerTSPercentageMap = new HashMap<>();
	    
	    static Map<String, Integer> playerDoubleDoublesMap = new HashMap<>();
	    static Map<String, Integer> playerTripleDoublesMap = new HashMap<>();
	    
	    
	    
	    static Map<String, Integer> totalOpponentPTSMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentFGMMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentFGAMap = new HashMap<>();
	    static Map<String, Integer> totalOpponent3PTMMap = new HashMap<>();
	    static Map<String, Integer> totalOpponent3PTAMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentFTMMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentFTAMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentDNKMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentFBPTSMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentPTSiPMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentSCPTSMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentBPTSMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentASTMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentOREBMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentDREBMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentTREBMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentSTLMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentBLKMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentTOMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentPTSOffMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentTFMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentTOPMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentMINMap = new HashMap<>();

	    static Map<String, Double> totalOpponentFGPercentageMap = new HashMap<>();
	    static Map<String, Double> totalOpponent3PTPercentageMap = new HashMap<>();
	    static Map<String, Double> totalOpponentFTPercentageMap = new HashMap<>(); 
	    static Map<String, Double> totalOpponentPOSMap = new HashMap<>();
	    static Map<String, Integer> totalOpponentGamesMap = new HashMap<>();
	    static Map<String, String> opponentTeams = new HashMap<>(); // Note: Renamed based on assumed use
	    static Map<String, Double> opponentGradeMap= new HashMap<>();
	    
	    static Map<String, Map<String, List<List<Integer>>>> games = new HashMap<>();
	    
	    
	    
	    
	 // Define weights for different categories
	    final static double weightPOG = 50.0; // Weight for Player of the Game
	    final static double weightPRF = 0.8; // Weight for Points Responsible For
	    final static double weightKeyStats = 1.1; // Slightly higher weight for rebounds, assists, etc.
	    final static double weightGS = 1.5;  // Weight for Game Score
	    final static double weightEfficiency = 20.0; // Weight for efficiency metrics (FG%, 3PT%, FT%)
	    final static double weightNegative = 2.2; // Weight for turnovers and fouls
	    
	    static double VOP = 0;
	    static double Factor = 0;
	    static double tournamentPTS = 0;
	    static double tournamentAST = 0;
	    static double tournamentFGM = 0;
	    static double tournamentFGA = 0;
	    static double tournamentOREB = 0;
	    static double tournamentTO = 0;
	    static double tournamentFTM = 0;
	    static double tournamentFTA = 0;
	    static double tournamentFLS = 0;
	    

        static double tournamentSTL = 0;
        static double tournament3PTM = 0;

        static double tournamentBLK = 0;
        static double tournamentDREB = 0;
        
        private static final double COEFF_FGM = 85.910;
        private static final double COEFF_STL = 53.897;
        private static final double COEFF_3PM = 51.757;
        private static final double COEFF_FTM = 46.845;
        private static final double COEFF_BLK = 39.190;
        private static final double COEFF_OREB = 39.190;
        private static final double COEFF_AST = 34.677;
        private static final double COEFF_DREB = 14.707;
        private static final double COEFF_PF = -17.174;
        private static final double COEFF_FT_MISSED = -20.091;
        private static final double COEFF_FG_MISSED = -39.190;
        private static final double COEFF_TO = -53.897;
        
        static List <String> playerNames = new ArrayList<>();
        static Map<String, Map<Integer, Map<String,Number>>> allPlayerStats = new LinkedHashMap<>();
        

	    
 public static void addPlayerNames(Map<String, List<Map<String, List<Map<String, String>>>>> mainMap) {
	 
     String playerName = "";
     
     for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : mainMap.entrySet()) {
         List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();

         // Iterate over each sheet's data for the current team
         for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
         	
             for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {     
                     playerName = sheetEntry.getKey();  
                     playerNames.add(playerName);

             }
             
         }

     	HighestStatTracker.initializePlayerTournamentHighs(playerNames);
         
     }
     Collections.sort(playerNames);

 }
    

    public static void addAllPlayerStats(Map<String, List<Map<String, List<Map<String, String>>>>> mainMap) {
        // Iterate over each team's data
    	
        int totalPTS = 0, totalREB = 0, totalAST = 0, totalBLK = 0, totalSTL = 0, totalTO = 0,
                totalFGM = 0, totalFGA = 0, total3PTM = 0, total3PTA = 0, totalFTM = 0, totalFTA = 0,
                totalOREB = 0, totalFLS = 0, totalPlusMinus = 0, totalPOG = 0, totalMIN = 0, totalPRF = 0, totalDNK = 0;
            double totalFPTS = 0;

            int totalGames = 0;
            int totalTeamMIN = 0;


            
            String playerName = "";
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();

            // Iterate over each sheet's data for the current team
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
                Map<Integer, Map<String, Number>> playerStats = new LinkedHashMap<>();
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {
                	
                        List<Map<String, String>> sheetRows = sheetEntry.getValue();

                        
                        // Print player's stats
                        playerName = sheetEntry.getKey();  
                        playerTeams.put(playerName, teamEntry.getKey());

                        // Iterate over each row of stats
                        for (Map<String, String> dataMap : sheetRows) {
                            
                            totalGames++;
                        	Map<String, Number> gameStats = new LinkedHashMap<>();
                        	gameStats.put("MIN", Integer.parseInt(dataMap.get("MIN")));
                        	// Accumulate totals from dataMap and store in gameStats
                        	totalPTS += Integer.parseInt(dataMap.get("PTS"));
                        	gameStats.put("PTS", Integer.parseInt(dataMap.get("PTS")));
                        	totalREB += Integer.parseInt(dataMap.get("REB"));
                        	gameStats.put("REB", Integer.parseInt(dataMap.get("REB")));
                        	totalAST += Integer.parseInt(dataMap.get("AST"));
                        	gameStats.put("AST", Integer.parseInt(dataMap.get("AST")));
                        	totalBLK += Integer.parseInt(dataMap.get("BLK"));
                        	gameStats.put("BLK", Integer.parseInt(dataMap.get("BLK")));
                        	totalSTL += Integer.parseInt(dataMap.get("STL"));
                        	gameStats.put("STL", Integer.parseInt(dataMap.get("STL")));
                        	totalTO += Integer.parseInt(dataMap.get("TO"));
                        	gameStats.put("TO", Integer.parseInt(dataMap.get("TO")));
                        	totalFGM += Integer.parseInt(dataMap.get("FGM"));
                        	gameStats.put("FGM", Integer.parseInt(dataMap.get("FGM")));
                        	totalFGA += Integer.parseInt(dataMap.get("FGA"));
                        	gameStats.put("FGA", Integer.parseInt(dataMap.get("FGA")));
                        	total3PTM += Integer.parseInt(dataMap.get("3PTM"));
                        	gameStats.put("3PTM", Integer.parseInt(dataMap.get("3PTM")));
                        	total3PTA += Integer.parseInt(dataMap.get("3PTA"));
                        	gameStats.put("3PTA", Integer.parseInt(dataMap.get("3PTA")));
                        	totalFTM += Integer.parseInt(dataMap.get("FTM"));
                        	gameStats.put("FTM", Integer.parseInt(dataMap.get("FTM")));
                        	totalFTA += Integer.parseInt(dataMap.get("FTA"));
                        	gameStats.put("FTA", Integer.parseInt(dataMap.get("FTA")));
                        	totalOREB += Integer.parseInt(dataMap.get("OR"));
                        	gameStats.put("OREB", Integer.parseInt(dataMap.get("OR")));
                        	gameStats.put("DREB", Integer.parseInt(dataMap.get("REB")) - Integer.parseInt(dataMap.get("OR")));
                        	totalFLS += Integer.parseInt(dataMap.get("FLS"));
                        	gameStats.put("FLS", Integer.parseInt(dataMap.get("FLS")));
                        	totalPlusMinus += Integer.parseInt(dataMap.get("+/-"));
                        	gameStats.put("+/-", Integer.parseInt(dataMap.get("+/-")));
                        	totalMIN += Integer.parseInt(dataMap.get("MIN"));

                        	totalPRF += Integer.parseInt(dataMap.get("PRF"));
                        	gameStats.put("PRF", Integer.parseInt(dataMap.get("PRF")));
                        	totalDNK += Integer.parseInt(dataMap.get("DNK"));
                        	gameStats.put("DNK", Integer.parseInt(dataMap.get("DNK")));
                        	totalFPTS += Double.parseDouble(dataMap.get("FPTS"));
                        	gameStats.put("FPTS", Double.parseDouble(dataMap.get("FPTS")));// Assuming FPTS should be integer for the map
                        	gameStats.put("GS", Double.parseDouble(dataMap.get("GS")));
                        	totalPOG += Integer.parseInt(dataMap.get("POG"));
                        	gameStats.put("POG", Integer.parseInt(dataMap.get("POG")));
                        	
                        	
                        	
                        	                        	
                        	
                            
                            totalPlayerMIN+= Integer.parseInt(dataMap.get("MIN"));
                            totalPlayerFGA += Integer.parseInt(dataMap.get("FGA"));
                            totalPlayer3PTA += Integer.parseInt(dataMap.get("3PTA"));
                            totalPlayerFTA += Integer.parseInt(dataMap.get("FTA"));
    
                            HighestStatTracker.updateHighestPlayerStats(sheetEntry.getKey(), dataMap);
                            HighestStatTracker.updatePlayerTournamentHighs(sheetEntry.getKey(), dataMap);
                            updateDoubleTripleDoubles(playerName, dataMap);
                            playerStats.put(totalGames, gameStats);

                    }
                }
                totalPlayers++;
                totalPlayerPTSMap.put(playerName, totalPTS);
                totalPlayerREBMap.put(playerName, totalREB);
                totalPlayerASTMap.put(playerName, totalAST);
                totalPlayerBLKMap.put(playerName, totalBLK);
                totalPlayerSTLMap.put(playerName, totalSTL);
                totalPlayerTOMap.put(playerName, totalTO);

                	totalPlayerFGMMap.put(playerName, totalFGM);
                	totalPlayerFGAMap.put(playerName, totalFGA);
                	if (totalFGA > 0) {
                		totalPlayerFGPercentageMap.put(playerName, (double)totalFGM/totalFGA);
                	}
                	
                	

                	totalPlayer3PTMMap.put(playerName, total3PTM);
                	totalPlayer3PTAMap.put(playerName, total3PTA);
                	if (total3PTA > 0) {
                		totalPlayer3PTPercentageMap.put(playerName, (double)total3PTM/total3PTA);
                	}
                	 


                	totalPlayerFTMMap.put(playerName, totalFTM);
                	totalPlayerFTAMap.put(playerName, totalFTA);
                	if (totalFTA > 0) {
                		totalPlayerFTPercentageMap.put(playerName, (double)totalFTM/totalFTA);	
                	}
                	

                
                totalPlayerOREBMap.put(playerName, totalOREB);
                totalPlayerFLSMap.put(playerName, totalFLS);
                totalPlayerPlusMinusMap.put(playerName, totalPlusMinus);
                if (totalPOG > 0) {
                    totalPlayerPOGMap.put(playerName, totalPOG);     
                }
         
                totalPlayerMINMap.put(playerName, totalMIN);
                totalPlayerPRFMap.put(playerName, totalPRF);
                totalPlayerDNKMap.put(playerName, totalDNK);
                totalPlayerGamesMap.put(playerName, totalGames);
                totalPlayerFPTSMap.put(playerName, totalFPTS);        
                totalPlayerGSMap.put(playerName, (double)(totalPTS+0.4*totalFGM-0.7*totalFGA-0.4*(totalFTA-totalFTM)+0.7*totalOREB+0.3*(totalREB-totalOREB)+totalSTL+totalAST*0.7+0.7*totalBLK-0.4*totalFLS-totalTO)/totalGames);
                totalPlayerDREBMap.put(playerName, totalREB - totalOREB);
                if (totalTO > 0) {
                	totalPlayerASTTORatioMap.put(playerName, (double)totalAST/totalTO);
                }
                
                tournamentPTS += totalPTS;
                tournamentAST += totalAST;
                tournamentFGM += totalFGM;
                tournamentFGA += totalFGA;
        	    tournamentOREB += totalOREB;
        	    tournamentTO += totalTO;
        	    tournamentFTM += totalFTM;
        	    tournamentFTA += totalFTA;
        	    tournamentFLS += totalFLS;
        	    tournamentSTL += totalSTL;
                tournament3PTM += total3PTM;

                tournamentBLK += totalBLK;
                tournamentDREB += (totalREB - totalOREB);
                
        	    totalTeamMIN += totalMIN;
        	    allPlayerStats.put(playerName, playerStats);
                
                totalPTS = 0; totalREB = 0; totalAST = 0; totalBLK = 0; totalSTL = 0; totalTO = 0;
                        totalFGM = 0; totalFGA = 0; total3PTM = 0; total3PTA = 0; totalFTM = 0; totalFTA = 0;
                        totalOREB = 0; totalFLS = 0; totalPlusMinus = 0; totalPOG = 0; totalMIN = 0; totalPRF = 0; totalDNK = 0;
                totalFPTS = 0;
                totalGames = 0;
            }
            totalTeamMINMap.put(teamEntry.getKey(), totalTeamMIN);
            totalTeamMIN = 0;  
        }
        
        Iterator<Map.Entry<String, Integer>> ddIterator = playerDoubleDoublesMap.entrySet().iterator();
        while (ddIterator.hasNext()) {
            Map.Entry<String, Integer> entry = ddIterator.next();
            if (playerDoubleDoublesMap.get(entry.getKey()) == 0) { // Condition to remove the entry
            	ddIterator.remove(); // Removes the current element without throwing an exception
            }
        }
        
        Iterator<Map.Entry<String, Integer>> tdIterator = playerTripleDoublesMap.entrySet().iterator();
        while (tdIterator.hasNext()) {
            Map.Entry<String, Integer> entry = tdIterator.next();
            if (playerTripleDoublesMap.get(entry.getKey()) == 0) { // Condition to remove the entry
            	tdIterator.remove(); // Removes the current element without throwing an exception
            }
        }

        
        
        

    	avgPlayerMIN = (double)totalPlayerMIN/totalPlayers;
    	avgPlayerFGA = (double)totalPlayerFGA/totalPlayers;
    	avgPlayer3PTA = (double)totalPlayer3PTA/totalPlayers;
    	avgPlayerFTA = (double) totalPlayerFTA/totalPlayers;
//    	
//    	removeRankingsForPercentage();	

    }
    
    public static void addOpponentMIN(Map<String, List<Map<String, List<Map<String, String>>>>> mainMap) {
        // Iterate over each team's data
    	

            int totalOpponentMIN = 0;
            int totalMIN = 0;
            int i = 0;
            
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();
            String teamName = teamEntry.getKey();
            // Iterate over each sheet's data for the current team
            for (int j = 0; j < 1; j++) {
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : dataList.get(0).entrySet()) {
                		
                        List<Map<String, String>> sheetRows = sheetEntry.getValue();

                        
                        // Iterate over each row of stats
                        for (Map<String, String> dataMap : sheetRows) {
                            // Print individual stats
                        	                            
                        	String opponentTeamAbbreviation = dataMap.get("Games");
                        	opponentTeamAbbreviation = opponentTeamAbbreviation.startsWith("@") ? opponentTeamAbbreviation.substring(1).trim() : opponentTeamAbbreviation.substring(3).trim();
                        	String getTeam = TeamRecordAnalyzer.teamNames.get(opponentTeamAbbreviation);
                        	totalMIN += findOpponentMIN(mainMap, teamName, getTeam, i);
                        	i++;
                    }
                }
                
                
                totalOpponentMIN += totalMIN;
                totalMIN = 0;
                
                i = 0;
            }
            totalOpponentMINMap.put(teamName, totalOpponentMIN);
            totalOpponentMIN = 0;
            
        }


    }
    
    public static int findOpponentMIN(Map<String, List<Map<String, List<Map<String, String>>>>> mainMap, String teamName, String opponentName, int num) {
        // Iterate over each team's data
    	

            int totalOpponentMIN = 0;
            int totalMIN = 0;
            int i = 0;

        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();

            // Iterate over each sheet's data for the current team
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
            	
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {

                        List<Map<String, String>> sheetRows = sheetEntry.getValue();

                        // Iterate over each row of stats
                        for (Map<String, String> dataMap : sheetRows) {
                            // Print individual stats
                        	String TeamAbbreviation = dataMap.get("Games");
                        	TeamAbbreviation = TeamAbbreviation.startsWith("@") ? TeamAbbreviation.substring(1).trim() : TeamAbbreviation.substring(3).trim();

                        	if (i == num && teamEntry.getKey().equals(opponentName) && TeamRecordAnalyzer.teamNames.get(TeamAbbreviation).equals(teamName)) {
                        		totalMIN += Integer.parseInt(dataMap.get("MIN"));
                        	}
                        	i++;
                        	                            
                    }
                }
                
                
                totalOpponentMIN += totalMIN;
                totalMIN = 0;
                i = 0;

            }

            
        }

        
       return totalOpponentMIN;

    }
    
    public static void updateDoubleTripleDoubles(String playerName, Map<String, String> playerGameStats) { // Assuming player name is stored with key "PlayerName"
        int[] stats = new int[]{
            Integer.parseInt(playerGameStats.getOrDefault("PTS", "0")),
            Integer.parseInt(playerGameStats.getOrDefault("REB", "0")),
            Integer.parseInt(playerGameStats.getOrDefault("AST", "0")),
            Integer.parseInt(playerGameStats.getOrDefault("STL", "0")),
            Integer.parseInt(playerGameStats.getOrDefault("BLK", "0"))
        };

        int doubleDigits = 0;
        for (int stat : stats) {
            if (stat >= 10) {
                doubleDigits++;
            }
        }

        playerDoubleDoublesMap.putIfAbsent(playerName, 0);
        playerTripleDoublesMap.putIfAbsent(playerName, 0);

        if (doubleDigits >= 3) { // Triple-double
            playerTripleDoublesMap.put(playerName, playerTripleDoublesMap.get(playerName) + 1);
        } 
        if (doubleDigits >= 2) { // Double-double
            playerDoubleDoublesMap.put(playerName, playerDoubleDoublesMap.get(playerName) + 1);
        }
    }
    
    public static void addPlayerPER() {
    	
    	
    	double totalLeagueFTMissed = tournamentFTA - tournamentFTM;
        double totalLeagueFGMissed = tournamentFGA - tournamentFGM;
        
        
        double tournamentPace = 0;
        double tournamentMIN = 0;
        double tournamentPOS = 0;
        for (String teamName : totalTeamMINMap.keySet()) {
        	tournamentPOS += (totalTeamFGAMap.get(teamName) - totalTeamOREBMap.get(teamName) 
        			+ totalTeamTOMap.get(teamName) + (0.4*totalTeamFTAMap.get(teamName)));
        	tournamentMIN += (double)totalTeamMINMap.get(teamName);
        }
        
        tournamentPace = 48.0 * (tournamentPOS / tournamentMIN);
        
        double tournamentUPER = (1.0 / totalPlayerMIN) * (
            (COEFF_FGM * tournamentFGM) +
            (COEFF_STL * tournamentSTL) +
            (COEFF_3PM * tournament3PTM) +
            (COEFF_FTM * tournamentFTM) +
            (COEFF_BLK * tournamentBLK) +
            (COEFF_OREB * tournamentOREB) +
            (COEFF_AST * tournamentAST) +
            (COEFF_DREB * tournamentDREB) +
            (COEFF_PF * tournamentFLS) +
            (COEFF_FT_MISSED * totalLeagueFTMissed) +
            (COEFF_FG_MISSED * totalLeagueFGMissed) +
            (COEFF_TO * tournamentTO)
        );
    	
    	
    	
    	
    	VOP = tournamentPTS / (tournamentFGA - tournamentOREB + tournamentTO + 0.44 * tournamentFTA);
    	Factor =  (2.0 / 3.0) - (0.5 * (tournamentAST / tournamentFGM)) / (2.0 * (tournamentFGM / tournamentFTM));
    	double DRBP = (tournamentDREB)/(tournamentDREB + tournamentOREB);

    	
        
        for (String name : totalPlayerPTSMap.keySet()) {
        	
        	double playerMIN = (double)totalPlayerMINMap.get(name);
        	double player3PTM = (double)totalPlayer3PTMMap.get(name);
        	double playerAST = (double)totalPlayerASTMap.get(name);
        	
        	double teamAST = (double)totalTeamASTMap.get(playerTeams.get(name));
        	double teamFGM = (double)totalTeamFGMMap.get(playerTeams.get(name));
        	
        	double playerFGM = (double)totalPlayerFGMMap.get(name);
        	double playerFGA = (double)totalPlayerFGAMap.get(name);
        	double playerFTM = (double)totalPlayerFTMMap.get(name);
        	double playerFTA = (double)totalPlayerFTAMap.get(name);
        	
        	
        	
        	double playerTO = (double)totalPlayerTOMap.get(name);
        	
        	
        	double playerREB = (double)totalPlayerREBMap.get(name);
        	double playerOREB = (double)totalPlayerOREBMap.get(name);
        	double playerSTL = (double)totalPlayerSTLMap.get(name);
        	double playerBLK = (double)totalPlayerBLKMap.get(name);
        	double playerFLS = (double)totalPlayerFLSMap.get(name);

        	
        	
        	double part1 = (2.0/3.0) * playerAST;
        	double part2 =(2.0 - (Factor * (teamAST / teamFGM))) * playerFGM;
        	double part3 = (playerFTM *0.5 * (1.0 + (1.0 - (teamAST / teamFGM)) + (2.0/3.0) * (teamAST / teamFGM)));
        	double part4 = VOP * playerTO;
        	double part5 = VOP * DRBP * (playerFGA - playerFGM);
        	double part6 = VOP * 0.44 * (0.44 + (0.56 * DRBP)) * (playerFTA - playerFTM);
        	double part7 = VOP * (1.0 - DRBP) * (playerREB - playerOREB);
        	double part8 = VOP * DRBP * playerOREB;
        	double part9 = VOP * playerSTL;
        	double part10 = VOP * DRBP * playerBLK;
        	double part11 = playerFLS * ((tournamentFTM / tournamentFLS) - (0.44 * (tournamentFTA / tournamentFLS) * VOP));
    
        	
        		double uPER = 	
        					(player3PTM	
        					+ part1
                            + part2
                            + part3
                            - part4
                            - part5
                            - part6
                            + part7
                            + part8
                            + part9
                            + part10
                            - part11)/playerMIN; 
        double teamPOS = (totalTeamFGAMap.get(playerTeams.get(name)) - totalTeamOREBMap.get(playerTeams.get(name)) 
    			+ totalTeamTOMap.get(playerTeams.get(name)) + (0.4*totalTeamFTAMap.get(playerTeams.get(name))));
        double teamPace = teamPOS/(totalTeamMINMap.get(playerTeams.get(name))) * 48;
        double aPER = uPER * (tournamentPace/teamPace);
        double PER =   aPER* (tournamentUPER/15) * (totalPlayerMINMap.get(name)/totalPlayerGamesMap.get(name));
        
        totalPlayerPERMap.put(name, (PER > 0)  ? PER : 0);	
        	
        }
        
        
    	
    }
    
    
    public static void addPlayerScore() {
        
        for (String name : totalPlayerPTSMap.keySet()) {
        	// Initialize the score
        	double score = 0;


        	score += totalPlayerPTSMap.getOrDefault(name, 0); // Points remain a fundamental measure
        	score += (totalPlayerREBMap.getOrDefault(name, 0)*1.2 + totalPlayerASTMap.getOrDefault(name, 0)*1.5 + totalPlayerSTLMap.getOrDefault(name, 0)*3 
        	+totalPlayerBLKMap.getOrDefault(name, 0)* 3) * weightKeyStats; // Emphasize rebounds

        	// Adjust for efficiency and weighted negative impacts
        	// Subtract attempts to encourage efficiency but use a lesser negative impact since attempts also indicate involvement
        	score -= (totalPlayerTOMap.getOrDefault(name, 0) + totalPlayerFLSMap.getOrDefault(name, 0)) * weightNegative; // Turnovers

        	// Consider efficiency and turnovers with balanced approach
        	score -= (totalPlayerFGPercentageMap.getOrDefault(name, 0.0) + 
        			totalPlayer3PTPercentageMap.getOrDefault(name, 0.0) + 
        			totalPlayerFTPercentageMap.getOrDefault(name, 0.0)*0.44) * weightEfficiency; // Efficiency is still important but balanced  


        	// Include advanced stats with their weights
        	// Include POG with increased weight
        	score += totalPlayerPOGMap.getOrDefault(name, 0) * weightPOG;         	
        	score += totalPlayerPRFMap.getOrDefault(name, 0) * weightPRF; // Points Responsible For
        	score += totalPlayerGSMap.getOrDefault(name, 0.0) * weightGS; // Game Score

        	// Adjustments for more comprehensive consideration (optional based on data availability)
        	// These might include offensive rebounds, plus/minus, and others that you find relevant
        	score += totalPlayerOREBMap.getOrDefault(name, 0) * 1.2;
        	score += totalPlayerPlusMinusMap.getOrDefault(name, 0)*0.7;
        	score /= (double)totalPlayerGamesMap.get(name);
            // Update the score map
            playerScoreMap.put(name, score);
        }
    }
    

    
    
    public static void addStatPercentages() {
    	
    	
    	for (String name : totalPlayerOREBMap.keySet()) {
    		double OREBPercentage = (double)(totalPlayerOREBMap.get(name) * (totalTeamMINMap.get(playerTeams.get(name))/5.0)) / 
    				(totalPlayerMINMap.get(name) * (totalTeamOREBMap.get(playerTeams.get(name)) + totalOpponentDREBMap.get(playerTeams.get(name))));
    		
    		
    		double DREBPercentage = (double)(totalPlayerDREBMap.get(name) * (totalTeamMINMap.get(playerTeams.get(name))/5.0)) / 
    				(totalPlayerMINMap.get(name) * (totalTeamDREBMap.get(playerTeams.get(name)) + totalOpponentOREBMap.get(playerTeams.get(name))));
    		double TREBPercentage = (double)(totalPlayerREBMap.get(name) * (totalTeamMINMap.get(playerTeams.get(name))/5.0)) / 
    				(totalPlayerMINMap.get(name) * (totalTeamTREBMap.get(playerTeams.get(name)) + totalOpponentTREBMap.get(playerTeams.get(name))));
    		
    		double ASTPercentage = (double) totalPlayerASTMap.get(name) / 
    				((totalPlayerMINMap.get(name)/(totalTeamMINMap.get(playerTeams.get(name))/5.0)) * totalTeamFGMMap.get(playerTeams.get(name)) - totalPlayerFGMMap.get(name));
    		
    		double usageRate = (double)((totalPlayerFGAMap.get(name) + 0.44 * totalPlayerFTAMap.get(name) + totalPlayerTOMap.get(name)) *
    				(totalTeamMINMap.get(playerTeams.get(name))/5.0)) / (totalPlayerMINMap.get(name) * 
    						(totalTeamFGAMap.get(playerTeams.get(name)) + 0.44 * totalTeamFTAMap.get(playerTeams.get(name)) + totalTeamTOMap.get(playerTeams.get(name))));
    		double TSA = (double) totalPlayerFGAMap.get(name) + 0.44*totalPlayerFTAMap.get(name);
    		
    		double TSPercentage = (double) totalPlayerPTSMap.get(name)/(2*TSA);

    			totalPlayerOREBPercentageMap.put(name,OREBPercentage);
        		totalPlayerDREBPercentageMap.put(name,DREBPercentage);
        		totalPlayerTREBPercentageMap.put(name, TREBPercentage);

    		
    		totalPlayerASTPercentageMap.put(name, ASTPercentage);
    		totalPlayerUsageRateMap.put(name, usageRate);
        	totalPlayerTSPercentageMap.put(name, TSPercentage);
    	}
    	
    	
    }
    
    public static void findRoster(String teamName, Scanner scan) {
    	int i = 0;
    	double teamGradeNum = 0.0;
    	String teamGrade = "";
    	Map<Integer, String> choosePlayer = new HashMap<>();
		for (String player : playerTeams.keySet()) {
    		if (playerTeams.get(player).equals(teamName)) {
    			i++;
    			 choosePlayer.put(i,player);
    	            double playerScore = playerScoreMap.get(player);
    	            teamGradeNum += playerScore;
    		}
    	}
    	
		teamGradeNum/=i;
		if (teamGradeNum <= 20.0) {
			teamGrade = "F";
		}
		else if (teamGradeNum > 20.0 && teamGradeNum <= 22.5) {
			teamGrade = "E";
		}
		else if (teamGradeNum > 22.5 && teamGradeNum <= 25.0) {
			teamGrade = "D";
		}
		else if (teamGradeNum > 25.0 && teamGradeNum <= 30.0) {
			teamGrade = "C";
		}
		else if (teamGradeNum > 30.0 && teamGradeNum <= 35.0) {
			teamGrade = "B";
		}
		else if (teamGradeNum > 35.0) {
			teamGrade = "A";
		}
		boolean loop = false;
		while (!loop) {


		System.out.println("Team Grade: " + teamGrade);

    	System.out.println("");
    	
			System.out.println("Please Choose which player you are looking for(press L to leave): ");
			for (int integer : choosePlayer.keySet()) {
				String playerGrade = ""; 
                double playerScore = playerScoreMap.get(choosePlayer.get(integer));
                if (playerScore <= 2.50) {
                	playerGrade = "F";
                }
                else if (playerScore > 2.50 && playerScore <= 5.00) {
                	playerGrade = "E-";
                }
                else if (playerScore > 5.00 && playerScore <= 7.50) {
                	playerGrade = "E";
                }
                else if (playerScore > 7.50 && playerScore <= 10.00) {
                	playerGrade = "E+";            	
                }
                else if (playerScore > 10.00 && playerScore <= 12.50) {
                	playerGrade = "D-";
                }
                else if (playerScore > 12.50 && playerScore <= 15.00) {
                	playerGrade = "D";
                }
                else if (playerScore > 15.00 && playerScore <= 17.50) {
                	playerGrade = "D+";
                }
                else if (playerScore > 17.50 && playerScore <= 20.00) {
                	playerGrade = "C-";
                }
                else if (playerScore > 20.00 && playerScore <= 25.00) {
                	playerGrade = "C";
                }
                else if (playerScore > 25.00 && playerScore <= 30.00) {
                	playerGrade = "C+";
                }
                else if (playerScore > 30.00 && playerScore <= 40.00) {
                	playerGrade = "B-";
                }
                else if (playerScore > 40.00 && playerScore <= 50.00) {
                	playerGrade = "B";
                }
                else if (playerScore > 50.00 && playerScore <= 60.00) {
                	playerGrade = "B+";
                }
                else if (playerScore > 60.00 && playerScore <= 70.00) {
                	playerGrade = "A-";  
                }
                else if (playerScore > 70.00 && playerScore <= 90.00) {
                	playerGrade = "A";   
                }
                else if (playerScore > 90.00) {
                	playerGrade = "A+";
                }
				System.out.println("[" + integer + "]" + " " + choosePlayer.get(integer) + ", Grade: " + playerGrade);
			}
			System.out.println("[L] Leave\n");
			String choosePlayerIndex = scan.nextLine();
			
			if (App.isPositiveInteger(choosePlayerIndex)  || App.containsValueIgnoreCase(choosePlayer,choosePlayerIndex)) {
				if (App.isPositiveInteger(choosePlayerIndex) && Integer.parseInt(choosePlayerIndex) > 0 && Integer.parseInt(choosePlayerIndex) <= choosePlayer.size()) {
					String choosePlayerName = choosePlayer.get(Integer.parseInt(choosePlayerIndex));
					StatsPrinter.printPlayerStats(choosePlayerName, App.playerMap, 0, 0, true, false);
				}

				else if (App.containsValueIgnoreCase(choosePlayer,choosePlayerIndex)) {
					String choosePlayerName = App.findValueIgnoreCase(choosePlayer, choosePlayerIndex);
					StatsPrinter.printPlayerStats(choosePlayerName, App.playerMap, 0, 0, true, false);
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
    
    
    public static void addPlayerOffAndDefRatings() {
        
        for (String name : totalPlayerPTSMap.keySet()) {
        	
        	double PTS = totalPlayerPTSMap.get(name);
        	double AST = totalPlayerASTMap.get(name);
        	double ORB = totalPlayerOREBMap.get(name);
        	double DRB = totalPlayerDREBMap.get(name);
        	double BLK = totalPlayerBLKMap.get(name);
        	double STL = totalPlayerSTLMap.get(name);
        	double TOV = totalPlayerTOMap.get(name);
        	double FGM = totalPlayerFGMMap.get(name);
        	double FGA = totalPlayerFGAMap.get(name);
        	double TPM = totalPlayer3PTMMap.get(name);
        	double FTM = totalPlayerFTMMap.get(name); 
        	double FTA = totalPlayerFTAMap.get(name);
        	double PF = totalPlayerFLSMap.get(name);
        	double MP = totalPlayerMINMap.get(name);
        	
        	
        	String teamName = playerTeams.get(name);
        	double Team_PTS = totalTeamPTSMap.get(teamName);
        	double Team_AST = totalTeamASTMap.get(teamName);
        	double Team_ORB = totalTeamOREBMap.get(teamName);
        	double Team_DRB = totalTeamDREBMap.get(teamName);
        	double Team_BLK = totalTeamBLKMap.get(teamName);
        	double Team_STL = totalTeamSTLMap.get(teamName);
        	double Team_TOV = totalTeamTOMap.get(teamName);
        	double Team_FGM = totalTeamFGMMap.get(teamName);
        	double Team_FGA = totalTeamFGAMap.get(teamName);
        	double Team_3PM = totalTeam3PTMMap.get(teamName);
        	double Team_FTM = totalTeamFTMMap.get(teamName);
        	double Team_FTA = totalTeamFTAMap.get(teamName);
        	double Team_PF = totalTeamTFMap.get(teamName);
        	double Team_MP = totalTeamMINMap.get(teamName);
        	double Team_Possessions = totalTeamPOSMap.get(teamName);
        	
        	
        	
        	double Opponent_PTS = totalOpponentPTSMap.get(teamName);
        	double Opponent_TRB = totalOpponentTREBMap.get(teamName);
        	double Opponent_ORB = totalOpponentOREBMap.get(teamName);
        	double Opponent_TOV = totalOpponentTOMap.get(teamName);
        	double Opponent_FGM = totalOpponentFGMMap.get(teamName);
        	double Opponent_FGA = totalOpponentFGAMap.get(teamName);
        	double Opponent_FTM = totalOpponentFTMMap.get(teamName);
        	double Opponent_FTA = totalOpponentFTAMap.get(teamName);
        	double Opponent_MP = totalOpponentMINMap.get(teamName);
        	
        	
        	
        	
        	double qAST = (double)((MP / (Team_MP / 5.0)) * (1.14 * ((Team_AST - AST) / Team_FGM))) + ((((Team_AST / Team_MP) * MP * 5.0 - AST) / ((Team_FGM / Team_MP) * MP * 5.0 - FGM)) * (1.0 - (MP / (Team_MP / 5.0))));
        	double FG_Part = (double)FGM * (1.0 - 0.5 * ((PTS - FTM) / (2.0 * FGA)) * qAST);
        	double AST_Part = (double)0.5 * (((Team_PTS - Team_FTM) - (PTS - FTM)) / (2.0 * (Team_FGA - FGA))) * AST;
        	double FTDivide = (FTA > 0.0) ? (double)FTM/FTA : 0.0;
        	double FT_Part = (double)(1.0-Math.pow(1.0-(FTDivide), 2.0))*0.4*FTA;
        	double Team_Scoring_Poss = (double)Team_FGM + (1.0 - Math.pow(1.0 - (Team_FTM / Team_FTA), 2.0)) * Team_FTA * 0.4;
        	double Team_ORBPercentage = (double)Team_ORB / (Team_ORB + (Opponent_TRB - Opponent_ORB));
        	double Team_PlayPercentage = (double)Team_Scoring_Poss / (Team_FGA + Team_FTA * 0.4 + Team_TOV);
        	double Team_ORB_Weight = (double)((1.0 - Team_ORBPercentage) * Team_PlayPercentage) / ((1.0 - Team_ORBPercentage) * Team_PlayPercentage + Team_ORBPercentage * (1.0 - Team_PlayPercentage));
        	double ORB_Part = (double)ORB * Team_ORB_Weight * Team_PlayPercentage;
        	double FGxPoss = (double)(FGA - FGM) * (1.0 - 1.07 * Team_ORBPercentage);
        	double FTxPoss = (double)(Math.pow(1.0 - (FTDivide),2.0)) * 0.4 * FTA;
        	
        	double ScPoss = (double)(FG_Part + AST_Part + FT_Part) * (1.0 - (Team_ORB / Team_Scoring_Poss) * Team_ORB_Weight * Team_PlayPercentage) + ORB_Part;
        	double TotPoss = (double)ScPoss + FGxPoss + FTxPoss + TOV;
        	
        	
        	double PProd_FG_Part = (double)2.0 * (FGM + 0.5 * TPM) * (1.0 - 0.5 * ((PTS - FTM) / (2.0 * FGA)) * qAST);
        	
        	double PProd_AST_Part = (double )2.0 * ((Team_FGM - FGM + 0.5 * (Team_3PM - TPM)) / (Team_FGM - FGM)) * 0.5 * (((Team_PTS - Team_FTM) - (PTS - FTM)) / (2.0 * (Team_FGA - FGA))) * AST;
        	
        	double PProd_ORB_Part = (double)ORB * Team_ORB_Weight * Team_PlayPercentage * (Team_PTS / (Team_FGM + (1.0 - Math.pow(1 - (Team_FTM / Team_FTA),2.0)) * 0.4 * Team_FTA));
        	
        	double PProd = (double)(PProd_FG_Part + PProd_AST_Part + FTM) * (1.0 - (Team_ORB / Team_Scoring_Poss) * Team_ORB_Weight * Team_PlayPercentage) + PProd_ORB_Part;
        	
        	double ORtg = (double)100.0 * (PProd / TotPoss);
        	
        	double FloorPercentage = (double)ScPoss / TotPoss;
        	playerOffRatingMap.put(name, ORtg);
        	playerFloorPercentageMap.put(name, FloorPercentage);
        	
        	
        	double DORPercentage = (double)Opponent_ORB / (Opponent_ORB + Team_DRB);
        	double	DFGPercentage = (double)Opponent_FGM / Opponent_FGA;
        	
        	        	
        	double FMwt = (double)(DFGPercentage * (1.0 - DFGPercentage)) / (DFGPercentage * (1.0 - DORPercentage) + (1.0 - DFGPercentage) * DORPercentage);
        	
        	double Stops1 = (double)STL + BLK * FMwt * (1.0 - 1.07 * DORPercentage) + DRB * (1.0 - FMwt);
        	double Stops2 = (double)(((Opponent_FGA - Opponent_FGM - Team_BLK) / Team_MP) * FMwt * (1.0 - 1.07 * DORPercentage) + ((Opponent_TOV - Team_STL) / Team_MP)) * 
        			MP + (PF / Team_PF) * 0.4 * Opponent_FTA * Math.pow(1 - (Opponent_FTM / Opponent_FTA), 2.0);

        	double Stops = (double)Stops1 + Stops2;
        	
        	double StopPercentage = (double)(Stops * Opponent_MP) / (Team_Possessions * MP);
        	
        	
        	
        	
        	double Team_Defensive_Rating = (double)100.0 * (Opponent_PTS / Team_Possessions);
        	double D_Pts_per_ScPoss = (double)Opponent_PTS / (Opponent_FGM + (1.0 - Math.pow(1.0 - (Opponent_FTM / Opponent_FTA), 2.0)) * Opponent_FTA*0.4);
        	double DRtg = (double)Team_Defensive_Rating + 0.2 * (100.0 * D_Pts_per_ScPoss * (1.0 - StopPercentage) - Team_Defensive_Rating);
        	
        	playerDefRatingMap.put(name, DRtg);	
        	double PRF = totalPlayerPRFMap.get(name);
        	double PTSScoredPercentage = (double) PTS/Team_PTS;
        	double PTSAssistedPercentage = (double) (PRF-PTS)/Team_PTS;
        	double prfPercentage = (double)PRF/Team_PTS;
        	playerPTSScoredPercentageMap.put(name, PTSScoredPercentage);
        	playerPTSAssistedPercentageMap.put(name,PTSAssistedPercentage);
        	playerPRFPercentageMap.put(name, prfPercentage);
        		
        }
    }
    
    
    public static void addAllTeamStats(Map<String, List<Map<String, List<Map<String, String>>>>> mainMap, boolean isTeam) {
        // Variable initialization for team statistics
    	 int totalPTS = 0, totalAST = 0, totalBLK = 0, totalSTL = 0, totalTO = 0,
    	            totalFGM = 0, totalFGA = 0, total3PTM = 0, total3PTA = 0, totalFTM = 0, totalFTA = 0,
    	            totalOREB = 0, totalDREB = 0, totalTREB = 0, totalTF = 0, totalFBPTS = 0, totalPTSiP = 0, totalPTSOff = 0, totalSCPTS = 0,
    	        totalBPTS = 0, totalDNK = 0;
    	 int totalTOP = 0;
        double totalPOS = 0;
        String teamName = "";
        int totalGames = 0;

        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();
            teamName = teamEntry.getKey(); // Team name
            
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {
                    List<Map<String, String>> sheetRows = sheetEntry.getValue();

                    // Iterate over each row of stats for the team
                    for (Map<String, String> dataMap : sheetRows) {
                    	totalPTS += Integer.parseInt(dataMap.get("PTS"));
                        totalFGM += Integer.parseInt(dataMap.get("FGM"));
                        totalFGA += Integer.parseInt(dataMap.get("FGA"));
                        total3PTM += Integer.parseInt(dataMap.get("3PTM"));
                        total3PTA += Integer.parseInt(dataMap.get("3PTA"));
                        totalFTM += Integer.parseInt(dataMap.get("FTM"));
                        totalFTA += Integer.parseInt(dataMap.get("FTA"));
                        totalDNK += Integer.parseInt(dataMap.get("DNK"));
                        totalFBPTS += Integer.parseInt(dataMap.get("FBPTS"));
                        totalPTSiP += Integer.parseInt(dataMap.get("PTSiP"));
                        totalSCPTS += Integer.parseInt(dataMap.get("SCPTS"));
                        totalBPTS += Integer.parseInt(dataMap.get("BPTS"));
                        totalAST += Integer.parseInt(dataMap.get("AST"));
                        totalOREB += Integer.parseInt(dataMap.get("OREB"));
                        totalDREB += Integer.parseInt(dataMap.get("DREB"));
                        totalTREB += Integer.parseInt(dataMap.get("TREB"));
                        totalSTL += Integer.parseInt(dataMap.get("STL"));
                        totalBLK += Integer.parseInt(dataMap.get("BLK"));
                        totalTO += Integer.parseInt(dataMap.get("TO"));
                        totalPTSOff +=  Integer.parseInt(dataMap.get("PTS Off"));
                        totalTF += Integer.parseInt(dataMap.get("TF"));
                        totalPOS += Double.parseDouble(dataMap.get("POS"));
                        String[] parts = dataMap.get("TOP").split(":");
                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);
                        totalTOP += hours * 60 + minutes;
                        totalGames++;
                        HighestStatTracker.updateHighestTeamStats(teamName, dataMap, isTeam); 
                    }
                }
            }
            

         // Update team stats maps
            
            
         if (isTeam) {
             totalTeamPTSMap.put(teamName, totalPTS);
             totalTeamFGMMap.put(teamName, totalFGM);
             totalTeamFGAMap.put(teamName, totalFGA);
             totalTeamFGPercentageMap.put(teamName, (double)totalFGM / totalFGA);

             totalTeam3PTMMap.put(teamName, total3PTM);
             totalTeam3PTAMap.put(teamName, total3PTA);
             totalTeam3PTPercentageMap.put(teamName, (double)total3PTM / total3PTA);

             totalTeamFTMMap.put(teamName, totalFTM);
             totalTeamFTAMap.put(teamName, totalFTA);
             totalTeamFTPercentageMap.put(teamName, (double)totalFTM / totalFTA);

             // Assuming DNK is some statistic you have for teams as well
             totalTeamDNKMap.put(teamName, totalDNK);

             // If FBPTS, PTSPiP, and BPTS represent specific team statistics like fast break points, points in paint, and bench points, for example:
             totalTeamFBPTSMap.put(teamName, totalFBPTS);
             totalTeamPTSiPMap.put(teamName, totalPTSiP);
             totalTeamSCPTSMap.put(teamName, totalSCPTS);
             totalTeamBPTSMap.put(teamName, totalBPTS);

             // Update other team stats maps similarly
             totalTeamASTMap.put(teamName, totalAST);
             totalTeamOREBMap.put(teamName, totalOREB);
             totalTeamDREBMap.put(teamName, totalDREB);
             totalTeamTREBMap.put(teamName, totalTREB);
             totalTeamSTLMap.put(teamName, totalSTL);
             totalTeamBLKMap.put(teamName, totalBLK);
             totalTeamTOMap.put(teamName, totalTO);
             totalTeamPTSOffMap.put(teamName, totalPTSOff);
             totalTeamTFMap.put(teamName, totalTF);
             totalTeamTOPMap.put(teamName, totalTOP); // Assuming TOP represents some team statistic
             totalTeamPOSMap.put(teamName, totalPOS); // Assuming POS represents possessions or another relevant statistic
             totalTeamGamesMap.put(teamName, totalGames);
             totalTeamPPPMap.put(teamName, totalPTS/totalPOS);
             totalTeamOERMap.put(teamName, (totalPTS/totalPOS) * 100);
         }
         else {
        	 totalOpponentPTSMap.put(teamName, totalPTS);
        	 totalOpponentFGMMap.put(teamName, totalFGM);
        	 totalOpponentFGAMap.put(teamName, totalFGA);
        	 totalOpponentFGPercentageMap.put(teamName, (double)totalFGM / totalFGA);

        	 totalOpponent3PTMMap.put(teamName, total3PTM);
        	 totalOpponent3PTAMap.put(teamName, total3PTA);
        	 totalOpponent3PTPercentageMap.put(teamName, (double)total3PTM / total3PTA);

        	 totalOpponentFTMMap.put(teamName, totalFTM);
        	 totalOpponentFTAMap.put(teamName, totalFTA);
        	 totalOpponentFTPercentageMap.put(teamName, (double)totalFTM / totalFTA);

        	 // Assuming DNK is some statistic you have for opponents as well
        	 totalOpponentDNKMap.put(teamName, totalDNK);

        	 // If FBPTS, PTSPiP, and BPTS represent specific opponent statistics like fast break points, points in paint, and bench points, for example:
        	 totalOpponentFBPTSMap.put(teamName, totalFBPTS);
        	 totalOpponentPTSiPMap.put(teamName, totalPTSiP);
        	 totalOpponentSCPTSMap.put(teamName, totalSCPTS);
        	 totalOpponentBPTSMap.put(teamName, totalBPTS);

        	 // Update other opponent stats maps similarly
        	 totalOpponentASTMap.put(teamName, totalAST);
        	 totalOpponentOREBMap.put(teamName, totalOREB);
        	 totalOpponentDREBMap.put(teamName, totalDREB);
        	 totalOpponentTREBMap.put(teamName, totalTREB);
        	 totalOpponentSTLMap.put(teamName, totalSTL);
        	 totalOpponentBLKMap.put(teamName, totalBLK);
        	 totalOpponentTOMap.put(teamName, totalTO);
        	 totalOpponentPTSOffMap.put(teamName, totalPTSOff);
        	 totalOpponentTFMap.put(teamName, totalTF);
        	 totalOpponentTOPMap.put(teamName, totalTOP); // Assuming TOP represents some opponent statistic
        	 totalOpponentPOSMap.put(teamName, totalPOS); // Assuming POS represents possessions or another relevant statistic
        	 totalOpponentGamesMap.put(teamName, totalGames);
        	 totalOpponentPPPMap.put(teamName, totalPTS/totalPOS);
        	 totalTeamDERMap.put(teamName, (totalPTS/totalPOS) * 100);

         }


            // Update team statistics maps
           
            // Reset totals for next team
            totalPTS = 0; totalFGM = 0; totalFGA = 0; total3PTM = 0; total3PTA = 0; totalFTM = 0; totalFTA = 0; totalDNK = 0;
            totalFBPTS = 0; totalPTSiP = 0; totalSCPTS = 0; totalBPTS = 0; totalAST = 0; totalOREB = 0; totalDREB = 0; totalTREB = 0; totalSTL = 0; totalBLK = 0; totalTO = 0; totalTF = 0; totalPTSOff = 0; totalTOP = 0; totalPOS = 0;
            totalGames = 0;
        }

        


    }
    
    
    
    public static void addAllGames(Map<String, List<Map<String, List<Map<String, String>>>>> teamMap,Map<String, List<Map<String, List<Map<String, String>>>>> opponentMap) {
    	String teamName = "";
    	
    	int i = 0;
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : teamMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = teamEntry.getValue();
            teamName = teamEntry.getKey(); // Team name
        	Map<String, List<List<Integer>>> newMap = new HashMap<>();
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {

                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {
                    List<Map<String, String>> sheetRows = sheetEntry.getValue();
                    // Iterate over each row of stats for the team
                    for (Map<String, String> dataMap : sheetRows) {
                    	List<List<Integer>> h2hList = new ArrayList<>();
                    	List<Integer> newList = new ArrayList<>();
                		if (newMap.containsKey(dataMap.get("Games"))) {
                			h2hList = new ArrayList<>(newMap.get(dataMap.get("Games")));
                		}

                    	int teamScore = Integer.parseInt(dataMap.get("PTS"));
                    	int opponentScore = Integer.parseInt(opponentMap.get(teamName).get(0).get("Opponent Stats").get(i).get("PTS"));
                    	newList.add(teamScore);
                    	newList.add(opponentScore);
//                    	if (!games.isEmpty() && games.get(teamName).containsKey(dataMap.get("Games"))) {
//                    		games.get(teamName).get(dataMap.get("Games")).add(newList);
//                    	}
//                    	else {

//                    	}
                        		h2hList.add(newList);
                        		newMap.put(dataMap.get("Games"), h2hList);

                    		
                    	
                    	
                    	i++;
                    }
                    
                }
            }
            games.put(teamName, newMap);
            i = 0;

         }
        
        HighestStatTracker.updateMOV(games);
        

  	
    }
    
    public static void addTournamentAwardsScore(List<Map<String, Object>> standings) {
    	
    	int limit = standings.size()*5;
    	int player = 1;

    	
    	Map<String, Integer> newMINMap = new HashMap<>(totalPlayerMINMap);
    	
    	newMINMap = sortByBigValue(newMINMap);
    	
    	for (String playerName : newMINMap.keySet()) {
    		double points = totalPlayerPTSMap.getOrDefault(playerName, 0);
    		double assists = totalPlayerASTMap.getOrDefault(playerName, 0);
    		double offensiveRebounds = totalPlayerOREBMap.getOrDefault(playerName, 0);
    		double pointsResponsibleFor = totalPlayerPRFMap.getOrDefault(playerName, 0);
    		double fgm = totalPlayerFGMMap.getOrDefault(playerName, 0);
    		double fga = totalPlayerFGAMap.getOrDefault(playerName, 0);
    		double threem  = totalPlayer3PTMMap.getOrDefault(playerName, 0);
    		double threea = totalPlayer3PTAMap.getOrDefault(playerName, 0);
    		double ftm = totalPlayerFTMMap.get(playerName);
    		double fta = totalPlayerFTAMap.get(playerName);
    		double fgPercentage = (fga > 0)?(double)fgm/fga : 0;
    		double threePointPercentage = (threea > 0)?(double)threem/threea : 0;
    		double freeThrowPercentage = (fta > 0)?(double)ftm/fta : 0;
    		double defensiveRebounds = totalPlayerDREBMap.get(playerName);
    		double blocks = totalPlayerBLKMap.get(playerName);
    		double steals = totalPlayerSTLMap.get(playerName);
    		double fouls = totalPlayerFLSMap.get(playerName);
    		double plusMinus = totalPlayerPlusMinusMap.get(playerName);
    		double teamWins = 0;
    		String teamName = playerTeams.get(playerName);
    		for (int i = 0; i < standings.size(); i++) {
    			if (standings.get(i).get("Team").toString().equals(teamName)) {
    				teamWins = Integer.valueOf(standings.get(i).get("W").toString());
    			}	
    		}
    		double playerEfficiencyRating = totalPlayerPERMap.get(playerName);
    		double gameScore = totalPlayerGSMap.get(playerName);
    		double turnovers = totalPlayerTOMap.get(playerName);
    		double minutes = totalPlayerMINMap.get(playerName);
    		double teamsOffensiveRating = totalTeamOERMap.get(teamName);
    		double teamsDefensiveRating = totalTeamDERMap.get(teamName);
    		double playerOffensiveRating = playerOffRatingMap.get(playerName);
    		double playerDefensiveRating = playerDefRatingMap.get(playerName);
    		double playerScore = playerScoreMap.get(playerName);
    		double gamesPlayed = totalTeamGamesMap.get(teamName);
    		double POGs = totalPlayerPOGMap.getOrDefault(playerName,0);
    		double fieldGoalsMade = totalPlayerFGMMap.get(playerName);
    		double fieldGoalsAttempted = totalPlayerFGAMap.get(playerName);
    		double threePointMade = totalPlayer3PTMMap.get(playerName);
    		double threePointAttempted = totalPlayer3PTAMap.get(playerName);
    		double freeThrowsMade = totalPlayerFTMMap.get(playerName);
    		double freeThrowsAttempted = totalPlayerFTAMap.get(playerName);
    		
    		
    		double MVPScore = TournamentAwardsCalculator.calculateMVP(points, assists, offensiveRebounds, pointsResponsibleFor,
    	            fgPercentage, threePointPercentage,  freeThrowPercentage,
    	            defensiveRebounds, blocks, steals, fouls,
    	            plusMinus, teamWins, playerEfficiencyRating, gameScore,
    	            turnovers, teamsOffensiveRating, teamsDefensiveRating,
    	            playerOffensiveRating, playerDefensiveRating,playerScore, gamesPlayed, POGs);
    		
    		
    		double DPScore = TournamentAwardsCalculator.calculateDefensivePlayerScore(defensiveRebounds, blocks, steals, 
                     fouls,  teamsDefensiveRating,  playerDefensiveRating,gamesPlayed);
    		
    		double OPScore = TournamentAwardsCalculator.calculateOffensivePlayerScore(offensiveRebounds, points, assists, pointsResponsibleFor,
    	            fieldGoalsMade, fieldGoalsAttempted,
    	            threePointMade, threePointAttempted,
    	            freeThrowsMade,  freeThrowsAttempted,
    	            teamsOffensiveRating, playerOffensiveRating,gamesPlayed);
    		
    		playerMVPScoreMap.put(playerName, MVPScore);
    		playerDPOTScoreMap.put(playerName, DPScore);
    		playerOPOTScoreMap.put(playerName, OPScore);
    		if (player > limit) {
    			player6MOTScoreMap.put(playerName, MVPScore);
    		}
    		player++;
    	}
    	
    }
    
    
    
    
    

    

    
    public static void removeRankingsForPercentage() {
    	Map<String, Integer> newFGMap = new HashMap<>(totalPlayerFGAMap);
    	
    	
	    Iterator<Map.Entry<String, Integer>> FGIterator = newFGMap.entrySet().iterator();
	    while (FGIterator.hasNext()) {
	        Map.Entry<String, Integer> entry = FGIterator.next();
	        String name = entry.getKey();
	        double playerFGA = entry.getValue();

	        // Check if player's FGA is less than the average FGA
	        if (playerFGA < avgPlayerFGA) {
	            // Remove player's FGA from FGM and FGA maps
	        	totalPlayerFGPercentageMap.remove(name);
	            FGIterator.remove(); // Remove the entry from totalFGAMap
	        }
	    }
	    Map<String, Integer> new3PTMap = new HashMap<>(totalPlayer3PTAMap);

	    
	    Iterator<Map.Entry<String, Integer>> PT3Iterator = new3PTMap.entrySet().iterator();
	    while (PT3Iterator.hasNext()) {
	        Map.Entry<String, Integer> entry = PT3Iterator.next();
	        String name = entry.getKey();
	        double playerFGA = entry.getValue();

	        // Check if player's FGA is less than the average FGA
	        if (playerFGA < avgPlayer3PTA) {
	            // Remove player's FGA from FGM and FGA maps
	        	totalPlayer3PTPercentageMap.remove(name);
	        	
	            PT3Iterator.remove(); // Remove the entry from totalFGAMap
	        }
	    }

	    
	    Map<String, Integer> newFTMap = new HashMap<>(totalPlayerFTAMap);
	    
	    Iterator<Map.Entry<String, Integer>> FTIterator = newFTMap.entrySet().iterator();
	    while (FTIterator.hasNext()) {
	        Map.Entry<String, Integer> entry = FTIterator.next();
	        String name = entry.getKey();
	        double playerFGA = entry.getValue();

	        // Check if player's FGA is less than the average FGA
	        if (playerFGA < avgPlayerFTA) {
	            // Remove player's FGA from FGM and FGA maps
	        	totalPlayerFTPercentageMap.remove(name);
	            FTIterator.remove(); // Remove the entry from totalFGAMap
	        }
	    }
	    
	    

    }
    
    
    
    
    
    public static <K, V extends Number> Map<K, V> sortBySmallValue(Map<K, V> unsortedMap) {
        return unsortedMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().doubleValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // In case of duplicates, keep the first
                        LinkedHashMap::new));
    }
    



    
    public static <K, V extends Number> Map<K, V> sortByBigValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                // Sorting in descending order
                return Double.compare(o2.getValue().doubleValue(), o1.getValue().doubleValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    
    
    
    
    public static void rankPlayers(Map<String, ? extends Number> statMap, int topTotal) {
        int rank = 0;
        List<Number> playerRankRecord = new ArrayList<>();
        int count = 0;
        int i = 0;

        Map<String, ? extends Number> newStatMap = new HashMap<>(statMap);

        if (statMap.equals(playerDefRatingMap)) {
        	newStatMap = sortBySmallValue(newStatMap);
        }
        else {
        	newStatMap = sortByBigValue(newStatMap);
        }
        
        
        

        int maxNameLength = 0;
        for (String playerName : newStatMap.keySet()) {
            maxNameLength = Math.max(maxNameLength, playerName.length());
        }
        
        int maxFormattedNumberLength = 0;
        

        for (Map.Entry<String, ? extends Number> playerStat : newStatMap.entrySet()) {
            Number score = playerStat.getValue();
            String formattedNumber = "";
            if (statMap.equals(totalPlayerPlusMinusMap) && score.doubleValue() >= 0) {
            	formattedNumber += "+";
            }
            formattedNumber += String.valueOf(score);
            if (statMap.equals(totalPlayerFPTSMap) || statMap.equals(totalPlayerGSMap) || statMap.equals(totalPlayerASTTORatioMap)) {
                DecimalFormat df = new DecimalFormat("0.0");

                // Format the number to two decimal places
                formattedNumber = df.format(score);
            } else if (statMap.equals(totalPlayerPERMap) || statMap.equals(playerScoreMap) || statMap.equals(playerOffRatingMap)|| statMap.equals(playerDefRatingMap)) {
                DecimalFormat df = new DecimalFormat("0.00");

                // Format the number to two decimal places
                formattedNumber = df.format(score);
            }

            if (!playerRankRecord.contains(score)) {
                rank += count + 1;
                count = 0;
                playerRankRecord.add(score);
            } 
            else {
                count++;
            }
            i++;
            maxFormattedNumberLength = Math.max(maxFormattedNumberLength, formattedNumber.length());

            double avgStat = score.doubleValue() / (double) totalPlayerGamesMap.get(playerStat.getKey());

         // Padding for player name
            String paddedName = String.format("%-30s", playerStat.getKey());

            // Padding for rank
            String paddedRank = String.format("%-5s", rank + ".");

            // Padding for formatted number
            String paddedFormattedNumber = String.format("%-7s", formattedNumber);

            System.out.println(paddedRank + " " + paddedName + " " + ((statMap.equals(playerMVPScoreMap) || 
            		statMap.equals(playerDPOTScoreMap) || statMap.equals(player6MOTScoreMap) || statMap.equals(playerOPOTScoreMap))?  "" :
                            ((statMap.equals(totalPlayerFGPercentageMap) ||
                                    statMap.equals(totalPlayer3PTPercentageMap) ||
                                    statMap.equals(totalPlayerFTPercentageMap) || 
                                    statMap.equals(totalPlayerOREBPercentageMap)|| 
                                    statMap.equals(totalPlayerDREBPercentageMap) ||
                                    statMap.equals(totalPlayerASTPercentageMap) ||
                                    statMap.equals(totalPlayerUsageRateMap) ||
                                    statMap.equals(totalPlayerTSPercentageMap)||
                                    statMap.equals(totalPlayerTREBPercentageMap) || statMap.equals(playerFloorPercentageMap) || 
                                    statMap.equals(playerPRFPercentageMap) || statMap.equals(playerPTSScoredPercentageMap) ||
                            		statMap.equals(playerPTSAssistedPercentageMap)) ? new DecimalFormat("#0.0%").format(score) : paddedFormattedNumber) +
                            "\t\t" +
                            ((statMap.equals(totalPlayerGSMap) || statMap.equals(totalPlayerPERMap) || statMap.equals(totalPlayerFGPercentageMap) ||
                                    statMap.equals(totalPlayer3PTPercentageMap) || statMap.equals(totalPlayerFTPercentageMap) || 
                                    statMap.equals(totalPlayerPOGMap) || statMap.equals(playerScoreMap) || statMap.equals(totalPlayerOREBPercentageMap)|| 
                                    statMap.equals(totalPlayerDREBPercentageMap)||
                                    statMap.equals(totalPlayerASTPercentageMap)||
                                    statMap.equals(totalPlayerUsageRateMap) ||
                                    statMap.equals(totalPlayerTSPercentageMap)||
                                    statMap.equals(totalPlayerTREBPercentageMap)|| 
                                    statMap.equals(playerOffRatingMap)|| 
                                    statMap.equals(playerDefRatingMap)|| 
                                    statMap.equals(playerFloorPercentageMap) || 
                                    statMap.equals(playerDoubleDoublesMap) ||
                                    statMap.equals(playerTripleDoublesMap) || 
                                    statMap.equals(totalPlayerASTTORatioMap) || 
                                    statMap.equals(playerPRFPercentageMap)|| 
                                    statMap.equals(playerPTSScoredPercentageMap) ||
                            		statMap.equals(playerPTSAssistedPercentageMap) || 
                            		statMap.equals(playerOPOTScoreMap)) ? "" : "AVG: "  + ((statMap.equals(totalPlayerPlusMinusMap) && score.doubleValue() >= 0) ?  "+" : "") + new DecimalFormat("0.0").format(avgStat))) + "\t" + playerTeams.get(playerStat.getKey()));         
            if (i >= topTotal) {
                break;
            }
        }
    }
    
    
    
    public static void rankTeams(Map<String, ? extends Number> statMap, boolean isTeam) {
        int rank = 0;
        List<Number> teamRankRecord = new ArrayList<>();
        int count = 0;


        Map<String, ? extends Number> newStatMap = new HashMap<>(statMap);

        if (isTeam) {
        	 newStatMap = sortByBigValue(newStatMap);
        }
        else {
        	newStatMap = sortBySmallValue(newStatMap);
        }
        
       
        

        int maxNameLength = 0;
        for (String teamName : newStatMap.keySet()) {
            maxNameLength = Math.max(maxNameLength, teamName.length());
        }
        
        int maxFormattedNumberLength = 0;
        

        for (Map.Entry<String, ? extends Number> teamStat : newStatMap.entrySet()) {
            Number score = teamStat.getValue();
            String formattedNumber = "";
            formattedNumber += String.valueOf(score);
            if (statMap.equals(totalTeamTOPMap) || statMap.equals(totalOpponentTOPMap)) {
                long averageTOP = score.longValue() / (long) totalTeamGamesMap.get(teamStat.getKey());
                long avgHours = averageTOP / 60;
                long avgMinutes = averageTOP % 60;
                formattedNumber = String.format("%02d:%02d", avgHours, avgMinutes); 
            }
            if (statMap.equals(totalTeamOERMap) || statMap.equals(totalTeamDERMap) || statMap.equals(totalTeamPOSMap) || statMap.equals(totalOpponentPOSMap)) {
                DecimalFormat df = new DecimalFormat("0.0");

                // Format the number to two decimal places
                formattedNumber = df.format(score);
            } else if (statMap.equals(totalTeamPPPMap) || statMap.equals(totalOpponentPPPMap)) {
                DecimalFormat df = new DecimalFormat("0.00");

                // Format the number to two decimal places
                formattedNumber = df.format(score);
            }

            if (!teamRankRecord.contains(score)) {
                rank += count + 1;
                count = 0;
                teamRankRecord.add(score);
            } 
            else {
                count++;
            }

            maxFormattedNumberLength = Math.max(maxFormattedNumberLength, formattedNumber.length());

            double avgStat = score.doubleValue() / (double) totalTeamGamesMap.get(teamStat.getKey());
            if (statMap.equals(totalTeamOERMap)) {
            	avgStat = totalTeamPPPMap.get(teamStat.getKey());
            }
            else if (statMap.equals(totalTeamDERMap)) {
            	avgStat = totalOpponentPPPMap.get(teamStat.getKey());
            }
            

         // Padding for player name
            String paddedName = String.format("%-30s", teamStat.getKey() + ":");

            // Padding for rank
            String paddedRank = String.format("%-5s", rank + ".");

            // Padding for formatted number
            String paddedFormattedNumber = String.format("%-7s", formattedNumber);

            System.out.println(paddedRank + " " + paddedName + " " +
                    ((statMap.equals(totalTeamFGPercentageMap) ||
                            statMap.equals(totalTeam3PTPercentageMap) ||
                            statMap.equals(totalTeamFTPercentageMap) ||
                                    statMap.equals(totalOpponentFGPercentageMap) ||
                                    statMap.equals(totalOpponent3PTPercentageMap) ||
                                    statMap.equals(totalOpponentFTPercentageMap)) ? new DecimalFormat("#0.0%").format(score) : paddedFormattedNumber) +
                    "\t\t" +
                    ((statMap.equals(totalTeamFGPercentageMap) ||
                            statMap.equals(totalTeam3PTPercentageMap) ||
                            statMap.equals(totalTeamFTPercentageMap) ||
                                    statMap.equals(totalOpponentFGPercentageMap) ||
                                    statMap.equals(totalOpponent3PTPercentageMap) ||
                                    statMap.equals(totalOpponentFTPercentageMap) || 
                                    statMap.equals(totalTeamTOPMap) || statMap.equals(totalOpponentTOPMap)) ? "" :  
                                    	(!(statMap.equals(totalTeamOERMap) || (statMap.equals(totalTeamDERMap))) ? 
                                    			"AVG: " + new DecimalFormat("0.0").format(avgStat) : "PPP: " + new DecimalFormat("0.00").format(avgStat))));         
        }
    }
    
    
    
    





    public static String findRankStat(String objectName, Map<String, ? extends Number> statMap, boolean isPlayerOrTeam) {
        int rank = 0;
        List<Number> playerRankRecord = new ArrayList<>();
        int count = 0;
 
        Map<String, ? extends Number> newStatMap = new HashMap<>(statMap);
        
        if (isPlayerOrTeam) {
        	newStatMap = sortByBigValue(newStatMap);
        }
        else {
        	newStatMap = sortBySmallValue(newStatMap);
        }
        
        for (Map.Entry<String, ? extends Number> playerStat : newStatMap.entrySet()) {
            Number score = playerStat.getValue();
            if (!playerRankRecord.contains(score)) {
                rank += count + 1;
                count = 0;
                playerRankRecord.add(score);
            } else {
                count++;
            }
            if (objectName.equalsIgnoreCase(playerStat.getKey())) {
                return rankWithOrdinal(rank);
            }
        }
        return "NaN";
    }
    
    public static int findRankStatNum(String playerName, Map<String, ? extends Number> statMap) {
        int rank = 0;
        List<Number> playerRankRecord = new ArrayList<>();
        int count = 0;
        System.out.println(statMap);
        Map<String, ? extends Number> newStatMap = new HashMap<>(statMap);
        if (statMap.equals(totalPlayerPERMap)) {
            Iterator<? extends Map.Entry<String, ? extends Number>> iterator = newStatMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ? extends Number> entry = iterator.next();
                String name = entry.getKey();
                double totalMinutes = totalPlayerMINMap.getOrDefault(name, 0);
                // Check if player's total minutes exceed the average
                if ((double) totalMinutes < avgPlayerMIN) {
                    // Remove player from totalPERMap using iterator's remove() method
                    iterator.remove();
                }
            }
            
        }
        
        
        newStatMap = sortByBigValue(newStatMap);
        for (Map.Entry<String, ? extends Number> playerStat : newStatMap.entrySet()) {
            Number score = playerStat.getValue();
            if (!playerRankRecord.contains(score)) {
                rank += count + 1;
                count = 0;
                playerRankRecord.add(score);
            } else {
                count++;
            }
            if (playerName.equalsIgnoreCase(playerStat.getKey())) {
                return rank;
            }
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    

    public static String rankWithOrdinal(int rank) {
        int lastTwoDigits = rank % 100;  // Gets the last two digits of the number
        if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
            return rank + "th";  // Correctly handles cases like 111th, 112th, 113th, etc.
        }
        switch (rank % 10) {  // Checks the last digit for the ordinal indicator
            case 1:
                return rank + "st";
            case 2:
                return rank + "nd";
            case 3:
                return rank + "rd";
            default:
                return rank + "th";
        }
    }

    


public static void printPlayerRankings(String playerName) {
	System.out.println("RANK\t" +  findRankStat(playerName, totalPlayerMINMap, true) + "\t" + findRankStat(playerName, totalPlayerPTSMap, true) + "\t" + findRankStat(playerName, totalPlayerREBMap, true) + "\t" + findRankStat(playerName, totalPlayerASTMap, true) + "\t" + findRankStat(playerName, totalPlayerBLKMap, true) + "\t" +
	        findRankStat(playerName, totalPlayerSTLMap, true) + "\t" + findRankStat(playerName, totalPlayerTOMap, true) + "\t" + findRankStat(playerName, totalPlayerFGMMap, true) + "\t" + findRankStat(playerName, totalPlayerFGAMap, true) + "\t" + findRankStat(playerName, totalPlayer3PTMMap, true) + "\t" +
	        findRankStat(playerName, totalPlayer3PTAMap, true) + "\t" +findRankStat(playerName, totalPlayerFTMMap, true) + "\t" + findRankStat(playerName, totalPlayerFTAMap, true) + "\t" + findRankStat(playerName, totalPlayerOREBMap, true) + "\t" + findRankStat(playerName, totalPlayerDREBMap, true) + "\t" + findRankStat(playerName, totalPlayerFLSMap, true) + "\t" +
	        findRankStat(playerName, totalPlayerPlusMinusMap, true) + "\t" + findRankStat(playerName, totalPlayerFGPercentageMap, true) + "\t" + findRankStat(playerName, totalPlayer3PTPercentageMap, true) + "\t" + findRankStat(playerName, totalPlayerFTPercentageMap, true) + "\t"  + findRankStat(playerName, totalPlayerPRFMap, true) + "\t" + findRankStat(playerName, totalPlayerDNKMap, true) + "\t" +
	        findRankStat(playerName, totalPlayerFPTSMap, true) + "\t" + findRankStat(playerName, totalPlayerGSMap, true) + "\t" + findRankStat(playerName, totalPlayerPOGMap, true));

}



public static void printTeamRankings(String teamName, boolean isTeam) {
	
	if (isTeam) {
		System.out.println("RANK\t" + findRankStat(teamName, totalTeamPTSMap, true) + "\t" + findRankStat(teamName, totalTeamFGMMap, true) + "\t" + findRankStat(teamName, totalTeamFGAMap, true) + "\t" + findRankStat(teamName, totalTeam3PTMMap, true) + "\t" +
		        findRankStat(teamName, totalTeam3PTAMap, true) + "\t" + findRankStat(teamName, totalTeamFTMMap, true) + "\t" + findRankStat(teamName, totalTeamFTAMap, true) + "\t" + findRankStat(teamName, totalTeamDNKMap, true) + "\t" + findRankStat(teamName, totalTeamFBPTSMap, true) + "\t" +
		        findRankStat(teamName, totalTeamPTSiPMap, true) + "\t" + findRankStat(teamName, totalTeamSCPTSMap, true) + "\t" + findRankStat(teamName, totalTeamBPTSMap, true) + "\t" + findRankStat(teamName, totalTeamASTMap, true) + "\t" + findRankStat(teamName, totalTeamOREBMap, true) + "\t" + findRankStat(teamName, totalTeamDREBMap, true) + "\t" +
		        findRankStat(teamName, totalTeamTREBMap, true) + "\t" + findRankStat(teamName, totalTeamSTLMap, true) + "\t" + findRankStat(teamName, totalTeamBLKMap, true) + "\t" + findRankStat(teamName, totalTeamTOMap, false) + "\t" + findRankStat(teamName, totalTeamPTSOffMap, true) + "\t" + findRankStat(teamName, totalTeamTFMap, false) + "\t" +
		        findRankStat(teamName, totalTeamTOPMap, false) + "\t" + findRankStat(teamName, totalTeamFGPercentageMap, true) + "\t" + findRankStat(teamName, totalTeam3PTPercentageMap, true) + "\t" + findRankStat(teamName, totalTeamFTPercentageMap, true) + "\t" +  
		        findRankStat(teamName, totalTeamPOSMap, true));
	}
	else {
		System.out.println("RANK\t" + findRankStat(teamName, totalOpponentPTSMap, false) + "\t" + findRankStat(teamName, totalOpponentFGMMap, false) + "\t" + findRankStat(teamName, totalOpponentFGAMap, false) + "\t" + findRankStat(teamName, totalOpponent3PTMMap, false) + "\t" +
		        findRankStat(teamName, totalOpponent3PTAMap, false) + "\t" + findRankStat(teamName, totalOpponentFTMMap, false) + "\t" + findRankStat(teamName, totalOpponentFTAMap, false) + "\t" + findRankStat(teamName, totalOpponentDNKMap, false) + "\t" + findRankStat(teamName, totalOpponentFBPTSMap, false) + "\t" +
		        findRankStat(teamName, totalOpponentPTSiPMap, false) + "\t" + findRankStat(teamName, totalOpponentSCPTSMap, false) + "\t" + findRankStat(teamName, totalOpponentBPTSMap, false) + "\t" + findRankStat(teamName, totalOpponentASTMap, false) + "\t" + findRankStat(teamName, totalOpponentOREBMap, false) + "\t" + findRankStat(teamName, totalOpponentDREBMap, false) + "\t" +
		        findRankStat(teamName, totalOpponentTREBMap, false) + "\t" + findRankStat(teamName, totalOpponentSTLMap, false) + "\t" + findRankStat(teamName, totalOpponentBLKMap, false) + "\t" + findRankStat(teamName, totalOpponentTOMap, true) + "\t" + findRankStat(teamName, totalOpponentPTSOffMap, false) + "\t" + findRankStat(teamName, totalOpponentTFMap, true) + "\t" +
		        findRankStat(teamName, totalOpponentTOPMap, true) + "\t" + findRankStat(teamName, totalOpponentFGPercentageMap, false) + "\t" + findRankStat(teamName, totalOpponent3PTPercentageMap, false) + "\t" + findRankStat(teamName, totalOpponentFTPercentageMap, false) + "\t" +  
		        findRankStat(teamName, totalOpponentPOSMap, false));


	}

}

public static String printPlayerRatings(String playerName, String ratingName, boolean isTeam) {
	String string = "";
	if (ratingName.equals("PER")) {
		string = findRankStat(playerName, totalPlayerPERMap, isTeam);
	}
	if (ratingName.equals("POG")) {
		string = findRankStat(playerName, totalPlayerPOGMap, isTeam);
	}
	if (ratingName.equals("TS%")) {

			string = findRankStat(playerName, totalPlayerTSPercentageMap, isTeam);
		}	
	if (ratingName.equals("AST%")) {

			string = findRankStat(playerName, totalPlayerASTPercentageMap, isTeam);
		}
	if (ratingName.equals("OREB%")) {

		string = findRankStat(playerName, totalPlayerOREBPercentageMap, isTeam);
	}
	if (ratingName.equals("DREB%")) {

		string = findRankStat(playerName, totalPlayerDREBPercentageMap, isTeam);
	}
	if (ratingName.equals("TREB%")) {

		string = findRankStat(playerName, totalPlayerTREBPercentageMap, isTeam);
	}
	if (ratingName.equals("USGRATE")) {

		string = findRankStat(playerName, totalPlayerUsageRateMap, isTeam);
	}
	if (ratingName.equals("OFFRTG")) {

		string = findRankStat(playerName, playerOffRatingMap, isTeam);
	}
	if (ratingName.equals("DEFRTG")) {

		string = findRankStat(playerName, playerDefRatingMap, isTeam);
	}
	if (ratingName.equals("DD")) {

		string = findRankStat(playerName, playerDoubleDoublesMap, isTeam);
	}
	if (ratingName.equals("TD")) {

		string = findRankStat(playerName, playerTripleDoublesMap, isTeam);
	}
	if (ratingName.equals("PLAYERSCORE")) {

		string = findRankStat(playerName, playerScoreMap, isTeam);
	}
	


	
	return string;
}





public static String printTeamEfficiencyRankings(String teamName, String efficiencyName, boolean isTeam) {
	String string = "";
	if (efficiencyName.equals("PPP")) {
		if (isTeam) {
			string = findRankStat(teamName, totalTeamPPPMap, true);
		}
		else {
			string = findRankStat(teamName, totalOpponentPPPMap, false);
		}
		
	}
	if (efficiencyName.equals("ER")) {
		if (isTeam) {
			string = findRankStat(teamName, totalTeamOERMap, true);
		}
		else {
			string = findRankStat(teamName, totalTeamDERMap, false);
		}	
	}
	
	return string;
}




public static boolean isEqualToFGMaps(Map<String, ? extends Number> otherMap) {
    return totalPlayerFGMMap.equals(otherMap) || 
    		totalPlayerFGAMap.equals(otherMap) || 
    		totalPlayer3PTMMap.equals(otherMap) ||
    		totalPlayer3PTAMap.equals(otherMap) ||
    		totalPlayerFTMMap.equals(otherMap) ||
    		totalPlayerFTAMap.equals(otherMap);
}



public static void getRankings(int statNum, int totalPlayers) {
    System.out.println("---------------------------------------------------------------------------------------------------");
    switch (statNum) {
        case 1:
            printCentered("Points Leaders");
            rankPlayers(totalPlayerPTSMap, totalPlayers);
            break;
        case 2:
            printCentered("Rebound Leaders");
            rankPlayers(totalPlayerREBMap, totalPlayers);
            break;
        case 3:
            printCentered("Assist Leaders");
            rankPlayers(totalPlayerASTMap, totalPlayers);
            break;
        case 4:
            printCentered("Block Leaders");
            rankPlayers(totalPlayerBLKMap, totalPlayers);
            break;
        case 5:
            printCentered("Steals Leaders");
            rankPlayers(totalPlayerSTLMap, totalPlayers);
            break;
        case 6:
            printCentered("Turnover Leaders");
            rankPlayers(totalPlayerTOMap, totalPlayers);
            break;
        case 7:
            printCentered("Field Goals Made Leaders");
            rankPlayers(totalPlayerFGMMap, totalPlayers);
            break;
        case 8:
            printCentered("Field Goals Attempted Leaders");
            rankPlayers(totalPlayerFGAMap, totalPlayers);
            break;
        case 9:
            printCentered("3 Pointers Made Leaders");
            rankPlayers(totalPlayer3PTMMap, totalPlayers);
            break;
        case 10:
            printCentered("3 Pointers Attempted Leaders");
            rankPlayers(totalPlayer3PTAMap, totalPlayers);
            break;
        case 11:
            printCentered("Free Throws Made Leaders");
            rankPlayers(totalPlayerFTMMap, totalPlayers);
            break;
        case 12:
            printCentered("Free Throws Attempted Leaders");
            rankPlayers(totalPlayerFTAMap, totalPlayers);
            break;
        case 13:
            printCentered("Offensive Rebound Leaders");
            rankPlayers(totalPlayerOREBMap, totalPlayers);
            break;
        case 14:
            printCentered("Defensive Rebound Leaders");
            rankPlayers(totalPlayerDREBMap, totalPlayers);
            break;
        case 15:
            printCentered("Fouls Leaders");
            rankPlayers(totalPlayerFLSMap, totalPlayers);
            break;
        case 16:
            printCentered("Plus Minus Leaders");
            rankPlayers(totalPlayerPlusMinusMap, totalPlayers);
            break;
        case 17:
            printCentered("Field Goal Percentage Leaders");
            rankPlayers(totalPlayerFGPercentageMap, totalPlayers);
            break;
        case 18:
            printCentered("3 Point Percentage Leaders");
            rankPlayers(totalPlayer3PTPercentageMap, totalPlayers);
            break;
        case 19:
            printCentered("Free Throw Percentage Leaders");
            rankPlayers(totalPlayerFTPercentageMap, totalPlayers);
            break;
        case 20:
            printCentered("Assist Percentage Leaders");
            rankPlayers(totalPlayerASTPercentageMap, totalPlayers);
            break;
        case 21:
            printCentered("Offensive Rebound Percentage Leaders");
            rankPlayers(totalPlayerOREBPercentageMap, totalPlayers);
            break;
        case 22:
            printCentered("Defensive Rebound Percentage Leaders");
            rankPlayers(totalPlayerDREBPercentageMap, totalPlayers);
            break;
        case 23:
            printCentered("Total Rebound Percentage Leaders");
            rankPlayers(totalPlayerTREBPercentageMap, totalPlayers);
            break;
        case 24:
            printCentered("Minutes Leaders");
            rankPlayers(totalPlayerMINMap, totalPlayers);
            break;
        case 25:
            printCentered("PRF Leaders");
            rankPlayers(totalPlayerPRFMap, totalPlayers);
            break;
        case 26:
            printCentered("Dunk Leaders");
            rankPlayers(totalPlayerDNKMap, totalPlayers);
            break;
        case 27:
            printCentered("PER Leaders");
            rankPlayers(totalPlayerPERMap, totalPlayers);
            break;
        case 28:
            printCentered("Fantasy Points Leaders");
            rankPlayers(totalPlayerFPTSMap, totalPlayers);
            break;
        case 29:
            printCentered("Game Score Leaders");
            rankPlayers(totalPlayerGSMap, totalPlayers);
            break;
        case 30:
            printCentered("POG Leaders");
            rankPlayers(totalPlayerPOGMap, totalPlayers);
            break;
        case 31:
            printCentered("Player Score Leaders");
            rankPlayers(playerScoreMap, totalPlayers);
            break;
        case 32:
            printCentered("Usage Rate Leaders");
            rankPlayers(totalPlayerUsageRateMap, totalPlayers);
            break;
        case 33:
            printCentered("True Shooting Percentage Leaders");
            rankPlayers(totalPlayerTSPercentageMap, totalPlayers);
            break;
        case 34:
            printCentered("Points Scored Percentage Leaders");
            rankPlayers(playerPTSScoredPercentageMap, totalPlayers);
            break;
        case 35:
            printCentered("Points Assisted Percentage Leaders");
            rankPlayers(playerPTSAssistedPercentageMap, totalPlayers);
            break;
        case 36:
            printCentered("Points Responsible For Percentage Leaders");
            rankPlayers(playerPRFPercentageMap, totalPlayers);
            break;
        case 37:
            printCentered("Offensive Rating Leaders");
            rankPlayers(playerOffRatingMap, totalPlayers);
            break;
        case 38:
            printCentered("Defensive Rating Leaders");
            rankPlayers(playerDefRatingMap, totalPlayers);
            break;
        case 39:
            printCentered("Floor Percentage Leaders");
            rankPlayers(playerFloorPercentageMap, totalPlayers);
            break;
        case 40:
            printCentered("Double Double Leaders");
            rankPlayers(playerDoubleDoublesMap, totalPlayers);
            break;
        case 41:
            printCentered("Triple Double Leaders");
            rankPlayers(playerTripleDoublesMap, totalPlayers);
            break;
        case 42:
            printCentered("Assist to Turnover Ratio Leaders");
            rankPlayers(totalPlayerASTTORatioMap, totalPlayers);
            break;
        case 43:
            printCentered("MVP Ladder");
            rankPlayers(playerMVPScoreMap, totalPlayers);
            break;
        case 44:
            printCentered("DPOT Ladder");
            rankPlayers(playerDPOTScoreMap, totalPlayers);
            break;
        case 45:
            printCentered("OPOT Ladder");
            rankPlayers(playerOPOTScoreMap, totalPlayers);
            break;    
        case 46:
            printCentered("6MOT Ladder");
            rankPlayers(player6MOTScoreMap, totalPlayers);
            break;
            
    }
    System.out.println("---------------------------------------------------------------------------------------------------");
    System.out.println("\n\n");
}

public static void getTeamRankings(int statNum, boolean isTeam) {
    
	System.out.println("---------------------------------------------------------------------------------------------------");
	switch (statNum) {
	    case 1:
	        printCentered("Points " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamPTSMap : totalOpponentPTSMap, isTeam);
	        break;
	    case 2:
	        printCentered("Field Goals " + (isTeam ? "Made " : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamFGMMap : totalOpponentFGMMap, isTeam);
	        break;
	    case 3:
	        printCentered("Field Goals " + (isTeam ? "Attempted " : "Allowed Attempts ") + "Leaders");
	        rankTeams(isTeam ? totalTeamFGAMap : totalOpponentFGAMap, isTeam);
	        break;
	    case 4:
	        printCentered("3 Pointers " + (isTeam ? "Made " : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeam3PTMMap : totalOpponent3PTMMap, isTeam);
	        break;
	    case 5:
	        printCentered("3 Pointers  " + (isTeam ? "Attempted " : "Allowed Attempts ") + "Leaders");
	        rankTeams(isTeam ? totalTeam3PTAMap : totalOpponent3PTAMap, isTeam);
	        break;
	    case 6:
	        printCentered("Free Throws " + (isTeam ? "Made " : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamFTMMap : totalOpponentFTMMap, isTeam);
	        break;
	    case 7:
	        printCentered("Free Throws " + (isTeam ? "Attempted " : "Allowed Attempts ") + "Leaders");
	        rankTeams(isTeam ? totalTeamFTAMap : totalOpponentFTAMap, isTeam);
	        break;
	    case 8:
	        printCentered("Dunk" + (isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamDNKMap : totalOpponentDNKMap, isTeam);
	        break;
	    case 9:
	        printCentered("Fast Break Points " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamFBPTSMap : totalOpponentFBPTSMap, isTeam);
	        break;
	    case 10:
	        printCentered((isTeam ? "" : "Allowed ") + "Points in the Paint "  + "Leaders");
	        rankTeams(isTeam ? totalTeamPTSiPMap : totalOpponentPTSiPMap, isTeam);
	        break;
	    case 11:
	        printCentered("Second Chance Points " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamSCPTSMap : totalOpponentSCPTSMap, isTeam);
	        break;
	    case 12:
	        printCentered("Bench Points " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamBPTSMap : totalOpponentBPTSMap, isTeam);
	        break;
	    case 13:
	        printCentered("Assist" + (isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamASTMap : totalOpponentASTMap, isTeam);
	        break;
	    case 14:
	        printCentered("Offensive Rebound" + (isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamOREBMap : totalOpponentOREBMap, isTeam);
	        break;
	    case 15:
	        printCentered("Defensive Rebound" + (isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamDREBMap : totalOpponentDREBMap, isTeam);
	        break;
	    case 16:
	        printCentered("Total Rebound" + (isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamTREBMap : totalOpponentTREBMap, isTeam);
	        break;
	    case 17:
	        printCentered("Steals " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamSTLMap : totalOpponentSTLMap, isTeam);
	        break;
	    case 18:
	        printCentered("Block" +	(isTeam ? " " : "s Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamBLKMap : totalOpponentBLKMap, isTeam);
	        break;
	    case 19:
	        printCentered((isTeam ? " " : "Opponent ") + "Turnover" + (isTeam ? " " : "s ") + "Leaders");
	        rankTeams(isTeam ? totalTeamTOMap : totalOpponentTOMap, !isTeam); // Note the inversion of isTeam for "allowed" stats
	        break;
	    case 20:
	        printCentered((isTeam ? "" : "Opponent ") + "Points Off Turnovers " + "Leaders");
	        rankTeams(isTeam ? totalTeamPTSOffMap : totalOpponentPTSOffMap, isTeam);
	        break;
	    case 21:
	        printCentered((isTeam ? "" : "Opponent ") + "Team Fouls "  + "Leaders");
	        rankTeams(isTeam ? totalTeamTFMap : totalOpponentTFMap, !isTeam); // Note the inversion of isTeam for "allowed" stats
	        break;
	    case 22:
	        printCentered((isTeam ? "" : "Opponent ") +"Time of Possession " + "Leaders");
	        rankTeams(isTeam ? totalTeamTOPMap : totalOpponentTOPMap, !isTeam); // Note the inversion of isTeam for "allowed" stats
	        break;
	    case 23:
	        printCentered((isTeam ? "" : "Opponent ") + "Field Goal Percentage "  + "Leaders");
	        rankTeams(isTeam ? totalTeamFGPercentageMap : totalOpponentFGPercentageMap, isTeam);
	        break;
	    case 24:
	        printCentered((isTeam ? "" : "Opponent ") +"3 Point Percentage " + "Leaders");
	        rankTeams(isTeam ? totalTeam3PTPercentageMap : totalOpponent3PTPercentageMap, isTeam);
	        break;
	    case 25:
	        printCentered((isTeam ? "" : "Opponent ") +"Free Throw Percentage " + "Leaders");
	        rankTeams(isTeam ? totalTeamFTPercentageMap : totalOpponentFTPercentageMap, isTeam);
	        break;
	    case 26:
	        printCentered("Possessions " + (isTeam ? "" : "Allowed ") + "Leaders");
	        rankTeams(isTeam ? totalTeamPOSMap : totalOpponentPOSMap, isTeam);
	        break;
	    case 27:
	        printCentered((isTeam ? "Offensive Efficiency Rating " : "Defensive Efficiency Rating ") + "Leaders");
	        rankTeams(isTeam ? totalTeamOERMap : totalTeamDERMap, isTeam);
	        break;       
	}


    System.out.println("---------------------------------------------------------------------------------------------------");
    System.out.println("\n\n");
}

public static void printCentered(String text) {
    int width = 90; // Adjust the width as needed
    int spaces = (width - text.length()) / 2;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < spaces; i++) {
        sb.append(" ");
    }
    sb.append(text);
    System.out.println(sb.toString() + "\n");
}


    
    
    
}
