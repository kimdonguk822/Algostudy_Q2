package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제: 14562 태권왕
 * 링크: https://www.acmicpc.net/problem/14562
 */
public class B14562 {
	
	static int C, S, T;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		C = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for(int tc = 1; tc <= C; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			
			Queue<int[]> que = new LinkedList<>();
			boolean[][] visited = new boolean[150][150]; 
			que.offer(new int[] {S,T,0});
			visited[S][T] = true;
			while(true) {
				int[] scores = que.poll();
				
				if(scores[0] == scores[1]) {
					sb.append(scores[2]).append("\n");
					break;
				}
				
				//경우 1 S*2 T+3
				int ns1 = scores[0] * 2;
				int nt1 = scores[1] + 3;
				if(ns1 < 150 && nt1 < 150 && !visited[ns1][nt1]) {
					visited[ns1][nt1] = true;
					que.offer(new int[] {ns1, nt1, scores[2] + 1});
				}
				
				// 경우 2: S+1, T
				int ns2 = scores[0] + 1;
				int nt2 = scores[1];
				if(ns2 < 150 && !visited[ns2][nt2]) {
					visited[ns2][nt2] = true;
					que.offer(new int[] {ns2, nt2, scores[2] + 1});
				}
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
