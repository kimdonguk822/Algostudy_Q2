// 문제: 백준 16236번 
// 문제제목 : 아기 상어
// 링크: https://www.acmicpc.net/problem/16236
// 메모리 : 21932KB
// 시간: 184ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int shark_x, shark_y;
	static int N;
	static int[][] ocean;
	static boolean[][] visited;
	static Shark shark;
	

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Shark {
		int r, c, size, time, eat;

		public Shark(int r, int c, int size, int time, int eat) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.time = time;
			this.eat = eat;
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int r,c,dist;
		 
		
		public Fish(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}


		@Override
		public int compareTo(Fish o) {
			if (this.dist != o.dist) {
				return this.dist - o.dist;
			}
			if (this.r != o.r) {
				return this.r - o.r;
			}
			return this.c - o.c;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		ocean = new int[N][N];

		// 상어 입력값 저장 완료
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ocean[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		/* 메인함수 구현 
		 * 1. 반복문 돌면서 상어위치 찾고, 좌표 저장 후 좌표값을 0으로 바꿈 
		 * 2. 상어 객체 초기화(size : 2, eat : 0, time : 0) 
		 * 3. whlie문 시작 
		 * - bfs 메서드 실행 : 가장 가까운 물고기(target) 반환 
		 * - target == null이면 반복문 끝 
		 * - target이 있으면 상어좌표이동, 시간 누적(time+1), 먹은 횟수(eat+1) 증가 
		 * - target.eat == target.size -> target.size++ 
		 * - 먹은 물고기 자리를 ocean에서 0으로 바꿈(?) 
		 * 4. 최종 누적 시간 출력
		 */
		shark_x = 0;
		shark_y = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ocean[i][j] == 9) {
					shark_x = i;
					shark_y = j;
					ocean[i][j] = 0;
				}
			}
		}

		int size = 2; 
		int totalTime = 0;
		int eat = 0;
		shark = new Shark(shark_x, shark_y, size, totalTime, eat);
		while (true) {
			Fish target = bfs();
			if (target == null) break;
			
			//상어 위치 이동
			shark.r = target.r;
			shark.c = target.c;
			
			//시간 누적
			totalTime += target.dist;
			ocean[target.r][target.c] = 0;
			
			//먹은 개수 증가 + 크기 조건 확인
			shark.eat++;
			if (shark.eat == shark.size) {
				shark.size++;
				shark.eat = 0;
			}
		}
		
		System.out.println(totalTime);

	}

	
	/* 
	 * 
	 * 
	 * */
	static Fish bfs() {
		visited = new boolean[N][N];
		Queue<Fish> q = new ArrayDeque<>();
		//물고기의 정보를 저장할 리스트를 만들어놓고
		//내 사이즈보다 작은 값이면 먹으면서 리스트에 집어넣자!
		List<Fish> list = new ArrayList<>();
		
		q.add(new Fish(shark.r, shark.c, 0));
		visited[shark.r][shark.c] = true;
		
		while (!q.isEmpty()) {
			Fish curr = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if (nr<0 || nc<0 || nr>=N || nc>= N) {
					continue;
				}
				if (visited[nr][nc]) {
					continue;
				}
				
				//이동할 위치의 값을 저장
				int target = ocean[nr][nc];
				
				//이동할 위치의 값이 내 사이즈보다 크면? -> 못넘어감..
				if (target > shark.size) {
					continue;
				}
				
				visited[nr][nc] = true;
				
				//이동할 위치의 값이 내 사이즈보다 작으면? -> 리스트에 넣읍시다..
				if (target != 0 && target<shark.size) {
					list.add(new Fish(nr, nc, curr.dist+1));
				}
				
				//이동할 위치의 값이 내 사이즈하고 같으면? -> 거리값만 추가하고 큐에 넣읍시다..
				q.offer(new Fish(nr, nc, curr.dist+1));
			}
		}
		
		if (list.isEmpty()) {
			return null;
		}
		
		//리스트 정렬을 통해서 거리->상->좌 순으로 정렬하자!
		Collections.sort(list);
		return list.get(0);
	}

}
