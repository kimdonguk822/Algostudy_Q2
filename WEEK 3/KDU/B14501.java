// 문제: 백준 14501번
// 문제제목 : 퇴사
// 링크: https://www.acmicpc.net/problem/14501
// 메모리 : 14448KB
// 시간: 104ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static Council[] council;
	static int max;

	static class Council {
		int day, profit;

		public Council(int day, int profit) {
			this.day = day;
			this.profit = profit;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		council = new Council[N];
		for (int i = 0; i < N; i++) {
			council[i] = new Council(0, 0);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			council[i].day = Integer.parseInt(st.nextToken());
			council[i].profit = Integer.parseInt(st.nextToken());

			// 배열 전체 돌면서 하나씩 dp를 돌림
			// 꼭 dp를 돌린다기 보다는 완탐으로 계산하고 최대수익저장하면 되지 않나...?

		}

		max = Integer.MIN_VALUE;
		// dfs 재귀?
		dfs(0, 0);
		
		System.out.println(max);

	}

	static void dfs(int day, int profit) {
		// d : 날짜, p : 수익
		if (day >= N) {
			max = Math.max(max, profit);
			return;
		}
		
		if (day + council[day].day <= N) {
			dfs(day + council[day].day, profit + council[day].profit);
		}
		
		dfs(day+1, profit);
	}

}