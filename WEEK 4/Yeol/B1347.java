// 문제: 백준 1347번
// 문제제목 : 미로만들기
// 링크: https://www.acmicpc.net/problem/1347
// 메모리 : 14252KB
// 시간: 108ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1347_미로만들기 {
	static int[] dx = {1, 0, -1, 0};//남 서 북 동
	static int[] dy = {0, -1, 0, 1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());//명령 개수(1~49)
		String cmds = br.readLine();//명령
		
		boolean[][] visited = new boolean[101][101];
		int x = 50, y = 50;//시작 위치 (50, 50)
		int dir = 0;//남쪽 방향부터 시작
		
		//시작 위치 방문 처리
		visited[x][y] = true;
		
		//최대/최소 x, y 설정
		int minX = x, maxX = x, minY = y, maxY = y;
		
		for(int i = 0; i < N; i++) {
			char c = cmds.charAt(i);
			
			if(c == 'R') {
				dir = (dir+1) % 4;//오른쪽 회전
			}
			else if(c == 'L') {
				dir = (dir-1+4) % 4;//왼쪽 회전
			}
			else {
				x = x + dx[dir];
				y = y + dy[dir];
				visited[x][y] = true;
				
				//최대/최소 갱신
				minX = Math.min(minX, x);
				maxX = Math.max(maxX, x);
				minY = Math.min(minY, y);
				maxY = Math.max(maxY, y);
			}
			
			
		}
		
		//출력은 최대/최소 범위 안만 하면 됨
		for(int i = minX; i <= maxX; i++) {
			for(int j = minY; j <= maxY; j++) {
				if(visited[i][j]) {
					sb.append('.');
				}
				else {
					sb.append('#');
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}//main
	
}//class
