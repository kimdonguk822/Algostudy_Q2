import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 친구들 관계에서 최단 거리 수 구하는 문제 -> BFS
public class B1389_케빈베이컨의6단계법칙 {

	static int N;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 유저 수 2~100
		int M = sc.nextInt(); // 친구 관계 수 1~5000

		// 인접리스트를 이용한 친구 관계 저장
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 친구 관계 입력 받기 (무방향, 가중치 없음)
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();

			graph[A].add(B);
			graph[B].add(A);
		} 
		
		
		// 최단 거리 수를 구하기 위함
		int min = Integer.MAX_VALUE;
		int ans = 0;

		for (int i = 1; i <= N; i++) {
			// i번 유저와 1~N번까지 유저의 최단 거리 수 배열
			int[] dist = bfs(i);

			// i번 유저와 1~N번까지 유저의 최단 거리 수 합 구하기
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += dist[j];
			}

			// 케빈 베이컨 수가 가장 작은 사람 (여러 명일 경우 번호가 더 작은 사람 한 명만)
			if (sum < min) {
				min = sum;
				ans = i;
			}

		}

		System.out.println(ans);
	}

	// E번 유저와 1~N번까지 유저의 최단 거리 수 배열을 반환하는 BFS
	private static int[] bfs(int E) {

		int[] dist = new int[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		queue.add(E);
		visited[E] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			// 핵심 로직 ... 
			// 현재 유저와 친구인 사람을 한명씩 꺼내서 
			for (int next : graph[curr]) {
				// 아직 방문안한 친구이면 
				if (!visited[next]) {
					visited[next] = true;
					// 그 친구와 거리를 현재 유저+1 로 저장
					dist[next] = dist[curr] + 1;
					// 그 친구를 큐에 넣기 
					queue.add(next);
				}

			}

		}

		return dist;
	}

}
