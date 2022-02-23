package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {
	static int N;
	static int L, R;
	static int res;
	static int[] arr;
	static ArrayList<Integer> list = new ArrayList<>();

	static int lowerBound(int start, int end, int target) {
		int L = start;
		int R = end;
		while (L < R) {
			int mid = (L + R) / 2;
			if (arr[mid] >= target)
				R = mid;
			else
				L = mid + 1;
		}
		return R;
	}

	static int upperBound(int start, int end, int target) {
		int L = start;
		int R = end;
		while (L < R) {
			int mid = (L + R) / 2;
			if (arr[mid] > target)
				R = mid;
			else
				L = mid + 1;
		}
		return R;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);

		for (int i = 0; i < N - 2; ++i) {
			for (int j = i + 1; j < N - 1; ++j) {
				int target = 0 - (arr[i] + arr[j]);
				int L = lowerBound(j + 1, N, target);
				if (arr[L] != target || L == N)
					continue;
				int R = upperBound(j + 1, N, target);
				res += R - L;
			}
		}

		System.out.println(res);
	}
}
