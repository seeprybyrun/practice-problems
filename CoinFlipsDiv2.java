// Problem Statement

// You have n coins, labeled 0 through n-1. Each of the coins shows either heads or tails.
// You are given the states of all coins in the String state with n characters.
// For each i, state[i] is 'H' if coin i shows heads, or 'T' if it shows tails.

// The coins are laid out in a row, ordered from coin 0 to coin n-1. A coin is called interesting
// if it differs from at least one of its neighbors. (For example, a coin that shows heads is interesting
// if at least one of its neighbors shows tails.) Return the number of interesting coins.


// Definition

// Class:	CoinFlipsDiv2
// Method:	countCoins
// Parameters:	String
// Returns:	int
// Method signature:	int countCoins(String state)
// (be sure your method is public)


// Notes
// -	The value of n is not given explicitly. Instead, you can determine it as the number of characters in state.

// Constraints
// -	state will have between 1 and 50 elements, inclusive.
// -	Each character of state will be either 'H' or 'T'.

public class CoinFlipsDiv2 {

	public int countCoins(String state) {

		int numInteresting = 0;

		for( int i = 0; i < state.length(); ++i) {

			char curr = state.charAt(i);
			char prev = curr;
			char next = curr;

			if( i > 0 ) {
				prev = state.charAt(i-1);
			}
			if( i < state.length() - 1 ) {
				next = state.charAt(i+1);
			}

			boolean isInteresting = (curr != prev || curr != next);
			if( isInteresting ) {
				++numInteresting;
			}

		}

		return numInteresting;

	}


	public static void main(String[] args) {

		CoinFlipsDiv2 cfd2 = new CoinFlipsDiv2();

		int result = 0;
		int correct = 0;
		int caseNum = -1;

		// Examples
		// 0)

		// "HT"
		// Returns: 2
		// Coin 0 is interesting because it shows heads and its only neighbor shows tails. Similarly,
		// coin 1 is interesting because it shows tails and its only neighbor shows heads. Thus, the answer is 2.

		result = cfd2.countCoins("HT");
		correct = 2;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 1)

		// "T"
		// Returns: 0
		// In this test case there is a single coin. It has no neighbors, and therefore it has no different neighbors.
		// This means that the coin is not interesting.

		result = cfd2.countCoins("T");
		correct = 0;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 2)

		// "HHH"
		// Returns: 0
		// None of these coins are interesting, because each of them is only adjacent to coins that show the same side.

		result = cfd2.countCoins("HHH");
		correct = 0;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");


		// 3)

		// "HHTHHH"
		// Returns: 3
		// Here, the three interesting coins are coins 1, 2, and 3. (Remember that the indices are 0-based.)

		result = cfd2.countCoins("HHTHHH");
		correct = 3;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 4)

		// "HHHTTTHHHTTTHTHTH"
		// Returns: 12

		result = cfd2.countCoins("HHHTTTHHHTTTHTHTH");
		correct = 12;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

	}

}
