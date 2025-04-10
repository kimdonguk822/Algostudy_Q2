package 문제풀이;
import java.util.*;

public class B31946_죽음의등굣길 {

    static int N, M, X;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 행 개수 
        M = sc.nextInt(); // 열 개수 

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = sc.nextInt();
        	
        X = sc.nextInt(); // 점프력 (1~10) 
        
        // 모든 입력 받기 완료 
        
        // bfs 결과가 true면 "ALIVE", false면 "DEAD" 출력 
        if (bfs()) System.out.println("ALIVE");
        else System.out.println("DEAD");
    }

    
    // 무사히 등교 가능 여부이므로 boolean으로 반환
    // 도달 가능 여부만 파악하면 됨 -> 너비우선탐색(bfs)으로 목적지 도달하면 바로 탐색 종료 
    // dfs는 스택오버플로우 가능성 
    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        
        // 처음 출발하는 색깔 체크 
        int color = map[0][0];
        // 처음 출발하는 칸의 좌표를 큐에 넣음 
        queue.offer(new int[]{0, 0});
        // 처음 출발 칸 방문 처리 
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            // 목적지(학교)에 도착하면 true 반환
            if (x == N - 1 && y == M - 1) return true;

            // 절댓값이 X이므로 가능한 i와 j의 범위는 -X이상 X이하 
            for (int i = -X; i <= X; i++) {
                for (int j = -X; j <= X; j++) {
                    int nx = x + i; // i = nx - x; 
                    int ny = y + j; // j = ny - y; 
                    
                    // 맨해튼 거리가 X 이하인 보도블록으로만 이동 가능
                    // = 맨해튼 거리가 X 초과이면 패스 
                    // if ( Math.abs(nx-x) + Math.abs(ny-y) > X) continue; 
                    if (Math.abs(i) + Math.abs(j) > X) continue; 
                    
                    // map 범위를 넘어가면 pass 
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    
                    // 이미 방문한 칸이면 pass
                    if (visited[nx][ny]) continue;
                    
                    // 출발 칸과 색이 다르면 pass 
                    if (map[nx][ny] != color) continue;

                    // 방문할 수 있는 칸이면, 방문 처리하고 큐에 넣기
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        // 목적지(학교)에 도착 못하면 false 반환 
        return false;
    }
}
