package com.demo.NBATournamentProject;

import java.util.*;

public class PlayerOfTheRoundAnalyzer {
    private Map<String, List<Map<String, List<Map<String, String>>>>> playerStatsMap;
    
    public static Map<String, Integer> teamWinsForPOTRMap = new HashMap<>();
    public static Map<String, Double> playerScoreForPOTRMap = new HashMap<>();
    public static Map<String, Integer> POTRPOGSMap = new HashMap<>();

    public PlayerOfTheRoundAnalyzer(Map<String, List<Map<String, List<Map<String, String>>>>> playerStatsMap) {
        this.playerStatsMap = playerStatsMap;
    }
    
    

    public List<String> calculatePlayerOfTheRound(int round) {
        final int gamesPerRound = 5;
        Map<String, Integer> pogsPerPlayer = new HashMap<>();
        // Calculate POGs for each player within the specified round
        for (List<Map<String, List<Map<String, String>>>> teamStats : playerStatsMap.values()) {
            for (Map<String, List<Map<String, String>>> playerData : teamStats) {
                for (Map.Entry<String, List<Map<String, String>>> playerEntry : playerData.entrySet()) {
                    String playerName = playerEntry.getKey();
                    List<Map<String, String>> gameStatsList = playerEntry.getValue();
                    int startIndex = (round - 1) * gamesPerRound;
                    int endIndex = Math.min(startIndex + gamesPerRound, gameStatsList.size());
                    
                    if (endIndex%5 == 0) {
                        int playerPOGs = 0;
                        for (int i = startIndex; i < endIndex; i++) {
                            Map<String, String> gameStats = gameStatsList.get(i);
                            playerPOGs += Integer.parseInt(gameStats.getOrDefault("POG", "0"));

                        }
                        if (playerPOGs > 0) {
                        	pogsPerPlayer.put(playerName, pogsPerPlayer.getOrDefault(playerName, 0) + playerPOGs);
                        }
                    }   
                }
            }
        }
        

        // Team wins are assumed to be calculated elsewhere
        Map<String, Integer> teamWins = TeamRecordAnalyzer.calculateTeamWinsPerRound(round, gamesPerRound);
        
        
        // Player scores need to be calculated and filled into playerScores map
        // This step is crucial and should be done based on the specific round's performance
        Map<String, Double> playerScores = calculatePlayerScoresForRound(round);
        
        

        // Filter candidates based on POGs
        List<String> candidates = determinePlayersOfTheRound(pogsPerPlayer, teamWins);

        // Final tiebreaker based on player scores
        
        if (!candidates.isEmpty()) {
            if (candidates.size() > 1) {
            	candidates = breakTieWithPlayerScores(candidates, playerScores);
            }
            String teamName = RankAnalyzer.playerTeams.get(candidates.get(0));
        	teamWinsForPOTRMap.put(candidates.get(0), teamWins.get(teamName));
        	playerScoreForPOTRMap.put(candidates.get(0), playerScores.get(candidates.get(0)));
        	POTRPOGSMap.put(candidates.get(0),pogsPerPlayer.get(candidates.get(0)));
        }

        

        return candidates;
    }

    private List<String> determinePlayersOfTheRound(Map<String, Integer> pogsPerPlayer, Map<String, Integer> teamWins) {
    	if (!pogsPerPlayer.isEmpty()) {
            int maxPogs = Collections.max(pogsPerPlayer.values());
            List<String> candidates = new ArrayList<>();
            pogsPerPlayer.forEach((player, pogs) -> {
                if (pogs.equals(maxPogs)) {
                    candidates.add(player);
                }
            });

            if (candidates.size() > 1) {
                int maxWins = candidates.stream()
                                        .mapToInt(player -> teamWins.getOrDefault(RankAnalyzer.playerTeams.get(player), 0))
                                        .max()
                                        .orElse(0);
                candidates.removeIf(player -> teamWins.getOrDefault(RankAnalyzer.playerTeams.get(player), 0) < maxWins);
            }
            
            

            return candidates;
    	}
    	return new ArrayList<String>();
    	

    }

