// 문제: 백준 B1389번 
// 문제제목 : 케빈베이컨컨
// 링크: https://www.acmicpc.net/problem/1389
// 메모리 : 14624KB
// 시간: 108ms



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B1389_케빈베이컨 {
	static int N, M; // 유저 수, 친구 관계 수
	static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			//방향 X, 가중치 X
			adj[A].add(B);
			adj[B].add(A);
		}

		int minSum = Integer.MAX_VALUE; // 최소 케빈 베이컨 수 저장 변수
		int ans = 0; // 케빈 베이컨 수가 가장 작은 사람 번호

		// 모든 유저에 대해 케빈 베이컨 수 계산
		for (int i = 1; i <= N; i++) {
			int sum = bfs(i); // i번 유저의 케빈 베이컨 수
			if (sum < minSum) {
				minSum = sum;
				ans = i;
			}
		}

		sb.append(ans);
		System.out.println(sb);
	}//main

	
	private static int bfs(int start) {
		boolean[] visited = new boolean[N + 1];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { start, 0 });
		visited[start] = true;

		int total = 0; // 케빈 베이컨 수 (거리 총합)

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int now = curr[0]; // 현재 유저
			int dist = curr[1]; // 현재 거리

			total += dist; // 거리 누적

			// 인접한 친구들 탐색
			for (int next : adj[now]) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(new int[] { next, dist + 1 }); // 거리 1 증가
				}
			}
		}

		return total; // start 유저의 케빈 베이컨 수 반환
	}
}//class
