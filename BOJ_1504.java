package BOJ;
import java.io.*;
import java.util.*;

public class BOJ_1504 {
	static int N, E;
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to =to;
			this.weight = weight;
		}
	}
	static ArrayList<Edge>[] edges;
	
	static int[] dist;
	static final int INF = 200_000_000;
	
	static class Info{
		int idx;
		int dist;
		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	
	static int dijkstra(int start, int end) {
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
	
	static void solution(int req1, int req2, StringBuilder sb) {
		int res1 = 0;
		int res2 = 0;
		
		res1 += dijkstra(1, req1);
		res1 += dijkstra(req1, req2);
		res1 += dijkstra(req2, N);
		
		if(res1 >= INF) {
			sb.append(-1);
			return;
		}
		
		res2 += dijkstra(1,  req2);
		res2 += dijkstra(req2, req1);
		res2 += dijkstra(req1, N);
		
		if(res2 >= INF) {
			sb.append(-1);
			return;
		}
		
		sb.append(Math.min(res1, res2));
		return;
	}
	
	
@SuppressWarnings("unchecked")
public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();
	StringTokenizer st = new StringTokenizer(br.readLine());
	N = Integer.parseInt(st.nextToken());
	E = Integer.parseInt(st.nextToken());
	
	edges = new ArrayList[N+1];
	for(int i=0; i<=N; i++) {
		edges[i] = new ArrayList<>();
	}
	dist = new int[N+1];
	
	for(int i=0; i<E; i++) {
		st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int weight = Integer.parseInt(st.nextToken());
		
		edges[start].add(new Edge(end, weight));
		edges[end].add(new Edge(start, weight));
	}
	
	st = new StringTokenizer(br.readLine());
	int req1 = Integer.parseInt(st.nextToken());
	int req2 = Integer.parseInt(st.nextToken());
	
	solution(req1, req2, sb);
	
	System.out.println(sb);
	
}
	
}
