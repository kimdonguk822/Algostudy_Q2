// 문제: 백준 1389번 
// 문제제목 : 케빈 베이컨의 6단계 법칙
// 링크: https://www.acmicpc.net/problem/1389
// 메모리 : 14460KB
// 시간: 112ms
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


//케빈 베이컨
public class B1398 {
    static int N, M;
    static List<Integer>[] graph;

    
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 유저 수
        M = sc.nextInt(); // 친구 관계 수

        graph = new ArrayList[N + 1]; //그래프 만듦
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        } //그래프 만듦


        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }//친구 관계 입력받음

        int minSum = Integer.MAX_VALUE;// 비교를 위하여 MaxValue로 설정
        int answer = 0;//정답

        for (int i = 1; i <= N; i++) {
            int sum = bfs(i); // i번 사람의 케빈 베이컨 수 구함

            if (sum < minSum) {//수가 가장 작을 때를 구하기 위함
                minSum = sum;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    // i번 사람의 케빈 베이컨 수 구하기
    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1]; // 거리 저장

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }

        int sum = 0; //케빈 베이컨 수
        for (int i = 1; i <= N; i++) {
            sum += dist[i];
        }

        return sum;
    }
}
