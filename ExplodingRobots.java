// Problem Statement

// You have two robots. Currently, the robots are standing on two different points on an infinite two-dimensional plane.
// You are given the ints x1, y1, x2, and y2. These are the initial coordinates of both robots: one is located at
// (x1,y1), the other one is at (x2,y2).

// The robots know four instructions: U, D, L, and R. These represent moving 1 unit up, down, left, and right. More
// formally, U increases the robot's y coordinate by 1, D decreases the y coordinate by 1, L decreases the x coordinate
// by 1, and R increases the x coordinate by 1.

// You are going to send both robots the same sequence of instructions at the same time. This sequence of instructions
// is given in the String instructions.

// Both robots are going to execute this sequence of instructions. They will spend exactly one second executing each
// instruction. Unfortunately, your robots are a bit buggy. It is possible that they will ignore some instructions and
// spend the corresponding seconds just standing in place. Each robot has its own bugs and therefore the two robots may
// ignore different subsets of instructions. (It is possible that a robot will ignore all instructions, and it is also
// possible that a robot will execute all instructions it was given.)

// The robots will explode if they ever occupy the same point at the same time.

// Return the String "Explosion" if it is possible that your two robots will explode. Otherwise, return "Safe".


// Definition

// Class:	ExplodingRobots
// Method:	canExplode
// Parameters:	int, int, int, int, String
// Returns:	String
// Method signature:	String canExplode(int x1, int y1, int x2, int y2, String instructions)
// (be sure your method is public)


// Constraints
// -	x1,y1,x2,y2 will be between -25 and 25, inclusive.
// -	(x1,y1) will be different from (x2,y2).
// -	instructions will have between 1 and 50 characters, inclusive.
// -	Each character of instructions will be 'U', 'R', 'L', or 'D'.


public class ExplodingRobots {

	public String canExplode(int x1, int y1, int x2, int y2, String instructions) {

		for( int i = 0; i < 256; ++i ) {

			int x = i;
			boolean[] ignore1 = new boolean[4];
			boolean[] ignore2 = new boolean[4];

			for( int j = 0; j < 4; ++j ) {
				ignore1[j] = (x % 2 == 1);
				x /= 2;
			}

			for( int j = 0; j < 4; ++j ) {
				ignore2[j] = (x % 2 == 1);
				x /= 2;
			}

			Robot r1 = new Robot(x1, y1, ignore1);
			Robot r2 = new Robot(x2, y2, ignore2);

			if( pathsIntersect(r1, r2, instructions) ) {
				return "Explosion";
			}
		}

		return "Safe";

	}

	private boolean pathsIntersect(Robot r1, Robot r2, String instructions) {

		int x1Prev = r1.getX();
		int y1Prev = r1.getY();

		int x2Prev = r2.getX();
		int y2Prev = r2.getY();

		boolean robotsInSamePlace = (x1Prev == x2Prev && y1Prev == y2Prev);
		if( robotsInSamePlace ) {
			return true;
		}

		for( int i = 0; i < instructions.length(); ++i ) {

			r1.move(instructions.charAt(i));
			r2.move(instructions.charAt(i));

			int x1Curr = r1.getX();
			int y1Curr = r1.getY();

			int x2Curr = r2.getX();
			int y2Curr = r2.getY();

			boolean robotsCrossedThruSamePoint = (x1Prev == x2Curr && x1Curr == x2Prev &&
												  y1Prev == y2Curr && y1Curr == y2Prev);

			if( robotsCrossedThruSamePoint ) {
				return true;
			}

			x1Prev = x1Curr;
			y1Prev = y1Curr;

			x2Prev = x2Curr;
			y2Prev = y2Curr;

			robotsInSamePlace = (x1Prev == x2Prev && y1Prev == y2Prev);
			if( robotsInSamePlace ) {
				return true;
			}

		}

		return false;

	}


	class Robot {

		private int xPos;
		private int yPos;
		private boolean[] ignore;

		final int RIGHT = 0;
		final int UP = 1;
		final int LEFT = 2;
		final int DOWN = 3;

		public Robot(int startX, int startY, boolean[] ignore) {
			this.xPos = startX;
			this.yPos = startY;
			this.ignore = ignore;
		}

		public void move(char direction) {
			if( direction == 'R' && !this.ignore[RIGHT] ) {
				++xPos;
			}

			if( direction == 'U' && !this.ignore[UP] ) {
				++yPos;
			}

			if( direction == 'L' && !this.ignore[LEFT] ) {
				--xPos;
			}

			if( direction == 'D' && !this.ignore[DOWN] ) {
				--yPos;
			}
		}

		public int getX() {
			return this.xPos;
		}

		public int getY() {
			return this.yPos;
		}

	}

	public static void main(String[] args) {

		ExplodingRobots er = new ExplodingRobots();

		String result = "";
		String correct = "";
		int caseNum = -1;

		// Examples
		// 0)

		// 1
		// 0
		// 2
		// 0
		// "L"
		// Returns: "Explosion"
		// We have two robots. One starts at coordinates (1,0), the other at (2,0). We send them a command to move left.
		// If the first robot ignores it and the second robot moves left, they will collide and explode.

		result = er.canExplode(1, 0, 2, 0, "L");
		correct = "Explosion";
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result.equals(correct) ? "pass" : "fail");

		// 1)

		// 1
		// 0
		// 2
		// 0
		// "U"
		// Returns: "Safe"
		// We have the same starting positions as in Example 0, but this time we send a command to go up.
		// Regardless of whether they ignore it or perform it, they cannot collide.

		result = er.canExplode(1, 0, 2, 0, "U");
		correct = "Safe";
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result.equals(correct) ? "pass" : "fail");

		// 2)

		// 1
		// 0
		// 2
		// 0
		// "UL"
		// Returns: "Explosion"
		// In this case, one possible scenario where the robots collide is when the first robot performs the
		// instructions "U", and the second robot performs the instructions "UL".

		result = er.canExplode(1, 0, 2, 0, "UL");
		correct = "Explosion";
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result.equals(correct) ? "pass" : "fail");

		// 3)

		// 3
		// 3
		// 5
		// 5
		// "LURLL"
		// Returns: "Safe"

		result = er.canExplode(3, 3, 5, 5, "LURLL");
		correct = "Safe";
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result.equals(correct) ? "pass" : "fail");

		// 4)

		// 10
		// 5
		// -9
		// -10
		// "LULULULLLUULRULULULULULULLULULLULD"
		// Returns: "Explosion"

		result = er.canExplode(10, 5, -9, -10, "LULULULLLUULRULULULULULULLULULLULD");
		correct = "Explosion";
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result.equals(correct) ? "pass" : "fail");

	}

}








