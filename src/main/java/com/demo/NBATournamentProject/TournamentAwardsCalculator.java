package com.demo.NBATournamentProject;

public class TournamentAwardsCalculator {
    public static double calculateMVP(double points, double assists, double offensiveRebounds, double pointsResponsibleFor,
            double fgPercentage, double threePointPercentage, double freeThrowPercentage,
            double defensiveRebounds, double blocks, double steals, double fouls,
            double plusMinus, double teamWins, double playerEfficiencyRating, double gameScore,
            double turnovers, double teamsOffensiveRating, double teamsDefensiveRating,
            double playerOffensiveRating, double playerDefensiveRating, double playerScore, double gamesPlayed, double POGs) {

		// Adjusting weights to accommodate PlayerScore
		// Offensive Production Score
		double offensiveScore = ((points * 0.35 + assists * 0.25 + offensiveRebounds * 0.15 + pointsResponsibleFor * 0.1) * 22.5) / 100;
		
		// Efficiency Score (considers player's offensive and defensive ratings)
		double efficiencyScore = ((fgPercentage * 0.3 + threePointPercentage * 0.2 + freeThrowPercentage * 0.2 +
		         (100 - playerDefensiveRating) * 0.05 + playerOffensiveRating * 0.05) * 17.5) / 100;
		
		// Defensive Score
		double defensiveScore = ((defensiveRebounds * 0.25 + blocks * 0.25 + steals * 0.25 - fouls * 0.1) * 17.5) / 100;
		
		// Team Impact Score - includes team's offensive and defensive ratings
		double teamImpactScore = ((plusMinus*0.8  + teamWins * 75 + playerEfficiencyRating * 50 + gameScore * 1.2 +
		         teamsOffensiveRating * 1.1 + teamsDefensiveRating * 1.1 + POGs * 100) * 22.5) / 100;
		
		// Control & Usage Score (inversely for turnovers)
		double controlUsageScore = ((-turnovers * 0.4) * 7.5) / 100;
		
		// PlayerScore (direct impact on MVP score)
		double playerScoreImpact = playerScore * 20; // Assuming PlayerScore is a comprehensive metric, we give it a significant weight
		
		// Summing the category scores to get the MVP score
		double mvpScore = (double)(offensiveScore + efficiencyScore + defensiveScore + teamImpactScore + controlUsageScore + playerScoreImpact)/gamesPlayed;
		return mvpScore;
	}
    

    public static double calculateDefensivePlayerScore(double defensiveRebounds, double blocks, double steals, 
                                                       double fouls, double teamsDefensiveRating, double playerDefensiveRating, 
                                                       double gamesPlayed) {
        // Defensive Contribution Score
        double defensiveScore = (blocks * 0.25 + steals * 0.25 + defensiveRebounds * 0.025 - fouls * 0.1) * 45;

        // Player's Defensive Impact
        // Lower defensive rating is better. We'll use it directly to emphasize the player's individual defensive efficiency.
        double playerDefensiveImpactScore = (100 - playerDefensiveRating) * 90; // Assuming 100 as a baseline for excellent defense

        // Team's Defensive Impact
        double maxDefensiveRating = 140; // Example max expected defensive rating for scaling
        double defensiveImpactScore = ((maxDefensiveRating - teamsDefensiveRating) / maxDefensiveRating) * 45;

        // Total Defensive Score
        double totalDefensiveScore = (defensiveScore + defensiveImpactScore + playerDefensiveImpactScore)/gamesPlayed;

        return totalDefensiveScore;
    }
    
    public static double calculateOffensivePlayerScore(double offensiveRebounds, double points, double assists, double pointsResponsibleFor,
            double fieldGoalsMade, double fieldGoalsAttempted,
            double threePointMade, double threePointAttempted,
            double freeThrowsMade, double freeThrowsAttempted,
            double teamsOffensiveRating, double playerOffensiveRating, double gamesPlayed) {
		// Shooting Efficiency Scores
		double fgPercentage = fieldGoalsAttempted == 0 ? 0 : (fieldGoalsMade / fieldGoalsAttempted) * 100;
		double threePointPercentage = threePointAttempted == 0 ? 0 : (threePointMade / threePointAttempted) * 100;
		double freeThrowPercentage = freeThrowsAttempted == 0 ? 0 : (freeThrowsMade / freeThrowsAttempted) * 100;
		double shootingEfficiency = (fgPercentage * 0.5 + threePointPercentage * 0.3 + freeThrowPercentage * 0.2) * 30;
		
		// Individual Offensive Contributions
		double individualContributions = (points * 0.25 + assists * 0.20 + pointsResponsibleFor * 0.15 + offensiveRebounds * 0.05) * 30;
		
		// Efficiency and Impact
		double efficiencyImpact = (playerOffensiveRating * 0.6 + teamsOffensiveRating * 0.4) * 30;
		
		
		// Calculate Total Offensive Score, normalize by games played to get average per game
		double totalOffensiveScore = (shootingEfficiency + individualContributions + efficiencyImpact) / gamesPlayed;
		
		return totalOffensiveScore;
}
    
    


}
