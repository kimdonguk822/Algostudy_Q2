import java.util.Scanner;

/*
 *  문제: 1389 케빈 베이컨의 6단계 법칙
 *  메모리: 18636 KB 시간: 208ms
 *  링크: https://www.acmicpc.net/problem/1389
 */
import java.util.*;

public class B1389 {
	static int n;
	static List<Integer>[] relation;
	static int[] ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt(); // 사람 수
		int m = sc.nextInt(); // 관계 수

		relation = new ArrayList[n + 1];
		ans = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			relation[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			relation[a].add(b);
			relation[b].add(a);
		}

		for (int i = 1; i < n + 1; i++) {
			bfs(i);
		}

		int min = Integer.MAX_VALUE;
		int person = -1;

		for (int i = 1; i <= n; i++) {
			if (ans[i] < min) {
				min = ans[i];
				person = i;
			}
		}

		System.out.println(person);
	}

	static void bfs(int cur) {
		boolean[] visited = new boolean[n + 1];
		int[] kavin = new int[n + 1];
		Queue<Integer> que = new LinkedList<>();

		que.add(cur);
		visited[cur] = true;

		while (!que.isEmpty()) {
			int now = que.poll();

			for (int next : relation[now]) {
				if (!visited[next]) {
					visited[next] = true;
					kavin[next] = kavin[now] + 1;
					que.add(next);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			ans[cur] += kavin[i];
		}
	}
}
