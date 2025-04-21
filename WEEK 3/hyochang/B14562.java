import java.util.Scanner;

/*
 *  문제: 14562 태권왕
 *  메모리: 118736 KB 시간: 252ms
 *  링크: https://www.acmicpc.net/problem/14562
 */
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class B14562 {

	static class scoreBoard {
		int ts, es, doubletap;

		public scoreBoard(int ts, int es, int doubletap) {
			this.ts = ts;
			this.es = es;
			this.doubletap = doubletap;
		}

		@Override
		public String toString() {
			return "scoreBoard [ts=" + ts + ", es=" + es + ", doubletap=" + doubletap + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for (int tc = 0; tc < t; tc++) {
			int taescore = sc.nextInt();
			int enemyscore = sc.nextInt();
			/*
			 * 연속 - 내 점수*2 상대+3 
			 * 단일 = +1 
			 * 큐에 넣고 같아 지면 종료 2차원 배열로 방문처리
			 */
			boolean[][] visited = new boolean[1001][1001];
			Queue<scoreBoard> que = new ArrayDeque<>();

			que.add(new scoreBoard(taescore, enemyscore, 0));
			visited[taescore][enemyscore] = true;

			int ans = 0;
			search: while (true) {
				int s = que.size();
				for (int i = 0; i < s; i++) {
					scoreBoard sb = que.poll();
					int ts = sb.ts;
					int es = sb.es;
//					System.out.println(sb);
					if (ts == es) {
						ans = sb.doubletap;
						break search;
					}
					int general = ts + 1;
					int doubleScore = ts * 2;
					int doubleEnemy = es + 3;

					if (doubleScore <= 1000 && doubleEnemy <= 1000 && !visited[general][es]) {

						que.add(new scoreBoard(general, es, sb.doubletap + 1));
						visited[general][es] = true;
					}
					if (doubleScore <= 1000 && doubleEnemy <= 1000 && !visited[doubleScore][doubleEnemy]) {

						que.add(new scoreBoard(doubleScore, doubleEnemy, sb.doubletap + 1));
						visited[doubleScore][doubleEnemy] = true;
					}

				}
			}
			System.out.println(ans);
		}
	}
}
