package GraphTheory;
import java.util.*;


class TravellingSalesMan {
    public static void main(String[] args) {
        int[][]graph = { { 0, 20, 42, 25 }, { 20, 0, 30, 34 }, { 42, 30, 0, 10 }, { 25, 34, 10, 0 } };
        int n=graph.length;
        //source is already taken 0001
        //int mincost=tsp_rec(graph,1,0,n,0);
        
      
        int[][]dp=new int[(1<<n)][n];

        for(int[]d:dp) Arrays.fill(d,-1);
        int mincost=tsp_dp(graph,1,0,n,0,dp);
        System.out.println(mincost);

        
        
    

    }

    public static int tsp_rec(int[][]graph, int taken, int curr, int n,int src) {
        if(taken==(1<<n)-1){
            return graph[curr][src];
        }

        int min=(int)1e9;

        for(int city=0;city<n;city++){
            int mask=(1<<city);

            if((taken&mask)==0){
                int future = tsp_rec(graph,taken|mask,city,n,src);
                int mycost= graph[curr][city];

                min=Math.min(min,future+mycost);
            }
        }

        return min;
    }

    public static int tsp_dp(int[][]graph, int taken, int curr, int n,int src,int[][]dp) {
        if(taken==(1<<n)-1){
            return graph[curr][src];
        }

        if(dp[taken][curr]!=-1) return dp[taken][curr];

        int min=(int)1e9;

        for(int city=0;city<n;city++){
            int mask=(1<<city);

            if((taken&mask)==0){
                int future = tsp_dp(graph,taken|mask,city,n,src,dp);
                int mycost= graph[curr][city];

                min=Math.min(min,future+mycost);
            }
        }

        return dp[taken][curr]=min;
    }


    //https://leetcode.com/problems/find-the-shortest-superstring/

    public static String shortestSuperstring(String[] words) {
        return "";
    }



     
}

