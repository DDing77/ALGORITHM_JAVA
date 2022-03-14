package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_1764 {
	static int N, M;
	static TreeSet<String> list = new TreeSet<>();
	static ArrayList<String> res = new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			list.add(input);
		}
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			if(list.contains(input)) {
				res.add(input);
			}
		}
		Collections.sort(res);
		sb.append(res.size()).append('\n');
		for(String string : res) sb.append(string).append('\n');
		System.out.print(sb);
	}
}
