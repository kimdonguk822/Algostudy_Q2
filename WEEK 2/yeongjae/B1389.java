package W2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1389 {
	static StringTokenizer st;
	static int N; // 유저의 수
	static int M; // 친구관계수 > 간선
	static List<Integer>[] list;
	static int min, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 유저수
		M = Integer.parseInt(st.nextToken()); // 친구관계수
		list = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		} // 인접리스트 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			list[v].add(s);
			list[s].add(v);
		} // 인접리스트 넣기

		min = Integer.MAX_VALUE;
		ans = 0;

		for (int i = 1; i <= N; i++) {
			int sum = bfs(i); // 1번부터 연관애들 다 찾기 > 2 > 3 > 4...
			if (sum < min) {
				min = sum;
				ans = i;
			}
		}

		System.out.println(ans);

	} // main

	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();

		q.add(start);
		boolean[] visited = new boolean[N + 1]; // 1번인 친구 하고 초기화, 2번인 친구 하고 초기화
		int[] dist = new int[N + 1]; // 매번 초기화
		// 배열 번호 == 시작노드 > 그 시작노드의 값을 저장해

		visited[start] = true;
		dist[start] = 0; // 자기자신은 0 넣기

		while (!q.isEmpty()) {
			int num = q.poll(); 

			for (int value : list[num]) {
				if (!visited[value]) {
					visited[value] = true;
					dist[value] = dist[num] + 1; // 새로오는 애는 이전것의 등수 +1
					q.add(value);
				}
			} // bfs돌리기
			
		} // 그냥 bfs 템플릿
		
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += dist[i];
		}
		return sum;

	} // bfs > 어차피 최소경로 보장

}
