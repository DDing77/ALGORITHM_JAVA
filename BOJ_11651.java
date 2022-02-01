package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11651{
	static class Dot implements Comparable<Dot>{
		int x;
		int y;
		public Dot(int x, int y) {
			this.x =x;
			this.y =y;
		}
		@Override
		public int compareTo(Dot o) {
			// TODO Auto-generated method stub
			if(y== o.y) return x - o.x; 
			return y - o.y;
		}
	}
	static int N;
	static Dot[] dots;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N= Integer.parseInt(br.readLine());
		dots = new Dot[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dots[i] = new Dot(a, b);
		}
		Arrays.sort(dots);
		for(int i=0; i<N; i++) {
			sb.append(dots[i].x).append(" ").append(dots[i].y).append('\n');
		}
		System.out.print(sb);
	}



}
