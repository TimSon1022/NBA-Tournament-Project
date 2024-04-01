package com.demo.NBATournamentProject;

public class TournamentAwardsCalculator {
    public static double calculateMVP(double points, double assists, double offensiveRebounds, double pointsResponsibleFor,
            double fgPercentage, double threePointPercentage, double freeThrowPercentage,
            double defensiveRebounds, double blocks, double steals, double fouls,
            double plusMinus, double teamWins, double playerEfficiencyRating, double gameScore,
            double turnovers, double minutes, double teamsOffensiveRating, double teamsDefensiveRating,
            double playerOffensiveRating, double playerDefensiveRating, double playerScore) {

		// Adjusting weights to accommodate PlayerScore
		// Offensive Production Score
		double offensiveScore = ((points * 0.35 + assists * 0.25 + offensiveRebounds * 0.15 + pointsResponsibleFor * 0.1) * 22.5) / 100;
		
		// Efficiency Score (considers player's offensive and defensive ratings)
		double efficiencyScore = ((fgPercentage * 0.3 + threePointPercentage * 0.2 + freeThrowPercentage * 0.2 +
		         (100 - playerDefensiveRating) * 0.05 + playerOffensiveRating * 0.05) * 17.5) / 100;
		
		// Defensive Score
		double defensiveScore = ((defensiveRebounds * 0.25 + blocks * 0.25 + steals * 0.25 - fouls * 0.1) * 17.5) / 100;
		
		// Team Impact Score - includes team's offensive and defensive ratings
		double teamImpactScore = ((plusMinus * 0.2 + teamWins * 0.1 + playerEfficiencyRating * 0.1 + gameScore * 0.2 +
		         teamsOffensiveRating * 0.1 + teamsDefensiveRating * 0.1) * 22.5) / 100;
		
		// Control & Usage Score (inversely for turnovers)
		double controlUsageScore = ((-turnovers * 0.4 + minutes * 0.6) * 7.5) / 100;
		
		// PlayerScore (direct impact on MVP score)
		double playerScoreImpact = playerScore * 20 / 100; // Assuming PlayerScore is a comprehensive metric, we give it a significant weight
		
		// Summing the category scores to get the MVP score
		double mvpScore = offensiveScore + efficiencyScore + defensiveScore + teamImpactScore + controlUsageScore + playerScoreImpact;
		
		return mvpScore;
	}
    

    public static double calculateDefensivePlayerScore(double defensiveRebounds, double blocks, double steals, 
                                                       double fouls, double teamsDefensiveRating, double playerDefensiveRating, 
                                                       double minutes, double gamesPlayed) {
        // Defensive Contribution Score
        double defensiveScore = (blocks * 0.25 + steals * 0.25 + defensiveRebounds * 0.2 - fouls * 0.1 + minutes * 0.05) * 45;

        // Player's Defensive Impact
        // Lower defensive rating is better. We'll use it directly to emphasize the player's individual defensive efficiency.
        double playerDefensiveImpactScore = (100 - playerDefensiveRating) * 0.5; // Assuming 100 as a baseline for excellent defense

        // Team's Defensive Impact
        double maxDefensiveRating = 140; // Example max expected defensive rating for scaling
        double defensiveImpactScore = ((maxDefensiveRating - teamsDefensiveRating) / maxDefensiveRating) * 45;

        // Total Defensive Score
        double totalDefensiveScore = (defensiveScore + defensiveImpactScore + playerDefensiveImpactScore)/gamesPlayed;

        return totalDefensiveScore;
    }


}
