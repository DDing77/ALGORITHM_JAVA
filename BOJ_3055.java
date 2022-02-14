package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : DDing
// solution : BFS 동시 2개 사용

public class BOJ_3055 {
	static int R, C;
	static char[][] map;
	static boolean[][] isVisit;
	static int[][] score;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static void solution() {
		int dX =0;
		int dY = 0;
		Queue<Integer> animal = new LinkedList<>();
		Queue<Integer> water = new LinkedList<>();

		// w, S 위치 찾기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					animal.add(i);
					animal.add(j);
					isVisit[i][j] = true;
				} else if (map[i][j] == '*') {
					water.add(i);
					water.add(j);
				} else if(map[i][j] == 'D') {
					dX = i;
					dY = j;
				}
			}
		}
		
		while(!animal.isEmpty()) {
			// 물 먼저 이동
			int wSize = water.size()/2;
			for(int i=0; i<wSize; i++) {
				int wX = water.poll();
				int wY = water.poll();
				
				for(int k=0; k<4; k++) {
					int nwX = wX + dir[k][0];
					int nwY = wY + dir[k][1];
					if(nwX <0 || nwY <0 || nwX >=R || nwY >= C) continue;
					if(map[nwX][nwY] == '.') {
						water.add(nwX);
						water.add(nwY);
						map[nwX][nwY] = '*';
					}
				}
			}
			// 고슴도치 이동
			int aSize = animal.size()/2;
			for(int i=0; i<aSize; i++) {
				int aX = animal.poll();
				int aY = animal.poll();
				
				for(int k=0; k<4; k++) {
					int naX = aX + dir[k][0];
					int naY = aY + dir[k][1];
					if(naX <0 || naY <0 || naX >=R || naY >=C) continue;
					if(map[naX][naY] == '*' || map[naX][naY] == 'X' || map[naX][naY] == 'S') continue;
					if(isVisit[naX][naY]) continue;
					if(map[naX][naY] == 'D') {
						score[naX][naY] = score[aX][aY] + 1;
						animal.clear();
						break;
					}
					map[naX][naY] = 'S';
					score[naX][naY] = score[aX][aY] + 1;
					animal.add(naX);
					animal.add(naY);
				}
			}
		}
		if( score[dX][dY] == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(score[dX][dY]);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		isVisit = new boolean[R][C];
		score = new int[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}

		solution();
	}
}
