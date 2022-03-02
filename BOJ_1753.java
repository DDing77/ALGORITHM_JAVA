package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static int V, E;
	static int startNum;
	static int[] dist;

	static class Edge {
		int end;
		int weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	static class Info {
		int idx;
		int dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

	static ArrayList<Edge>[] edges;

	static void dijkstra(int start) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		dist[start] = 0;
		pq.add(new Info(start, 0));

		while (!pq.isEmpty()) {
			Info now = pq.poll();
			if (dist[now.idx] < now.dist)
				continue;
			for (Edge e : edges[now.idx]) {
				if (dist[now.idx] + e.weight >= dist[e.end])
					continue;
				dist[e.end] = dist[now.idx] + e.weight;
				pq.add(new Info(e.end, dist[e.end]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		startNum = Integer.parseInt(br.readLine());

		dist = new int[V + 1];
		edges = new ArrayList[V + 1];

		for (int i = 0; i <= V; i++)
			edges[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[start].add(new Edge(end, weight));
		}
		dijkstra(startNum);
		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append('\n');
			} else
				sb.append(dist[i]).append('\n');
		}
		System.out.print(sb);
	}
}
