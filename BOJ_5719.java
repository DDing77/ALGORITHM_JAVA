package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5719 {
	static int N, M, S, D;
	static ArrayList<Integer>[] parent;
	static int[] distance;
	static boolean[][] isShortest;

	static class Info {
		int idx;
		int dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static ArrayList<Edge>[] list;

	static void backTracking(int start, int now) {
		if (start == now)
			return;
		for (int i : parent[now]) {
			if (!isShortest[i][now]) {
				isShortest[i][now] = true;
				backTracking(start, i);
			}
		}
	}

	static void dijkstra(int start, int end) {
		distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingLong(o->o.dist));
		distance[start] = 0;
		pq.add(new Info(start, 0));

		while (!pq.isEmpty()) {
			Info info = pq.poll();
			if (info.dist > distance[info.idx])
				continue;
			for (Edge e : list[info.idx]) {
				if (isShortest[info.idx][e.to])
					continue;
				if (distance[e.to] == info.dist + e.weight) {
					parent[e.to].add(info.idx);
				} else if (distance[e.to] > info.dist + e.weight) {
					parent[e.to].clear();
					parent[e.to].add(info.idx);
					distance[e.to] = info.dist + e.weight;
					pq.add(new Info(e.to, distance[e.to]));
				}
			}
		}

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if( N == 0 && M == 0) break;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			parent = new ArrayList[N];
			list = new ArrayList[N];
			isShortest = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				parent[i] = new ArrayList<>();
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				list[start].add(new Edge(end, weight));
			}
			
			dijkstra(S, D);
			backTracking(S, D);
			dijkstra(S,D);
			
			if(distance[D] == Integer.MAX_VALUE) {
				sb.append(-1).append('\n');
			} else {
				sb.append(distance[D]).append('\n');
			}
		}
		System.out.print(sb);
	}

}
