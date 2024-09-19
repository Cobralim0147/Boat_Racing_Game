package final_oop;

import java.util.*;

public abstract class Component{
    Random random = new Random();

    
    protected int x;
    protected int y;
    protected int magnitude;
    private boolean isPassaway;


    //setter and getter 
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getMag(){
        return magnitude;
    }

    //generate magnitude from 0-6
    public void generateMag(){
        this.magnitude = random.nextInt(6) +1;
    }

    //check if there are duplicate coordinates
    public boolean checkCoordinates(int comX, int comY, ArrayList<Component> components) {
        for (Component component : components) {
            if (component.getX() == comX && component.getY() == comY) {
                return true;
            }
        }
        return false;
    }

    //generate coordinate for the components
    public void generateCoord(ArrayList<Component> components) {
        int comX;
        int comY;
        do {
            comX = new Random().nextInt(10);
            comY = new Random().nextInt(10);
        } while (checkCoordinates(comX, comY, components) || (comX == 0 && comY == 0) || (comX == 9 && comY == 9));

        x = comX;
        y = comY;

        // Set the isPassaway flag to true for Passaway components
        if (this instanceof Passaway) {
            isPassaway = true;
        }
    }


    public void activateComponent(Player player) {
        if (player.isProtected() && isBomb()) {
            // If the player is protected and the component is a bomb, apply the protection
            System.out.println(player.getPlayerName() + " stepped on a Bomb but was protected by the Shield.");
            this.magnitude = 0; // Set the bomb's magnitude to 0
        } else if (this instanceof Passaway) {
            // If the component is a Passaway, the player loses the game
            System.out.println(player.getPlayerName() + " stepped on a Passaway and loses the game!");
            player.setReachedDestination(true);
        } else if (this instanceof Passaway && isBomb()) {
        	System.out.println(player.getPlayerName() + " stepped on a Passaway and loses the game!");
            player.setReachedDestination(true);
        }
         else {
            // Default behavior for other components (e.g., currents)
            player.movePlayer(getMag()); // Apply the magnitude effect on the player's movement
        }
    }


    
    public boolean isBomb() {
        return this instanceof Bomb;
    }
    
    public boolean isPassaway() {
        return isPassaway;
    }
    
    public void setPassaway(boolean passaway) {
        isPassaway = passaway;
    }
       

    public String toString() {
        return "Component [x=" + x + ", y=" + y + ", magnitude="+ magnitude+ "]";
    }
}
    
