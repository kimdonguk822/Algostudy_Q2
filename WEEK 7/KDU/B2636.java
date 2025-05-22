// 문제: 백준 2636번
// 문제제목 : 치즈
// 링크: https://www.acmicpc.net/problem/2636
// 메모리 : 16464KB
// 시간: 140ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B13144 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		
		int N = Integer.parseInt(br.readLine());

		// 수열 저장할 배열 선언
		int[] arr = new int[N];

		// 현재 투 포인터 구간 내 원소들의 중복 여부 확인용 Set
		Set<Integer> set = new HashSet<>();

		// 수열 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투 포인터의 시작과 끝
		int start = 0;
		int end = 0;

		// 조건을 만족하는 모든 부분 수열의 개수를 저장할 변수 (int 범위를 넘을 수 있으므로 long 사용)
		long count = 0;

		// end 포인터가 배열 끝에 도달할 때까지 반복
		while (end < N) {

			// 현재 end가 가리키는 원소가 set에 이미 있다면 -> 중복이므로 start를 이동시키며 중복 제거
			while (set.contains(arr[end])) {
				set.remove(arr[start]); // start가 가리키는 원소 제거
				start++; // start 포인터 오른쪽으로 한 칸 이동
			}

			// 중복이 없으므로 set에 현재 원소 추가
			set.add(arr[end]);

			// 현재 구간 [start, end]의 모든 부분 수열은 중복 없는 수열이므로 개수를 더해줌
			// end - start + 1개의 부분 수열이 가능
			count += end - start + 1;

			// end 포인터 한 칸 이동
			end++;
		}

		
		sb.append(count);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
/* int 배열로 치즈 배열을 만들어서 관리
 * 치즈 녹는걸 어떻게 찾지..? -> 델타탐색으로 확인했을때 치즈가 없으면 되지 않나..?
 * 근데 구멍이 뚫려있는 경우도 있는데..? -> \
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈 {

	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static List<Cheese> meltList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Cheese {
		int r, c;

		public Cheese(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로 길이
		C = Integer.parseInt(st.nextToken()); // 가로 길이

		map = new int[R][C];

		// 치즈 입력값 입력 완료
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		int count = 0;

		/* 공기 좌표인 (0,0)에서 계속해서 bfs를 돌려주면서
		 * 반복문 한번당 하는 행위
		 * 1. bfs 메서드 진행(치즈를 녹일 좌표를 리스트에 저장함)
		 * 2. 저장한 좌표를 모두 공기로 바꿔줌
		 * 3. 시간을 증가시키고 리스트의 사이즈를 체크해줌
		 * -> 이 작업을 통해 치즈가 녹는 시간, 치즈가 모두 녹기 바로 직전의 치즈 칸 개수를
		 * 체크할 수 있음.
		 * 
		 * 
		 * */
		while (true) {
			visited = new boolean[R][C];

			meltList = new ArrayList<>();

			bfs(0, 0);

			if (meltList.isEmpty()) {
				break;
			}

			for (Cheese cheese : meltList) {
				map[cheese.r][cheese.c] = 0;
			}

			time++;
			count = meltList.size();
		}

		sb.append(time).append("\n").append(count);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	
	/* 공기를 기준으로 bfs를 진행시켜 가장 겉에 있는 치즈를 만나면
	 * 치즈 좌표를 저장하는 meltList에 좌표를 저장해놓자
	 * -> 좌표를 따로 저장하지 않고 바로 치즈를 공기로 바꾸게 되면?
	 * -> 이후 다른 경로에서 똑같은 칸을 다시 탐색하거나, 잘못된 시간에 잘못된 치즈가 녹게됨
	 * (치즈를 녹이는 타이밍이 어긋날 수 있음)
	 * 
	 * 그래서 공기랑 접촉한 치즈의 좌표를 meltList에 저장하고, BFS가 끝나면 한번에 그 좌표를
	 * 공기로 바꾸는 작업이 필요함
	 * */
	static void bfs(int r, int c) {
		Queue<Cheese> q = new LinkedList<>();

		q.add(new Cheese(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Cheese curr = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc]) {
					continue;
				}

				// 핵심코드
				// 공기면 BFS 계속 돌리자
				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.add(new Cheese(nr, nc));
				}
				// 치즈면 meltList에 좌표를 저장해놓자
				else if (map[nr][nc] == 1) {
					visited[nr][nc] = true;
					meltList.add(new Cheese(nr, nc));
				}
			}
		}

	}

}
