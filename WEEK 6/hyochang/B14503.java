import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  로봇 청소기
 *  https://www.acmicpc.net/problem/14503
 *  14396kb 112ms
 */

public class B14503 {
	// 0 북, 1 동, 2 남, 3 서
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m;
	static int[][] room;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		room = new int[n][m];

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] sample = copy(room);
		int cleanedCount = cleaning(sample, y, x, dir);
		System.out.println(cleanedCount);
	}

	private static int[][] copy(int[][] room) {
		int[][] newRoom = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(room[i], 0, newRoom[i], 0, m);
		}
		return newRoom;
	}

	private static int cleaning(int[][] sample, int y, int x, int dir) {
		int count = 0;

		while (true) {
			if (sample[y][x] == 0) {
				sample[y][x] = -1;
				count++;
			}

			boolean moved = false;
			for (int i = 0; i < 4; i++) {
				dir = (dir + 3) % 4;
				int ny = y + dy[dir];
				int nx = x + dx[dir];

				if (ny >= 0 && ny < n && nx >= 0 && nx < m && sample[ny][nx] == 0) {
					y = ny;
					x = nx;
					moved = true;
					break;
				}
			}

			if (!moved) {
				int backDir = (dir + 2) % 4;
				int by = y + dy[backDir];
				int bx = x + dx[backDir];

				if (by >= 0 && by < n && bx >= 0 && bx < m && sample[by][bx] != 1) {
					y = by;
					x = bx;
				} else {
					break;
				}
			}
		}

		return count;
	}
}
