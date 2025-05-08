package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 숫자 개수 입력
        String[] input = br.readLine().split(" "); // 숫자 입력받기
        
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) { // 역순으로 저장 
            stack.push(Integer.parseInt(input[i]));
        }

        Stack<Integer> sideStack = new Stack<>();
        int expected = 1; // 현재 기다리는 숫자
        boolean isValid = true;

        while (!stack.isEmpty()) {
            int num = stack.pop();

            if (num == expected) {
                expected++; // 원하는 숫자면 증가
            } else {
                while (!sideStack.isEmpty() && sideStack.peek() == expected) {
                    sideStack.pop();
                    expected++;
                }

                if (num == expected) {
                    expected++; // 바로 사용할 수 있는 경우
                } else {
                    sideStack.push(num); // 옆으로 이동
                }
            }
        }

        // sideStack에 남아 있는 숫자가 순서대로 있는지 확인
        while (!sideStack.isEmpty()) {
            if (sideStack.pop() == expected) {
                expected++;
            } else {
                isValid = false;
                break;
            }
        }

        System.out.println(isValid ? "Nice" : "Sad");
    }
}
