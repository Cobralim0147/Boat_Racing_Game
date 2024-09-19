package final_oop;

import java.util.ArrayList;

public class Current extends Component {

    public Current (ArrayList<Component> components) {
        generateMag();
        generateCoord(components);
    } 

}