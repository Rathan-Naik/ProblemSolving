/**
Red, Green and Blue
Given a pixel sequence consisting of red, green and blue pixels find the count of all contiguous subsequences such that each subsequence has a minimum length of 3 and the sequence cycles between red r, green g and blue b. For eg. if the sequence starts with g then the next character in the sequence should be b and the next should be r and so on.

Input Format
First line of test case consists of an integer t denoting the number of test cases. Next t test cases follow. Each test case consists of two lines. First line of each test case is the length of the pixel sequence. Second line is the pixel sequence.

Sample Input
5
3
rgb
4
brgb
2
rg
1
b
16
rgbrbbrgbgbrgrgb

Sample Output
1
3
0
0
10
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;
public class ColorSequenceSolution {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//Output answer list
		List<Integer> ans = new ArrayList<Integer>();

		//Number of test cases
		int t = Integer.parseInt(br.readLine().trim());

		for(int i=0;i<t;i++) {
			int size = Integer.parseInt(br.readLine().trim());
			
			// String to be processed
			String str= br.readLine().trim();

			/**
			 * count - current sequence length, since there is one character already processed initialize with 1
			 * total - total sequence found in the string, final required answer
			 */
			int count=1, total=0;

			for(int j=0;j<size-1;j++) {
				/** Keep incrementing until you the sequence breaks */
				if(isSeq(str.charAt(j), str.charAt(j+1))) {
					count++;
				} else {
					/** Size of the seq has to be greater than 3*/
					if(count>2) {
						total = findTotalSequences(count, total);
					}
					
					/** Restart the new sequence */
					count=1;
				}
			}

			/** Final Sequence has not been added to the total yet, so add it here */
			if(count>2) {
				//System.out.print(count);
				total = findTotalSequences(count, total);
			}
			ans.add(total);
		}

		ans.forEach(x->System.out.println(x));
	}

	/**
	 * count all possible sequences using below formula
	 * Add 1 to the generated result, because the entire string itself is one sequence
	 */
	private static int findTotalSequences(int count, int total) {
		total += count * (count-3)/2; 
		total++;
		return total;
	}

	/**
	 * Checks the sequence
	 */
	private  static  boolean isSeq(char curr, char next){
		boolean seq= false;
		if(curr=='r' && next=='g') {
			seq=true; 
		} else if(curr=='g' && next=='b') {
			seq=true;
		} else if(curr=='b' && next=='r') {
			seq=true;
		}
		return seq;
	}

}
