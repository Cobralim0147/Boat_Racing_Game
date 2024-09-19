package final_oop;

import java.util.Random;

public class Dice {
    //method
    private int diceVal;

    //setter and getter 
    public int getDiceVal() {
        return diceVal;
    }

    public void setDiceVal(int diceVal) {
        this.diceVal = diceVal;
    }

    //throw dice, returns 1-6
    public void throwDice() {
        Random random = new Random();
        this.setDiceVal(random.nextInt(6) +1);     
    }
}