package Arrays;
import java.util.*;

public class ArrayMath{

    public static void main(String[] args) {
       int[] A = {  1, 1, 1, 1 };
       int B = 3;
      int var=  MinimumLightstoActivate(A,B);
      System.out.println(var);

    }

   // https://www.interviewbit.com/problems/pick-from-both-sides/
    public static int PickFromBothSides(int[] A, int B) {
        int n=A.length;
        int sum=0;
        for(int i=0;i<B;i++) sum+=A[i]; //include from first left side
        int ans=sum;

        //now start removing from one side and add from the other
        //update the ans
        for(int i=0;i<B;i++){
            sum-=A[B-i-1];
            sum+=A[n-i-1];

            ans=Math.max(ans,sum);
        }
        return ans;
    }
    //https://www.interviewbit.com/problems/min-steps-in-infinite-grid/

    public  static int coverPoints(int[] A, int[] B) {
        int min=0;
        int n=A.length;
        for(int i=1;i<n;i++){
            int diffX=Math.abs(A[i]-A[i-1]);
            int diffY=Math.abs(B[i]-B[i-1]);
            min+=Math.max(diffX,diffY);
        }
        
        return min;
    }

    public static int MinimumLightstoActivate(int[] A, int B){
        int min=0;
        int n=A.length;
        int i=0;
        while(i<n){
            int rm=Math.min(n-1,i+B-1);
            int lm=Math.max(0,i-B+1);
            int next_i=-1;
            for(int j=rm;j>=lm;j--){
                if(A[j]==1){
                    next_i=j+B;
                    break;
                }
            }

            if(next_i==-1) return -1;
            min++;
            i=next_i;
        }

        return min;

    }

    //https://www.interviewbit.com/problems/maximum-sum-triplet/

   

    public static int MaximumSumTriplet(int[]arr) {
        int n=arr.length;
        int[]maxOnright=new int[n];
        int max=arr[n-1];
        for(int i=n-1;i>=0;i--){
             max=Math.max(max,arr[i]);
             maxOnright[i]=max;
        }

        int ans=0;
        TreeSet<Integer>set=new TreeSet<>();
        set.add(arr[0]);

        for(int i=1;i<n-1;i++){
            int mid=arr[i];
            int right=maxOnright[i+1];
            if(set.lower(arr[i])!=null && mid<right ){
                int csum=mid+right+set.lower(arr[i]);
                ans=Math.max(ans,csum);

            }
             set.add(arr[i]);
        }

        return ans;


    }

    
}