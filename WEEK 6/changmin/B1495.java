package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* B1495 기타리스트
 * https://www.acmicpc.net/problem/1495
 */
public class B1495 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //곡의 개수
		int S = Integer.parseInt(st.nextToken()); //시작 볼륨
		int M = Integer.parseInt(st.nextToken()); //최대 볼륨
		
		int[] V = new int[N]; //바꿀 수 있는 볼륨의 리스트
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = -1; //마지막 곡의 볼륨 최댓값
		
		boolean[][] dp = new boolean[N+1][M+1]; // N번째 곡에서 가능했던 볼륨을 true
		
		dp[0][S] = true;
		
		for(int i = 0; i < N; i++) { //i번째
			
			for(int v = 0; v <= M; v++) { //v볼륨일때
				
				if(dp[i][v]) { //true면 +도 해보고 -도 해봄
					
					if(v + V[i] <= M) { //해당 볼륨에서 + 했을때 최대 볼륨 이하인가
						dp[i+1][v + V[i]] = true; 
					}
					if(v - V[i] >= 0) { // - 했을때 0 이상인가
						dp[i+1][v - V[i]] = true;
					}
				}
			}
		}
		
		for(int i = M; i >= 0; i--) { //볼륨 높은쪽부터 돌아보면서 true를 만나면 종료
			if(dp[N][i]) {
				max = i;
				break;
			}
		}
		
		System.out.println(max);
		
		
	}
}
