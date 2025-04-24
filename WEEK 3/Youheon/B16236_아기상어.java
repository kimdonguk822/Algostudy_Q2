import java.util.*;

public class B16236_아기상어 {
    
	static int N;
    // map: 바다 상태 (0=빈칸, 1~6=물고기 크기, 9=아기상어 시작 위치)
    static int[][] map;
    static boolean[][] visited;
    static int sharkR, sharkC; 
    // 아기상어의 처음 크기 2, 먹은 물고기 수, 총 이동시간 
    static int sharkSize = 2, eatCount = 0, totalTime = 0;
    // 방향 배열 (위, 왼, 오, 아래)
    static int[] dr = {-1, 0, 0, 1}; 
    static int[] dc = {0, -1, 1, 0};

    // 먹을 수 있는 물고기를 저장할 클래스 
    static class Fish implements Comparable<Fish> {
        int r, c, dist;

        Fish(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }


        // 가장 가까운, 가장 위쪽, 가장 왼쪽 순으로 정렬
        // 정렬 기준: 거리 → 행 번호 → 열 번호 
        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {
                if (this.r == o.r) return Integer.compare(this.c, o.c);
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt(); // map 정보 입력받기 
                if (map[i][j] == 9) { // 처음 아기상어 위치 저장 
                    sharkR = i; 
                    sharkC = j;
                    map[i][j] = 0; // 아기상어 위치를 0으로 초기화
                }
            }
        }

        // 반복해서 먹을 수 있는 물고기를 찾아서 먹자.. 
        while (true) {
        	
        	// 타겟 물고기 찾기(현재 아기상어 위치에서 가장 우선순위가 높은 물고기 찾기)
            Fish target = bfs(); 
            // 타겟 물고기가 존재하지 않으면 종료 
            if (target == null) break;

            // 타겟 물고기가 있으면 
   
            // 아기상어가 타겟 물고기 위치로 이동
            sharkR = target.r;
            sharkC = target.c;
            totalTime += target.dist; // 이동시간 증가 
            eatCount++; // 먹은 물고기 수 증가 

            // 먹은 물고기 수가 상어 크기만큼이면 
            if (eatCount == sharkSize) {
                sharkSize++; // 상어 사이즈 증가 
                eatCount = 0; // 먹은 물고기 수를 0으로 초기화 
            }

            map[sharkR][sharkC] = 0; // 물고기 먹음 
        }

        System.out.println(totalTime);
    }

    
    // bfs로 먹을 수 있는 가장 가까운 물고기 찾기 
    static Fish bfs() {
    	
        visited = new boolean[N][N]; // 방문 배열 초기화 
        Queue<int[]> queue = new LinkedList<>();
        List<Fish> fishList = new ArrayList<>(); // 먹을 수 있는 물고기 리스트 

        // 시작 위치를 큐에 넣기 
        queue.add(new int[]{sharkR, sharkC, 0});
        visited[sharkR][sharkC] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            // 4방향 탐색 
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue; // 맵 범위 밖이면 패스 
                if (visited[nr][nc]) continue; // 이미 방문했으면 패스 
                if (map[nr][nc] > sharkSize) continue; // 아기상어보다 큰 물고기가 있으면 패스 

                visited[nr][nc] = true;
                
                // 이동한 자리에 아기상어보다 작은 물고기가 있으면 
                if (map[nr][nc] != 0 && map[nr][nc] < sharkSize) {
                	// 먹을 수 있는 물고기 리스트에 추가하기  
                    fishList.add(new Fish(nr, nc, dist + 1));
                }
                
                // 아기상어가 이동할 수 있는 곳 큐에 넣기 
                queue.add(new int[]{nr, nc, dist + 1});
            }
        }

        // 먹을 수 있는 물고기가 없으면 
        if (fishList.isEmpty()) return null;
        
        // 먹을 수 있는 물고기 우선순위에 따라 정렬 
        Collections.sort(fishList); 
        // 가장 우선순위 높은 물고기 반환
        return fishList.get(0);     
    }
}
