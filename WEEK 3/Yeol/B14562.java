// 문제: 백준 14562번
// 문제제목 : 태권왕
// 링크: https://www.acmicpc.net/problem/14562
// 메모리 : 14392KB
// 시간: 108ms



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14562_태권왕 {

    static class Node {
        int s, t, cnt;

        public Node(int s, int t, int cnt) {
            this.s = s;
            this.t = t;
            this.cnt = cnt;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());

        for (int i = 0; i < C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());//태균이 점수
            int t = Integer.parseInt(st.nextToken());//상대 점수

            sb.append(bfs(s, t)).append("\n");
        }

        System.out.print(sb);
    }
    
    public static int bfs(int s, int t) {
    	Queue<Node> q = new ArrayDeque<>();
    	q.offer(new Node(s, t, 0));
    	
    	while (!q.isEmpty()) {
    		Node curr = q.poll();
    		
    		if (curr.s == curr.t) return curr.cnt;
    		if (curr.s > curr.t) continue;
    		
    		q.offer(new Node(curr.s + 1, curr.t, curr.cnt + 1));    // 연산 1
    		q.offer(new Node(curr.s * 2, curr.t + 3, curr.cnt + 1));    // 연산 2
    	}
    	
    	return -1;
    }
}
