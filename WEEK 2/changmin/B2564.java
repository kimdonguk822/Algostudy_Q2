package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 *  문제: 2564 경비원
 *  링크: https://www.acmicpc.net/problem/2564
 */
public class B2564 {
	public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1) 입력: 가로(width), 세로(height)
        int width  = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        // 2) 입력: 가게 수 N
        int N = Integer.parseInt(br.readLine());
        int[][] stores = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stores[i][0] = Integer.parseInt(st.nextToken()); // 방향
            stores[i][1] = Integer.parseInt(st.nextToken()); // 거리
        }

        // 3) 입력: 경비원 위치
        st = new StringTokenizer(br.readLine());
        int guardDir  = Integer.parseInt(st.nextToken());
        int guardDist = Integer.parseInt(st.nextToken());

        // 전체 둘레
        int perimeter = 2 * (width + height);

        // 경비원 1차원 좌표
        int guardPos = toLinear(guardDir, guardDist, width, height);

        // 4) 모든 가게에 대해 최단 거리 합 계산
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int storePos = toLinear(stores[i][0], stores[i][1], width, height);
            int d = Math.abs(guardPos - storePos);
            answer += Math.min(d, perimeter - d);
        }

        // 5) 출력
        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    static int toLinear(int dir, int dist, int w, int h) {
        switch (dir) {
            case 1: 
                return dist;
            case 2: 
                return w + h + (w - dist);
            case 3: 
                return w + h + w + (h - dist);
            case 4: 
                return w + dist;
            default:
            	return 0;
        }
    }
}
