package final_oop;

import java.util.ArrayList;

public class Passaway extends Component {

    public Passaway(String name, ArrayList<Component> components) {
        // Initialize the Passaway with the name and other properties
        //generateMag();
        generateCoord(components);
        setPassaway(true); // Set the isPassaway flag to true for Passaway components
    }

    @Override
    public void activateComponent(Player player) {
        //System.out.println(player.getPlayerName() + " stepped on a Passaway and loses the game!");
        player.setReachedDestination(true);
    }

}
