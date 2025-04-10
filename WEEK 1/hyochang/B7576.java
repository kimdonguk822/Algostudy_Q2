import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class B7576 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[][] tomato = new int[m][n];
		int target = 0;

		Queue<int[]> que = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				tomato[i][j] = sc.nextInt();
				if (tomato[i][j] == 1) {
					que.add(new int[] { i, j });
				}
				if (tomato[i][j] == 0) {
					target++;
				}
			}
		}
		if (target == 0) {
			System.out.println(0);
			return;
		}

		int[] dy = { 1, -1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		int ans = 0;
		while (!que.isEmpty() && target != 0) {
			int s = que.size();
			for (int a = 0; a < s; a++) {
				int[] temp = que.poll();
				int y = temp[0];
				int x = temp[1];
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny >= 0 && ny < m && nx >= 0 && nx < n && tomato[ny][nx] == 0) {
						tomato[ny][nx] = 1;
						que.add(new int[] { ny, nx });
						target--;
					}
				}
			}
			ans++;
		}
		if (target != 0) {
			System.out.println(-1);
		} else
			System.out.println(ans);

	}
}