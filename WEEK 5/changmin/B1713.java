package baek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/* B1713 후보 추천하기
 * https://www.acmicpc.net/problem/1713
 */
public class B1713 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); // 사진틀의 개수
		int T = Integer.parseInt(br.readLine()); // 추천 횟수

		int[] frames = new int[N]; // 사진틀
		int[] chuC = new int[N]; // 사진틀에 추천받은 횟
		int[] time = new int[N];

		int[] chu = new int[T]; // 추천 목록

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < T; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}

		for (int tc = 0; tc < T; tc++) {
			int num = chu[tc]; // 지금 추천한 학생

			boolean ok = false; // 사진틀에 넣었는지
			
			for (int i = 0; i < N; i++) { //사진틀에 들어가있는지
				if (frames[i] == num) {
					chuC[i]++;
					ok = true;
					break;
				}
			}
			
			if(!ok) { //위에서 못넣었으면 빈공간에 넣기
				for (int i = 0; i < N; i++) {
					if (frames[i] == 0) {
						frames[i] = num;
						chuC[i] = 1;
						time[i] = tc;
						ok = true;
						break;
					}
				}
			}

			if (!ok) { // 사진틀에 넣지 못한경우
				

				int byeIdx = 0; // 보내버릴 사진틀 인덱스
				int minC = Integer.MAX_VALUE; // 보내버릴 사진틀 추천수

				for (int i = 0; i < N; i++) {
					if (chuC[i] < minC || (chuC[i] == minC && time[i] < time[byeIdx])) { //추천수가 적거나 , 같을경우 오래된 순서대로
						byeIdx = i;
						minC = chuC[i];
					}
				}

				frames[byeIdx] = num;
				chuC[byeIdx] = 1;
				time[byeIdx] = tc;
			}
		}
		
		Arrays.sort(frames);
		
		for (int i = 0; i < N; i++) {
			if(frames[i] == 0) {
				continue;
			}
			bw.write(frames[i] + " ");
		}

		bw.flush();
		bw.close();
		br.close();

	}
}
