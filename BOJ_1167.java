package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1167 {
	static class Node {
		int end;
		int weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	static int N;
	static boolean[] isVisited;

	static ArrayList<Node>[] list;

	static int node = 0;
	static int max = 0;

	static void DFS(int idx, int dist) {
		isVisited[idx] = true;
		if (dist > max) {
			node = idx;
			max = dist;
		}

		for (Node next : list[idx]) {
			if (!isVisited[next.end]) {
				DFS(next.end, dist + next.weight);
				isVisited[next.end] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		isVisited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			while (true) {
				int end = Integer.parseInt(st.nextToken());
				if (end == -1) break;
				list[start].add(new Node(end, Integer.parseInt(st.nextToken())));
			}
		}
		DFS(1, 0);
		isVisited = new boolean[N + 1];
		DFS(node, 0);
		System.out.println(max);
	}
}
