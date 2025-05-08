import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 체인 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		LinkedList<Integer> chain = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chain.add(Integer.parseInt(st.nextToken()));
		}
		
		
		/*
		 * 앞에서부터 길이가 1이 아닌 놈들은 삭제하고 
		 * 아니라면 길이를 1 줄이면서 2번째 놈을 제거하는 방법 
		 * list의 사이즈가 1일때까지 반복
		 */
		
		
		int count = 0;

		while (chain.size() > 1) {
			if (chain.size()>=2 && chain.getFirst() > 1 ) { // 길이 1 줄이고 삭제하고 카운트 추가
				chain.set(0, chain.getFirst() - 1);
				chain.remove(1);
				count++;
			} else { //맨 앞 체인의 길이가 1이므로 연결 불가 -> 삭제
				chain.removeFirst();
			}
		}
		
		System.out.println(count);
	}

}
