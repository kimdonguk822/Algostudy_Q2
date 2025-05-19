// 문제: 백준 1495번
// 문제제목 : 경사로
// 링크: https://www.acmicpc.net/problem/1495
// 메모리 : 
// 시간: 시간초과

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1495_fail {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N : 곡의 개수
		int S = Integer.parseInt(st.nextToken()); // S : 시작 볼륨
		int M = Integer.parseInt(st.nextToken()); // M : 최대 큰 볼륨값

		/*
		 * 리스트 만들어서 값 계속 갱신함. 단, 0보다 작아지거나 M보다 커지면 리스트에 넣지 말자..
		 * 
		 */

		List<Integer> list = new ArrayList<>();
		
		boolean canfinal = true;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (i == 0) { // 처음에는 S에서 값을 더하거나 빼야함
				if (S - input >= 0) {
					list.add(S - input);
				}
				if (S + input <= M) {
					list.add(S + input);
				}
			} else { // 그 뒤부터는 알아서 하세요..
				// 리스트의 사이즈만큼 돌면서 더하고 뺀값들을 넣어준다.
				int s = list.size();
				for (int j = 0; j < s; j++) {
					list.add(list.get(j) - input);
					list.add(list.get(j) + input);
				}
				// 원래 값들 제거하자
				for (int j = 0; j < s; j++) {
					list.remove(0);
				}
				// 값 벗어나는 것들도 제거하자
				for (int j = 0; j < s; j++) {
					// 신기술 접목
					list.removeIf(n -> n < 0 || n > M);
				}
			}
			
			if (list.isEmpty()) {
				canfinal = false;
				sb.append("-1");
				break;
			}
		}
		
		if (canfinal) {
			Collections.sort(list);
			sb.append(list.get(list.size()-1));
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
