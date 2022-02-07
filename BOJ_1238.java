package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {
	static int N , M, X;
	static final int  INF = 1000000;
	static int[] dist;
	static int[] partyDist;
	static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to= to;
			this.weight = weight;
		}
	}
	static class Info{
		int idx;
		int dist;
		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	static ArrayList<Edge>[] edges; 
	
	static void fromPartyDijkstra(int start) {
		Arrays.fill(partyDist, INF);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
		partyDist[start] = 0;
		pq.add(new Info(start, 0));
		
		while(!pq.isEmpty()) {
			Info info = pq.poll();
			if(partyDist[info.idx] < info.dist) continue;
			for(Edge e : edges[info.idx]) {
				if(partyDist[info.idx] + e.weight > partyDist[e.to]) continue;
				partyDist[e.to] = partyDist[info.idx] + e.weight;
				pq.add(new Info(e.to, partyDist[e.to]));
			}
		}
	}
	static int toPartyDijkstra(int start, int end) {
		Arrays.fill(dist, INF);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
		dist[start] = 0;
		pq.add(new Info(start, 0));
		
		while(!pq.isEmpty()) {
			Info info = pq.poll();
			if(dist[info.idx] < info.dist) continue;
			for(Edge e : edges[info.idx]) {
				if(dist[info.idx] + e.weight > dist[e.to]) continue;
				dist[e.to] = dist[info.idx] + e.weight;
				pq.add(new Info(e.to, dist[e.to]));
			}
		}
		return dist[end];
	}
	
	static void solution(int X) {
		fromPartyDijkstra(X);
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(i == X) continue;
			int toParty = toPartyDijkstra(i, X);
			max = Math.max(max,  toParty + partyDist[i]);
//			System.out.println("toParty " + i + " : " + toParty );
		}
//		System.out.println(Arrays.toString(partyDist));
		System.out.println(max);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		partyDist = new int[N+1];
		
		edges = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[start].add(new Edge(end, weight));
		}
		solution(X);
	}

}
