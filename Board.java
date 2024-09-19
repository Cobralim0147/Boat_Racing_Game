package final_oop;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int[][] gameBoard = new int[10][10];
    private ArrayList<Component> components = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    //setter and getter 
    public int[][] getGameBoard() {
        return gameBoard;
    }


    public void setGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = 0;
            }
        }

        // Set the game board with regular components (bombs and currents)
        for (Component component : components) {
            if (!(component instanceof Shield)) {
                this.gameBoard[component.getX()][component.getY()] = component.getMag();
            }
        }
    }
    
    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    //create bomb component
    public void generateBombs(){
        for(int i=0; i<10;i++){
            Bomb bomb = new Bomb(components);
            components.add(bomb);
        }
    }

    //create current component
    public void generateCurrents(){
        for(int i=0; i<10;i++){
            Current current = new Current(components);
            components.add(current);
        }
    }
    
    public void generateShields() {
        String[] shieldNames = { "Shield" }; // Add more shield names as needed

        for (int i = 0; i < 5; i++) { // Create 5 shield power-ups (adjust the number as needed)
            String randomShieldName = shieldNames[new Random().nextInt(shieldNames.length)];
            Shield shield = new Shield(randomShieldName, components);
            components.add(shield);
        }
    }
    
    public void generatePassaways() {
        String passawayName = "Passaway"; // Set the name for the passaway

        for (int i = 0; i < 2; i++) { // Generate 2 passaways (adjust the number as needed)
            Passaway passaway = new Passaway(passawayName, components);
            components.add(passaway);
        }
    }



    //print all the components in the ArrayList
    public void printComponents(){
        for(Component component: components){
            System.out.println(component);
        }
    }

    //add players
    public void addPlayers(Player userPlayer){
        players.add(userPlayer);
    }

    //print all the players in the ArrayList
    public void printPlayers(){
        for(Player player: players){
            System.out.println(player);
        }
    }

    //print the Board 
    public void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                boolean isShield = false;
                boolean isPassaway = false;

                for (Component component : components) {
                    if (component.getX() == i && component.getY() == j) {
                        if (component instanceof Shield) {
                            isShield = true;
                        } else if (component instanceof Passaway) {
                            isPassaway = true;
                        }
                    } 
                }

                if (isShield) {
                    System.out.print("S\t"); // Display 'S' for shield element
                } else if (isPassaway) {
                    System.out.print("P\t"); // Display 'P' for passaway element
                } else {
                    System.out.print(gameBoard[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    //check for availability
    public Boolean checkAvailability(int userX, int userY){
        if (gameBoard[userX][userY] == 0){
            return true;
        }
        return false;
    }



public Component getComponentAtPosition(int x, int y) {
    for (Component component : components) {
        if (component.getX() == x && component.getY() == y) {
            return component;
        }
    }
    return null;
}

}