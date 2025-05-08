import java.util.Arrays;
import java.util.Scanner;

//백준_1347
//미로 만들기
public class practice01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 움직임 수
		String move = sc.next(); // 움직임을 기록한 문자열

		// 시계 방향
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int dir = 2; // 남쪽을 보며 서 있음

		// 미로 지도
		char[][] map = new char[101][101];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = '#';
			}
		} // 다 채움

		// 중심을 정중앙으로 설정
		int x = 50, y = 50;
		map[x][y] = '.'; // 시작 위치

		// 지도 범위 만들기
		int minX = x, maxX = x, minY = y, maxY = y;

		for (int i = 0; i < move.length(); i++) {
			char moves = move.charAt(i);
			if (moves == 'F') {
				x += dx[dir];
				y += dy[dir];
				map[x][y] = '.';

				// 범위 갱신
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			} else if (moves == 'L') {
				dir = (dir + 3) % 4;
			} else if (moves == 'R') {
				dir = (dir + 1) % 4;
			}
		}

		// 출력
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
