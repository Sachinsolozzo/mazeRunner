import java.util.*;

public class mazesol {
    public static Maze myMap = new Maze();
    public static Scanner input = new Scanner(System.in);
    public static String direction;
    public static int moves = 0;
    public static boolean jumpedPit;

    public static void main(String[] args){
        intro();
        userMove();
    }

    public static void intro(){
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your present position:");
        myMap.printMap();
    }

    public static void userMove(){
        direction = promptUser();//ask user for direction and verifies input
        switch (direction){//handles all accepted input
            case "R":
                if(myMap.canIMoveRight()){ // checks if can move right
                    if(myMap.isThereAPit(direction)){ // check if there's a pit
                        if(!navigatePit(direction)) break; //navigate pit and if user response is invalid breaks and ask new input
                    }
                    else {myMap.moveRight();} // if there's no pit, move right
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you've hit a wall.");
                }
                break;

            case "L":
                if(myMap.canIMoveLeft()){
                    if(myMap.isThereAPit(direction)){
                        if(!navigatePit(direction)) break;
                    } else {myMap.moveLeft();}
                    myMap.printMap();
                } else {
                    System.out.println("Sorry, you've hit a wall.");
                }
                break;

            case "U":
                if(myMap.canIMoveUp()){
                    if(myMap.isThereAPit(direction)){
                        if(!navigatePit(direction)) break;
                    } else {myMap.moveUp();}
                    myMap.printMap();
                } else{
                    System.out.println("Sorry, you've hit a wall.");
                }
                break;

            case "D":
                if(myMap.canIMoveDown()){
                    if(myMap.isThereAPit(direction)){
                        if(!navigatePit(direction)) break;
                    } else { myMap.moveDown();}
                    myMap.printMap();
                } else{
                    System.out.println("Sorry, you've hit a wall.");
                }
        }
        moves++;
        if(myMap.didIWin()) System.out.println("Congratulations, you made it out alive and you did it in " + moves + " moves!");
        else{
            movesMessage(moves); // handles notification number of moves
            if( moves < 100 ) userMove(); // continues asking user for input if lower than 100, exits otherwise
        }
    }

    public static String promptUser(){
        System.out.print("Where would you like to move? (R, L, U, D) ");
        direction=input.next();
        if(!direction.equals("R") && !direction.equals("L") && !direction.equals("U") && !direction.equals("D")) promptUser();
        return direction;
    }

    public static void movesMessage(int moves){
        switch (moves){
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;

            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;

            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;

            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
    }

    public static boolean navigatePit(String direction){ // handles pit and return true is user accepted to jump and false otherwise
        System.out.print("Watch out! There's a pit ahead, jump it? ");
        String willJump = input.next();
        if(willJump.startsWith("y")){
            myMap.jumpOverPit(direction);
            jumpedPit = true;
        }else jumpedPit = false;
        return jumpedPit;
    }
}