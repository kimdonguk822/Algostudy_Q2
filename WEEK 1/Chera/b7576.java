/**
 * 문제 : 백준 7576번 토마토
 * 메모리 : 121548KB
 * 시간 : 568ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class b7576 {
	
	static Queue<Pos> qu;
	static int N, M, day, check;
	static int[][] arr;
	
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		qu = new LinkedList<>();
		int state = 0; // 0의 개수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					qu.add(new Pos(i, j));
				}
				if (arr[i][j] == 0) {
					state++;
				}
			}
		}
		check = state;
		day = -1;
		bfs();
		if (state == 0) {
			System.out.println(0);
		}else if(check!=0) {
			System.out.println(-1);
		}else {
			System.out.println(day);
		}

	}

	static void bfs() {

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		
		while (!qu.isEmpty()) {

			int size = qu.size();
			for (int j = 0; j < size; j++) {
				Pos p = qu.poll();
				int pr = p.r;
				int pc = p.c;

				for (int i = 0; i < dx.length; i++) {
					int nr = pr + dx[i];
					int nc = pc + dy[i];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}

					if (arr[nr][nc] != 0) {
						continue;
					}

					qu.add(new Pos(nr, nc));
					arr[nr][nc] = 1;
					check--;
				}
			}
			day++;
		}

	}

}
