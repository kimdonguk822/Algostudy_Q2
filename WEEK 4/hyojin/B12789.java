import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//백준_12789
//도키도키 간식드리미
public class practice {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 학생 수

		Queue<Integer> que = new LinkedList<>();
		Stack<Integer> stack = new Stack<>(); // 대기

		for (int i = 0; i < N; i++) {
			que.add(sc.nextInt());
		} // 입력 받음

		int curr = 1; // 현재 번호

		while (!que.isEmpty()) {
			if (que.peek() == curr) { // 현재 번호가 받을 순서이면
				que.poll(); // 줄에서 꺼냄
				curr++; // 다음 번호
			} else if (!stack.isEmpty() && stack.peek() == curr) {
				stack.pop(); // 대기에서 데리고 옴
				curr++; // 다음 번호
			} else {
				stack.push(que.poll()); // 줄에서 꺼내 대기시킴
			}
		}

		//전부 대기 장소로 이동한 경우
		while (!stack.isEmpty()) {
			if (stack.peek() == curr) {
				stack.pop();
				curr++;
			} else {
				System.out.println("Sad");
				return;
			}
		}

		System.out.println("Nice");
	}// main

}
