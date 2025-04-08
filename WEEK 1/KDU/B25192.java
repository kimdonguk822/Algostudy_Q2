// 문제: 백준 25192번 
// 문제제목 : Yes or yes
// 링크: https://www.acmicpc.net/problem/25195
// 메모리 : 59740KB
// 시간: 436ms

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
    static int N, M;
    static List<Integer>[] adj;
    static boolean[] visited;
    static boolean[] fan;
    static boolean gomgomcheck;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 인접 그래프 생성 및 초기화
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 방문배열 초기화
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 유향 그래프
            adj[u].add(v);
        }


        //fan 배열 : 곰곰이가 있는 위치를 표시하기 위한 boolean 배열
        //곰곰이가 있는 위치를 처음에 true로 바꿔줌
        int gomgom = Integer.parseInt(br.readLine());
        fan = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < gomgom; i++) {
            int s = Integer.parseInt(st.nextToken());
            fan[s] = true;
        }
        
        bfs(1);
        
        //처음 bfs를 들어갈때 1번 노드에 곰곰이가 있는 경우를 처리해주기 위해서 조건문 걸어줌
        if (fan[1]) {
			sb.append("Yes");
		}else {
	        if (gomgomcheck) {
				sb.append("yes");
			}else {
				sb.append("Yes");
			}
		}
        
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
    
    
    static void bfs(int node) {
    	Queue<Integer> q = new ArrayDeque<>();
    	q.add(node);
    	visited[node] = true;
    	
    	
    	while (!q.isEmpty()) {
			int curr = q.poll();
			
			//가장 마지막 노드(인접노드가 없는경우)이면서 곰곰이가 있지 않는 경우에는
            //곰곰이를 한번도 만나지 않고 갈수 있는 경우가 생기므로 yes를 찍는 boolean 변수를 true로 바꿔줌
			if (adj[curr].isEmpty() && !fan[curr]) {
				gomgomcheck = true;
				return;
			}
			
			
			for (int next : adj[curr]) {
				//방문하지 않았고, 곰곰이가 있는 곳이 아니라면 큐를 돌린다.
				if (!visited[next] && !fan[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
			
		}
    	
    	//while문이 끝났다는 건 돌 수 있는 곳으로 모두 갔다는 말
    	
    }
    

}