package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// author : DDing
// solution : 브루트포스

public class BOJ_1065 {
	static int X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());

		if (X < 100) {
			System.out.println(X);
		} else {
			int cnt = 99;
			for (int i = 100; i <= X; i++) {
				int a = i / 100;
				int b = (i / 10) % 10;
				int c = i % 10;
				if ((a - b) == (b - c))
					cnt++;
			}
			System.out.println(cnt);
		}
	}
}
