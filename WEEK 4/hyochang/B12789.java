import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 도키도키 간식드리미
 * 14380kb 116ms
 * https://www.acmicpc.net/problem/12789
 */
public class B12789 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Integer> stack = new Stack<>();

		int cur = 1;
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty() && stack.peek() == cur) {
				stack.pop();
				cur++;
			}
			if (num == cur) {
				cur++;
			} else {
				stack.push(num);
			}
		}
		while (!stack.isEmpty() && stack.peek() == cur) {
			stack.pop();
			cur++;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if (cur == n + 1) {
			bw.write("Nice");
		} else {
			bw.write("Sad");
		}
		bw.flush();
		bw.close();
	}
}
