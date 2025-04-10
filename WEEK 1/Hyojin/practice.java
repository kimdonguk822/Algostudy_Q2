import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class practice {
	static int N;
	static int M;
	static boolean[][] visit;
	static int[][] arr;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int X;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 등굣길 행의 개수
		M = sc.nextInt(); // 등굣길 열의 갯수
		arr = new int[N][M];
		visit = new boolean[N][M];


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		X = sc.nextInt(); // 지훈이의 점프력

		// 0이면 빨간색 보도블록, 1인 경우 회색 보도블록

		int startc = arr[0][0]; // 등굣길 시작점
		int endc = arr[N - 1][M - 1]; // 학교

		// 시작점과 끝점이 다른 경우에는 무조건 dead 출력
		if (startc != endc) {
			System.out.println("DEAD");
			return;
		}

		// 그 외의 경우에는 bfs로 한다..~ 끝까지 가면 alive 아니면 dead
		// 근데 점프하는 경우는 어떻게 구현할지...?

		// 일단 갈 수 있는 곳들을 다 true로 바꾼다음, 거리가 되면?위치 옮기기??

		if (go(0, 0, startc)) {
			System.out.println("ALIVE");
		} else {
			System.out.println("DEAD");
		}

//		System.out.println(Arrays.deepToString(arr));

	}// main

	static boolean go(int sr, int sc, int color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { sr, sc });
		visit[sr][sc] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			if (r == N - 1 && c == M - 1) {
				return true;
			}

			for (int d = 0; d < 4; d++) {
				int nr, nc;

				if (arr[r][c] == color) {// 같은 색이면 1칸 이동
					nr = r + dr[d];
					nc = c + dc[d];
				} else { // 다른 색일때는 점프
					nr = r + dr[d] * X;
					nc = c + dr[d] * X;
				}

				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (visit[nr][nc])
					continue;

				visit[nr][nc] = true;
				q.add(new int[] { nr, nc });

			}

		}
		return false;
	}

}
