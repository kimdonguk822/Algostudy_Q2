package W4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  리그오브레전설
 *  DP
 */

/*
 * N은 N초 다 쓰고 N이 한번 쓰이면
 * 
 * */

public class B17271_리그오브레전설 {
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {

		final int MOD = 1000000007;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 싸움시간
		int M = Integer.parseInt(st.nextToken()); // 스킬의 시전시간
		
		int [] dp = new int [N+1]; // 입력값 만큼의 배열을 해야 출력할 때 편함
		dp[0] = 1; // 초기 값은 1로 설정
		
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i-1]; // 기본 1초짜리 스킬 > 왜 여기서는 MOD를 안하지?
			// 가능한 조합의 수는 나누기
			if(i >= M) {
				dp[i] = (dp[i] + dp[i - M]) % (MOD);
			}
		}
		
		System.out.println(dp[N]);
		
	}// main

}
