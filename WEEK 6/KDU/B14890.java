// 문제: 백준 14890번
// 문제제목 : 경사로
// 링크: https://www.acmicpc.net/problem/14890
// 메모리 : 15076KB
// 시간: 124ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 경사로 {

	static int N, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/*
		 * 각 행/열을 하나씩 보면서 경사로 설치가 가능한지 체크 인접 칸끼리의 높이 차이를 보면서, 
		 * 차이가 0이면 -> 그대로 진행 
		 * 차이가 1이면 -> 높아지는 방향 -> 이전 L칸 체크 
		 * 차이가 -1이면 -> 낮아지는 방향 -> 이후 L칸 체크 
		 * 차이가 2 이상이면 -> 실패 
		 * 사용된 칸은 별도로 기록(경사로 중복 방지)
		 */

		// 행 검사(행 고정)
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			boolean[] used = new boolean[N]; //경사로를 설치했는지 여부 확인 배열
			boolean canPass = true; //길을 만들 수 있는지를 체크하는 boolean 배열
			for (int j = 0; j < N-1; j++) {
				int curr = map[i][j];
				int next = map[i][j+1];
				if (next == curr) { // 평지
					continue;
				}else if (next - curr == 1) { // 오르막
					for (int idx = j-(L-1); idx <= j; idx++) {
						if (idx < 0 || map[i][idx] != curr || used[idx]) {
							canPass = false;
							break;
						}
					}
					if (canPass) {
						for (int idx = j-(L-1); idx <= j; idx++) {
							used[idx] = true;
						}
					}
				}else if (next - curr == -1) { // 내리막
					for (int idx = j+1; idx <= j+L; idx++) {
						if (idx>=N || map[i][idx] != next || used[idx]) {
							canPass = false;
							break;
						}
					}
					if (canPass) {
						for (int idx = j+1; idx <= j+L; idx++) {
							used[idx] = true;
						}
					}
				}else { // 경사로 불가능
					canPass = false;
				}
				
				//조기 탈출!!
				if (!canPass) {
					break;
				}
			}
			if (canPass) {
				cnt++;
			}
		}
		
		// 열 검사 (열 고정)
		for (int i = 0; i < N; i++) {
		    boolean[] used = new boolean[N]; // 경사로 설치 여부
		    boolean canPass = true;

		    for (int j = 0; j < N - 1; j++) {
		        int curr = map[j][i];
		        int next = map[j + 1][i];

		        if (next == curr) { // 평지
		            continue;
		        } else if (next - curr == 1) { // 오르막
		            for (int idx = j - (L - 1); idx <= j; idx++) {
		                if (idx < 0 || map[idx][i] != curr || used[idx]) {
		                    canPass = false;
		                    break;
		                }
		            }
		            if (canPass) {
		            	for (int idx = j-(L-1); idx <= j; idx++) {
		            		used[idx] = true;
		            	}
					}
		        } else if (next - curr == -1) { // 내리막
		            for (int idx = j + 1; idx <= j + L; idx++) {
		                if (idx >= N || map[idx][i] != next || used[idx]) {
		                    canPass = false;
		                    break;
		                }
		            }
		            if (canPass) {
		                for (int idx = j + 1; idx <= j + L; idx++) {
		                    used[idx] = true;
		                }
		            }
		        } else { // 높이차 2 이상 → 불가능
		            canPass = false;
		        }

		        if (!canPass) break;
		    }

		    if (canPass) cnt++;
		}

		System.out.println(cnt);
	}//메인함수
	

}
