import java.io.*;
import java.util.*;

public class B25195 {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        List<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            indegree[v]++;
        }
        
        int S = Integer.parseInt(br.readLine()); // 팬클럽이 있는 정점의 수
        boolean[] isFan = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            isFan[Integer.parseInt(st.nextToken())] = true;
        }
        
        // 위상 정렬(탑로지컬 순서) 계산 – DAG이므로 가능
        List<Integer> topo = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            topo.add(cur);
            for (int next : adj[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        
        // 역탑로지컬 순서로 안전 경로 여부를 계산
        // safe[v] == true면, 정점 v에서 팬 없이 여행을 마칠 수 있는 경로가 존재함.
        boolean[] safe = new boolean[N + 1];
        Collections.reverse(topo);
        for (int v : topo) {
            if (isFan[v]) {
                safe[v] = false;
            } else if (adj[v].isEmpty()) {
                // 팬 없이 도착 가능한 말단 노드
                safe[v] = true;
            } else {
                boolean existsSafe = false;
                for (int nxt : adj[v]) {
                    if (safe[nxt]) {
                        existsSafe = true;
                        break;
                    }
                }
                safe[v] = existsSafe;
            }
        }
        
        // 정답: 정점 1에서 안전하게 여행을 마칠 수 있는 경로가 존재하면 "yes",
        // 그렇지 않으면 (어떤 선택을 하더라도 팬을 만나게 된다면) "Yes"
        System.out.println(safe[1] ? "yes" : "Yes");
    }
}
