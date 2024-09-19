package final_oop;

public class Player {
	private String PlayerName;
	private int score;
	private int coordX;
	private int coordY;
	private int roundsTaken;
	private boolean reachedDestination;
	private boolean protectedStatus;
	private boolean usedShield;
	private boolean steppedOnPassaway;
	
	//constructor
	public Player(String playerName) {
	    PlayerName = playerName;
	    roundsTaken = 0;
	    reachedDestination = false;
	    protectedStatus = false;	// for Shield to use
	    usedShield = false; 	// for Shield to use
	}

    //setter & getter
	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public int getRoundsTaken() {
		return roundsTaken;
	}
	
	public void setRoundsTaken(int roundsTaken) {
		this.roundsTaken = roundsTaken;
	}

	public boolean isReachedDestination() {
		return reachedDestination;
	}

	public void setReachedDestination(boolean reachedDestination) {
		this.reachedDestination = reachedDestination;
	}
	
	public boolean isProtected() {
        return protectedStatus;
    }

    public void setProtected(boolean isProtected) {
        this.protectedStatus = isProtected;
    }
    
    public boolean hasUsedShield() {
        return usedShield;
    }

    public void setUsedShield(boolean usedShield) {
        this.usedShield = usedShield;
    }
    
    public boolean hasSteppedOnPassaway() {
        return steppedOnPassaway;
    }
    
    public void setSteppedOnPassaway(boolean steppedOnPassaway) {
        this.steppedOnPassaway = steppedOnPassaway;
    }

	//move forward 
	public void movePlayer(int move) {
		if ((getCoordX() + move) >= 10) { // move to next line
			if (getCoordY() == 9) {
				int remainingMove = (getCoordX() + move) - 9;
				setCoordX(9);
				setCoordX(getCoordX() - remainingMove);
			} else {
				setCoordY(coordY += 1);
				setCoordX((coordX + move) - 10);
			}
		} else if ((getCoordX() + move) < 0) {
			if ((getCoordX() + move) < 0 && getCoordY() == 0) { // move to 0,0
				setCoordY(0);
				setCoordX(0);
			} else { // move backwards to previous line
				setCoordY(coordY -= 1);
				setCoordX((coordX + move) + 10);
			}
		} else {
			setCoordX(coordX += move); // move according to mag
		}
	    roundsTaken++;
		
	}



	@Override
	public String toString() {
		return String.format("Player [PlayerName=%s, score=%s]", PlayerName, score );
	}
	
}
	
	