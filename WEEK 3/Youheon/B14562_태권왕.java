import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// bfs로 최소 거리(최단 횟수) 찾기 
// https://www.acmicpc.net/problem/14562
public class B14562_태권왕 {

	// 현재 상태를 전달할 클래스
	static class State {
		int s, t, cnt;

		public State(int s, int t, int cnt) {
			super();
			this.s = s;
			this.t = t;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt(); // 테스트 케이스 수

		for (int i = 0; i < tc; i++) {
			int S = sc.nextInt(); // 현재 태균이 점수
			int T = sc.nextInt(); // 현재 상대 점수

			// (S, T)를 큐에 넣고, S==T가 되는 순간의 depth 출력하기
			Queue<State> queue = new LinkedList<>();
			queue.add(new State(S, T, 0));

			int result = 0;

			while (!queue.isEmpty()) {
				State cur = queue.poll();

				// bfs는 레벨 순서로 탐색하기 떄문에 S==T 가 되는 순간 멈추면 가장 빠른 경로!
				if (cur.s == cur.t) {
					result = cur.cnt;
					break;
				}

				// 매 턴에 2가지 경우 존재
				// A킥: 현재 점수만큼 득점 + 상대 3점 득점
				// B킥: 1점 득점

				// A킥이 가능할 때 A킥 수행
				// 조건문은 필수가 아님(가지치기 위함)
				if (cur.s * 2 <= cur.t + 3) {
					queue.add(new State(cur.s * 2, cur.t + 3, cur.cnt + 1));
				}

				// 모든 경우에서 B킥 수행
				queue.add(new State(cur.s + 1, cur.t, cur.cnt + 1));

			}

			// S와 T가 같아지는 최소 연속 발차기 횟수 출력
			System.out.println(result);
		}

	}

}
