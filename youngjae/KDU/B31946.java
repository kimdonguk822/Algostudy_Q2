package 첫주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B31946 {
	static int N;
	static int M;
	static int X;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 가로
		M = Integer.parseInt(br.readLine()); // 세로

		arr = new int[N][M];
		visited = new boolean[N][M]; // 사실 안해도 될거같은데 그냥 하자

		// 배열 입력
		for (int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// 점프력 X 입력
		X = Integer.parseInt(br.readLine());

		String ans = bfs(0, 0);
		System.out.println(ans);
	}

	// 칸을 탄색하며 한칸씩 가야함
	static String bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] num = q.poll();
			int rr = num[0]; // 행
			int cc = num[1]; // 열을 받아옴

			if (rr == N - 1 && cc == M - 1) {
				return "ALIVE";
			} // 끝에 도착하면 미로 탈출 st

			for (int i = -X; i <= X; i++) {
				for (int j = -X; j <= X; j++) {
					int distance = Math.abs(i) + Math.abs(j); // 왜 -rr과 -cc를 하지 않아야되는가
					if (distance > X)
						continue;

					int nr = rr + i; // 맨헤튼 거리 이하인 친구로 간다.
					int nc = cc + j;

					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}

					if (arr[nr][nc] != arr[rr][cc]) {
						continue;
					}

					if (visited[nr][nc]) {
						continue;
					}

					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}

			}

		}
		return "DEAD";
	}
}
