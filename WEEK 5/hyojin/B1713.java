import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//백준_1713
//후보추천하기

public class practice03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 사진틀 갯수
		int M = sc.nextInt(); // 추천 횟수

		int[] re = new int[101]; // 추천 수
		int[] time = new int[101]; // 게시 시간
		boolean[] what = new boolean[101]; // 게시했는지 확인
		List<Integer> frame = new ArrayList<>(); // 사진

		for (int a = 0; a < M; a++) { // a는 시간
			int num = sc.nextInt();

			if (what[num]) { // 게시가 되었을 때는 추천 늘려줌
				re[num]++;
			} else {
				if (frame.size() >= N) {
					// 삭제할 학생 찾기
					int min = Integer.MAX_VALUE; // 추천 가장 적음
					int old = Integer.MAX_VALUE; // 오래됨
					int remove = -1;

					for (int p : frame) {
						if (re[p] < min || (re[p] == min && time[p] < old)) {
							min = re[p];
							old = time[p];
							remove = p;
						}
					}

					// 제거해야함
					for (int i = 0; i < frame.size(); i++) {
						if (frame.get(i) == remove) {
							frame.remove(i);
							break;
						}
					}
					what[remove] = false;
					re[remove] = 0;
					time[remove] = 0;
				}

				// 새 학생 넣어야함
				frame.add(num);
				what[num] = true;
				re[num] = 1;
				time[num] = a;
			}
		}

		// 정렬시키고 출력하기
		Collections.sort(frame);
		for (int student : frame) {
			System.out.print(student + " ");
		}
	}
}