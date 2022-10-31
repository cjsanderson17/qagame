package lesson.gridGame;

public class Player extends Entity {
	
	public Player(int xPos, int yPos, String voiceline) {
		super(xPos, yPos, voiceline);
		
		Player.SYMBOL = 1;
	}
}
