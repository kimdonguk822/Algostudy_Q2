import java.util.Scanner;

/*
 *  문제: 14501 퇴사
 *  메모리: 17732KB 시간: 172ms
 *  링크: https://www.acmicpc.net/problem/17732
 */
import java.util.Scanner;

public class B14501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];
        int[] dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for (int i = n; i >= 1; i--) {
            if (i + t[i] <= n + 1) {
                dp[i] = Math.max(p[i] + dp[i + t[i]], dp[i + 1]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
    }
}
