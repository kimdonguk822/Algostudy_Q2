// 문제: 백준 14562번번
// 문제제목 : 태권왕
// 링크: https://www.acmicpc.net/problem/14562
// 메모리 : 14452KB
// 시간: 112ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int score1, score2;

	static class Info {
		int score1, score2, kick;

		public Info(int score1, int score2, int kick) {
			this.score1 = score1;
			this.score2 = score2;
			this.kick = kick;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			score1 = Integer.parseInt(st.nextToken());
			score2 = Integer.parseInt(st.nextToken());

			Info person = new Info(score1, score2, 0);

			int res = bfs(person);

			sb.append(res).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	// 발차기 종류 A,B를 한 Info 객체를 전부 queue에 집어넣고 돌리면서
	// 점수가 같아지면 return!!!!
	static int bfs(Info person) {
		// Info 객체를 저장한 배열을 큐에 넣음
		Queue<Info> q = new ArrayDeque<>();
		q.add(person);

		while (!q.isEmpty()) {
			Info curr = q.poll();

			if (curr.score1 == curr.score2) {
				return curr.kick;
			}

			if (curr.score1 < curr.score2) {
				q.add(new Info(curr.score1 * 2, curr.score2 + 3, curr.kick + 1));
				q.add(new Info(curr.score1 + 1, curr.score2, curr.kick + 1));
			}

		}

		return 0;

	}
}
