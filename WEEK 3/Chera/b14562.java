/**
 * 문제 : 백준 14562번 태권왕
 * 메모리 : 14604KB
 * 시간 : 108ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b14562 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tScore = Integer.parseInt(st.nextToken());
			int eScore = Integer.parseInt(st.nextToken());

			Queue<int[]> qu = new LinkedList<>();
			qu.add(new int[] { tScore, eScore });

			int cnt = 0;
			label: while (!qu.isEmpty()) {
				int size = qu.size();
				for (int i = 0; i < size; i++) {
					int[] p = qu.poll();
					int pt = p[0]; // 태균 점수
					int pe = p[1]; // 상대 점수
					if (pt == pe)
						break label;

					if (pt + 1 <= pe) {
						qu.add(new int[] { pt + 1, pe });
					}

					if (pt * 2 <= pe + 3) {
						qu.add(new int[] { pt * 2, pe + 3 });
					}
				}
				cnt++;
			}

			System.out.println(cnt);
		}
	}
}
