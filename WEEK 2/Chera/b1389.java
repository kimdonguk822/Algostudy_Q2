/**
 * 문제 : 백준 1389번 케빈 베이컨의 6단계 법칙
 * 메모리 : 14536KB
 * 시간 : 124ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1389 {
	static int N;
	static List<List<Integer>> graph;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 유저의 수
		int M = Integer.parseInt(st.nextToken()); // 관계의 수
		
		graph = new ArrayList<>();
		for(int i = 0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i =0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		res = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			bfs(i);
		}
		int min = Integer.MAX_VALUE;
		int ans = 0;
		for(int i = 1; i<=N; i++) {
			if(min>res[i]) {
				ans = i;
				min = res[i];
			}
		}
		System.out.println(ans);
	}
	
	static void bfs(int idx) {
		Queue<Integer> qu = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		qu.add(idx);
		visited[idx] = true;
		int depth = 1;
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i = 0; i<size; i++) {
				int n = qu.poll();
				
				for(int j = 0; j<graph.get(n).size(); j++) {
					if(visited[graph.get(n).get(j)]) continue;
					
					qu.add(graph.get(n).get(j));
					visited[graph.get(n).get(j)] = true;
					res[idx] += depth;
				}
			}
			depth++;
		}
	}
	

}
