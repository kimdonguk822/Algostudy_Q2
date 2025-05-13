/**
 * 문제 : 백준 14890번 경사로
 * 메모리 : 14936KB
 * 시간 : 124ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		int L = Integer.parseInt(st.nextToken()); // 경사로의 길이

		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			
			// 행 기준 검사
			int r_h = arr[i][0]; // 행 기준 검사 시 기준 높이
			boolean rCheck = true; // 지나갈 수 있는 길인지 확인
			boolean[] slope = new boolean[N]; // 경사로가 이미 존재하는지 확인
			
			row : for (int j = 0; j < N ; j++) {
				int r = arr[i][j]; // 지금 갈 곳의 높이
				if (Math.abs(r - r_h) > 1) {
					rCheck = false;
				} else {
					if (Math.abs(r - r_h) == 1) { // 높이가 1차이 날때
						if (r - r_h < 0) { // 높은곳->낮은곳 (r_h(현재 높이)가 더 높음)
							for (int k = 0; k < L; k++) {
								if (j + k >= N || slope[j+k] || arr[i][j + k] != r) { // 경사로를 놓을 수 없음
									rCheck = false;
									break row;
								}
								slope[j+k] = true;
							}
							j += L-1; // 경사로의 길이만큼 점프
						}else if (r - r_h > 0) { // 낮은곳->높은곳 (r(가야할곳)이 더 높음)
							for (int k = 1; k <= L; k++) {
								if (j - k < 0 || slope[j-k] || arr[i][j - k] != r_h) { // 경사로를 놓을 수 없음
									rCheck = false;
									break row;
								}
								slope[j-k] = true;
							}
						}
						r_h = r;
					}
				}
			}
			// 열 기준 검사
			int c_h = arr[0][i]; // 열 기준 검사 시 기준 높이
			boolean cCheck = true; // 지나갈 수 있는 길인지 확인
			slope = new boolean[N]; // 경사로가 존재하는지 확인하는 배열
			
			col : for (int j = 0; j < N; j++) {
				int c = arr[j][i]; // 지금 갈 곳의 높이
				if (Math.abs(c - c_h) > 1) {
					cCheck = false;
				} else {
					if (Math.abs(c - c_h) == 1) { // 높이가 1차이 날때
						if (c - c_h < 0) { // 높은곳->낮은곳 (r_h(현재 높이)가 더 높음)
							for (int k = 0; k < L; k++) {
								if (j + k >= N || slope[j+k] || arr[j+k][i] != c) { // 경사로를 놓을 수 없음
									cCheck = false;
									break col;
								}
								slope[j+k] = true;
							}
							j += L-1; // 경사로의 길이만큼 건너띄기
						}else if (c - c_h > 0) { // 낮은곳->높은곳 (r(가야할곳)이 더 높음)
							for (int k = 1; k <= L; k++) {
								if ( j - k < 0 || slope[j-k] || arr[j-k][i] != c_h) { // 경사로를 놓을 수 없음
									cCheck = false;
									break col;
								}
								slope[j-k] = true;
							}
						}
						c_h = c;
					}
				}
			}
			if (rCheck)
				cnt++;
			if (cCheck)
				cnt++;
		}
	

		System.out.println(cnt);
	}

}