package Arrays;
import java.util.*;

public class ArrayMath{

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
    
}