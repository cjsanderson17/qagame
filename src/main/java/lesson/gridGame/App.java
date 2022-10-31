package lesson.gridGame;

import java.util.*;

public class App {

	public static final Integer EMPTY_SQUARE = 0;
	public static final Integer PLAYER = 1;
	public static final Integer TREASURE = 2;
	public static final Integer ENEMY = 3;

	private Map map;

	private HashMap<Integer, Entity> entities = new HashMap<>();

	private int treasureX;
	private int treasureY;

	private int numEnemies = 0;

	private boolean winStatus = false;

	public App(int numRows, int numCols, int numEnemies) {

		map = new Map(numRows, numCols);
		this.numEnemies = numEnemies;
		generateEntity(App.PLAYER);
		generateEntity(App.TREASURE);
	}
	
	public static void main(String[] args) {
		App app = new App(4,4,0);
		app.runGame();
		
	}
	
	public void runGame() {
		System.out.println("Game has started");

		while (!winStatus) {

			System.out.print("Player is currently at: (" + entities.get(App.PLAYER).getxPos());
			System.out.println(", " + entities.get(App.PLAYER).getyPos() + ")");
			System.out.println("Treasure is currently at: (" + treasureX + ", " + treasureY + ")");
			printGrid();
			checkDistance();

			try {
				Scanner scan = new Scanner(System.in);
				char move = scan.next(".").charAt(0);
				if (move == 'w')
					movePlayer(0, 1);
				else if (move == 'a')
					movePlayer(-1, 0);
				else if (move == 's')
					movePlayer(0, -1);
				else if (move == 'd')
					movePlayer(1, 0);				
			} catch (InputMismatchException e) {
				System.out.println("Invalid move (w, a, s, d)");
			}
		}

		System.out.println("You have won");
	}

	public void generateEntity(int symbol) {

		Random rand = new Random();

		int xSet = rand.nextInt(map.getRows());
		int ySet = rand.nextInt(map.getCols());

		if (!map.addEntity(symbol, xSet, ySet))
			this.generateEntity(symbol);

		if (symbol == App.TREASURE) {
			treasureX = xSet;
			treasureY = ySet;
		}

		Entity play;
		if (symbol == App.PLAYER) {
			play = new Player(xSet, ySet, "Voice");
			entities.put(App.PLAYER, play);
		}
	}

	public boolean checkWin() {

		if (entities.get(App.PLAYER).getxPos() == treasureX && entities.get(App.PLAYER).getyPos() == treasureY)
			return true;

		return false;
	}

	public void checkDistance() {

		int xDiff = Math.abs(treasureX - entities.get(App.PLAYER).getxPos());

		int yDiff = Math.abs(treasureY - entities.get(App.PLAYER).getyPos());

		System.out.println("You are " + (xDiff + yDiff) + " moves away!");

	}

	public boolean movePlayer(int x, int y) {
		
		int playerX = entities.get(App.PLAYER).getxPos();
		int playerY = entities.get(App.PLAYER).getyPos();
		
		if (!(playerX + x >= 0 && playerX + x < map.getRows())) {
			System.out.println("Whoops - you tried to move off grid");
			return false;
		}
		if (!(playerY + y >= 0 && playerY + y < map.getCols())) {
			System.out.println("Whoops - you tried to move off grid");
			return false;
		}

		entities.get(App.PLAYER).setxPos(entities.get(App.PLAYER).getxPos() + x);
		entities.get(App.PLAYER).setyPos(entities.get(App.PLAYER).getyPos() + y);

		if (checkWin()) {
			winStatus = true;
		} else {
			map.updateSquare(entities.get(App.PLAYER).getxPos(), entities.get(App.PLAYER).getyPos(), App.PLAYER);
		}

		return true;
	}
	
	public void printGrid() {
		for(int rows = 0; rows < map.getRows(); rows ++) {
			String line = "";
			for(int cols = 0; cols < map.getCols(); cols ++) {
				
				
				
			}
			System.out.println(line);
		}

	}
}