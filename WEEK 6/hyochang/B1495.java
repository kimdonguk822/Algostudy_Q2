import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1495 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] volumes = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			volumes[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] cur = new boolean[m + 1];
		cur[s] = true;

		for (int i = 0; i < n; i++) {
			boolean[] pre = new boolean[m + 1];
			for (int j = 0; j < m + 1; j++) {
				if (!cur[j]) {
					continue;
				}
				if (j + volumes[i] <= m) {
					pre[j + volumes[i]] = true;
				}
				if (j - volumes[i] >= 0) {
					pre[j - volumes[i]] = true;
				}
			}
			cur = pre;
		}

		int ans = -1;
		for (int v = m; v >= 0; v--)
			if (cur[v]) {
				ans = v;
				break;
			}
		System.out.println(ans);
	}
}
