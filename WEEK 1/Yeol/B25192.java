// 문제: 백준 25192번 
// 문제제목 : Yes or yes
// 링크: https://www.acmicpc.net/problem/25195
// 메모리 : X
// 시간: X

// 인접 행렬로 하니까 네번째 예제에서 메모리 초과됨.
// 인접 리스트 사용해야 하는데 할 줄 몰라서 일단 제출합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B25195 {
    static boolean[][] adj;
    static boolean[] isFan;
    static boolean found = false;
    static int N,M,S,u,v,s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드 수
        M = Integer.parseInt(st.nextToken()); // 간선 수

        adj = new boolean[N + 1][N + 1];
        isFan = new boolean[N + 1];

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v= Integer.parseInt(st.nextToken());
            adj[u][v] = true;
        }

        // 매니저 수 및 매니저 리스트
        int S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            s = Integer.parseInt(st.nextToken());
            isFan[s] = true;
        }

        // DFS 시작
        dfs(1);

        System.out.println(found ? "yes" : "Yes");
    }

    static void dfs(int node) {
        if (isFan[node]) return;

        // 리프 노드인지 확인
        boolean isLeaf = true;
        for (int i = 1; i <= N; i++) {
            if (adj[node][i]) {
                isLeaf = false;
                break;
            }
        }

        if (isLeaf) {
            found = true;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (found) return; // 이미 찾았으면 탐색 종료
            if (adj[node][i]) {
                dfs(i);
            }
        }
    }
}//class