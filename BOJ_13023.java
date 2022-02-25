package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean isVisit[];
	static void DFS(int start, int cnt) {
		if(cnt == 4) {
			System.out.println(1);
			System.exit(0);
		}
		isVisit[start] = true;
		for(int next : list[start]) {
			if(!isVisit[next]) {
				isVisit[next] = true;
				DFS(next, cnt+1);
				isVisit[next] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		for(int i=0; i<N; i++) list[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
		for(int i=0; i<N; i++) {
			isVisit = new boolean[N];
			DFS(i, 0);
		}
		System.out.println(0);
	}
}
