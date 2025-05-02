package W3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B12789 {
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		int[] student = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			student[i] = Integer.parseInt(st.nextToken());
		} // 입력완료

		Stack<Integer> stack = new Stack<>();
		int order = 1; // 순서 지금 현재 누구부터?

		// 뽑으면서 order랑 비교해서 지금 순서이면 order++처리
		// 아니면 stack
		for (int i = 0; i < N; i++) {
			if (student[i] == order) {
				order++;
			} else {
				while (!stack.isEmpty() && stack.peek() == order) {
					stack.pop();
					order++;
				}
				stack.push(student[i]);
			}
		}

		while (!stack.isEmpty()) {
			if(stack.peek() == order) {
				stack.pop();
				order++;
			}else {
				System.out.println("Sad");
				return;
			}
		}
		System.out.println("Nice");

	} // main

}
