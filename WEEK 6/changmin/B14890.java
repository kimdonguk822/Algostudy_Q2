package baek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* B14890 경사로
 * https://www.acmicpc.net/problem/14890
 */
public class B14890 {

    static int N, L; //크기, 경사로 길이
    static int[][] map; //높이 정보
    static int count; //개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 여기까지 입력

        count = 0; //초기화

        for (int i = 0; i < N; i++) {
            if (checkLine(map[i])) { //가로는 그냥 넣고
            	count++;
            }
            
            int[] col = new int[N];
            for (int j = 0; j < N; j++) { //세로는 배열하나 만들어서 넣음 (or 가로세로 검사 메서드를 따로 만들어도 되긴함)
            	col[j] = map[j][i];
            }
            if (checkLine(col)) { //세로검사
            	count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean checkLine(int[] line) {
        boolean[] used = new boolean[N]; // 경사로 놓은 칸

        for (int i = 0; i < N - 1; i++) {
            int curr = line[i]; //지금
            int next = line[i + 1]; //다음

            if (curr == next) continue; // 높이 같음

            // 차이 2 이상 -> 불가능
            if (Math.abs(curr - next) > 1) return false;

            // 내리막
            if (curr - 1 == next) { //다음칸이 지금보다 낮은경우
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || line[j] != next || used[j]) { //범위 밖이거나, 높이가 다르거나, 이미 설치했으면 실패
                    	return false;
                    }
                    used[j] = true; //아니면 설치
                }
            }

            // 오르막
            else if (curr + 1 == next) { //다음칸이 높은경우
                for (int j = i; j > i - L; j--) { //뒤로 돌면서 확인
                    if (j < 0 || line[j] != curr || used[j]) { //밖으로 나가거나, 높이가 다르거나, 이미 설치했으면 실패
                    	return false; 
                    }
                    used[j] = true; //설치
                }
            }
        }

        return true;
    }
}