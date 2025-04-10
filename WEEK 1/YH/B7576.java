package 문제풀이;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 최단 시간을 구하는 문제이므로 너비우선탐색(bfs)로 접근 
public class B7576_토마토 {

	// 토마토의 위치를 담는 클래스
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt(); // 가로 칸 수
		int N = sc.nextInt(); // 세로 칸 수

		int[][] box = new int[N][M]; // 토마토 박스 배열
		int[][] dist = new int[N][M]; // 거리(날짜) 기록 & 방문 체크 배열

		// 인접 토마토 탐색 위한 델타 배열
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		// bfs 탐색을 위한 Queue
		Queue<Pair> queue = new LinkedList<>();

		// 1: 익은 토마토, 0: 안익은 토마토, -1: 토마토 없음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 토마토 박스에 토마토 정보 입력 받음
				box[i][j] = sc.nextInt();
				// 익은 토마토면 큐에 추가
				if (box[i][j] == 1)
					queue.add(new Pair(i, j));
				// 안익은 토마토면 거리를 -1로 설정
				if (box[i][j] == 0)
					dist[i][j] = -1;
			}
		}

		// bfs 시작
		while (!queue.isEmpty()) {
			// 큐에 미리 담아두었던 익은 토마토 부터 탐색 시작 (거리: 0)
			Pair curr = queue.poll();

			// 익은 토마토의 상하좌우에 위치한 토마토에 대하여
			for (int d = 0; d < 4; d++) {
				int nx = curr.x + dx[d];
				int ny = curr.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (dist[nx][ny] >= 0)
					continue;

				// 현재 토마토 기준으로 하루 후 익음 처리
				dist[nx][ny] = dist[curr.x][curr.y] + 1;
				// 그 토마토를 큐에 넣는다
				queue.offer(new Pair(nx, ny));
			}

		} // 토마토 익히기 끝

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// bfs 후에도 안 익은 토마토가 존재하면
				if (dist[i][j] == -1) {
					System.out.println(-1);
					return;
				}
				result = Math.max(result, dist[i][j]);
			}
		}
		// 모든 토마토가 익었으면 dist 배열에서 가장 큰 수 (모든 토마토가 익는데 걸린 시간) 출력
		System.out.println(result);

	}

}
