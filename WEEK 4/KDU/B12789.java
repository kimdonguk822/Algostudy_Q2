// 문제: 백준 12789번
// 문제제목 : 도키도키 간식드리미
// 링크: https://www.acmicpc.net/problem/12789
// 메모리 : 14460KB
// 시간: 116ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B12789 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 학생 수 N 입력
        int N = Integer.parseInt(br.readLine());
        
        // 스택과 큐 초기화
        Stack<Integer> s = new Stack<>();  // 대기중인 학생들을 담을 스택
        Queue<Integer> q = new ArrayDeque<>();  // 줄 서 있는 학생들의 번호를 담을 큐
        
        // 큐에 학생 번호들 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }
        
        // 현재 처리해야 할 번호는 1번부터 시작
        int seq = 1;
        
        // 큐가 빌 때까지 계속 처리
        while (!q.isEmpty()) {
            // 큐에서 학생 번호를 하나씩 꺼냄
            int curr = q.poll();
            
            // 만약 현재 학생 번호가 처리해야 할 번호와 같으면 바로 통과
            if (seq == curr) {
                seq++;  // 처리할 다음 학생 번호로 이동
            } else {
                // 아니라면 대기 중인 학생이므로 스택에 저장
                s.push(curr);
            }
            
            // 스택의 가장 위에 있는 학생이 처리해야 할 번호와 같으면 계속 처리
            while (!s.isEmpty() && s.peek() == seq) {
                s.pop();  // 스택에서 꺼내고
                seq++;  // 처리할 다음 학생 번호로 이동
            }
        }
        
        // 모든 학생을 정상적으로 처리했으면 "Nice", 그렇지 않으면 "Sad" 출력
        if (s.isEmpty()) {
            sb.append("Nice");
        } else {
            sb.append("Sad");
        }
        
        System.out.println(sb.toString());
    }
}
