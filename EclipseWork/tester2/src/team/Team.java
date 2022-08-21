package team;

public class Team {

	private String teamName;
	private int teamWins;
	private int teamLosses;

	public String getName() {
		return teamName;
	}

	public void setName(String teamName) {
		this.teamName = teamName;
	}

	public int getWins() {
		return teamWins;
	}

	public void setWins(int teamWins) {
		this.teamWins = teamWins;
	}

	public int getLosses() {
		return teamLosses;
	}

	public void setLosses(int teamLosses) {
		this.teamLosses = teamLosses;
	}

	public double getWinPercentage() {
		return teamWins / (double) (teamWins + teamLosses);
	}
	
	public void printStanding() {
		  if (getWins() >= 0.5) {
//			  	System.out.printf("Win percentage: %.2f\n", GetWinPercentage());
				System.out.println("Congratulations, Team " + getName() + " has a winning average!");
			} else {
				System.out.println("Team " + getName() + " has a losing average.");
			}
	      
	}
}
