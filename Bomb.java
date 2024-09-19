package final_oop;
import java.util.ArrayList;


public class Bomb extends Component {

    public Bomb(ArrayList<Component> components){
        generateMag();
        magnitude = -magnitude;
        generateCoord(components);
        
    }

}