import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1347 {
    static int[] dx = {1, 0, -1, 0}; // 남, 서, 북, 동
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String command = br.readLine();

        // 미로 공간 확보 (시작은 중앙에서)
        boolean[][] map = new boolean[101][101];
        int x = 50, y = 50;
        int dir = 0; // 처음에는 남쪽

        map[x][y] = true;

        // 지도 범위 추적
        int minX = x, maxX = x, minY = y, maxY = y;

        for (char c : command.toCharArray()) {
            if (c == 'L') {
                dir = (dir + 3) % 4; // 왼쪽 회전
            } else if (c == 'R') {
                dir = (dir + 1) % 4; // 오른쪽 회전
            } else if (c == 'F') {
                x += dx[dir];
                y += dy[dir];
                map[x][y] = true;

                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(map[i][j] ? '.' : '#');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
