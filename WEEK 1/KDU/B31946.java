// 문제: 백준 31946번 
// 문제제목 : 죽음의 등굣길
// 링크: https://www.acmicpc.net/problem/31946
// 메모리 : 17336KB
// 시간: 200ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, power;
	static int[][] schoolway;
	static boolean[][] visited;
	static boolean alive;
	static int jumpcount;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Pos {
		int x, y, value;

		public Pos(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		schoolway = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				schoolway[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		power = Integer.parseInt(br.readLine());

		jumpcount = 0;
		alive = false;
		bfs(0, 0, schoolway[0][0]);

		if (alive) { // 살았다!
			sb.append("ALIVE");
		} else { // 죽었다...ㅠ
			sb.append("DEAD");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	// bfs함수
	// 1~X까지 반복문으로 이동하면서 방문처리 & 같은숫자면 큐에 추가?
	// 이동은 델타함수로 가자
	static void bfs(int r, int c, int value) {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(r, c, value));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Pos curr = q.poll();

			// 끝좌표에 도달했으니까 ALIVE 출력하자
			if (curr.x == N - 1 && curr.y == M - 1) {
//				sb.append("alive");
				alive = true;
				return;
			}
//			if (jumpcount > power) {
//				alive = false;
//				return;
//			}

			// 델타탐색 시작
			// 델타탐색이 아닌 맨해튼거리탐색 필요
			for (int dx = -power; dx <= power; dx++) {
				for (int dy = -power; dy <= power; dy++) {
					
					//맨해튼 거리가 power보다 커지면 그냥 pass
					if (Math.abs(dx) + Math.abs(dy) > power) {
						continue;
					}
					//점프할수 있는 좌표
					int nr = curr.x + dx;
					int nc = curr.y + dy;
					
					// 범위 벗어나면 pass
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					// 방문했거나, 내가 가진 value하고 다르면 pass
					if (visited[nr][nc] || (schoolway[nr][nc] != curr.value)) {
						continue;
					}
					
					// 다 통과했으니까.. bfs 돌려야겠지?
					visited[nr][nc] = true;
//					jumpcount++;
					q.add(new Pos(nr, nc, value));
				}
			}
		}
		
	}

}
