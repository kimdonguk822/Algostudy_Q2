import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B14890 {
	static int n, m, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;

		for (int i = 0; i < n; i++) {
			if (check(map[i]))
				ans++;
			if (check(sero(i)))
				ans++;
		}

		System.out.println(ans);
	}

	static int[] sero(int cur) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = map[i][cur];
		}
		return result;
	}

	static boolean check(int[] line) {
		boolean[] checking = new boolean[n];
		for (int i = 0; i < n - 1; i++) {
			if (line[i] == line[i + 1])
				continue;
			if (Math.abs(line[i] - line[i + 1]) > 1)
				return false;

			if (line[i] + 1 == line[i + 1]) {
				for (int j = 0; j < m; j++) {
					int diff = i - j;
					if (diff < 0 || line[diff] != line[i] || checking[diff]) {
						return false;
					}
					checking[diff] = true;
				}
			} else if (line[i] - 1 == line[i + 1]) {
				for (int j = 1; j < 1 + m; j++) {
					int idx = i + j;
					if (idx >= n || line[idx] != line[i + 1] || checking[idx])
						return false;
					checking[idx] = true;
				}
				i += m - 1;
			}
		}
		return true;
	}
}
