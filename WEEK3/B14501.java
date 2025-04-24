package W2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501 {
	static StringTokenizer st;
	static int[] T;
	static int[] P; //
	static int max;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = new int[N];
		P = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		} // N번 반복
			// 입력 끝

		max = Integer.MIN_VALUE; // 최대수익 저장
		dfs(0, 0);

		System.out.println(max);

	} // main

	static void dfs(int day, int price) {
		// 종료조건
		if (day >= N) { // 넘거나 같으면 끝
			max = Math.max(max, price);
			return;
		}

		// 1. 오늘 상담
		if (day + T[day] <= N) {
			dfs(day + T[day], price + P[day]);
		}
		
		// 오늘은 건너뛰시죠
		dfs(day + 1, price);

	} // dfs

}
