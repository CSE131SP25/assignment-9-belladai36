package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;



public class Game {
	
	private Snake snake;
	private Food food;
	private int score = 0;

	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
	}
	
	

	public void play() {
	

		while (snake.isInbounds()) {
			int dir = getKeypress();

			if (dir != -1) {
				snake.changeDirection(dir);
			}

			snake.move();

			if (snake.eatFood(food)) {
				food = new Food();
				score ++;
			}

			updateDrawing();
		}

		System.out.println("Game Over!");
	}

	private int getKeypress() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	

	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.textLeft(0.02, 0.98, "Score: " + score);
		
		StdDraw.pause(50);
		StdDraw.show();
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}

