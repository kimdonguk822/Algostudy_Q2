package baek;

import java.util.Scanner;

/* B17271 리그 오브 레전설(Small)
 * https://www.acmicpc.net/problem/17271
 */

public class B17271 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //제한시간
		int M = sc.nextInt(); //B스킬 시전 시간
		
		int[] dp = new int[N+1]; //
		dp[0] = 1;
		
		for (int i = 1; i < M && i <= N; i++) {  // B스킬 시전시 전까지는 조합이 1개 , N이 B스킬 시전시간보다 작은경우 고려
		    dp[i] = 1;
		}
		
		for(int i = M; i < N+1; i++ ) { //dp
			dp[i] = (dp[i-1] + dp[i-M])  % 1000000007; 
		}
		
		int sum = dp[N]; //N초의 경우의수
		
		System.out.println(sum);
	}
}
