package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// author : DDing
// solution : Kruskal(MST)

public class BOJ_16398 {
	static int N;
	static int[] parent;

	static class Info {
		int start;
		int end;
		long weight;

		public Info(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	static ArrayList<Info> infos;

	static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		infos = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				infos.add(new Info(i, j, Integer.parseInt(st.nextToken())));
			}
		}

		Collections.sort(infos, Comparator.comparingLong(o -> o.weight));
		int cnt = 0;
		long sum = 0;
		for (int i = 0; i < infos.size(); i++) {
			Info now = infos.get(i);
			if(now.start == now.end) continue;
			if (union(now.start, now.end)) {
				cnt++;
				sum += now.weight;
				if (cnt == N - 1) {
					break;
				}
			}else continue;

		}
		System.out.println(sum);
	}

}
