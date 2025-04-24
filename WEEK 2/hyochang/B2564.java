import java.util.Scanner;

/*
 *  문제: 2564 경비원
 *  메모리: 17916KB 시간: 180ms
 *  링크: https://www.acmicpc.net/problem/2564
 */
public class B2564 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int width = sc.nextInt();
		int height = sc.nextInt();
		int n = sc.nextInt();
		int range = 2 * (width + height);
		int[] stores = new int[n];

		for (int i = 0; i < n; i++) {
			int dir = sc.nextInt();
			int dist = sc.nextInt();
			stores[i] = search(dir, dist, width, height);
		}

		int dongDir = sc.nextInt();
		int dongDist = sc.nextInt();
		int donggeun = search(dongDir, dongDist, width, height);

		int sum = 0;
		for (int store : stores) {
			int distance = Math.abs(donggeun - store);
			sum += Math.min(distance, range - distance);
		}

		System.out.println(sum);
	}

	static int search(int direction, int distance, int width, int height) {
		switch (direction) {
		case 1:
			return distance;
		case 2:
			return width + height + (width - distance);
		case 3:
			return 2 * (width + height) - distance;
		case 4:
			return width + distance;
		default:
			return 0;
		}
	}
}
