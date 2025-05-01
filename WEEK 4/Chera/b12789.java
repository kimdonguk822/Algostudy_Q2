/**
 * 문제 : 백준 12789번 도키도키
 * 메모리 : 144332KB
 * 시간 : 116ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class b12789 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 현재 줄 서있는 곳
		Queue<Integer> present = new LinkedList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			present.add(Integer.parseInt(st.nextToken()));
		}
		// 한명씩만 설 수 있는 공간
		Stack<Integer> extra = new Stack<>();
		
		int cnt = 1; // 현재 간식을 받아야할 번호(순서)
		
        while (!present.isEmpty() || !extra.isEmpty()) { // 현재 대기줄과 여분 대기줄 모두 비어있으면 끝냄
            if (!present.isEmpty() && present.peek() == cnt) { // 현재 대기줄에 사람이 있고, 맨 앞의 번호가 현재 간식 받을 차례면
                present.poll(); // 현재 대기줄에서 삭제하고
                cnt++; // 간식 차례 +1
            } else if (!extra.isEmpty() && extra.peek() == cnt) { // 여분 대기줄에 사람이 있고, 맨 앞의 번호가 간식 받을 차례이면
                extra.pop(); // 여분 대기줄에서 삭제
                cnt++; // 간식 차례 +1
            } else if (!present.isEmpty()) { // 여분대기줄과 현재 대기줄 맨 앞의 번호 모두 간식 받을 차례가 아니고, 현재 대기줄이 비어있지 않으면
                extra.push(present.poll()); // 현재 대기줄에서 꺼내서 여분 대기줄로 넣음
            } else {
                break;
            }
        }
		
		if(present.isEmpty() && extra.isEmpty()) {
			System.out.println("Nice");
		}else {
			System.out.println("Sad");
		}
		
	}

}
