package algo;

import java.util.Scanner;

// 리그 오브 레전설(small) 
// https://www.acmicpc.net/problem/17271
// 메모리 17680kb, 시간 172ms
public class B17271 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 싸움 시간 N초 (1~10000)
		// A스킬 시전 시간 1초
		// B스킬 시전 시간 M초 (2~100)
		int M = sc.nextInt();

		// 1과 M을 조합해서 N 만들기

		// 가능한 조합의 수를 1,000,000,007로 나눈 나머지 값을 출력하기 위해
		int mod = 1000000007;

		int[] dp = new int[N + 1];
		dp[0] = 1; // 길이 0을 만드는 경우 1가지

		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1];

			if (i >= M) {
				dp[i] = (dp[i] + dp[i - M]) % mod;
			}

		}

		System.out.println(dp[N]);

	}
}
