import java.util.Scanner;

/*
 *  문제: 15683 감시
 *  메모리: 56364KB 시간: 276ms
 *  링크: https://www.acmicpc.net/problem/15683
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class cctv {
		int y, x, kind;

		public cctv(int y, int x, int kind) {
			this.y = y;
			this.x = x;
			this.kind = kind;
		}
	}

	static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };

	static int n, m;
	static int ans = Integer.MAX_VALUE;

	static ArrayList<cctv> cctvs = new ArrayList<>();

	static int[][][] directions = { {}, { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 1 }, { 2, 3 } },
			{ { 0, 2 }, { 2, 1 }, { 1, 3 }, { 3, 0 } }, { { 0, 1, 2 }, { 0, 2, 3 }, { 1, 2, 3 }, { 0, 1, 3 } },
			{ { 0, 1, 2, 3 } } };

	public static void B15683(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] office = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if (office[i][j] >= 1 && office[i][j] <= 5) {
					cctvs.add(new cctv(i, j, office[i][j]));
				}
			}
		}

		dfs(0, copy(office));
		System.out.println(ans);
	}

	static void dfs(int depth, int[][] map) {
		if (depth == cctvs.size()) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0)
						cnt++;
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}

		cctv c = cctvs.get(depth);
		for (int[] dirSet : directions[c.kind]) {
			int[][] temp = copy(map);
			for (int dir : dirSet) {
				watch(c.y, c.x, dir, temp);
			}
			dfs(depth + 1, temp);
		}
	}

	static void watch(int y, int x, int dir, int[][] map) {
		int ny = y, nx = x;
		while (true) {
			ny += dy[dir];
			nx += dx[dir];
			if (ny < 0 || ny >= n || nx < 0 || nx >= m)
				break;
			if (map[ny][nx] == 6)
				break;
			if (map[ny][nx] == 0)
				map[ny][nx] = -1;
		}
	}

	static int[][] copy(int[][] table) {
		int[][] res = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(table[i], 0, res[i], 0, m);
		}
		return res;
	}
}