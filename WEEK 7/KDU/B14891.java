// 문제: 백준 13144번
// 문제제목 : 톱니바퀴
// 링크: https://www.acmicpc.net/problem/14891
// 메모리 : 14380KB
// 시간: 108ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 톱니바퀴를 큐에 넣고 원형큐처럼 사용할거임
 * 시계방향으로 돌리면 가장 오른쪽에 있는 값을 빼서 제일 왼쪽으로 넣어주고
 * 반시계방향으로 돌리면 가장 왼쪽에 있는 값을 빼서 제일 오른쪽으로 넣어줌
 * 방향을 결정하는건 왼쪽에 있는 톱니바퀴의 3번째 값과 오른쪽에 있는 톱니바퀴의 7번째 값을 비교해야 됨.
 * -> 둘이 값이 다르다면 왼쪽에 있는 톱니바퀴의 방향과 반대로 오른쪽 톱니바퀴가 돌아감
 * -> 클래스에는 톱니바퀴의 1~8번 극과 방향을 저장하자!
 * -> 최종적으로 점수를 체크하는 메서드를 구현해서 사용하자
 * 
 * 그렇다면 메서드는 총 몇개? 3개
 * 1. 톱니바퀴의 방향을 결정하는 메서드
 * 2. 톱니바퀴의 극을 돌리는 메서드
 * 3. 점수 계산 메서드 
 * */

public class 톱니바퀴 {

	static class Gear {

		List<Integer> pole;
		int dir;

		// 생성자를 통해 입력값들을 쪼개서 리스트에 넣어줌.
		public Gear(String input) {
			pole = new ArrayList<>();
			for (int i = 0; i < input.length(); i++) {
				pole.add(input.charAt(i) - '0');
			}
		}

		// 톱니바퀴 극을 돌리는 메서드
		public void rotate(int dir) {
			if (dir == 1) { // 시계 방향 -> 마지막 값을 처음으로 넣자!
				int poll = pole.remove(7);
				pole.add(0, poll);
			} else if (dir == -1) { // 반시계 방향 -> 처음 값을 마지막으로 넣자!
				int poll = pole.remove(0);
				pole.add(poll);
			}
		}

		public int getLeftPole() {
			return pole.get(6);
		}

		public int getRightPole() {
			return pole.get(2);
		}

		public int getTopPole() {
			return pole.get(0);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		/*
		 * 1번 톱니바퀴 : gear[0] 
         * 2번 톱니바퀴 : gear[1] 
         * 3번 톱니바퀴 : gear[2] 
         * 4번 톱니바퀴 : gear[3]
		 */

		Gear[] gear = new Gear[5];
		for (int i = 0; i < 4; i++) {
			gear[i + 1] = new Gear(br.readLine());
		}

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearnum = Integer.parseInt(st.nextToken());
			int geardir = Integer.parseInt(st.nextToken());

			rotateGear(gear, gearnum, geardir);
		}

		sb.append(getScore(gear));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static int getScore(Gear[] gear) {
		int score = 0;
		for (int i = 1; i <= 4; i++) {
			if (gear[i].getTopPole() == 1) { //S극이면 점수얻자
				if (i==1) {
					score += 1;
				}else if (i==2) {
					score += 2;
				}else if (i==3) {
					score += 4;
				}else if (i==4) {
					score += 8;
				}
			}
		}
		return score;
	}

	/*
	 * 방향을 결정하는건 왼쪽에 있는 톱니바퀴의 3번째 값과 오른쪽에 있는 톱니바퀴의 7번째 값을 비교해야 됨. 
     * -> 둘이 값이 다르다면 왼쪽에 있는 톱니바퀴의 방향과 반대로 오른쪽 톱니바퀴가 돌아감 
     * -> 클래스에는 톱니바퀴의 1~8번 극과 방향을 저장하자! 
     * -> 최종적으로 점수를 체크하는 메서드를 구현해서 사용하자
	 */
	static void rotateGear(Gear[] gear, int gearnum, int geardir) {
		int[] rotatedir = new int[5];
		rotatedir[gearnum] = geardir;

		// 왼쪽
		for (int i = gearnum; i > 1; i--) {
			if (gear[i].getLeftPole() != gear[i - 1].getRightPole()) {
				rotatedir[i - 1] = -rotatedir[i];
			} else
				break;
		}

		// 오른쪽
		for (int i = gearnum; i < 4; i++) {
			if (gear[i].getRightPole() != gear[i + 1].getLeftPole()) {
				rotatedir[i + 1] = -rotatedir[i];
			} else
				break;
		}

		// 돌리자!
		for (int i = 1; i <= 4; i++) {
			if (rotatedir[i] != 0) {
				gear[i].rotate(rotatedir[i]);
			}
		}
	}

}
