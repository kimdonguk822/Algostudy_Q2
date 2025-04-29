import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* 
 * 1347 - 미로 만들기
 * 14360kb 104ms
 * https://www.acmicpc.net/problem/1347
 * 시작점이 주어져있지 않으므로 101 101 크기의 배열 생성
 * 
 */
public class B1347 {

	static class hong {
		int x, y, dir;

		public hong(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	// 남 0 동 1 서 2 북 3
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, -1, 0, 1 };
	static char[][] maze;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());

		maze = new char[101][101];
		hong jun = new hong(50, 50, 0);
		mark(jun);
		String note = br.readLine();
		for (int i = 0; i < n; i++) {
			char comm = note.charAt(i);

			move(comm, jun);
		}

		char[][] ans = translate();
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static char[][] translate() {
		int garoStart = 101;
		int garoEnd = 0;
		int seroStart = 101;
		int seroEnd = 0;

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (maze[i][j] == '.') {
					seroStart = Math.min(seroStart, i);
					seroEnd = Math.max(seroEnd, i);
					garoStart = Math.min(garoStart, j);
					garoEnd = Math.max(garoEnd, j);
				}
			}
		}

		int n = seroEnd - seroStart + 1;
		int m = garoEnd - garoStart + 1;
		char[][] result = new char[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maze[seroStart + i][garoStart + j] == '.') {
					result[i][j] = '.';
				} else {
					result[i][j] = '#';
				}
			}
		}

		return result;
	}

	private static void mark(hong jun) {
		maze[jun.y][jun.x] = '.';
	}

	private static void move(char comm, hong jun) {
		switch (comm) {
		case 'R':
			int rd = (jun.dir + 1) % 4;
			jun.dir = rd;
			break;
		case 'L':
			int ld = ( jun.dir+3) % 4;
			jun.dir = ld;
			break;
		case 'F':
			int y = jun.y + dy[jun.dir];
			int x = jun.x + dx[jun.dir];
			jun.y = y;
			jun.x = x;
			break;
		}
		mark(jun);
	}
}