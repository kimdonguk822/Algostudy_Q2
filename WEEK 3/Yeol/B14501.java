// 문제: 백준 14501번
// 문제제목 : 퇴사
// 링크: https://www.acmicpc.net/problem/14501
// 메모리 : 14248KB
// 시간: 104ms



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501_퇴사 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 남은 일수
        int[] T = new int[N + 1]; // 상담에 걸리는 시간(일수)
        int[] P = new int[N + 1]; // 받을 수 있는 금액
        int[] dp = new int[N + 2]; // N+1일까지 계산 가능하게 함

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            // i일까지의 최대 수익을 갱신
            // 상담을 하지 않고 넘어가는 경우, 이전까지의 최대 수익(dp[i-1])을 현재 날짜까지 이어줌
            dp[i] = Math.max(dp[i], dp[i - 1]);

            // i일에 상담을 했을 경우, 그 상담이 끝나는 날(i + T[i]) 이후의 수익을 갱신
            // 퇴사일(N)을 넘지 않는다면 해당 날짜까지 도달 가능하므로 고려
            if (i + T[i] <= N + 1) {
                // 기존의 i + T[i]일까지의 최대 수익과
                // i일에 상담을 진행한 경우의 수익(dp[i] + P[i]) 중 더 큰 값으로 갱신
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }


        // 마지막 날까지의 최대 이익 출력
        System.out.println(Math.max(dp[N], dp[N + 1]));
    }
}
