package W6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 청소하는 영역 개수 구하기
 * 
 * 
 * 
 * */

public class B14503 {
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 가로
		int M = Integer.parseInt(st.nextToken()); // 세로

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 첫번째 x좌표
		int c = Integer.parseInt(st.nextToken()); // 첫번째 y좌표
		// d가 0 : 북, 1 : 동, 2 : 남, 3: 서
		int d = Integer.parseInt(st.nextToken()); // 청소기가 최초에 바라보는 방향

		int map[][] = new int[N][M];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		} // 입력완료

		// 델타 탐색
		int[] dr = { -1, 0, 1, 0 }; // 북 동 남 서
		int[] dc = { 0, 1, 0, -1 }; // 북동남서

		int cleaned = 0; // 결과를 저장할 변수

		// 시작 좌표를 중심으로
		while (true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸 청소
			if (map[r][c] == 0) {
				map[r][c] = 5; // 청소완료를 5로 표시 >
				cleaned++;
			}
			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
			boolean notClean = false;
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				// 내 안에 있다면
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
					notClean = true;
					break;
				}

			}

			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있다면,
			if (notClean) { // 청소할 곳이 있으면
				d = (d + 3) % 4; // 반시계 90도 회전

				int nr = r + dr[d];
				int nc = c + dc[d];

				// 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한 칸 전진
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
					r = nr;
					c = nc; // 그 값 저장

					continue;
				}

			} else { // 청소할 칸이 없는 경우
				// 후진
				int back = (d + 2) % 4;
				int bx = r + dr[back];
				int by = c + dc[back];

				// 후진 가능
				if (bx >= 0 && by >= 0 && bx < N && by < M && map[bx][by] != 1) {
					r = bx;
					c = by;
				} else {
					// 후진 불가
					break;
				}

			}

		}

		System.out.println(cleaned);
		
	} // main

}
