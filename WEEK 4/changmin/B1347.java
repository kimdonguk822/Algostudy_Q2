package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*B1347 미로만들기
 * https://www.acmicpc.net/problem/1347
 */
public class B1347 {

	// 남(0), 서(1), 북(2), 동(3)
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, -1, 0, 1}; 

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 홍준이가 적은 내용의 길이
		String line = br.readLine(); // 입력받은 줄

		char[][] map = new char[101][101];

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				map[i][j] = '#';
			}
		}
		
		//중앙을 시작점으로
		int x = 50; 
		int y = 50;

		map[x][y] = '.';
		
		//100*100에서 실제 사용 범위
		int minX = x;
		int minY = y;
		int maxX = x;
		int maxY = y;
		
		//시작방향
		int dir = 0;
		
		//입력 내용에따라서 처리
		for (int i = 0; i < N; i++) {
			char c = line.charAt(i);

			if (c == 'L') {
				dir = (dir + 3) % 4; // 왼쪽 회전
				
			} else if (c == 'R') {
				dir = (dir + 1) % 4; // 오른쪽 회전
				
			} else if (c == 'F') { //이동
				x += dr[dir];
				y += dc[dir];
				map[x][y] = '.';
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
		}
		
		//출력
		for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
	}
}
