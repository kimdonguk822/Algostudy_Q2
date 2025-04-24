import java.util.Scanner;

public class B2564_경비원 {

	static class store {
		int dir, loc;

		public store(int dir, int loc) {
			super();
			this.dir = dir;
			this.loc = loc;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 블록의 가로 길이
		int M = sc.nextInt(); // 블록의 세로 길이

		int s = sc.nextInt(); // 상점의 개수
		store[] stores = new store[s];

		// 1 북 2 남 3 서 4 동
		for (int i = 0; i < s; i++) {

			int D = sc.nextInt(); // 상점이 위치한 방향
			int L = sc.nextInt(); // 상점이 위치한 거리

			stores[i] = new store(D, L);

		}

		int DGD = sc.nextInt(); // 동근이가 위치한 방향
		int DGL = sc.nextInt(); // 동근이가 위치한 거리

		int sum = 0;

		for (int i = 0; i < s; i++) {

			int SD = stores[i].dir;
			int SL = stores[i].loc;

			// 동근이가 북/남에 위치할 경우
			if (DGD == 1 || DGD == 2) {

				// 동근이가 북/남 & 상점이 북/남에 위치할 경우
				if (SD == 1 || SD == 2) {

					if (DGD == SD) {
						sum += Math.abs(DGL - SL);
					} else {

						int A = DGL + SL;
						int B = 2 * N - DGL - SL;

						sum += Math.min(A, B) + M;
					}

				}
				// 동근이가 북/남 & 상점이 동/서에 위치할 경우
				else {

					// 상점이 서쪽에 있으면
					if (DGD == 1 && SD == 3) {
						sum += DGL + SL;
					} else if (DGD == 2 && SD == 3) {
						sum += DGL + M - SL;
					}

					// 상점이 동쪽에 있으면
					else if (DGD == 1 && SD == 4) {
						sum += SL + N - DGL;
					} else {
						sum += N - DGL + M - SL;
					}

				}

				// 동근이가 서/동에 위치할 경우
			} else {

				// 상점이 서/동에 위치할 경우
				if (SD == 3 || SD == 4) {

					if (DGD == SD) {
						sum += Math.abs(DGL - SL);
					} else {

						int A = DGL + SL;
						int B = 2 * M - DGL - SL;

						sum += Math.min(A, B) + N;
					}

				}

				// 상점이 북/남에 위치할 경우
				else {

					// 상점이 북쪽에 있으면
					if (DGD == 3 && SD == 1) {
						sum += DGL + SL;
					} else if (DGD == 4 && SD == 1) {
						sum += DGL + N - SL;
					}

					// 상점이 남쪽에 있으면
					else if (DGD == 3 && SD == 2) {
						sum += SL + M - DGL;
					} else {
						sum += M - DGL + N - SL;
					}

				}

			}

		}

		// 동근이의 위치와 각 상점 사이의 최단 거리의 합 출력
		System.out.println(sum);

	}
}
