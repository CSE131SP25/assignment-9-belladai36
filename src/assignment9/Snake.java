package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	public Snake() {
		segments = new LinkedList<>();
		// Start with one segment in the middle of the screen
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
	}

	public void changeDirection(int direction) {
		if (direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}

	/**
	 * Moves the snake by updating the position of each of the segments
	 */
	public void move() {
		BodySegment head = segments.getFirst();
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;

		segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));

		segments.removeLast();
	}

	/**
	 * Draws the snake
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}

	/**
	 * Check if head intersects with food; grow if so
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		if (dist < (SEGMENT_SIZE + Food.FOOD_SIZE)) {
			BodySegment tail = segments.getLast();
			segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			return true;
		}
		return false;
	}

	/**
	 * Return true if the head is within the bounds of the screen
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		if (x >= 0 && x <= 1 && y >= 0 && y <= 1) {
			return true;
		}
		return false;
	}
}
