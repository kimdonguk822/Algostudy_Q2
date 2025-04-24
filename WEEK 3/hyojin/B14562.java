/*
 *  문제: 14562 태권왕
 *  메모리: 118736 KB 시간: 252ms
 *  링크: https://www.acmicpc.net/problem/14562
 */
import java.util.Scanner;

//태권왕
//dfs
public class B14562 {
    static int minCnt; // 최소 횟수 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt(); //테스트 케이스

      for(int i = 0; i < C ; i++) {
            int S = sc.nextInt();
            int T = sc.nextInt();

            minCnt = Integer.MAX_VALUE; //비교를 위해 최댓값으로 설정
            dfs(S, T, 0); //dfs 시행
            System.out.println(minCnt);
        }
    }

    static void dfs(int s, int t, int cnt) {
        if (s > t) return; // 더 이상 하지 않아도 됨
        if (s == t) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }

        dfs(s * 2, t + 3, cnt + 1); // A의 경우
        dfs(s + 1, t, cnt + 1);     // B의 경우
    }
}
