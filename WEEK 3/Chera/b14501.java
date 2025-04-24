/**
 * 문제 : 백준 14501번 퇴사
 * 메모리 : 14852KB
 * 시간 : 112ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14501 {
	static int[] res;
	static int[][] arr;
	static int N, max;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 퇴사까지 남은 일 수

		arr = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // T : 상담하는데 필요한 기간
			arr[i][1] = Integer.parseInt(st.nextToken()); // P : 받을 수 있는 금액
		}

		visited = new boolean[N + 1];
		max = Integer.MIN_VALUE;
		dfs(1);
		System.out.println(max);
	}

	static void dfs(int day) {
		if (day > N) {
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) {
					if (i+arr[i][0] <= N+1) {
						sum += arr[i][1];
					}
				}
			}
			max = Math.max(sum, max);
			return;
		}

		for (int i = day; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i + arr[i][0]);
				visited[i] = false;
			}
		}
	}

}
