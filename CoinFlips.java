// Problem Statement

// You have n coins. The coins are laid out in a row that goes from the left to the right.
// Different coins may have different values. You are given the int[] vals with n elements.
// The elements of vals are the values of your coins, in the order from the left to the right.

// You are also given the int prob with the following meaning: whenever you toss any of your coins,
// the probability that it comes up heads is prob/1,000,000,000.

// You are going to play a game with your coins. At the beginning of the game your score is zero.
// The game is played in turns. In each turn you perform the following actions:

// - Make a random coin toss with each coin in your row. (Note that this changes the sides shown on
// the coins but not their order in the row.)
// - Select the leftmost coin that came up heads. If all coins came up tails, select the leftmost of all coins.
// - If the selected coin has both a left neighbor and a right neighbor, let L and R be their values.
// Add L*R to your score. If the selected coin has fewer than two neighbors, do nothing in this step.
// - Remove the selected coin from the row. Shift all coins as necessary to remove any gaps in the row
// (without changing the relative order of coins in the row).
// - The game ends when you have no coins left. In other words, the game has exactly n turns.

// Compute and return the expected value of your score at the end of this game.

// Definition

// Class:	CoinFlips
// Method:	getExpectation
// Parameters:	int[], int
// Returns:	double
// Method signature:	double getExpectation(int[] vals, int prob)
// (be sure your method is public)


// Notes
// -	Your return value must have absolute or relative error less than 1e-6.
// -	Obviously, all coin flips are mutually independent random events.

// Constraints
// -	vals will have between 1 and 300 elements, inclusive.
// -	Each element of vals will be between 1 and 1,000, inclusive.
// -	p will be between 0 and 10^9, inclusive.

// Examples
// 0)
// {2,3,4}
// 500000000
// Returns: 2.0
// In this test case each coin comes up heads with probability 1/2. In order to get a nonzero score,
// we must select the middle coin in the first turn of the game. This happens only if the first series
// of coin tosses produces the outcomes THT or THH. The probability of seeing one of these two outcomes is 1/4.
// If we get one of the two desired outcomes, our score gets increased by 2*4 = 8. Thus, our expected score at
// the end of the game is (1/4)*8 = 2.

// 1)
// {5,1,4,2,3}
// 100000000
// Returns: 4.985392200480001
// Here is one possible way the game can play out:
// We toss all coins and we get the outcomes THHTH. We select the second coin (1-based index) which adds 5*4 = 20 to our score. After removing the selected coin we are left with a row of four coins. The values of these coins are 5, 4, 2, and 3, in this order.
// We toss all remaining coins and we get the outcomes TTHT. We select the third coin. This adds 4*3 = 12 to our score. Values of remaining coins: 5,4,3.
// We toss all remaining coins and we get the outcomes TTT. There are no heads, so we select the first coin. Nothing gets added to our score. Values of remaining coins: 4,3.
// We toss all remaining coins and we get the outcomes HH. We select the first coin. Nothing gets added to our score. Only the coin worth 3 remains.
// We toss the remaining coin and get tails. The only coin is selected, nothing is added to the score, the coin is removed, and the game ends.

// 2)
// {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
// 0
// Returns: 0.0
// Each coin always comes up tails. Hence, in each turn of the game we select the leftmost coin and nothing gets added to our score.

// 3)
// {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
// 1000000000
// Returns: 0.0

// 4)
// {1,2,3,4,5,6,7,8,9,10}
// 250000000
// Returns: 130.25145424313288

// 5)
// {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,5,6,2,6,4,3,3,8,3,2,7,9,5}
// 123456789
// Returns: 481.6707820920715

public class CoinFlips {

	public double getExpectation(int[] vals, int prob) {



	}

}
