package baek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* B14503 로봇청소기
 * https://www.acmicpc.net/problem/14503
 */
public class B14503 {

	static int[] dr = { -1, 0, 1, 0 }; // 북동남서 순서로 통일
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M; // 방의 크기
	static int r, c; // 처음 좌표
	static int d; // 처음 바라보는 방향 ( 0북 1동 2남 3서 )
	static int clean; // 청소한 칸의 개수

	static int[][] room; // 방
	static boolean[][] isclean; //청소한 공간인지

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		room = new int[N][M];
		isclean = new boolean[N][M]; // 청소했는지

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		} //여기까지 입력

		clean = 0; //몇개의 칸을 청소했는지
		
		while (true) {

			if (!isclean[r][c]) { // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
				isclean[r][c] = true;
				clean++; // 이건 continue 안해도 괜찮음
			}

			int noclean = 0; //청소하지 않은 구역을 센건데 boolean으로 true false하는게 더 좋아보임
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] != 1) { // 방사이즈 내부이고, 벽이 아닌경우
					if (!isclean[nr][nc]) { // 청소하지 않았으면
						noclean++;
					}
				}
			}

			if (noclean == 0) { // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
				int nd = (d + 2) % 4; // 뒷방향
				int nr = r + dr[nd];
				int nc = c + dc[nd];

				if (room[nr][nc] == 1 || nr < 0 || nc < 0 || nr >= N || nc >= M) {
					break; // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				}

				// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
				r = nr;
				c = nc;
				continue;

			} else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
				
				boolean ismove = false; //움직였는가
				
				for (int i = 0; i < 4; i++) { //90 * 4 해봐야함
					
					d = (d + 3) % 4; // 반시계 방향으로 90도 회전
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] != 1) {
						if (!isclean[nr][nc]) { // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
							r = nr;
							c = nc;
							ismove = true;
							break;
						}
					}
				}
				
				if(!ismove) { //만약 4방향 돌면서 못움직였으면 끝 
					break;
				}
			}
		}

		bw.write(clean + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
