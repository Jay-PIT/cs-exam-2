//Exam 2 by Juan Isasi
public class CSFinal {
	public static void main(String[] args) {
		int[] seq = {4,3,2};
		int[][] arr = {{3,2,3,1},{1,2,3,1},{1,2,3,1},{1,2,3,1}};
		int[] game = {5,3,4,5};
		System.out.println("Stone Game: " + stoneGame(game));
		System.out.println("Falling minimum sum: " + fallingSum(arr));
		System.out.println("Palindromic Substrings: " + palindromicSubstrings("abba"));
		System.out.println("Arithmetic Slices: " + arithmeticSlices(seq));
		System.out.println("ASCII: " + minimumDeleteSum("sea","eat"));
	}
	//Problem 1 - Solved by me
	public static boolean stoneGame(int[] piles) {
		int left = 0, right = piles.length - 1, alex = 0, lee = 0, hold = 0, turn = 0;
		while(true) {
			if(piles[right] >= piles[left]) {
				hold = piles[right];
				right--;
			} else {
				hold = piles[left];
				left++;
			}
			if(turn%2 == 0)
				alex += hold;
			else
				lee += hold;
			turn++;
			if(right < left)
				return alex > lee;
		}
	}
	//Problem 2 - Solved by me
	public static int fallingSum(int[][] arr) {
		int min = 101*(arr.length);
		for(int i = 0; i < arr[0].length; i++) {
			int temp = fallingSum(arr,0,i,0);
			if(temp < min)
				min = temp;
		}
		return min;
	}
	private static int fallingSum(int[][] arr, int row, int col, int total) {
		if(row == arr.length)
			return total;
		else if(col == -1 || col == arr[row].length)
			return 101*(arr.length);
		else {
			return Math.min(Math.min(fallingSum(arr, row+1, col+1, total+arr[row][col]), 
					fallingSum(arr, row+1, col, total+arr[row][col])), fallingSum(arr, row+1, col-1, total+arr[row][col]));
		}
	}
	//Problem 3 - Solved by me
	public static int palindromicSubstrings(String start) {
		int counter = 0;
		String temp = "";
		boolean isPalin = true;
		for(int i = 0; i < start.length(); i++) {
			for(int j = i; j < start.length(); j++) {
				temp += start.charAt(j);
				for(int k = 0; k < (temp.length()/2)+1; k++) {
					if(temp.charAt(k) != temp.charAt(temp.length()-1-k)) {
						isPalin = false;
					}
				}
				if(isPalin)
					counter++;
				isPalin = true;
			}
			temp = "";
		}
		return counter;
	}
	//Problem 4 - Solved by me
	public static int arithmeticSlices(int[] seq) {
		int slices = 0;
		for(int i = 0; i < seq.length-2; i++) {
			int diff = seq[i+1] - seq[i];
			for(int j = i+1; j < seq.length-1; j++) {
				if((seq[j+1] - seq[j]) == diff)
					slices++;
				else
					break;
			}
		}
		return slices;
	}
	//Problem 5 - Solution from LeetCode
	public static int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i);
            System.out.println();
            System.out.println("s1.codePointAt("+i+"): "+s1.codePointAt(i));
            System.out.println("dp["+i+"]["+s2.length()+"]: "+dp[i][s2.length()]);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j+1] + s2.codePointAt(j);
            System.out.println();
            System.out.println("s2.codePointAt("+j+"): "+s2.codePointAt(j));
            System.out.println("dp["+s1.length()+"]["+j+"]: "+dp[s1.length()][j]);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
            	System.out.println();
                if (s1.charAt(i) == s2.charAt(j)) {
                	System.out.println("Characters at s1("+i+") and s2("+j+")");
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i),
                                        dp[i][j+1] + s2.codePointAt(j));
                }
                System.out.println("dp["+i+"]["+j+"]: "+dp[i][j]);
            }
        }
        return dp[0][0];
    }
}
