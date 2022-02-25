package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// 123 456 789
public class BOJ_4779 {
	static int N;
	static ArrayList<Character> list;
	static void divideConquer(int left, int right) {
		if(left >= right) return;
		if((right- left + 1) %3 != 0) return;
		
		int len = right - left +1;
		
		for(int i=+left + (len/3); i<left + (len/3)*2; i++ ) {
			list.set(i, ' ');
		}
		divideConquer(left, (left + len/3)-1);
		divideConquer(left + (len/3)*2, right);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String tmp;
		while((tmp = br.readLine()) != null) {
			
			StringTokenizer st =new StringTokenizer(tmp);
			if(!st.hasMoreTokens()) break;
			list = new ArrayList<>();
			list.add('*');
			N = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<Math.pow(3, N); i++) list.add('-');
			divideConquer(1, list.size()-1);
//			System.out.println(list);
			for(char now : list) {
				if(now == '*') continue;
				sb.append(now);
			}
			sb.append('\n');
//			System.out.println(list.size()-1);
//			System.out.println("test" + Integer.parseInt(st.nextToken()));
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
