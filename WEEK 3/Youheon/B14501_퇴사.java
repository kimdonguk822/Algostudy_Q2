import java.util.Scanner;

// 여러 가지 옵션 중 "최대 가치"가 되도록 선택하는 문제 => DP로 풀기  
public class B14501_퇴사 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 퇴사까지 남은 날 수

		// 1차원 배열 2개 만들기 (2차원 배열X)
		int[] days = new int[N + 1]; // 상담 소요일
		int[] earn = new int[N + 1]; // 상담 보수

		for (int i = 1; i <= N; i++) {
			days[i] = sc.nextInt();
			earn[i] = sc.nextInt();
		} // 입력 완료

		// 최대 수익을 저장할 배열 ( dp[i] = i일까지의 최대 수익 )
		// ★ N+1일에 끝나는 상담의 결과를 반영해야하므로 배열의 크기는 N+2로 만들기
		int[] dp = new int[N + 2];

		for (int i = 1; i <= N; i++) {

			// i일까지의 최대 수익 저장
			// 전날의 수익이 더 크다면 전날 값을 선택 
			dp[i] = Math.max(dp[i], dp[i - 1]);

			// i일에 상담을 시작하면, i + days[i] - 1 일 까지는 상담을 맡을 수 없음!
			int endDay = i + days[i] - 1;

			if (endDay <= N) { // 퇴사 전에 이번 상담이 끝난다면
				// endDay+1 일까지의 최대 수익은
				// 이번 상담을 안맡는 경우 => dp[endDay+1] : 전날까지 번 수익
				// 이번 상담을 맡는 경우 => dp[i]+earn[i] : 전날까지 번 수익 + 이번 상담 수익
				// 중에서 더 큰 값으로 저장
				dp[endDay + 1] = Math.max(dp[endDay + 1], dp[i] + earn[i]);
			}
		}

		int maxProfit = 0;
		for (int i = 1; i <= N + 1; i++) {
			maxProfit = Math.max(maxProfit, dp[i]);
		}

		System.out.println(maxProfit);

	}

}
