// 문제: 백준 7576번 
// 문제제목 : 토마토
// 링크: https://www.acmicpc.net/problem/7576
// 메모리 : 103364KB
// 시간: 560ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {
    static int N,M,day;
    static int[][] map;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static Queue<Point> q = new ArrayDeque<>();
    
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());//
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); //값 입력
                if(map[i][j] == 1) { //1이면 익은 토마토니까
                    q.add(new Point(i, j));//전부 큐에 넣고 시작작
                }
            }
        }
        
        bfs();

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {//0이 남아있으면 모든 토마토가 익지 못한 것
                    System.out.println(-1);
                    return;
                }
                result = Math.max(result, map[i][j]);//가장 큰 값이 최소 일수인데...
            }
        }

        System.out.println(result - 1); // 1부터 시작했으니 1 빼줌
    }//main

    
    
    private static void bfs() {
        while(!q.isEmpty()) {
            Point curr = q.poll();
            
            for(int d = 0; d < 4; d++) {//4방향 탐색
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                
                if(nr<0 || nc<0 || nr>=N || nc>=M) continue;//범위 벗어나면 쳐내
                if(map[nr][nc] == 0) {//
                    q.add(new Point(nr, nc));
                    map[nr][nc] = map[curr.r][curr.c] + 1;
                }
            }
        }
    }
    
    
    
    
}//class