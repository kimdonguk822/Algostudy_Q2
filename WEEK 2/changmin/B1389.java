package changmin;

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

/*
 * B1389 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 */

public class B1389 {

	static int N, M; // 유저의 수, 친구 관계의 수
	static List<Integer>[] graph;
	static int min, minPerson;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		min = Integer.MAX_VALUE;

		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			graph[num1].add(num2);
			graph[num2].add(num1);
		}

		for (int i = 1; i <= N; i++) {
			int sum = bfs(i);
			if (sum < min) {
				min = sum;
				minPerson = i;
			}
		}
		
		bw.write(minPerson + "");
		bw.flush();
		bw.close();
		br.close();

	}

	public static int bfs(int num) {
		int sum = 0;
		visited = new boolean[N + 1];
		Queue<Integer> que = new LinkedList<>();
		visited[num] = true;
		que.offer(num);
		
		int depth = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			
			for(int i = 0 ; i < size; i++) {
				int node = que.poll();
				sum += depth;
				
				for(int j = 0; j < graph[node].size(); j++) {
					int next = graph[node].get(j);
					if(!visited[next]) {
						visited[next] = true;
						que.offer(next);
					}
				}
				
			}
			depth++;
		}
		

		return sum;
	}

}
