package sokoban;

import java.io.*;
import javax.swing.JLabel;

public class Map {
    
    private MapElement[][] myMap = new MapElement[12][12];
    private int length;
    private int breadth;
    private int playerX; // the player's x-coordinate
    private int playerY; // the player's y-coordinate
    private int crateX; // the crate's x-coordinate
    private int crateY; // the crate's y-coordinate
    private MapElement Crate = new Crate();
    private MapElement Wall = new Wall();
    private boolean complete;
    private boolean nxlevel;
    
    
    Map(){
         
        for (int i = 0; i < myMap.length; i++) {
            for (int j = 0; j < myMap.length; j++) {
                myMap[i][j] = new Floor();               
            }   
        }
    } 
    
    public MapElement[][] getMyMap() {
        return myMap;
    } 
 
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
        
    }
   
  // load mapdata from file
  public void loadMap(String mapName){
     try{
         InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream(mapName));
         BufferedReader reader = new BufferedReader(fileReader);
         String line = "";
         int row = 0;
         // Allocate the map array   
         while ((line = reader.readLine()) != null){             
             //Chop up the line into chars IF char = W then new wall else if char = F then new Floor
             for (int i = 0; i < 12; i++) {
                char c = line.charAt(i);
                
                if (c == 'P') {
                        playerX = i;
                        playerY = row;
                    }
                if (c == 'W'){
                    Wall.setObs(true);
                    Wall.setCanBePushed(false);
                }
                 if (c == 'C'){
                    Crate.setObs(true);
                    Crate.setCanBePushed(true);
                }
                 switch (c) {
                     case 'W':
                         myMap[row][i] = new Wall();
                         break;
                     case 'D':
                         myMap[row][i] = new Diamond();
                         break;
                     case 'P':
                         myMap[row][i] = new Player();
                         break;
                     case 'C':
                         myMap[row][i] = new Crate();
                         break; 
                     case 'T':
                         myMap[row][i] = new Tnt();
                         break;
                     case 'F':
                         myMap[row][i] = new Floor();
                         break;
                     default:
                         break;                        
                    } 
                }      row++;                 
            }         
         reader.close();
        }catch (IOException e){
         System.out.println("Error loading map data: "+ e.getMessage());
        }
    }
  
  private int level = 0;
  public void checkForWin(){
    // Iterate through the map to check if all diamonds are covered with crates
    boolean allDiamondsCovered = true;
    for (int i = 0; i < myMap.length; i++) {
        for (int j = 0; j < myMap[i].length; j++) {
            if (myMap[i][j] instanceof Diamond) {
                // Diamond found, check if it's covered with a crate
                if (!(myMap[i - 1][j] instanceof Crate && myMap[i - 2][j] instanceof Floor ||
                      myMap[i + 1][j] instanceof Crate && myMap[i + 2][j] instanceof Floor ||
                      myMap[i][j - 1] instanceof Crate && myMap[i][j - 2] instanceof Floor ||
                      myMap[i][j + 1] instanceof Crate && myMap[i][j + 2] instanceof Floor)) {
                    allDiamondsCovered = false;
                }
            }
        }
    }
     // If all diamonds are covered,set nxLevel to true and set complete to true when all levels are completed
    if (allDiamondsCovered) {
        nxlevel = true;
        level++;
        if (level <= 5) {
            loadMap("/map/Map" + level + ".map");         
        } else {
           complete = true;
           
        }
    }
}
  public boolean isComplete() {
        return complete;
    }
  
  public boolean nextLevel() {
        return nxlevel;
    }
 
    
    public void movePlayer(int dir) {
        int x = 0;
        int y = 0;
        
switch (dir) {
    case 1:  // move up 
 if (!myMap[playerY - 1][playerX].getSymbol().equals("W")) {
        // Check what's ahead
        if (myMap[playerY - 1][playerX].isCanBePushed()) {
            // Crate can be pushed
            if (!myMap[playerY - 2][playerX].isObs() && !myMap[playerY - 3][playerX].isObs()) {
                // Check what's underneath the player
                     if (myMap[playerY - 2][playerX].getSymbol().equals("D")) {
                    // Move the crate and the diamond
                    myMap[playerY - 2][playerX] = new Crate();
                    myMap[playerY - 2][playerX].setCanBePushed(false);
                   
                } else {
                    // Move the crate
                    myMap[playerY - 2][playerX] = new Crate();
                }
                
            } else {         
                // Cannot push crate because there is an obstacle or boundary              
                break;
            }
        } else if (myMap[playerY - 1][playerX].getSymbol().equals("D")) {
            // Diamond can be moved but cannot push it on its own
            if (!myMap[playerY - 2][playerX].isObs() && myMap[playerY - 2][playerX].isCanBePushed()) {
                // Move the diamond and the crate
                myMap[playerY - 3][playerX] = new Diamond();
                myMap[playerY - 2][playerX] = new Crate();
            } else if (!myMap[playerY - 2][playerX].isObs()) {
                // Move the diamond
                myMap[playerY - 2][playerX] = new Diamond();
            } else {
                // Cannot move diamond because there is an obstacle/boundary
                break;
            }           
        }
        
        if(myMap[playerY - 1][playerX].isCanBePushed() || myMap[playerY - 1][playerX] instanceof Floor){
        // Move the player

        String underneathElement = myMap[playerY][playerX].getSymbol();
        myMap[playerY][playerX] = new Floor();
        myMap[--playerY][playerX] = new Player();
        // Set the player underneath to step 2
        if (underneathElement.equals("F")) {
            myMap[playerY][playerX].setUnderneath(new Floor());
        } else if (underneathElement.equals("D") && myMap[playerY + 1][playerX] instanceof Crate) {
            myMap[playerY][playerX].setUnderneath(new Crate());
        } else {
            myMap[playerY][playerX].setUnderneath(new Diamond());
        }
         checkForWin();  // Check for win after each move
        }
    }
    break;

        
    case 2:  // move down
  if (!myMap[playerY + 1][playerX].getSymbol().equals("W")) {
        // Check what's ahead
        if (myMap[playerY + 1][playerX].isCanBePushed()) {
            // Crate can be pushed
            if (!myMap[playerY + 2][playerX].isObs() && !myMap[playerY + 3][playerX].isObs()) {
                // Check what's underneath the player
                if (myMap[playerY + 2][playerX].getSymbol().equals("D")) {
                    // Move the crate and the diamond
                    myMap[playerY + 2][playerX] = new Crate();
                    myMap[playerY + 2][playerX].setCanBePushed(false);
                } else {
                    // Move the crate
                    myMap[playerY + 2][playerX] = new Crate();
                }
            } else {         
                // Cannot push crate because there is an obstacle or boundary              
                break;
            }
        } else if (myMap[playerY + 1][playerX].getSymbol().equals("D")) {
            // Diamond can be moved but cannot push it on its own
            if (!myMap[playerY + 2][playerX].isObs() && myMap[playerY + 2][playerX].isCanBePushed()) {
                // Move the diamond and the crate
                myMap[playerY + 3][playerX] = new Diamond();
                myMap[playerY + 2][playerX] = new Crate();
            } else if (!myMap[playerY + 2][playerX].isObs()) {
                // Move the diamond
                myMap[playerY + 2][playerX] = new Diamond();
            } else {
                // Cannot move diamond because there is an obstacle or boundary
                break;
            }           
        }
        
        if(myMap[playerY + 1][playerX].isCanBePushed() || myMap[playerY + 1][playerX] instanceof Floor){
            // Move the player
            String underneathElement = myMap[playerY][playerX].getSymbol();
            myMap[playerY][playerX] = new Floor();
            myMap[++playerY][playerX] = new Player();
            // Set the player underneath to step 2
            if (underneathElement.equals("F")) {
                myMap[playerY][playerX].setUnderneath(new Floor());
            } else if (underneathElement.equals("D") && myMap[playerY - 1][playerX] instanceof Crate) {
                myMap[playerY][playerX].setUnderneath(new Crate());
            } else {
                myMap[playerY][playerX].setUnderneath(new Diamond());
            }
            checkForWin();  // Check for win after each move
        }
    }
    break;

    case 3: //move left
  if (!myMap[playerY][playerX - 1].getSymbol().equals("W")) {
        // Check what's ahead
        if (myMap[playerY][playerX - 1].isCanBePushed()) {
            // Crate can be pushed
            if (!myMap[playerY][playerX - 2].isObs() && !myMap[playerY][playerX - 3].isObs()) {
                // Check what's next to the crate
                if (myMap[playerY][playerX - 2].getSymbol().equals("D")) {
                    // Move the crate and the diamond
                    myMap[playerY][playerX - 2] = new Crate();
                    myMap[playerY][playerX - 2].setCanBePushed(false);
                } else {
                    // Move the crate
                    myMap[playerY][playerX - 2] = new Crate();
                }
            } else {
                // Cannot push crate because there is an obstacle or boundary
                break;
            }
        } else if (myMap[playerY][playerX - 1].getSymbol().equals("D")) {
            // Diamond can be moved but cannot push it on its own
            if (!myMap[playerY][playerX - 2].isObs() && myMap[playerY][playerX - 2].isCanBePushed()) {
                // Move the diamond and the crate
                myMap[playerY][playerX - 3] = new Diamond();
                myMap[playerY][playerX - 2] = new Crate();
            } else if (!myMap[playerY][playerX - 2].isObs()) {
                // Move the diamond
                myMap[playerY][playerX - 2] = new Diamond();
            } else {
                // Cannot move diamond because there is an obstacle or boundary
                break;
            }
        }

        if (myMap[playerY][playerX - 1].isCanBePushed() || myMap[playerY][playerX - 1] instanceof Floor) {
            // Move the player
            String underneathElement = myMap[playerY][playerX].getSymbol();
            myMap[playerY][playerX] = new Floor();
            myMap[playerY][--playerX] = new Player();
            // Set the player underneath to step 2
            if (underneathElement.equals("F")) {
                myMap[playerY][playerX].setUnderneath(new Floor());
            } else if (underneathElement.equals("D") && myMap[playerY][playerX + 1] instanceof Crate) {
                myMap[playerY][playerX].setUnderneath(new Crate());
            } else {
                myMap[playerY][playerX].setUnderneath(new Diamond());
            }
            checkForWin();  // Check for win after each move
        }
    }
    break;
    case 4:  //move right
      if (!myMap[playerY][playerX + 1].getSymbol().equals("W")) {
        // Check what's ahead
        if (myMap[playerY][playerX + 1].isCanBePushed()) {
            // Crate can be pushed
            if (!myMap[playerY][playerX + 2].isObs() && !myMap[playerY][playerX + 3].isObs()) {
                // Check what's to the right of the crate
                if (myMap[playerY][playerX + 2].getSymbol().equals("D")) {
                    // Move the crate and the diamond
                    myMap[playerY][playerX + 2] = new Crate();
                    myMap[playerY][playerX + 2].setCanBePushed(false);
                } else {
                    // Move the crate
                    myMap[playerY][playerX + 2] = new Crate();
                }
            } else {
                // Cannot push crate because there is an obstacle or boundary
                break;
            }
        } else if (myMap[playerY][playerX + 1].getSymbol().equals("D")) {
            // Diamond can be moved but cannot push it on its own
            if (!myMap[playerY][playerX + 2].isObs() && myMap[playerY][playerX + 2].isCanBePushed()) {
                // Move the diamond and the crate
                myMap[playerY][playerX + 3] = new Diamond();
                myMap[playerY][playerX + 2] = new Crate();
            } else if (!myMap[playerY][playerX + 2].isObs()) {
                // Move the diamond
                myMap[playerY][playerX + 2] = new Diamond();
            } else {
                // Cannot move diamond because there is an obstacle or boundary
                break;
            }
        }

        if (myMap[playerY][playerX + 1].isCanBePushed() || myMap[playerY][playerX + 1] instanceof Floor) {
            // Move the player
            String underneathElement = myMap[playerY][playerX].getSymbol();
            myMap[playerY][playerX] = new Floor();
            myMap[playerY][++playerX] = new Player();
            // Set the player underneath to step 2
            if (underneathElement.equals("F")) {
                myMap[playerY][playerX].setUnderneath(new Floor());
            } else if (underneathElement.equals("D") && myMap[playerY][playerX - 1] instanceof Crate) {
                myMap[playerY][playerX].setUnderneath(new Crate());
            } else {
                myMap[playerY][playerX].setUnderneath(new Diamond());
            }
            checkForWin();  // Check for win after each move
        }
    }
    break;
        
    default:
        // Invalid direction
        break;
        }     
    }    
}        
   





