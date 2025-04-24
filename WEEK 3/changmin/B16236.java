package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제: 16236 아기 상어
 * 링크: https://www.acmicpc.net/problem/16236
 */

public class B16236 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int babySharkSize; // 상어 사이즈
	static int x, y; // 현재 상어 좌표

	static int time, N; // 시간, 공간 크기
	static int[][] map; // 공간정보 (물고기 위치, 사이즈)
	static List<int[]> eatFish; // 먹을 수 있는 물고기의 거리 + 좌표 [거리, x, y] 순서

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


		babySharkSize = 2;
		time = 0;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 9) {
					x = i;
					y = j;
					map[i][j] = 0;
					continue;
				}
				map[i][j] = num;
			}
		}
		int eatcount = 0; //먹은 횟수
		while (true) {
			findFish();

			if (eatFish.size() == 0) { // 먹을 수 있는 물고기가 없다면 종료
				break;
			}

			selectFish();
			time += eatFish.get(0)[0];
			x = eatFish.get(0)[1];
			y = eatFish.get(0)[2];
			
			map[eatFish.get(0)[1]][eatFish.get(0)[2]] = 0;
			
			eatcount++;
			if(eatcount == babySharkSize) {
				babySharkSize++;
				eatcount = 0;
			}
		}
		bw.write(time + "");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void findFish() { // 먹을 수 있는 물고기 찾기
		eatFish = new ArrayList<>();

		visited = new boolean[N][N];
		Queue<int[]> que = new LinkedList<>();
		visited[x][y] = true;
		que.offer(new int[] { x, y, 0 });

		while (!que.isEmpty()) {
			int[] shark = que.poll();
			for (int i = 0; i < 4; i++) {
				int dx = shark[0] + dr[i];
				int dy = shark[1] + dc[i];
				int dis = shark[2];
				if ( dx >= 0 && dx < N && dy >= 0 && dy < N &&  !visited[dx][dy] && map[dx][dy] <= babySharkSize ) { // 같은 곳도 이동은 가능함
					if (map[dx][dy] != 0 && map[dx][dy] < babySharkSize) { // 0이 아니고 상어보다 작으면 밥임
						visited[dx][dy] = true;
						eatFish.add(new int[] { dis+1, dx, dy });
					} else {
						visited[dx][dy] = true;
						que.offer(new int[] {dx, dy, dis+1 });
					}
				}
			}
		}
	}

	public static void selectFish() {
		Collections.sort(eatFish, (a, b) -> {
			if (a[0] != b[0])
				return a[0] - b[0]; // 거리 오름차순
			if (a[1] != b[1])
				return a[1] - b[1]; // x좌표 오름차순 (위쪽 우선)
			return a[2] - b[2]; // y좌표 오름차순 (왼쪽 우선)
		});
	}

}

