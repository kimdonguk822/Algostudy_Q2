// 문제: 백준 13144번
// 문제제목 : List of Unique Numbers
// 링크: https://www.acmicpc.net/problem/13144
// 메모리 : 36356KB
// 시간: 344ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B13144 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		
		int N = Integer.parseInt(br.readLine());

		// 수열 저장할 배열 선언
		int[] arr = new int[N];

		// 현재 투 포인터 구간 내 원소들의 중복 여부 확인용 Set
		Set<Integer> set = new HashSet<>();

		// 수열 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투 포인터의 시작과 끝
		int start = 0;
		int end = 0;

		// 조건을 만족하는 모든 부분 수열의 개수를 저장할 변수 (int 범위를 넘을 수 있으므로 long 사용)
		long count = 0;

		// end 포인터가 배열 끝에 도달할 때까지 반복
		while (end < N) {

			// 현재 end가 가리키는 원소가 set에 이미 있다면 -> 중복이므로 start를 이동시키며 중복 제거
			while (set.contains(arr[end])) {
				set.remove(arr[start]); // start가 가리키는 원소 제거
				start++; // start 포인터 오른쪽으로 한 칸 이동
			}

			// 중복이 없으므로 set에 현재 원소 추가
			set.add(arr[end]);

			// 현재 구간 [start, end]의 모든 부분 수열은 중복 없는 수열이므로 개수를 더해줌
			// end - start + 1개의 부분 수열이 가능
			count += end - start + 1;

			// end 포인터 한 칸 이동
			end++;
		}

		
		sb.append(count);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
