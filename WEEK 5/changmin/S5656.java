package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* S5656 벽돌깨기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 */

public class S5656 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, W, H; // 구슬을 쏜 횟수, 가로세로
	static int[][] map; // 벽돌 정보
	static int minCount; // 남은 벽돌의 개수

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			minCount = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 여기까지 입력
				// 백트래킹
			bt(0);
			bw.write("#" + tc + " " + minCount + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static void bt(int depth) { // 백트래킹
		int count = counting(); // 남은 벽돌의 수

		if (depth == N || count == 0) { // 구슬을 다 쏘거나, 그 전에 벽돌이 다 사라지거나
			minCount = Math.min(minCount, count);
			return;
		}

		int[][] tmp = copyArr(map); // 현재 상태 저
		for (int i = 0; i < W; i++) { // 0~W-1 까지 한번씩 다 쏴보면서 bt
			for (int j = 0; j < H; j++) {
				if (map[j][i] > 0) {
					boom(j, i); // 처음으로만나는 제일 위 블록 폭
					break;
				}
			}

			down();
			bt(depth + 1);
			map = copyArr(tmp);
		}

	}

	public static int counting() { // 남은 벽돌 세기
		int c = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0) {
					c++;
				}
			}
		}
		return c;
	}

	public static void boom(int x, int y) { // 연쇄폭파는 어떻게 할까요 -> bfs
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { x, y, map[x][y] }); // 좌표랑 범위랑 넣어줌

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cx = cur[0];
			int cy = cur[1];
			int range = cur[2];
			map[cx][cy] = 0;

			for (int i = 1; i < range; i++) {
				for (int d = 0; d < 4; d++) {
					int nx = cx + dr[d] * i;
					int ny = cy + dc[d] * i;

					if (nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] > 0) {
						if (map[nx][ny] > 1) {
							que.offer(new int[] { nx, ny, map[nx][ny] });
						} else {
							map[nx][ny] = 0;
						}
					}
				}
			}
		}

	}

	public static void down() { // 빈공간 있을때 내려버려야 함
		for(int j = 0; j < W; j++) {
	        int bottom = H - 1; //제일 바닥
	        for(int i = H - 1; i >= 0; i--) { //반대로 탐색
	            if(map[i][j] > 0) { //벽돌이면
	                int temp = map[i][j]; //임시 저장
	                map[i][j] = 0; //원래 있던자리 0
	                map[bottom][j] = temp; //바닥자리로 끌고오고
	                bottom--; //바닥을 한칸 올려줌
	            }
	        }
	    }
	}	

	public static int[][] copyArr(int[][] arr) { // 배열 복사
		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
}
