// 문제: 백준 1495번
// 문제제목 : 후보 추천하기
// 링크: https://www.acmicpc.net/problem/1713
// 메모리 : 14616KB
// 시간: 120ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 기타리스트_DP {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N : 곡의 개수
		int S = Integer.parseInt(st.nextToken()); // S : 시작 볼륨
		int M = Integer.parseInt(st.nextToken()); // M : 최대 큰 볼륨값

		int[] volumes = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			volumes[i] = Integer.parseInt(st.nextToken());
		}
		// dp배열 생성
		boolean[][] dp = new boolean[N + 1][M + 1];
		dp[0][S] = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M; j++) {
				if (dp[i][j]) { // 이걸로 중복 방지 가능!!
					if (j - volumes[i] >= 0) {
						dp[i + 1][j - volumes[i]] = true;
					}
					if (j + volumes[i] <= M) {
						dp[i + 1][j + volumes[i]] = true;
					}
				}
			}
		}

		int result = -1;
		for (int i = M; i >= 0; i--) {
			if (dp[N][i]) {
				result = i;
				break;
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
