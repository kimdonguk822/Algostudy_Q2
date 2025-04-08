package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B25195 {

	static int N, M, S; // 정점의 개수, 간선의 개수, 곰곰이가 존재하는 정점의 개수
	static List<Integer>[] graph;// 그래프
	static boolean[] isGom; // 곰곰이 있는 정점
	static int[] gom;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1]; // 정점번호가 1부터 시작임 -> +1로 편하게
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 출발정점
			int end = Integer.parseInt(st.nextToken()); // 도착정점
			graph[start].add(end);
		}

		S = Integer.parseInt(br.readLine());
		isGom = new boolean[N + 1]; // 곰곰이인지
		gom = new int[S]; // 곰곰이 노드

		st = new StringTokenizer(br.readLine());
		// 곰곰이 입력받고
		for (int i = 0; i < S; i++) {
			gom[i] = Integer.parseInt(st.nextToken());
		}

		// 곰곰이 번호를 true로
		for (int i = 0; i < S; i++) {
			isGom[gom[i]] = true;
		}

		boolean isOk = bfs(1);

		if (isOk) {
			bw.write("Yes"); //어디로가도 곰곰이
		} else {
			bw.write("yes"); //만나지 않고 이동 가능
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[N + 1]; // 방문처리 배열
		que.offer(start);
		visited[start] = true;

		while (!que.isEmpty()) {
			int node = que.poll();

			if (isGom[node]) {
				continue; // 곰곰이 노드면 넘어감
			}
			
			if (graph[node].isEmpty()) {
	            return false; // 곰곰이 없는 리프 노드 도달
	        }

			for (int next : graph[node]) {
				if (!visited[next]) { // 방문하지 않았고
					if (!isGom[next]) {
						visited[next] = true; //방문처리
						que.offer(next);  // 곰곰이가 아니라면 큐에 삽입
					}
				}
			}

		}

		return true; // 갈 수 있는 모든 노드가 곰곰이가 있었거나, 끝에 도달할 수 없었음
	}
}
