package 첫주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B25195 {
	static int N;
	static int M;
	static int S;
	static int[][] map;
	static boolean[] visited;
	static boolean[] gom; // 방문하는데 1로 초깋
	static int count; // 곰곰이 불렀어?
	static boolean save;
	static List<Integer>[] adj;

//	팬클럽 곰곰이를 만나게 된다면 "Yes" 를, 팬클럽 곰곰이를 만나지 않고 이동하는 방법이 존재한다면 "yes" 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		count = 0;
        adj = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
		} // 유향그래프

		visited = new boolean[N + 1];
		gom = new boolean[N + 1];
		// 곰곰이가 있는 정점
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			int num = Integer.parseInt(st.nextToken());
			gom[num] = true; // 곰곰이 있는 배열 먼저 채워넣을래
		} // 입력완료

		save = false;

		dfs(1);

		if (save) {
			System.out.println("yes");
		} else {
			System.out.println("Yes");
		}
	}

	// dfs > v : 시작노드
	static void dfs(int v) {
		if (gom[v]) { // 곰곰이가 있는 배열이면 중단
			return;
		}

		visited[v] = true;
		boolean isLeaf = true;

		for (int value : adj[v]) {
			if(!visited[value]) {
				isLeaf = false;
				dfs(value);
			}
		}

		// 여기 오면 노드의 끝에 와버림 > 그래서 그 경로상에 문제 있는지 없는지 체큰
		if (isLeaf) {
			save = true; // 팬클럽 못 만남
		}

	}
}