    private List<String> breakTieWithPlayerScores(List<String> candidates, Map<String, Double> playerScores) {
        double maxScore = candidates.stream()
                                    .mapToDouble(candidate -> playerScores.getOrDefault(candidate, 0.0))
                                    .max().orElse(0.0);

        List<String> finalCandidates = new ArrayList<>();
        
        for (String candidate : candidates) {
            if (Double.compare(playerScores.getOrDefault(candidate, 0.0), maxScore) == 0) {
                finalCandidates.add(candidate);
            }
        }
        return finalCandidates;
    }


    
    private Map<String, Double> calculatePlayerScoresForRound(int round) {
        final int gamesPerRound = 5;
        int startGameIndex = (round - 1) * gamesPerRound;
        int endGameIndex = startGameIndex + gamesPerRound;

        Map<String, Double> playerScoresForRound = new HashMap<>();
        // Assuming weights are predefined or calculated elsewhere
        double weightKeyStats = 1.0; // for REB, AST, STL, BLK
        double weightNegative = 0.5; // for TO, FLS
        double weightEfficiency = 0.75; // for FG%, 3P%, FT%
        double weightPRF = 1.2;
        double weightGS = 1.5;
        double weightPOG = 2.5;
        
        
        double pts = 0,reb = 0,ast = 0,stl = 0,blk = 0,to = 0,fls = 0,fgm = 0,fga = 0,threepm = 0,threepa = 0,ftm = 0,fta = 0,prf = 0,oreb = 0,pog = 0, plusminus = 0;

        for (Map.Entry<String, List<Map<String, List<Map<String, String>>>>> teamEntry : playerStatsMap.entrySet()) {
            for (Map<String, List<Map<String, String>>> playerData : teamEntry.getValue()) {
                for (Map.Entry<String, List<Map<String, String>>> playerEntry : playerData.entrySet()) {
                    String playerName = playerEntry.getKey();
                    List<Map<String, String>> gameStatsList = playerEntry.getValue();


                    for (int i = startGameIndex; i < endGameIndex && i < gameStatsList.size(); i++) {
                        Map<String, String> gameStats = gameStatsList.get(i);

                        // Basic statistics
                        pts += Double.parseDouble(gameStats.getOrDefault("PTS", "0"));
                        reb += Double.parseDouble(gameStats.getOrDefault("REB", "0"));
                        ast += Double.parseDouble(gameStats.getOrDefault("AST", "0"));
                        stl += Double.parseDouble(gameStats.getOrDefault("STL", "0"));
                        blk += Double.parseDouble(gameStats.getOrDefault("BLK", "0"));
                        to += Double.parseDouble(gameStats.getOrDefault("TO", "0"));
                        fls += Double.parseDouble(gameStats.getOrDefault("FLS", "0"));

                        // Shooting percentages need total attempts to be calculated
                        fgm += Double.parseDouble(gameStats.getOrDefault("FGM", "0"));
                        fga += Double.parseDouble(gameStats.getOrDefault("FGA", "0"));
                        threepm += Double.parseDouble(gameStats.getOrDefault("3PM", "0"));
                        threepa += Double.parseDouble(gameStats.getOrDefault("3PA", "0"));
                        ftm += Double.parseDouble(gameStats.getOrDefault("FTM", "0"));
                        fta += Double.parseDouble(gameStats.getOrDefault("FTA", "0"));

                        
                        prf += Double.parseDouble(gameStats.getOrDefault("PRF", "0"));
                        
                        oreb += Double.parseDouble(gameStats.getOrDefault("OR", "0"));
                        plusminus += Double.parseDouble(gameStats.getOrDefault("+/-", "0"));
                        
                        pog += Double.parseDouble(gameStats.getOrDefault("POG", "0"));


                    }
                    double fgPercent = (fga > 0) ? (fgm / fga) * 100.0 : 0.0;
                    double threePercent = (threepa > 0) ? (threepm / threepa) * 100.0 : 0;
                    double ftPercent = (fta > 0) ? (ftm / fta) * 100.0 : 0.0;
                    double gamesCounted = (double) Math.min(gameStatsList.size() - startGameIndex, gamesPerRound);
                    double gs = (double)(pts+0.4*fgm-0.7*fga-0.4*(fta-ftm)+0.7*oreb+0.3*(reb-oreb)+stl+ast*0.7+0.7*blk-0.4*fls-to)/gamesCounted;

                    // Normalize the score by the number of games to average it
                    
                    // Score calculation with efficiency
                    double score = (double)pts;
                    score+= (double)((reb + ast + stl + blk) * weightKeyStats); 
                    score -= (double)((to + fls) * weightNegative);
                    score += (double)fgPercent * weightEfficiency;
                    score += (double)threePercent * weightEfficiency;
                    score += (double)ftPercent * weightEfficiency;
                    score += (double)(gs * weightGS); 
                    score += (double)(prf * weightPRF);
                    score += (double)(oreb* 1.2); 
                    score +=  (double)plusminus;
                    score += (double)pog * weightPOG;

                    
                    
                    
                    double averageScore = (gamesCounted > 0) ? (double)score / gamesCounted : 0;
                    playerScoresForRound.put(playerName, averageScore);
                    
                    pts = 0;
                    reb = 0;
                    ast = 0;
                    stl = 0;
                    blk = 0;
                    to = 0;
                    fls = 0;
                    fgm = 0;
                    fga = 0;threepm = 0;threepa = 0;ftm = 0;fta = 0;prf = 0;oreb = 0;pog = 0; plusminus = 0;
                }
            }
        }
        

        return playerScoresForRound;
    }


    


}
