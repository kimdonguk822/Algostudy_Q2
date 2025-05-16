// 문제: 백준 14503번
// 문제제목 : 로봇 청소기
// 링크: https://www.acmicpc.net/problem/14503
// 메모리 : 14420KB
// 시간: 112ms

/* 셋팅 필요한 것들
 * 1. 델타탐색 배열
 * 2. 로봇청소기 클래스(x좌표,y좌표,방향)
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14503 {

	static int N, M;
	static int[][] room;
	static int count;

	static class Robot {

		int r, c, dir;

		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	// 북 동 남 서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		room = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int robot_r = Integer.parseInt(st.nextToken());
		int robot_c = Integer.parseInt(st.nextToken());
		int robot_dir = Integer.parseInt(st.nextToken());

		Robot robot = new Robot(robot_r, robot_c, robot_dir);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = 0;
		cleaning(robot.r, robot.c, robot.dir);
		System.out.println(count);
	}

	
	/* 현재 칸이 청소가 안되어 있다? -> 값을 2로 바꾸자(청소 o)
	 * 주변 4칸 중에서 청소가 안되어 있는 빈칸이 있는 경우(room[nr][nc]==0)
	 * -> robot.dir 바꾸고, 바꾼 dir으로 이동한 room[nr][nc]가 0이면 이동하자
	 * 주변 4칸 중에서 청소가 안되어 있는 빈칸이 없는 경우(room[nr][nc]!=0)
	 * -> dir을 바꾸지는 않고 후진만 해야됨
	 * -> 후진 했을 떄 room[nr][nc] == 1이면 작동 멈추자(return)
	 * 
	 * 
	 * */
	public static void cleaning(int r, int c, int dir) {
		// 현재 있는 방이 청소가 안되어 있으면 청소 진행
		if (room[r][c] == 0) {
			room[r][c] = 2;
			count++;
		}
		//현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는 경우(room[nr][nc]==0)
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (room[nr][nc] == 0) { //청소 안되어 있으면 청소하자(재귀)
				cleaning(nr, nc, dir);
				return;
			}
		}
		int back = (dir + 2) % 4;
		int br = r + dr[back];
		int bc = c + dc[back];
		
		if (room[br][bc] == 1) { //벽이면 끝내자!
			return;
		}else { //방향 유지한채 후진해야됨!
			cleaning(br, bc, dir);
		}
		
	}

}
