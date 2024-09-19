package final_oop;

import java.util.*;
import java.util.concurrent.TimeUnit;


import java.util.Scanner;


public class Game {
    private boolean gamePlay;
    private int player1Score;
    private int player2Score;
    private Board boatGame;
    private Player player1;
    private Player player2;
    private Dice dice;
    

    // Constructor to initialize the game
    public Game() {
        gamePlay = false;
        player1Score = 0;
        player2Score = 0;
        boatGame = new Board();
        dice = new Dice();
    }
   /* 
    private void checkTrap(Player player) {
        System.out.println(player.getPlayerName() + "\nX value= " + player.getCoordX() + ", Y value= " + player.getCoordY());
        for (Component component : boatGame.getComponents()) {
            if (component.getX() == player.getCoordX() && component.getY() == player.getCoordY()) {
                if (component instanceof Shield) {
                    // If it's a power-up, activate its effect on the player
                    Shield powerUp = (Shield) component;
                    powerUp.activateComponent(player);
                } else {
                    // If it's a regular component (bomb, current), apply default behavior
                    if (!(component instanceof Passaway)) {
                        component.activateComponent(player);
                        System.out.println("\n******************\n" + player.getPlayerName() + " kena component of Mag= " + component.getMag() + "\n******************\n");
                    } else {
                        // If it's a passaway, just activate its effect without printing the message
                        component.activateComponent(player);
                    }
                }
                System.out.println(player.getPlayerName() + "\nplayerX value= " + player.getCoordX() + ", playerY value= " + player.getCoordY());
            }
        }
    }*/
  /*  
    private void checkTrap(Player player) {
        System.out.println(player.getPlayerName() + "\nX value= " + player.getCoordX() + ", Y value= " + player.getCoordY());
        for (Component component : boatGame.getComponents()) {
            if (component.getX() == player.getCoordX() && component.getY() == player.getCoordY()) {
                if (component instanceof Shield) {
                    // If it's a power-up, activate its effect on the player
                    Shield powerUp = (Shield) component;
                    powerUp.activateComponent(player);
                } else {
                    // If it's a regular component (bomb, current), apply default behavior
                    component.activateComponent(player);
                    System.out.println("\n******************\n" + player.getPlayerName() + " kena component of Mag= " + component.getMag() + "\n******************\n");
                }
                System.out.println(player.getPlayerName() + "\nplayerX value= " + player.getCoordX() + ", playerY value= " + player.getCoordY());
            }
        }
    } */

    private void checkTrap(Player player) {
        System.out.println(player.getPlayerName() + "\nX value= " + player.getCoordX() + ", Y value= " + player.getCoordY());
        for (Component component : boatGame.getComponents()) {
            if (component.getX() == player.getCoordX() && component.getY() == player.getCoordY()) {
                if (component instanceof Shield) {
                    // If it's a power-up, activate its effect on the player
                    Shield powerUp = (Shield) component;
                    powerUp.activateComponent(player);
                } else {
                    // If it's a regular component (bomb, current), apply default behavior
                    if (!(component instanceof Passaway)) {
                        component.activateComponent(player);
                        System.out.println("\n******************\n" + player.getPlayerName() + " kena component of Mag= " + component.getMag() + "\n******************\n");
                    } else {
                        // If it's a passaway, just activate its effect without printing the message
                        component.activateComponent(player);
                    }
                }
                System.out.println(player.getPlayerName() + "\nplayerX value= " + player.getCoordX() + ", playerY value= " + player.getCoordY());
            }
        }
    }

    
    // Method to start the game
    public void startGame() throws InterruptedException {
        // Create a board and create bombs, currents, and power-ups
        boatGame.generateBombs();
        boatGame.generateCurrents();
        boatGame.generateShields(); 
        boatGame.generatePassaways();

        // Create players
        System.out.println("Rules: \n\n"+
        		"1. Currents: Move forward or backward based on the current's strength.\n"
        		+ "2. Bombs: Landing on a bomb means you lose your turn and stay in the same place.\n"
        		+ "3. Shields: Shields protect you from the next bomb you encounter.\n"
        		+ "4. Passaways: Stepping on a passaway instantly makes you lose the game.");
        Scanner userName = new Scanner(System.in);
        System.out.println();
        System.out.print("Insert Player 1 Name:");
        String user1 = userName.nextLine();
        System.out.print("\nInsert Player 2 Name:");
        String user2 = userName.nextLine();
        player1 = new Player(user1);
        player2 = new Player(user2);
        boatGame.addPlayers(player1);
        boatGame.addPlayers(player2);

        // Print the initial board
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("");
        boatGame.setGameBoard();
        boatGame.printBoard();

        gamePlay = true;

        
        // Game loop
        
        do {
            playRound(player1);
            if (!gamePlay) {
                break;
            }
            //boatGame.setGameBoard();
            playRound(player2);
            TimeUnit.SECONDS.sleep(0);
            System.out.println("--------------------------------------------------");
        } while (gamePlay);

        // Print final scores and update top scores
        if (player1Score > player2Score) {
            System.out.println(player1.getPlayerName() + " is the winner with a score of " + player1Score + "!");
            FileIO.updateTopScores(getTop5PlayerScores(player1.getPlayerName(), player1Score));
        } else if (player2Score > player1Score) {
            System.out.println(player2.getPlayerName() + " is the winner with a score of " + player2Score + "!");
            FileIO.updateTopScores(getTop5PlayerScores(player2.getPlayerName(), player2Score));
        } else {
            System.out.println("It's a tie! Both players have a score of " + player1Score + "!");
        }
    }
    
