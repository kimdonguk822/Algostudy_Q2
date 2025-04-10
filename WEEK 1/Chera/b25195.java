/**
 * 문제 : 백준 25195번 Yes or Yes
 * 메모리 : 61776KB
 * 시간 : 420ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class b25195 {
	static List<Integer> list;
	static List<List<Integer>> graph;
	static boolean[] fan, visited;
	static boolean ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
		}
		fan = new boolean[N + 1];
		visited = new boolean[N + 1];
		
		int S = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < S; i++) {
			fan[Integer.parseInt(st.nextToken())] = true;
		}
		
		if(fan[1]) {
			System.out.println("Yes");
		}else {
			dfs(1, false); // false : 곰곰이 만나기 전
			if (ans) {
				System.out.println("yes");
			} else {
				System.out.println("Yes");
			}
		}
	}
	
	static void dfs(int idx, boolean Gom) {
		if(graph.get(idx).size()==0) {
			if(!Gom) {
				ans = true;
			}
			return;
		}
		
		for(int i = 0; i<graph.get(idx).size(); i++) {
			boolean NewGom = Gom;
			
			if(fan[graph.get(idx).get(i)]) {
				NewGom = true;
			}
			visited[i] = true;
			dfs(graph.get(idx).get(i), NewGom);
			visited[i] = false;
		}
	}
}
