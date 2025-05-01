// 문제: 백준 1347번
// 문제제목 : 미로 만들기
// 링크: https://www.acmicpc.net/problem/1347
// 메모리 : 14264KB
// 시간: 108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1347 {
    static int dir = 1;  // 0: 동, 1: 남, 2: 서, 3: 북

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int cnt = Integer.parseInt(br.readLine());
        String input = br.readLine();

        // 미로 크기 동적 추적: 최소 직사각형 영역을 추적
        int nowX = 50;
        int nowY = 50;

        boolean[][] map = new boolean[101][101];
        map[nowX][nowY] = true;

        // 미로의 최소 직사각형 영역을 추적
        int minX = 50, minY = 50, maxX = 50, maxY = 50;

        // 명령에 따른 이동
        for (int i = 0; i < cnt; i++) {
            char c = input.charAt(i);

            switch (c) {
                case 'F':
                    nowX += dx[dir];  // X 좌표 업데이트
                    nowY += dy[dir];  // Y 좌표 업데이트
                    map[nowX][nowY] = true;  // 지나온 길을 true로 표시

                    // 미로의 최소 직사각형 영역 업데이트
                    minX = Math.min(minX, nowX);
                    minY = Math.min(minY, nowY);
                    maxX = Math.max(maxX, nowX);
                    maxY = Math.max(maxY, nowY);
                    break;

                case 'L':
                    // 왼쪽 회전 (90도 반시계방향)
                    if (dir == 0) {
                        dir = 3;
                    } else {
                        dir -= 1;
                    }
                    break;

                case 'R':
                    // 오른쪽 회전 (90도 시계방향)
                    if (dir == 3) {
                        dir = 0;
                    } else {
                        dir += 1;
                    }
                    break;
            }
        }

        // 미로 출력: 최소 직사각형 영역 내에 '.'을 표시
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (map[i][j]) {
                    sb.append('.');  // 지나온 길은 '.'
                } else {
                    sb.append('#');  // 벽은 '#'
                }
            }
            sb.append("\n");
        }

        // 한 번에 출력
        System.out.print(sb.toString());
        br.close();
    }
}
