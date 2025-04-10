/**
 * 문제 : 백준 31946번 죽음의 등굣길
 * 메모리 : 125268KB
 * 시간 : 444ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b31946 {

	static class Pos {
		int r, c, x;

		public Pos(int r, int c, int x) {
			this.r = r;
			this.c = c;
			this.x = x;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] block = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				block[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int X = Integer.parseInt(br.readLine());

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[N][M];

		Queue<Pos> qu = new LinkedList<>();

		qu.add(new Pos(0, 0, 0));
		visited[0][0] = true;

		boolean ans = false;

		label: while (!qu.isEmpty()) {

			Pos p = qu.poll();
			int pr = p.r;
			int pc = p.c;
			int px = p.x;
			for (int i = 0; i < dx.length; i++) {

				int nr = pr + dx[i];
				int nc = pc + dy[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}

				if (visited[nr][nc]) {
					continue;
				}
				
				if(block[nr][nc]!=block[0][0] && px==X-1) {
					continue;
				}

				if(block[nr][nc]!=block[0][0]) {
					qu.add(new Pos(nr, nc, px+1));
				}else {
					qu.add(new Pos(nr, nc, 0));
					visited[nr][nc] = true;
				}

				if (block[nr][nc]==block[0][0] && nr == N - 1 && nc == M - 1) {
					ans = true;
					break label;
				}

			}
		}
		if (!ans) {
			System.out.println("DEAD");
		} else {
			System.out.println("ALIVE");
		}

	}
}
