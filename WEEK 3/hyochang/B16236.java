import java.util.Scanner;

/*
 *  문제: 16236 아기 상어
 *  메모리: 26956KB 시간: 256ms
 *  링크: https://www.acmicpc.net/problem/16236
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16236 {
    // 아기상어 크기 2 스스로 보다 작으면 먹을 수 없음
    // 1마리면 그 물고기를 먹음
    // 2마리 이상이면 가까운 걸 먹으러감
    // 9 아기상어 1 2 3 4 5 6 물고기의 크기
    // 성장 조건은 사이즈만큼 먹으면

    // 크기 위치(i,j,s) 객체
    // 입력받을 때 2이상인 수를 체크하여 먹어야하는 물고기를 파악
    // 그러면 크기가 증가하면 먹을 수 있는 범위 바뀜
    
    static class fish {
        int y;
        int x;
        int size;

        public fish(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }

    // static ocean 배열, 아기상어, 먹을 수 있는 상어, 시간, 델타이동,먹은 수
    static int[][] ocean;
    static int n;

    static fish baby;
    static int eatcnt = 0;
    static int time = 0;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ocean = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ocean[i][j] = sc.nextInt();

                if (ocean[i][j] == 9) {
                    baby = new fish(i, j, 2);
                    ocean[i][j] = 0;
                }
            }
        }

        // bfs
        // 1. 탐색하는 메서드 -> 작은 물고기 위치, 그 중 최단 거리인 놈으로
        // 2. 최단거리를 찾을 떄 BFS
        // 3. 시간, 먹은 횟수 찾는 카운트

        // static ocean 배열, 아기상어, 먹을 수 있는 상어, 시간, 델타이동,먹은 수

        while (true) {
            fish target = search();

            if (target == null) {
                break;
            }
            time += BFS(target.y, target.x);
            baby.y = target.y;
            baby.x = target.x;
            ocean[target.y][target.x] = 0;
            eatcnt++;

            if (eatcnt == baby.size) {
                baby.size++;
                eatcnt = 0;
            }
        }
        System.out.println(time);
    }

    private static fish search() { // 목표 정하는 bfs
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        que.add(new int[]{baby.y, baby.x, 0}); // 위치, 거리
        visited[baby.y][baby.x] = true;

        fish find = null;
        int dist = Integer.MAX_VALUE;

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int y = temp[0], x = temp[1], d = temp[2];

            if (ocean[y][x] > 0 && ocean[y][x] < baby.size) {
                if (d < dist) {
                    dist = d;
                    find = new fish(y, x, ocean[y][x]);
                } else if (d == dist) {
                    if (y < find.y || (y == find.y && x < find.x)) {
                        find.y = y;
                        find.x = x;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                if (visited[ny][nx] || ocean[ny][nx] > baby.size) continue;

                visited[ny][nx] = true;
                que.add(new int[]{ny, nx, d + 1});
            }
        }
        return find;
    }

    private static int BFS(int ty, int tx) { // 목표까지 가는 bfs
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        que.add(new int[]{baby.y, baby.x, 0});
        visited[baby.y][baby.x] = true;

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int y = temp[0], x = temp[1], t = temp[2];

            if (y == ty && x == tx) return t;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < n && !visited[ny][nx] && baby.size >= ocean[ny][nx]) {
                    visited[ny][nx] = true;
                    que.add(new int[]{ny, nx, t + 1});
                }
            }
        }
        return 0;
    }
}
