package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13334 {
	static int N;
	static int d;
	static int max = -1;
	static class People implements Comparable<People>{
		int left;
		int right;
		public People(int left, int right) {
			this.left = Math.min(left, right);
			this.right = Math.max(left, right);
		}
		@Override
		public int compareTo(People o) {
			return Integer.compare(right, o.right);
		}
	}
	static People[] peoples;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		peoples = new People[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()); 
			int right = Integer.parseInt(st.nextToken()); 
			peoples[i] = new People(left, right);
		}
		Arrays.sort(peoples);
		d= Integer.parseInt(br.readLine());
		
		for(int i=0; i<peoples.length; i++) {
			pq.add(peoples[i].left);
			
			while(!pq.isEmpty() && pq.peek() < peoples[i].right - d) pq.poll();
			
			max = Math.max(max, pq.size());
		}
		System.out.println(max);
	}
}
