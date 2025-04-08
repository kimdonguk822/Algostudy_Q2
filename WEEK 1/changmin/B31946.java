package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/* B31946 죽음의 등굣길
 * https://www.acmicpc.net/problem/31946
 */
public class B31946 {
	
	static int N, M, X; //행 열 점프력
	static char[][] map; //등굣길
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		} //입력
		
		X = Integer.parseInt(br.readLine());
		
		if(map[0][0] != map[N-1][M-1]) { //출발지점과 도착지점이 다르면 실패
			System.out.println("DEAD");
			return;
		}
		
		if (N == 1 && M == 1) { //사이즈가 1이면 가능
		    System.out.println("ALIVE");
		    return;
		}
		
		boolean ok = bfs(new int[] {0,0}); //0,0 시작으로 bfs탐색
		
		if(ok) { 
			System.out.println("ALIVE");
		} else {
			System.out.println("DEAD");
		}
		
	}
	
	public static boolean bfs(int[] pos) {
		boolean[][] visited = new boolean[N][M]; //방문처리 배열
		visited[pos[0]][pos[1]] = true; //시작지점은 방문
		Queue<int[]> que = new LinkedList<>(); //큐
		que.offer(pos); //큐에 시작지점 삽입
		
		while(!que.isEmpty()) { //큐가 빌때까지
			int[] curr = que.poll(); //큐에서 꺼내고
			int r = curr[0]; //좌표
	        int c = curr[1];
	        
			for(int i = -X; i <= X; i++) { //갈수있는 모든 범위를 탐색할건데
				for(int j = -X; j <= X; j++) {
					if(Math.abs(i) + Math.abs(j) > X) continue; //절댓값의 합이 X보다 크면 맨해튼거리 초과로 다음으로
					
					int nr = r + i; //새로운 좌표
					int nc = c + j;
					if(nr >= 0 && nr < N && nc >= 0 && nc < M &&  !visited[nr][nc] && map[nr][nc] == map[0][0]) { //새로운 좌표가 등굣길 내부고, 방문하지 않았고, 시작지점과 색이 같은경우
						if(nr == N-1 && nc == M-1) { //도착지점에 도달한경우 true반환
							return true;
						}
						//여기까지 왔으면 아직 도착은 하지 않았지만, 살아는 있는 상태
						visited[nr][nc] = true; //방문처리
						que.offer(new int[] {nr,nc}); //큐에 삽입
					}
				}
				
			}
			
			
		}
		
		
		
		return false; //탐색이 끝나고도 도착하지 못했으면 실패
	}
}
