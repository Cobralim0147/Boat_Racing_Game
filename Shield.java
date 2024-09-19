package final_oop;

import java.util.ArrayList;
import java.util.Random;

public class Shield extends Component {
    private String name;

    public Shield(String name, ArrayList<Component> components) {
        this.name = name;
        generateMag(); // The magnitude of the power-up (e.g., shield strength, teleport distance, etc.)
        generateCoord(components); // Generate random coordinates for the power-up
    }
    
 
    @Override
    public void activateComponent(Player player) {
        switch (getName()) {
            case "Shield":
                if (!player.hasUsedShield()) {
                    System.out.println(player.getPlayerName() + " stepped on the Shield at (" + player.getCoordX() + ", " + player.getCoordY() + ").");
                    player.setProtected(true);
                    player.setUsedShield(true);
                } else {
                    System.out.println(player.getPlayerName() + " stepped on a Shield but already used it before.");
                }
                break;
            default:
                if (player.isProtected() && isBomb()) {
                    // If the player is protected and the component is a bomb, apply the protection
                    System.out.println(player.getPlayerName() + " stepped on a Bomb but was protected by the Shield.");
                    this.magnitude = 0; // Set the bomb's magnitude to 0
                    player.setProtected(false); // Turn off the protection after using the shield
                    player.setUsedShield(false); // Reset the usedShield status to false
                } else {
                    // Default behavior for other components (e.g., currents)
                    if (isPassaway()) {
                        // Set the steppedOnPassaway flag to true if the player steps on a Passaway
                        player.setSteppedOnPassaway(true);
                    } else {
                        player.movePlayer(getMag()); // Apply the magnitude effect on the player's movement
                    }
                }
                break;
        }
    }

    

    // Getter for the power-up name
    public String getName() {
        return name;
    }
}
