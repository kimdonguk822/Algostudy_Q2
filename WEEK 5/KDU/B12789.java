// 문제: 백준 17271번
// 문제제목 : 리그 오브 레전설(Small)
// 링크: https://www.acmicpc.net/problem/17271
// 메모리 : KB
// 시간: 시간초과...ㅠ

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int count = 0;
    static final int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(0);

        System.out.println(count);
    }

    // 현재까지 사용한 시간 t
    static void dfs(int t) {
        if (t > N) return;
        if (t == N) {
            count = (count + 1) % mod;
            return;
        }

        dfs(t + 1); // Q 스킬
        dfs(t + M); // W 스킬
    }
}
