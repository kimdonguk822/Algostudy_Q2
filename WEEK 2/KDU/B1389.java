// 문제: 백준 1389번 
// 문제제목 : 케빈 베이컨의 6단계 법칙
// 링크: https://www.acmicpc.net/problem/1389
// 메모리 : 14460KB
// 시간: 112ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static List<Integer>[] graph;
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //사람수(정점수)
		M = Integer.parseInt(st.nextToken()); //친구관계수
		
		
		//인접 리스트 초기화
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//인접리스트 입력값 저장
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//무향(양방향) 그래프
			graph[a].add(b);
			graph[b].add(a);
		}
		
		//정점들을 돌면서 bfs를 돌리면서 케빈베이컨값을 저장하고 그중 최소값을 찾음
		int min = Integer.MAX_VALUE;
		int minidx = 0;
		for (int i = 1; i <= N; i++) {
			int res = bfs(i);
			if (min > res) {
				min = res;
				minidx = i;
			}
		}
		
		System.out.println(minidx);
//		System.out.println(bfs(1));
//		System.out.println(bfs(2));
//		System.out.println(bfs(3));
//		System.out.println(bfs(4));
//		System.out.println(bfs(5));
		
		
	}

	
	//bfs를 돌리면서 움직이는 값을 계속 저장하면서 리턴값에 저장하자!
	static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		int[] depth = new int[N+1];
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		q.add(start);
		int bacon = 0;
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			
			//종료 조건 : 따로 잡을 필요가 없음 -> 큐가 빌때까지 돌리면 모든 노드를 체크하게 됨!
			
			for (int next : graph[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					depth[next] = depth[curr] + 1;
					bacon += depth[next];
					q.add(next);
				}
			}
		}
		
		return bacon;
	}

}
