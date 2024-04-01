package com.demo.NBATournamentProject;

import java.text.DecimalFormat;
import java.util.*;

public class StatsPrinter {
	
	static int totalTeamPoints = 0;
	static double totalPPP = 0;
	static double totalOER = 0;
	
    public static void printPlayerStats(String playerName, Map<String, List<Map<String, List<Map<String, String>>>>> mainMap) {
        boolean playerFound = false;

        // Initialize sums for each statistic
        int totalPTS = 0, totalREB = 0, totalAST = 0, totalBLK = 0, totalSTL = 0, totalTO = 0,
            totalFGM = 0, totalFGA = 0, total3PTM = 0, total3PTA = 0, totalFTM = 0, totalFTA = 0,
            totalOR = 0, totalFLS = 0, totalPlusMinus = 0, totalPOG = 0, totalMIN = 0, totalPRF = 0, totalDNK = 0;
        double totalFPTS = 0;
        int totalGames = 0;
        int i = 0;
        String teamName = ""; // Variable to store the name of the team

        // Iterate over each team's data
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> entry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = entry.getValue();

            // Iterate over each sheet's data for the current team
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {
                    String currentPlayerName = sheetEntry.getKey();
                    
                    // Check if the current player name matches the input playerName
                    if (currentPlayerName.toLowerCase().equals(playerName.toLowerCase())) {
                        playerFound = true;
                        teamName = entry.getKey(); // Get the name of the team
                        playerName = currentPlayerName;
                        List<Map<String, String>> sheetRows = sheetEntry.getValue();

                        // Print player's stats
                        System.out.print("Player: " + currentPlayerName);
                        System.out.print("\t\t\t\t\t" + teamName + "\n"); // Print the team name

                        System.out.println("Games\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOR\tFLS\t+/-\tFG%\t3PT%\tFT%\tMIN\tPRF\tDNK\tFPTS\tGS\tPOG");
                        
                        // Iterate over each row of stats
                        for (Map<String, String> dataMap : sheetRows) {
                            // Update sums
                        	
//                        	if (i >= 10) {
                        		totalPTS += Integer.parseInt(dataMap.get("PTS"));
                                totalREB += Integer.parseInt(dataMap.get("REB"));
                                totalAST += Integer.parseInt(dataMap.get("AST"));
                                totalBLK += Integer.parseInt(dataMap.get("BLK"));
                                totalSTL += Integer.parseInt(dataMap.get("STL"));
                                totalTO += Integer.parseInt(dataMap.get("TO"));
                                totalFGM += Integer.parseInt(dataMap.get("FGM"));
                                totalFGA += Integer.parseInt(dataMap.get("FGA"));
                                total3PTM += Integer.parseInt(dataMap.get("3PTM"));
                                total3PTA += Integer.parseInt(dataMap.get("3PTA"));
                                totalFTM += Integer.parseInt(dataMap.get("FTM"));
                                totalFTA += Integer.parseInt(dataMap.get("FTA"));
                                totalOR += Integer.parseInt(dataMap.get("OR"));
                                totalFLS += Integer.parseInt(dataMap.get("FLS"));
                                totalPlusMinus += Integer.parseInt(dataMap.get("+/-"));
                                totalMIN += Integer.parseInt(dataMap.get("MIN"));
                                totalPRF += Integer.parseInt(dataMap.get("PRF"));
                                totalDNK += Integer.parseInt(dataMap.get("DNK"));
                                totalFPTS += Double.parseDouble(dataMap.get("FPTS"));
                                totalPOG += Integer.parseInt(dataMap.get("POG"));

                                // Print individual stats
                                
                                
                                
                                System.out.print(dataMap.get("Games") + "\t");
                                System.out.print(dataMap.get("PTS") + "\t");
                                System.out.print(dataMap.get("REB") + "\t");
                                System.out.print(dataMap.get("AST") + "\t");
                                System.out.print(dataMap.get("BLK") + "\t");
                                System.out.print(dataMap.get("STL") + "\t");
                                System.out.print(dataMap.get("TO") + "\t");
                                System.out.print(dataMap.get("FGM") + "\t");
                                System.out.print(dataMap.get("FGA") + "\t");
                                System.out.print(dataMap.get("3PTM") + "\t");
                                System.out.print(dataMap.get("3PTA") + "\t");
                                System.out.print(dataMap.get("FTM") + "\t");
                                System.out.print(dataMap.get("FTA") + "\t");
                                System.out.print(dataMap.get("OR") + "\t");
                                System.out.print(dataMap.get("FLS") + "\t");
                                System.out.print(dataMap.get("+/-") + "\t");
                                System.out.print(dataMap.get("FG%") + "\t");
                                System.out.print(dataMap.get("3PT%") + "\t");
                                System.out.print(dataMap.get("FT%") + "\t");
                                System.out.print(dataMap.get("MIN") + "\t");
                                System.out.print(dataMap.get("PRF") + "\t");
                                System.out.print(dataMap.get("DNK") + "\t");
                                System.out.print(dataMap.get("FPTS") + "\t");
                                System.out.print(dataMap.get("GS") + "\t");
                                if (Integer.parseInt(dataMap.get("POG")) != 0){
                                	System.out.println(dataMap.get("POG"));
                                }
                                else {
                                	System.out.println("");
                                }
                                totalGames++;
//                        	}
                        	i++;
                            
                        }
                    }
                }
            }
        }
        
        
        

        
        
        
        if (playerFound) {

            // Calculate averages
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
            double avgPTS = Double.parseDouble(decimalFormat.format((double) totalPTS / totalGames));
            double avgREB = Double.parseDouble(decimalFormat.format((double) totalREB / totalGames));
            double avgAST = Double.parseDouble(decimalFormat.format((double) totalAST / totalGames));
            double avgBLK = Double.parseDouble(decimalFormat.format((double) totalBLK / totalGames));
            double avgSTL = Double.parseDouble(decimalFormat.format((double) totalSTL / totalGames));
            double avgTO = Double.parseDouble(decimalFormat.format((double) totalTO / totalGames));
            double avgFGM = Double.parseDouble(decimalFormat.format((double) totalFGM / totalGames));
            double avgFGA = Double.parseDouble(decimalFormat.format((double) totalFGA / totalGames));
            double avg3PTM = Double.parseDouble(decimalFormat.format((double) total3PTM / totalGames));
            double avg3PTA = Double.parseDouble(decimalFormat.format((double) total3PTA / totalGames));
            double avgFTM = Double.parseDouble(decimalFormat.format((double) totalFTM / totalGames));
            double avgFTA = Double.parseDouble(decimalFormat.format((double) totalFTA / totalGames));
            double avgOR = Double.parseDouble(decimalFormat.format((double) totalOR / totalGames));
            double avgFLS = Double.parseDouble(decimalFormat.format((double) totalFLS / totalGames));
            double avgPlusMinus = Double.parseDouble(decimalFormat.format((double) totalPlusMinus / totalGames));
            double avgMIN = Double.parseDouble(decimalFormat.format((double)totalMIN / totalGames));
            double avgPRF = Double.parseDouble(decimalFormat.format((double)totalPRF / totalGames));
            double avgDNK = Double.parseDouble(decimalFormat.format((double)totalDNK / totalGames));
            double avgFPTS = Double.parseDouble(decimalFormat.format((double)(totalPTS+(totalREB*1.2)+(totalAST*1.5)+(totalBLK*3)+(totalSTL*3)-totalTO)/totalGames));

            double avgFGPercentage = (double) totalFGM / totalFGA;
            double avg3PTPercentage = (double) total3PTM / total3PTA;
            double avgFTPercentage = (double) totalFTM / totalFTA;
            
            double avgGS = Double.parseDouble(decimalFormat.format((double)(totalPTS+0.4*totalFGM-0.7*totalFGA-0.4*(totalFTA-totalFTM)+0.7*totalOR+0.3*(totalREB-totalOR)+totalSTL+totalAST*0.7+0.7*totalBLK-0.4*totalFLS-totalTO)/totalGames));

            System.out.println("AVG\t" + avgPTS + "\t" + avgREB + "\t" + avgAST + "\t" + avgBLK + "\t" + avgSTL + "\t" +
                    avgTO + "\t" + avgFGM + "\t" + avgFGA + "\t" + avg3PTM + "\t" + avg3PTA + "\t" + avgFTM + "\t" +
                    avgFTA + "\t" + avgOR + "\t" + avgFLS + "\t" + avgPlusMinus + "\t\t\t\t" + avgMIN + "\t" +
                    avgPRF + "\t" + avgDNK + "\t" + avgFPTS);
            
            // Print sums and averages
            
            System.out.println("\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOR\tFLS\t+/-\tFG%\t3PT%\tFT%\tMIN\tPRF\tDNK\tFPTS\tGS\tPOG\tPER");

            System.out.println("TOTAL\t" + totalPTS + "\t" + totalREB + "\t" + totalAST + "\t" + totalBLK + "\t" +
                    totalSTL + "\t" + totalTO + "\t" + totalFGM + "\t" + totalFGA + "\t" + total3PTM + "\t" +
                    total3PTA + "\t" + totalFTM + "\t" + totalFTA + "\t" + totalOR + "\t" + totalFLS + "\t" +
                    totalPlusMinus + "\t" + new DecimalFormat("#0.0%").format(avgFGPercentage) + "\t" + 
                    new DecimalFormat("#0.0%").format(avg3PTPercentage) + "\t"  + new DecimalFormat("#0.0%").format(avgFTPercentage) + "\t" +  
                    totalMIN + "\t" + totalPRF + "\t" + totalDNK + "\t"  +
                    new DecimalFormat("#0.0").format(totalFPTS) + "\t" + avgGS + "\t" + totalPOG + "\t" + new DecimalFormat("0.00").format(RankAnalyzer.totalPlayerPERMap.get(playerName)));

            System.out.println("");
            System.out.println("\tPTS\tREB\tAST\tBLK\tSTL\tTO\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tOR\tFLS\t+/-\tFG%\t3PT%\tFT%\tMIN\tPRF\tDNK\tFPTS\tGS\tPOG\tPER");
            RankAnalyzer.printPlayerRankings(playerName);
            

            System.out.println("Total POGs: " + totalPOG);     
            
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        else {
        	System.out.println("Player not found.");
        }
       
    }
    
    
    public static void printTeamStats(String teamName, Map<String, List<Map<String, List<Map<String, String>>>>> mainMap , boolean isTeamStats) {
        boolean teamFound = false;
        if (isTeamStats) {
        	totalTeamPoints = 0;
        	totalPPP = 0;
        	totalOER = 0;
        }
        
        // Initialize sums for each statistic
        int totalPTS = 0, totalAST = 0, totalBLK = 0, totalSTL = 0, totalTO = 0,
            totalFGM = 0, totalFGA = 0, total3PTM = 0, total3PTA = 0, totalFTM = 0, totalFTA = 0,
            totalOREB = 0, totalDREB = 0, totalTREB = 0, totalTF = 0, totalFBPTS = 0, totalPTSiP = 0, totalPTSOff = 0, totalSCPTS = 0,
        totalBPTS = 0, totalDNK = 0;
        double totalPOS = 0;
        int totalTOP = 0;
        int totalGames = 0;
        int i = 0;
        
        // Iterate over each team's data
        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> entry : mainMap.entrySet()) {
            List<Map<String, List<Map<String, String>>>> dataList = entry.getValue();
            String currentTeamName = entry.getKey();
            // Iterate over each sheet's data for the current team
            for (Map<String, List<Map<String, String>>> sheetDataMap : dataList) {
                for (Map.Entry<String, List<Map<String, String>>> sheetEntry : sheetDataMap.entrySet()) {
                    // Check if the current player name matches the input playerName
                    if (currentTeamName.toLowerCase().equals(teamName.toLowerCase())) {
                        teamFound = true;
                        String statName = sheetEntry.getKey(); // Get the name of the team
                        System.out.println(currentTeamName);
                        System.out.println(statName); // Print the team name
                        

                         
                        List<Map<String, String>> sheetRows = sheetEntry.getValue();

                        
                        System.out.println("Games\tPTS\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tDNK\tFBPTS\tPTSiP\tSCPTS\tBPTS\tAST\tOREB\tDREB\tTREB\tSTL\tBLK\tTO\tPTS Off\tTF\tTOP\tFG%\t3PT%\tFT%\tPOS");
                        
                        // Iterate over each row of stats
                        for (Map<String, String> dataMap : sheetRows) {
                            // Update sums
                        	
//                        	if (i >= 10) {
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

                                // Print individual stats
                                
                                
                                
                                System.out.print(dataMap.get("Games") + "\t");
                                System.out.print(dataMap.get("PTS") + "\t");
                                System.out.print(dataMap.get("FGM") + "\t");
                                System.out.print(dataMap.get("FGA") + "\t");
                                System.out.print(dataMap.get("3PTM") + "\t");
                                System.out.print(dataMap.get("3PTA") + "\t");
                                System.out.print(dataMap.get("FTM") + "\t");
                                System.out.print(dataMap.get("FTA") + "\t");
                                System.out.print(dataMap.get("DNK") + "\t");
                                System.out.print(dataMap.get("FBPTS") + "\t");
                                System.out.print(dataMap.get("PTSiP") + "\t");
                                System.out.print(dataMap.get("SCPTS") + "\t");
                                System.out.print(dataMap.get("BPTS") + "\t");
                                System.out.print(dataMap.get("AST") + "\t");
                                System.out.print(dataMap.get("OREB") + "\t");
                                System.out.print(dataMap.get("DREB") + "\t");
                                System.out.print(dataMap.get("TREB") + "\t");
                                System.out.print(dataMap.get("STL") + "\t");
                                System.out.print(dataMap.get("BLK") + "\t");
                                System.out.print(dataMap.get("TO") + "\t");
                                System.out.print(dataMap.get("PTS Off") + "\t");
                                System.out.print(dataMap.get("TF") + "\t");
                                System.out.print(dataMap.get("TOP") + "\t");
                                System.out.print(dataMap.get("FG%") + "\t");
                                System.out.print(dataMap.get("3PT%") + "\t");
                                System.out.print(dataMap.get("FT%") + "\t");
                                System.out.println(new DecimalFormat("0.0").format(Double.parseDouble(dataMap.get("POS"))) + "\t");
                                
                                totalGames++;
//                        	}
                        	i++;
                            
                        }
                    }
                }
            }
        }
        
        
        

        
        
        
        if (teamFound) {

            // Calculate averages
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
            double avgPTS = Double.parseDouble(decimalFormat.format((double) totalPTS / totalGames));
            double avgFGM = Double.parseDouble(decimalFormat.format((double) totalFGM / totalGames));
            double avgFGA = Double.parseDouble(decimalFormat.format((double) totalFGA / totalGames));
            double avg3PTM = Double.parseDouble(decimalFormat.format((double) total3PTM / totalGames));
            double avg3PTA = Double.parseDouble(decimalFormat.format((double) total3PTA / totalGames));
            double avgFTM = Double.parseDouble(decimalFormat.format((double) totalFTM / totalGames));
            double avgFTA = Double.parseDouble(decimalFormat.format((double) totalFTA / totalGames));
            double avgDNK = Double.parseDouble(decimalFormat.format((double)totalDNK / totalGames));
            double avgFBPTS = Double.parseDouble(decimalFormat.format((double)totalFBPTS / totalGames));
            double avgPTSiP = Double.parseDouble(decimalFormat.format((double) totalPTSiP / totalGames));
            double avgSCPTS = Double.parseDouble(decimalFormat.format((double) totalSCPTS / totalGames));
            double avgBPTS = Double.parseDouble(decimalFormat.format((double) totalBPTS / totalGames));
            double avgAST = Double.parseDouble(decimalFormat.format((double) totalAST / totalGames));
            double avgOREB = Double.parseDouble(decimalFormat.format((double) totalOREB / totalGames));
            double avgDREB = Double.parseDouble(decimalFormat.format((double) totalDREB / totalGames));
            double avgTREB = Double.parseDouble(decimalFormat.format((double) totalTREB / totalGames));
            double avgSTL = Double.parseDouble(decimalFormat.format((double) totalSTL / totalGames));
            double avgBLK = Double.parseDouble(decimalFormat.format((double) totalBLK / totalGames));
            double avgTO = Double.parseDouble(decimalFormat.format((double) totalTO / totalGames));
            double avgPTSOff = Double.parseDouble(decimalFormat.format((double) totalPTSOff / totalGames));;
            double avgTF = Double.parseDouble(decimalFormat.format((double) totalTF / totalGames));
            long averageTOP = totalTOP / totalGames;
            long avgHours = averageTOP / 60;
            long avgMinutes = averageTOP % 60;
            String avgTOP = String.format("%02d:%02d", avgHours, avgMinutes);    
            double avgFGPercentage = (double) totalFGM / totalFGA;
            double avg3PTPercentage = (double) total3PTM / total3PTA;
            double avgFTPercentage = (double) totalFTM / totalFTA;
            double avgPOS = totalPOS/totalGames;
            double PPP = totalPTS/totalPOS;
            double ER = (totalPTS/totalPOS) * 100;
            if (isTeamStats) {
            	totalTeamPoints = totalPTS;
            	totalPPP = PPP;
            	totalOER = ER;
            }
            
           
            System.out.println("AVG\t" + avgPTS + "\t" + avgFGM + "\t" + avgFGA + "\t" + avg3PTM + "\t" + avg3PTA + "\t" + avgFTM + "\t" + avgFTA + "\t" + 
                    avgDNK + "\t" + avgFBPTS + "\t" + avgPTSiP + "\t" + avgSCPTS + "\t" + avgBPTS + "\t" + avgAST + "\t" +
                    avgOREB + "\t" + avgDREB + "\t" + avgTREB + "\t" + avgSTL + "\t" +avgBLK + "\t" + avgTO + "\t" + avgPTSOff+ "\t" + avgTF + "\t" +
                    avgTOP + "\t\t\t\t" +  new DecimalFormat("0.0").format(avgPOS));
            
            // Print sums and averages
            
            System.out.println("Games\tPTS\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tDNK\tFBPTS\tPTSiP\tSCPTS\tBPTS\tAST\tOREB\tDREB\tTREB\tSTL\tBLK\tTO\tPTS Off\tTF\tTOP\tFG%\t3PT%\tFT%\tPOS");

            
            System.out.println("TOTAL\t" + totalPTS + "\t" + totalFGM + "\t" + totalFGA + "\t" + total3PTM + "\t" + total3PTA + "\t" + totalFTM + "\t" + totalFTA + "\t" + 
                    totalDNK + "\t" + totalFBPTS + "\t" + totalPTSiP + "\t" + totalSCPTS + "\t" + totalBPTS + "\t" + totalAST + "\t" +
                    totalOREB + "\t" + totalDREB + "\t" + totalTREB + "\t" + totalSTL + "\t" + totalBLK + "\t" + totalTO + "\t" + totalPTSOff+ "\t" + totalTF + "\t\t" + new DecimalFormat("#0.0%").format(avgFGPercentage)+ "\t" + new DecimalFormat("#0.0%").format(avg3PTPercentage) + "\t"  + new DecimalFormat("#0.0%").format(avgFTPercentage) + "\t"+  new DecimalFormat("0.0").format(totalPOS));


            System.out.println("");
            System.out.println("\tPTS\tFGM\tFGA\t3PTM\t3PTA\tFTM\tFTA\tDNK\tFBPTS\tPTSiP\tSCPTS\tBPTS\tAST\tOREB\tDREB\tTREB\tSTL\tBLK\tTO\tPTS Off\tTF\tTOP\tFG%\t3PT%\tFT%\tPOS");
            RankAnalyzer.printTeamRankings(teamName, isTeamStats);
            
            System.out.println("");
            if (!isTeamStats) {
            	int PD = totalTeamPoints - totalPTS;
            	 System.out.println("PPP\t" + new DecimalFormat("0.00").format(totalPPP)  + "\t" +  RankAnalyzer.printTeamEfficiencyRankings(teamName,"PPP",true) +  "\n" + 
            			 			"OER\t" + new DecimalFormat("0.0").format(totalOER) + "\t" + RankAnalyzer.printTeamEfficiencyRankings(teamName,"ER",true));
           	 System.out.println("Opp PPP\t" + new DecimalFormat("0.00").format(PPP) + "\t" + RankAnalyzer.printTeamEfficiencyRankings(teamName,"PPP",false) + "\n" + 
			 					"DER\t" + new DecimalFormat("0.0").format(ER) +"\t" + RankAnalyzer.printTeamEfficiencyRankings(teamName,"ER",false) + "\n" + 
           			 			"PD\t" + PD + "\n");
            }
             
            
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        else {
        	System.out.println("Team not found.");
        }
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
