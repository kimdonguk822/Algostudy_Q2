/**
 * 문제 : 백준 14503번 로봇청소기
 * 메모리 : 14472KB
 * 시간 : 112ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b14503 {

	static class Pos {
		int r, c, d;

		public Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 0북, 1동, 2남, 3서

		int cnt = 1; // 청소하는 칸의 개수

		Queue<Pos> qu = new LinkedList<>();

		qu.add(new Pos(r, c, d));
		arr[r][c] = -1; // 청소완료?

		while (true) {
			Pos p = qu.poll();
			int pr = p.r;
			int pc = p.c;
			
			// 1. 반시계 방향 탐색
			boolean notClean = false; // 청소되지 않은 빈칸이 있었는지 확인
			for (int i = 3; i >= 0; i--) {
				int nd = (p.d + i) % 4;
				int nr = pr + dir[nd][0];
				int nc = pc + dir[nd][1];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (arr[nr][nc] != 0)
					continue;

				qu.add(new Pos(nr, nc, nd));
				arr[nr][nc] = -1; // 청소완료
				notClean = true;
				cnt++;
				break;
			}
			
			if(notClean) continue;
			
			// 청소되지 않은 빈칸이 없었음! -> 후진
			int nr = pr + dir[p.d][0]*(-1);
			int nc = pc + dir[p.d][1]*(-1);
			
			if(nr<0||nc<0||nr>=N||nc>=M||arr[nr][nc]==1) break;
			
			qu.add(new Pos(nr, nc, p.d));
		}
		
		System.out.println(cnt);
	}
}
