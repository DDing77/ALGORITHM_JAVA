	package BOJ;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;
	import java.util.Comparator;
	import java.util.LinkedList;
	import java.util.PriorityQueue;
	import java.util.StringTokenizer;
	
	public class BOJ_13549 {
		static int N, K;
		static final int MAX = 100000;
		static int[] map = new int[MAX+1];
		static class Info {
			int idx;
			int dist;
			public Info(int idx, int dist) {
				this.idx = idx;
				this.dist = dist;
			}
		}
		static void dikjstra(int start) {
			PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
			map[start] = 0;
			pq.add(new Info(start, 0));
				
			while(!pq.isEmpty()) {
				Info info = pq.poll();
	
				if(map[info.idx] < info.dist) continue;
				if(info.idx * 2 <=MAX && info.dist < map[info.idx*2]) {
					map[info.idx*2] = info.dist;
					pq.add(new Info(info.idx*2, map[info.idx*2]));
				}
				if(info.idx + 1<=MAX && info.dist+1 < map[info.idx+1]) {
					map[info.idx+1] = info.dist +1;
					pq.add(new Info(info.idx+1, map[info.idx+1]));
				}
				if(0<= info.idx - 1 && info.dist +1 < map[info.idx - 1] ) {	
					map[info.idx -1] = info.dist + 1;
					pq.add(new Info(info.idx-1, map[info.idx -1]));
				}
			}
			System.out.println(map[K]);
		}
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			
			Arrays.fill(map,Integer.MAX_VALUE);
			dikjstra(N);
		}
	
	}
