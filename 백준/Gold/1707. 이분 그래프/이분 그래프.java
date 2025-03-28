import java.io.*;
import java.util.*;


public class Main {
	public static int K,V,E;
	public static String result;
	public static int[] set;		//정점 팀 설정 배열
	public static ArrayList<ArrayList<Integer>> graph;	//그래프 내용 저장 배열
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력값 처리하는 BufferedReader
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
        //결과값 출력하는 BufferedWriter
        //---------입력값 저장 및 리스트 초기화-----------
    	StringTokenizer st;
    	K = Integer.parseInt(br.readLine());
    	for(int i=0;i<K;i++) {
    		st = new StringTokenizer(br.readLine()," ");
    		V = Integer.parseInt(st.nextToken());
    		E = Integer.parseInt(st.nextToken());
    		set = new int[V+1];
    		graph = new ArrayList<>();
    		result = "YES";
    		for(int j=0;j<=V;j++) 
    			graph.add(new ArrayList<>());
    		
    		for(int j=0;j<E;j++) {
    			st = new StringTokenizer(br.readLine()," ");
    			int row = Integer.parseInt(st.nextToken());
    			int col = Integer.parseInt(st.nextToken());
    			graph.get(row).add(col);
    			graph.get(col).add(row);
    		}
    		for(int j=1;j<=V;j++) {		//1~V 탐색
    			if(set[j] == 0) {	//팀 정해지지 않은 정점 탐색
        			if(!bfs(j)) {	//BFS 탐색
        				result = "NO";
        				break;
        			}
    			}
    		}
    		bw.write(result + "\n");		//이분 그래프 BufferedWriter 저장
    	}
    	bw.flush();		//결과 출력 (비우고, 닫기)
    	bw.close();
    	br.close();
    }
	
    public static boolean bfs(int point) {
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(point);
    	set[point] = 1;		//기본팀(블루팀, 1팀) 설정
    	while(!queue.isEmpty()) {
    		int now_Node = queue.poll();
    		for(Integer value : graph.get(now_Node)) {
    			if(set[value] == set[now_Node]) {
    				return false;	//인접 정점 같은팀일 때
    			}
    			if(set[value]==0) {
    				set[value] = set[now_Node] * -1;	//인접 정점 반대팀 설정
    				queue.add(value);
    			}
    		}
    	}
    	return true;
    }
}