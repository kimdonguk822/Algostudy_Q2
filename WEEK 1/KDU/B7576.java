// 문제: 백준 7576번 
// 문제제목 : 토마토
// 링크: https://www.acmicpc.net/problem/7576
// 메모리 : 103812KB
// 시간: 576ms


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	static StringTokenizer st;
	static int N,M,time;
	static int[][] storage;
	static boolean[][] visited;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
    //좌표 저장을 위한 클래스 생성
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
        //storage : 토마토 저장 배열
        //visited : 토마토 방문 처리 배열
		storage = new int[N][M];
		visited = new boolean[N][M];
		
		Queue<Pos> q = new ArrayDeque<>();
		
		//배열에 입력값을 넣으면서 값이 1이면 bfs를 돌리기 위한 큐에 넣어주자!
        //why? -> 배열을 완전탐색으로 돌면서 bfs를 차례로 돌릴 경우 각자 토마토가 익기 시작하는
        //시점이 달라 최소 일수를 구할 수 없음
        //그러므로 bfs를 돌리기 위한 큐를 미리 만들어서 값이 1인 경우 전부 큐에 미리 넣어두고
        //한번에 while문으로 큐를 돌리면 동시처리가 가능함.
		int fullcount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				storage[i][j] = Integer.parseInt(st.nextToken());
				if (storage[i][j] == 1) {
					visited[i][j] = true;
					q.add(new Pos(i, j));
					fullcount++;
				}
			}
		}
		
        //fullcount배열을 통해 모든 토마토가 익어있는경우 모든 배열이 1로 채워져 있을 것이기 때문에
        //0으로 출력하고 main함수를 return함
		if (fullcount == N*M) {
			System.out.println(0);
			return;
		}
		
		time = -1;
		//큐에 넣은 값들을 한번에 bfs돌리기 때문에 큐의 사이즈만큼 반복문을 돌리면서 델타탐색을 해줘야됨
        //Q. 왜 큐의 사이즈만큼 반복문을 돌릴까..?
        //A. 같은 날에 익은 토마토를 한번에 처리하고, 그 다음날에 익을 토마토들은 다로 다음 레벨에서 처리하기 위함
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pos curr = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curr.x + dr[d];
					int nc = curr.y + dc[d];
					
					if (nr<0 || nc<0 || nr>=N || nc>=M) {
						continue;
					}
					if (visited[nr][nc] || storage[nr][nc] != 0) {
						continue;
					}
					
					visited[nr][nc] = true;
					storage[nr][nc] = 1;
					q.add(new Pos(nr, nc));
				}
			}
			time++;
		}
		
		//토마토가 모두 익지 못하는 상황이란? -> storage 배열에 0이 남아있다는 뜻!
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (storage[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(time);
	}

}
