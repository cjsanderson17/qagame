package lesson.gridGame;

import java.util.Arrays;

public class Map {

 /**
  *
  * 0 = empty square 1 = player 2 = treasure
  *
  */


 private int[][] gameMap;
 private int numRows;
 private int numCols;

 public Map(int numRows, int numCols) {
  this.numRows = numRows;
  this.numCols = numCols;

  this.generateMap();

 }

 public void updateSquare(int xPos, int yPos, int symbol) {
  gameMap[xPos][yPos] = symbol;
 }

 public boolean addEntity (int symbol, int xPos, int yPos) {

  if(gameMap[xPos][yPos] != App.EMPTY_SQUARE)
   return false;

  gameMap[xPos][yPos] = symbol;
  return true;

 }

 public void generateMap() {

  this.gameMap = new int[numRows][numCols];

  for (int x = 0; x < numRows; x++) {
   Arrays.fill(gameMap[x], 0);
  }
 }

 public int getRows() {
  return this.numRows;
 }

 public int getCols() {
  return this.numCols;
 }

}