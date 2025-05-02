package W3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1347_미로만들기 {
	static StringTokenizer st;
	static int[] dr = { 1, 0, -1, 0 }; // 남서북동
	static int[] dc = { 0, -1, 0, 1 }; // 남서북동
	static int dir; // 처음에는 남쪽 방향

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 맵 사이즈를 어떻게 만들까...
		// 최대 101칸 > 죄다 벽 만들어놓기
		char[][] map = new char[101][101];
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				map[i][j] = '#';
			}
		} // 맵 초기화
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		char[] move = new char[N];
		String cmd = br.readLine();
		for (int i = 0; i < N; i++) {
			move[i] = cmd.charAt(i);
		} // 입력완

		// 그럼 시작점은? 어딜 가든 0~50범위 사이인 중앙에서 시작
		dir = 0; // 초기는 남쪽 보고 있음
		int x = 50;
		int y = 50;
		map[x][y] = '.';

		int minX = x;
		int minY = y;
		int maxX = x;
		int maxY = y;

		// 점찍기
		for (int i = 0; i < N; i++) {
			if (move[i] == 'L') {
				dir = (dir + 3) % 4; // 서쪽은 +3이어ㅑ야함 남쪽이 기준이므로
			} else if (move[i] == 'R') {
				dir = (dir + 1) % 4;
			} else if (move[i] == 'F') { // 앞으로 가니까 좌표값을 초기화
				x += dr[dir];
				y += dc[dir];
				map[x][y] = '.';

				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
		}

		// 출력부분
		for (int i = minX; i <= maxX; i++) { 
		    for (int j = minY; j <= maxY; j++) {
		        System.out.print(map[i][j]);
		    }
		    System.out.println();
		}


	} // main

}
