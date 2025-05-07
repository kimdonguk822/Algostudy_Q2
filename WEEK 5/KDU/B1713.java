// 문제: 백준 1713번
// 문제제목 : 후보 추천하기
// 링크: https://www.acmicpc.net/problem/1713
// 메모리 : 14616KB
// 시간: 120ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Student {
		int num; // 학생 번호
		int cnt; // 추천 수
		int time; // 사진틀에 게시된 시간

		Student(int num, int cnt, int time) {
			this.num = num;
			this.cnt = cnt;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 사진틀 수
		int m = Integer.parseInt(br.readLine()); // 총 추천 수

		//입력값 저장 완료 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] input = new int[m];
		for (int i = 0; i < m; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		//리스트에 저장하자...
		List<Student> list = new ArrayList<>();

		//반복문 돌면서 학생 하나씩 빼서 체크 
		for (int i = 0; i < m; i++) {
			int cur = input[i];
			boolean exists = false;

			// 이미 게시된 학생이면 추천 수만 증가
			for (Student s : list) {
				if (s.num == cur) {
					s.cnt++;
					exists = true;
					break;
				}
			}
			
			// 사진 틀에 없다면?
			if (!exists) {
				// 사진틀이 꽉 찼다면 하나 제거
				if (list.size() == n) {
					// 추천 수가 가장 적고, 그중 가장 오래된 놈을 찾기 위한 정렬 
					Collections.sort(list, (a, b) -> {
						if (a.cnt == b.cnt)
							return a.time - b.time;
						return a.cnt - b.cnt;
					});
					list.remove(0); // 첫번째 놈 제거 
				}

				// 새로 등록
				list.add(new Student(cur, 1, i));
			}
		}

		// 번호 오름차순 정렬 후 출력
		list.sort(Comparator.comparingInt(a -> a.num));
		for (Student s : list) {
			sb.append(s.num).append(" ");
		}

		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
