import java.util.ArrayList;
import java.util.Scanner;

// 사각 지대의 최소 크기 구하기 
// dfs + 백트래킹 
public class B15683_감시 {

	static int N, M, min;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayList<Cctv> cctvList; //cctv를 담을 리스트 

	// 각 번호별 cctv가 비출 수 있는 방향을 담은 3차원 배열 
	// 1번 cctv: 한 쪽 방향 (4가지)
	// 2번 cctv: 양 쪽 방향 (2가지)
	// 3번 cctv: 두 방향 (4가지)
	// 4번 cctv: 세 방향 (4가지)
	// 5번 cctv: 모든 방향 (1가지)
	static int[][][] directions = { 
			{}, // 1번 cctv부터 있으므로 0번 만들어서 비워주기 
			{ { 0 }, { 1 }, { 2 }, { 3 } }, // 1번 cctv
			{ { 0, 2 }, { 1, 3 } }, // 2번 cctv
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, // 3번 cctv
			{ { 0, 1, 2 }, { 1, 2, 3 }, { 0, 2, 3 }, { 0, 1, 3 } }, // 4번 cctv
			{ { 0, 1, 2, 3 } } // 5번 cctv
	};

	// cctv 클래스 (행 위치, 열 위치, cctv번호)
	static class Cctv {
		int r, c, type;

		public Cctv(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 사무실 세로 크기
		M = sc.nextInt(); // 사무실 가로 크기

		map = new int[N][M];

		cctvList = new ArrayList<>();

		// 0 빈칸, 6 벽, 1~5 cctv (cctv는 최대 8개)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				// 입력 받으면서 1~5번 cctv를 cctvList에 추가 
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new Cctv(i, j, map[i][j]));
				}
			}
		}

		min = Integer.MAX_VALUE;

		// cctvList를 순회하면서 각 cctv가 가질 수 있는 방향을 전부 탐색
		// cctvList의 0번 cctv부터 출발!  
		dfs(0, map);

		System.out.println(min);
	}

	// 모든 cctv 방향 조합을 탐색하면서 감시 가능한 영역을 표시하는 메서드 
	// 사각지대의 개수를 세서 최솟값 갱신하기
	// depth: cctvList에서 몇 번째 cctv를 보고 있는지
	// officeMap: 현재 상태 사무실
	private static void dfs(int depth, int[][] officeMap) {

		// 마지막 cctv까지 탐색했으면 
		if (depth == cctvList.size()) {
			// 사각지대 수를 세서 최솟값 업데이트 
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (officeMap[i][j] == 0) {
						cnt++;
					}
				}
			}

			min = Math.min(min, cnt);
			return;
		}

		// 현재 탐색할 cctv 뽑아서 
		Cctv cctv = cctvList.get(depth);
		// 그 cctv가 비출 방향 배열 만들기 
		int[][] dirSet = directions[cctv.type];

		for (int[] dirs : dirSet) {
			// 현재 map 복사해놓고 
			int[][] tmp = copyMap(officeMap);

			// 현재 cctv가 돌아갈 방향별로 
			for (int d : dirs) {
				// watch 메서드로 탐색 진행 
				watch(tmp, cctv.r, cctv.c, d);
			}
			
			// 현재 cctv 방향에서 다봤으면 다음 cctv로 탐색 진행 
			dfs(depth + 1, tmp);

		}

	}

	// 현재 상태의 map을 복사하는 메서드
	static int[][] copyMap(int[][] src) {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = src[i][j];
			}
		}
		return result;
	}

	// 현재 상태의 map에서 cctv ON하는 메서드
	static void watch(int[][] map, int r, int c, int dir) {
		while (true) {
			r += dr[dir];
			c += dc[dir];

			// map 범위를 벗어나거나 벽을 만나면 끝 
			if (r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 6)
				break;
			// cctv가 지나간(비춘) 자리는 7로 바꾼다
			if (map[r][c] == 0) map[r][c] = 7;

		}

	}

}
