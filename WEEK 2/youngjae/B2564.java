package W2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2564 {
	static StringTokenizer st;
	static int w, h; // 가로와 너비

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken()); // 가로
		int h = Integer.parseInt(st.nextToken()); // 세로

		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken()); // 상점위치 배열 반복 몇번?
		int[] len = new int[Q + 1]; // 값들을 저장할 배열 > 맨 마지막은 시작위치

		for (int i = 0; i <= Q; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 방향 어디
			int distance = Integer.parseInt(st.nextToken());

			// 1. 북
			// 2. 남
			// 3. 동
			// 4. 서
			switch (num) { // 그냥 시계방향으로 돌기
			case 1:
				len[i] = distance; // 북서 방향기준 으로 북쪽은 그냥 distance만큼 감
				break;

			case 2:
				len[i] = w + h + (w - distance);
				break;

			case 3:
				len[i] = 2 * w + h + (h - distance);
				break;

			case 4:
				len[i] = distance + w;
				break;
			}

		} // 상점위치 배열 반복 몇번?

		// 최소거리 찾기
		int start = len[Q]; // 시작위치 > 가장 마지막 
		int result = 0; // 거리 합구하기

		for (int i = 0; i < Q; i++) { // 상점들 반복
			int target = len[i]; // 이제 start를 제외하고 하나씩
			int dis = Math.abs(start - target); // 처음 시계방향으로 빼본다
			int reversedis = 2 * w + 2 * h - dis; // 걍 반대계산 > 하나씩 거꾸로 가본다 > 이 수식은 몰라서 물어봣어요
			result += Math.min(dis, reversedis); // 시계방향과 반시계중  	

		}
		System.out.println(result);

	}
}
