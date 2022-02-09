package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2517 {
	static int N;

	static class Node implements Comparable<Node> {
		int rank;
		int power;

		public Node(int rank, int power) {
			this.rank = rank;
			this.power = power;
		}

		@Override
		public int compareTo(Node o) {
			return power - o.power;
		}

	}

	static int[] tree;
	static Node[] nodes;
	static int[] ans;

	static int queryTD(int left, int right, int queryLeft, int queryRight, int nodeNum) {
		if (queryRight < left || queryLeft > right)
			return 0;
		if (queryLeft <= left && right <= queryRight)
			return tree[nodeNum];
		int mid = (left + right) / 2;
		return queryTD(left, mid, queryLeft, queryRight, nodeNum * 2)
				+ queryTD(mid + 1, right, queryLeft, queryRight, nodeNum * 2 + 1);
	}

	static void updateTD(int left, int right, int target, int nodeNum) {
		if (target < left || target > right)
			return;
		tree[nodeNum] += 1;	
		if (left == right)
			return;
		int mid = (left + right) / 2;
		updateTD(left, mid, target, nodeNum * 2);
		updateTD(mid + 1, right, target, nodeNum * 2 + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int startLeaf = 1;
		while (startLeaf < N)
			startLeaf *= 2;
		int len = startLeaf * 2;
		;
		ans = new int[N + 1];
		tree = new int[len + 1];
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i + 1, Integer.parseInt(br.readLine()));
		}

		Arrays.sort(nodes);
//		for(int i=0; i<N; i++) {
//			System.out.println(nodes[i].power + " " + nodes[i].rank);
//		}
		for (int i = 0; i < N; i++) {
			int res = nodes[i].rank - queryTD(1,startLeaf, 1, nodes[i].rank, 1);
			ans[nodes[i].rank] = res;
			updateTD(1,startLeaf,nodes[i].rank, 1);
		}
		for(int i=1; i<=N; i++) {
			sb.append(ans[i]).append('\n');
		}
		System.out.println(sb);
	}

}
