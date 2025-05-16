package W6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class B1495 {
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 공연에서 연주할 곡의 개수
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [] volume = new int [N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			volume[i] = Integer.parseInt(st.nextToken());
		} // 입력완료
		
		boolean[] dp = new boolean[M + 1]; // 최대값까지 포함해야 하므로 M+1
        dp[S] = true; // 시작값은 true
		
		for (int i = 0; i < N; i++) {
			boolean[] next = new boolean[M+1]; // 즉각 수정을 가할경우, > 중간에 볼륨조절 불가한 경우는 -1이 나와야하므로 필요 없으면 예제 2번이 20이 나와버림
			
			for (int j = 0; j <= M; j++) {
				if(dp[j]) { // 시작점 찾아 최초에
					if(j + volume[i] <= M) {
						next[j + volume[i]] = true;
					}
					
					if(j - volume[i] >= 0) {
						next[j - volume[i]] = true;
					}
				}
			}
			dp = next;// next가 true로 바뀐 순간을 dp에 저장
		} // 전체 반복문 돌기
		
		int result = -1;
		for (int i = M; i >= 0; i++) {
			if(dp[i]) {
				result = i;
				break;
			}
		} // 최대값이니까 뒤에서부터
		System.out.println(result);
		
	}
}
