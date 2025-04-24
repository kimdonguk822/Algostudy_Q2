package W2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14562 {
	static StringTokenizer st;
	static int S; // 태균이의 점수
	static int T; // 상대 점수
	static int countKick; // 몇번 차는지 > 결과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken()); // 테스트 케이스의 수

		for (int tc = 1; tc <= C; tc++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			countKick = 0;

			countKick = Integer.MAX_VALUE;
			dfs(S, T, 0); // 시작
			System.out.println(countKick);
		} // tc만큼 입력받기

	} // main

	static void dfs(int s, int t, int count) {
		// 그 횟수 때 걸려야돼
		if (s > t) {
			return;
		} else if (s == t) {
			countKick = Math.min(countKick, count);
			return;
		}

		// 1. 궁극의 발차기
		dfs(s * 2, t + 3, count + 1);

		// 2. +1
		dfs(s + 1, t, count + 1);

	} // dfs

}
