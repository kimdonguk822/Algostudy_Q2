/*
 *  문제: 14501 퇴사
 *  메모리: 17732KB 시간: 172ms
 *  링크: https://www.acmicpc.net/problem/17732
 */
import java.util.Scanner;

//퇴사
public class B14501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 퇴사까지 남은 날 수
        int[] day = new int[N];   // 상담에 걸리는 날 수
        int[] earn = new int[N];  // 수익
        int[] dp = new int[N + 1]; // i일에 얻을 수 있는 최대 수익

        
        for (int i = 0; i < N; i++) {
            day[i] = sc.nextInt();
            earn[i] = sc.nextInt();
        }//입력 받음

        // dp
        for (int i = 0; i < N; i++) {
            // i일까지의 최대 수익
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // i일부터 상담을 시작, day[i]일 경과
            if (i + day[i] <= N) {
                // 상담을 했을 경우 수익 갱신
                dp[i + day[i]] = Math.max(dp[i + day[i]], dp[i] + earn[i]);
            }
        }

        System.out.println(dp[N]);
    }
}
