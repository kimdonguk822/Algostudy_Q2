// 문제: 백준 12789번
// 문제제목 : 도키도키
// 링크: https://www.acmicpc.net/problem/12789
// 메모리 : 14488KB
// 시간: 112ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.IOException;

public class B12789_도키도키 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());//승환이 앞에 서 있는 학생 수
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int curr = 1;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            //현재 받을 번호면 바로 처리
            if (num == curr) {
                curr++;
            }
            //현재 못 받는 번호면 스택에 넣을 건데
            else {
                //만약 스택의 top이 curr이랑 같으면 먼저 pop하고 처리
                while (!stack.isEmpty() && stack.peek() == curr) {
                    stack.pop();
                    curr++;
                }
                //현재 번호는 스택에 추가
                stack.push(num);
            }
        }

        // 남은 스택도 확인
        while (!stack.isEmpty() && stack.peek() == curr) {
            stack.pop();
            curr++;
        }

        if (stack.isEmpty()) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