    private List<FileIO.PlayerScore> getTop5PlayerScores(String winnerName, int winnerScore) {
        List<FileIO.PlayerScore> scores = FileIO.loadScores();
        scores.add(new FileIO.PlayerScore(winnerName, winnerScore));
        return scores;
    }

    
    private void playRound(Player player) throws InterruptedException {
        checkWin(player);
        if (!gamePlay) {
            return;
        }
        System.out.println(player.getPlayerName() + "'s Turn - Press Enter to roll the dice...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        dice.throwDice();
        int diceValue = dice.getDiceVal();
        System.out.println(player.getPlayerName() + " rolled a " + diceValue);

        player.movePlayer(diceValue);
        checkTrap(player); // Check for bombs and currents as before
        System.out.println();
        
        // Check for Passaway after moving
        for (Component component : boatGame.getComponents()) {
            if (component.getX() == player.getCoordX() && component.getY() == player.getCoordY() && component instanceof Passaway) {
                System.out.println(player.getPlayerName() + " stepped on a Passaway and loses the game!");
                gamePlay = false;

                // Explicitly make the other player the winner
                if (player == player1) {
                    System.out.println(player2.getPlayerName() + " wins!");
                    player2Score += player2.getRoundsTaken() + 1;
                } else if (player == player2) {
                    System.out.println(player1.getPlayerName() + " wins!");
                    player1Score += player1.getRoundsTaken() + 1;
                }
                break;
            }
        }
        TimeUnit.SECONDS.sleep(0);
        //System.out.println("--------------------------------------------------");
    }



    // Method to check if a player won    
    private void checkWin(Player player) {
        if (player.getCoordY() == 9 && player.getCoordX() == 9) {
            System.out.println(player.getPlayerName() + " won!");
            player.setScore(1);
            player.setReachedDestination(true);
            if (player.getPlayerName().equals(player1.getPlayerName())) {
                player1Score += player.getRoundsTaken() + 1;
            } else if (player.getPlayerName().equals(player2.getPlayerName())) {
                player2Score += player.getRoundsTaken() + 1;
            }
            gamePlay = false;
        } else {
            for (Component component : boatGame.getComponents()) {
                if (component.getX() == player.getCoordX() && component.getY() == player.getCoordY() && component instanceof Passaway) {
                    System.out.println(player.getPlayerName() + " stepped on a Passaway and loses the game!");
                    gamePlay = false;
                    // Explicitly make the other player the winner
                    if (player == player1) {
                        System.out.println(player2.getPlayerName() + " wins!");
                        player2Score += player2.getRoundsTaken() + 1;
                    } else if (player == player2) {
                        System.out.println(player1.getPlayerName() + " wins!");
                        player1Score += player1.getRoundsTaken() + 1;
                    }
                    break;
                }
            }
        }
    }

    
    public static void main(String[] args) throws InterruptedException {
    	FileIO.createFileIfNotExists();
    	Game game = new Game();
        game.startGame();        
    }
}
