import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class B31946 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[][] school = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				school[i][j] = sc.nextInt();
			}
		}
		int jump = sc.nextInt();

		Queue<int[]> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		int startColor = school[0][0];
		visited[0][0] = true;
		que.add(new int[] { 0, 0 });

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int y = cur[0];
			int x = cur[1];

			if (y == n - 1 && x == m - 1) {
				System.out.println("ALIVE");
				return;
			}

			for (int dy = -jump; dy <= jump; dy++) {
				for (int dx = -jump; dx <= jump; dx++) {
					if (Math.abs(dy) + Math.abs(dx) > jump)
						continue;

					int ny = y + dy;
					int nx = x + dx;
					if (ny >= 0 && ny < n && nx >= 0 && nx < m && !visited[ny][nx] && school[ny][nx] == startColor) {
						visited[ny][nx] = true;
						que.add(new int[] { ny, nx });
					}

				}
			}
		}

		System.out.println("DEAD");
	}
}
