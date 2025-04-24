package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 완탐 + DP인
 * 문제: 14501 퇴사
 * 링크: https://www.acmicpc.net/problem/17732
 */

public class B14501 {
	
	static class Quest {
		int time, money;

		public Quest(int time, int money) {
			this.time = time;
			this.money = money;
		}
		
	}
	
	static int N;
	static int[] dp;
	static Quest[] q;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); //N+1일에 퇴사
		
		dp = new int[N+2]; //N+1일에퇴사할거니가
		
		q = new Quest[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			q[i] = new Quest(time,money);
		}
		
		for(int i = 0; i < N; i++) {
			
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			
			int end = i + q[i].time;
			if(end <= N) {
				dp[end] = Math.max(dp[end], dp[i]+q[i].money);
			}
		}
		
		int max = 0;
		for(int i = 0; i <= N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}
}
