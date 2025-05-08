// 문제: 백준 17271번
// 문제제목 : 리그오브레전설
// 링크: https://www.acmicpc.net/problem/17271
// 메모리 : 14180KB
// 시간: 104ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17271_리그오브레전설 {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 총 시간
        int M = Integer.parseInt(input[1]); // B 스킬의 시전 시간

        int[] dp = new int[N + 1];
        dp[0] = 1; // 0초를 채우는 방법은 아무 것도 안 하는 1가지

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1]; // A 스킬만 사용
            if (i >= M) {
                dp[i] = (dp[i] + dp[i - M]) % MOD; // B 스킬 사용
            }
        }

        sb.append(dp[N]);
        System.out.println(sb);
    }
}
