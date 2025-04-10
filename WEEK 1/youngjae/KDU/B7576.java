package 첫주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {
	static int[] dr = { -1, 1, 0, 0 }; // 델타 행
	static int[] dc = { 0, 0, -1, 1 }; // 델타 열
	static int M;
	static int N;
	static int[][] tomato;
	static boolean[][] visited;
	static int day;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomato = new int[N][M];
		visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		boolean isOk = true; // 토마토 다 익었는지
		day = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // 한 줄씩 읽고 StringTokenizer 초기화
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1) {
					q.add(new int[] { i, j, 0 }); // 1인 애들만 전부 담아둔다
				} // 토마토가 익은게 잇으면 q에 다 넣는다.
				else if (tomato[i][j] == 0) {
					isOk = false;
				} // 익지 않은 토마토가 있다는 것은 토마토가 익어야한다는 의미

			}
		}

		// 이미 모든 토마토가 익어있으면 0을 출력
		if (isOk) {
			System.out.println(0);
			return;
		}

		// 아니면 채우면서 날짜를 계산
		bfs(q);

		// 모든 토마토를 못 채우면 -1을 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 0) {
					System.out.println(-1);
					return;
				}

			}
		}

		System.out.println(day);

	}

	static void bfs(Queue<int[]> q) {
		while (!q.isEmpty()) {
			int num[] = q.poll();
			int rr = num[0];
			int cc = num[1];
			int curr = num[2];

			day = Math.max(day, curr);
			for (int d = 0; d < 4; d++) {
				int nr = rr + dr[d];
				int nc = cc + dc[d];

				// 1. 내밖에 있으면 continue
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}

				// 2. 만약 그 델타한 위치가 0이 아니면 continue;
				if (tomato[nr][nc] != 0) {
					continue;
				}

				q.add(new int[] { nr, nc, curr + 1 });
				tomato[nr][nc] = 1;
				visited[nr][nc] = true;

			}
		}

	}
}

//for (int i = 0; i < N; i++) {
//	for (int j = 0; j < M; j++) {
//		System.out.print(tomato[i][j] + " ");
//	}
//	System.out.println();
//}
