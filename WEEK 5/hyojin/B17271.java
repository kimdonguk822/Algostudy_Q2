import java.util.Scanner;

//백준_17271
//리그 오브 레전설(Small)
public class practice04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 싸움 시간
		int M = sc.nextInt(); // B 스킬 시전 시간

		int c = 1000000007;

		int[] dp = new int[N + 1]; 
		dp[0] = 1; //아무것도 안했을때

		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1]; // A로 채움
			if (i >= M) {
				dp[i] = (dp[i] + dp[i - M]) % c; // B로 채움
			}
		}

		System.out.println(dp[N]);
	}
}