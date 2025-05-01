package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *B12789 도키도키 간식드리미
 *https://www.acmicpc.net/problem/12789
 */

public class B12789 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        Stack<Integer> mainLine = new Stack<>();
        Stack<Integer> subLine = new Stack<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            tmp[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = N - 1; i >= 0; i--) {
            mainLine.push(tmp[i]);
        }
        
        int num = 1;
        while (!mainLine.isEmpty() || !subLine.isEmpty()) {
            if (!mainLine.isEmpty() && mainLine.peek() == num) {
                mainLine.pop();
                num++;
            } else if (!subLine.isEmpty() && subLine.peek() == num) {
                subLine.pop();
                num++;
            } else if (!mainLine.isEmpty()) {
                subLine.push(mainLine.pop());
            } else {
                break;
            }
        }
        
        if (num > N) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}