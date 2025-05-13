/**
 * 문제 : 백준 1495번 기타리스트
 * 메모리 : 14332KB
 * 시간 : 108ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1495 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 곡의 개수
		int S = Integer.parseInt(st.nextToken()); // 시작 볼륨
		int M = Integer.parseInt(st.nextToken()); // 볼륨 최대치

		st = new StringTokenizer(br.readLine());
		int[] vol = new int[N + 1]; // 볼륨의 차이 저장 배열
		for (int i = 1; i <= N; i++) {
			vol[i] = Integer.parseInt(st.nextToken());
		}
		// dp[i][j] = true : i번째 곡에서 볼륨 크기 j 가 가능함 
		boolean[][] dp = new boolean[N + 1][M + 1];

		dp[0][S] = true;
		
		int ans = -1;
		
		for (int i = 1; i <= N; i++) { // i : 리스트에 적힌 곡 순서
			for (int j = 0; j <= M; j++) { // j : 볼륨 크기
				if (dp[i - 1][j]) {
					if (j + vol[i] <= M) {
						dp[i][j + vol[i]] = true;
						if(i==N)
							ans = Math.max(ans, j+vol[i]);
					}
					
					if (j - vol[i] >= 0) {
						dp[i][j - vol[i]] = true;
						if(i==N)
							ans = Math.max(ans, j-vol[i]);
					}
					
				}
			}
		}
		
		System.out.println(ans);
	}
}
