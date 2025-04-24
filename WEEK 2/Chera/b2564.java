/**
 * 문제 : 백준 2564번 경비원
 * 메모리 : 14172KB
 * 시간 : 100ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2564 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 가로 길이
		int M = Integer.parseInt(st.nextToken()); // 세로 길이

		int storeCnt = Integer.parseInt(br.readLine());

		int[][] store = new int[storeCnt][2];

		for (int i = 0; i < storeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int[] dong = new int[2];
		dong[0] = Integer.parseInt(st.nextToken());
		dong[1] = Integer.parseInt(st.nextToken());

		int[] cycle = { 1, 4, 2, 3 }; // 시계방향
		int[] oppocycle = { 1, 3, 2, 4 }; // 반시계방향
		int cycleIdx = -1; // 시계방향 배열에서 동근이의 인덱스
		int oppIdx = -1; // 반시계방향 배열에서 동근이의 인덱스
		for (int i = 0; i < 4; i++) {
			if (cycle[i] == dong[0])
				cycleIdx = i;
			if (oppocycle[i] == dong[0])
				oppIdx = i;
		}
		int sum = 0;
		for (int i = 0; i < storeCnt; i++) {
			if (cycle[(cycleIdx + 1) % 4] == store[i][0]) {
				if (dong[0] == 1)
					sum += (N - dong[1]) + store[i][1];
				else if (dong[0] == 4)
					sum += (M - dong[1]) + (N - store[i][1]);
				else if (dong[0] == 2)
					sum += dong[1] + (M - store[i][1]);
				else if (dong[0] == 3)
					sum += dong[1] + store[i][1];
				continue;
			}

			if (oppocycle[(oppIdx + 1) % 4] == store[i][0]) {
				if (dong[0] == 1)
					sum += dong[1] + store[i][1];
				else if (dong[0] == 3)
					sum += (M-dong[1]) + store[i][1];
				else if (dong[0] == 2)
					sum += (N - dong[1]) + (M - store[i][1]);
				else if (dong[0] == 4)
					sum += dong[1] + (N-store[i][1]);
				continue;
			}
			
			if(dong[0]==store[i][0]) {
				sum +=Math.abs(dong[1]-store[i][1]);
				continue;
			}

			int ans = 0;
			if (dong[0] == 1 || dong[0] == 2) {
				int dis1 = (N - dong[1]) + M + (N - store[i][1]);
				int dis2 = dong[1] + M + store[i][1];
				ans = Math.min(dis1, dis2);
				sum += ans;
			} else if (dong[0] == 3 || dong[0] == 4) {
				int dis1 = dong[1]+N+store[i][1];
				int dis2 = (M-dong[1])+N+(M-store[i][1]);
				ans = Math.min(dis1, dis2);
				sum += ans;
			} 
			
		}
		System.out.println(sum);
	}
}
