// 문제: 백준 17271번
// 문제제목 : 리그 오브 레전설(Small)
// 링크: https://www.acmicpc.net/problem/17271
// 메모리 : 14332KB
// 시간: 104KB

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17271 {
	static int N, M;
	static final int mod = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		long[] dp = new long[10001];
        /*
        dp[i] : i초동안 스킬을 활용할 수 있는 경우의 수
        반복문을 돌면서 i가 M보다 크다면 dp[i-M]이라는 경우의 수가
        생기므로 이를 추가로 더해줌
        (dp[i] = dp[i-1] + dp[i-M])
        그게 아니라면 단순히 1초만 더해지는 경우의 수이므로 추가로 더해주지 않음
        (dp[i] = dp[i-1])
        */
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			if (i >= M) {
				dp[i] = (dp[i - 1] + dp[i - M]) % mod;
			} else {
				dp[i] = dp[i - 1] % mod;
			}
		}

		System.out.println(dp[N]);
	}

}
