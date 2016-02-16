// Problem Statement

// Given a sequence of n integers, we can compute the bitwise xor of each pair of integers.
// This will give us a list of n^2 integers. Your goal in this task is to reverse this process:
// you will be given the list of n^2 integers and you have to count all sequences of length n that would produce it.

// Formally, you are given a int[] s with n^2 elements. We are now interested in sequences with the following properties:
// - The sequence has n elements.
// - Each element is an integer between 0 and m, inclusive.
// - If we denote the elements a[0], ..., a[n-1], then for each i and j between 0 and n-1, inclusive,
//   s[i*n+j] equals (a[i] xor a[j]).
// - Count all such sequences and return their count modulo 1,000,000,007.


// Definition

// Class:	XorLists
// Method:	countLists
// Parameters:	int[], int
// Returns:	int
// Method signature:	int countLists(int[] s, int m)
// (be sure your method is public)

// Constraints
// -	n will be between 1 and 10, inclusive.
// -	s will have exactly n*n elements.
// -	Each element of s will be between 0 and 1,000,000,000, inclusive.
// -	m will be between 0 and 1,000,000,000, inclusive.

public class XorLists {

	public int countLists(int[] s, int m){

		int[][] a = convertTo2DArray(s);

		if( !diagonalIsAllZero(a) || !isSymmetric(a) ) {
			return 0;
		}

		int n = a.length;
		int numValidSequences = 0;
		int[] variables = new int[n];

		for( int x = 0; x <= m; ++x ) {

			variables[0] = x;

			boolean allWithinRange = true;

			for( int i = 1; i < n; ++i ) {
				variables[i] = variables[i-1] ^ a[i-1][i];

				allWithinRange &= (0 <= variables[i] && variables[i] <= m);
				if( !allWithinRange ) {
					System.out.println("failed: " + x);
					break;
				}
			}

			if( allWithinRange ) {

				boolean isConsistent = true;

				// for( int i = 0; i < n; ++i ) {
				// 	for( int j = 0; j < i; ++j ) {
				// 		isConsistent &= ((variables[i] ^ variables[j]) == a[i][j]);
				// 		if( !isConsistent ) {
				// 			break;
				// 		}
				// 	}
				// 	if( !isConsistent ) {
				// 		break;
				// 	}
				// }

				if( isConsistent ) {
					++numValidSequences;
					numValidSequences %= 1000000007;
				}
			}
		}

		// x1 ^ x2 == b

		// given x1, pick x2 == x1 ^ b
		// only count it if 0 <= x1 ^ b <= m

		// x1 ^ x2 == b1
		// x1 ^ x3 == b2
		// x2 ^ x3 == b3

		//  0 b1 b2
		// b1  0 b3
		// b2 b3  0
		// given x1, pick x2 == x1 ^ b1
		// this forces x3, x4, etc.
		// only count the sequence if all the xi are 0 <= xi <= m

		return numValidSequences;
	}

	private static int[][] convertTo2DArray(int[] s) {
		int n = (int)Math.sqrt(s.length);

		int[][] a = new int[n][];

		for( int i = 0; i < n; ++i ) {
			a[i] = new int[n];
			for( int j = 0; j < n; ++j ) {
				a[i][j] = s[i*n + j];
			}
		}

		return a;
	}

	private static boolean diagonalIsAllZero(int[][] a) {
		for( int i = 0; i < a.length; ++i ) {
			if( a[i][i] != 0 ) {
				return false;
			}
		}
		return true;
	}

	private static boolean isSymmetric(int[][] a) {
		for( int i = 0; i < a.length; ++i ) {
			for( int j = 0; j < i; ++j ) {
				if( a[i][j] != a[j][i] ) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		XorLists xl = new XorLists();

		int result = 0;
		int correct = 0;
		int caseNum = -1;

		// Examples
		// 0)

		// {0}
		// 10000
		// Returns: 10001
		// Here, the unknown sequence has just a single element. Regardless of its value, if we xor it with itself,
		// it will produce a zero. Hence, there are 10001 valid sequences: (0), (1), ..., and (10000).

		int[] input0 = {0};
		result = xl.countLists(input0, 10000);
		correct = 10001;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 1)

		// {1}
		// 10000
		// Returns: 0
		// The bitwise xor of a number with itself will never be 1, so there are no valid sequences.

		int[] input1 = {1};
		result = xl.countLists(input1, 10000);
		correct = 0;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 2)

		// {0,1,2,
		//  1,0,3,
		//  2,3,0}
		// 5
		// Returns: 4

		int[] input2 = {0,1,2,1,0,3,2,3,0};
		result = xl.countLists(input2, 5);
		correct = 4;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 3)

		// {0,3,
		//  3,0}
		// 2
		// Returns: 2
		// Note that even though the numbers in the lists are constrainted from 0 to 2,
		// the XORs can be bigger than m and still have a solution.

		int[] input3 = {0,3,3,0};
		result = xl.countLists(input3, 2);
		correct = 2;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

		// 4)

		// {
		// 0,18955782,19774078,15197314,10559559,9167552,1059865,10395923,23165910,45583720,
		// 18955782,0,820344,29809284,25173569,27974854,19993119,29335317,4212176,60207982,
		// 19774078,820344,0,30039804,25991737,27678910,20813415,28515181,5030312,60438294,
		// 15197314,29809284,30039804,0,4637893,7079490,16238747,7947665,25599828,38824426,
		// 10559559,25173569,25991737,4637893,0,2802311,11603038,4161876,29383569,35040559,
		// 9167552,27974854,27678910,7079490,2802311,0,10211033,1393619,32151830,37515176,
		// 1059865,19993119,20813415,16238747,11603038,10211033,0,9342218,24205263,44540273,
		// 10395923,29335317,28515181,7947665,4161876,1393619,9342218,0,33544901,36252795,
		// 23165910,4212176,5030312,25599828,29383569,32151830,24205263,33544901,0,64419518,
		// 45583720,60207982,60438294,38824426,35040559,37515176,44540273,36252795,64419518,0
		// }
		// 1000000000
		// Returns: 976248323

		int[] input4 = {
		0,18955782,19774078,15197314,10559559,9167552,1059865,10395923,23165910,45583720,
		18955782,0,820344,29809284,25173569,27974854,19993119,29335317,4212176,60207982,
		19774078,820344,0,30039804,25991737,27678910,20813415,28515181,5030312,60438294,
		15197314,29809284,30039804,0,4637893,7079490,16238747,7947665,25599828,38824426,
		10559559,25173569,25991737,4637893,0,2802311,11603038,4161876,29383569,35040559,
		9167552,27974854,27678910,7079490,2802311,0,10211033,1393619,32151830,37515176,
		1059865,19993119,20813415,16238747,11603038,10211033,0,9342218,24205263,44540273,
		10395923,29335317,28515181,7947665,4161876,1393619,9342218,0,33544901,36252795,
		23165910,4212176,5030312,25599828,29383569,32151830,24205263,33544901,0,64419518,
		45583720,60207982,60438294,38824426,35040559,37515176,44540273,36252795,64419518,0
		};
		result = xl.countLists(input4, 1000000000);
		correct = 976248323;
		++caseNum;
		System.out.println("case " + caseNum + ": " + result);
		System.out.println(result == correct ? "pass" : "fail");

	}

}