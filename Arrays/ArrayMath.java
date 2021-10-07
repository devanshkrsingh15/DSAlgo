package Arrays;
import java.util.*;

public class ArrayMath{

    public static void main(String[] args) {
     //  int[] A = { 0,0,0,0,0,0,0,0,0 };
     int[] A = { 1, 2, 3, 0, 3 };
       int ans=solve(A.length,A);
       System.out.println(ans);;
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

    //https://www.interviewbit.com/problems/add-one-to-number/
    public static int[] AddOneToNumber(int[]arr){
        int n=arr.length;
        ArrayList<Integer>list=new ArrayList<>();
        int carr=1;
        int idx=n-1;
        while(idx>=0){
            int lastdigit=arr[idx]+carr;
            int tobeStored=lastdigit%10;
            carr=lastdigit/10;
            list.add(tobeStored);
            idx--;
        }
        if(carr==1) list.add(carr);
        Collections.reverse(list);
        int ridx=0;
        if(list.get(0)==0){
            while(ridx<list.size() && list.get(ridx)==0) ridx++;
        }
        
        ArrayList<Integer>ans=new ArrayList<>();
        
        for(int i=ridx;i<list.size();i++)ans.add(list.get(i));

        int[]fans=new int[ans.size()];
        int ptr=0;

        for(int ele:ans) fans[ptr++]=ele;
        



        return fans;

        
    }

    //https://www.interviewbit.com/problems/maximum-absolute-difference/
    public static int maxArr(int[] A) {
        int n=A.length;
        int[]diff=new int[n];
        int[]sum=new int[n];

        for(int i=0;i<n;i++){
            diff[i]=A[i]-(i+1);
            sum[i]=A[i]+(i+1);
        }

        int minDiff=Integer.MAX_VALUE;
        int minSum=Integer.MAX_VALUE;

        int maxDiff=Integer.MIN_VALUE;
        int maxSum=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            minDiff=Math.min(minDiff,diff[i]);
            minSum=Math.min(minSum,sum[i]);

            maxDiff=Math.max(maxDiff,diff[i]);
            maxSum=Math.max(maxSum,sum[i]);

        }


        return Math.max(maxDiff-minDiff,maxSum-minSum );
    
         
       
    }
    

    //https://www.interviewbit.com/problems/partitions/
    
    public int Partitions(int A, int[] arr) {
        int n=arr.length;

        int[]prefix=new int[n];
        for(int i=0;i<n;i++){
            if(i==0) prefix[i]=arr[i];
            else prefix[i]=prefix[i-1]+arr[i];
        }

        if(prefix[n-1]%3!=0) return 0;

        int[]suffix=new int[n];
        for(int i=n-1;i>=0;i--){
            if(i==n-1) suffix[i]=arr[i];
            else suffix[i]=suffix[i+1]+arr[i];
        }

       // for(int ele:suffix) System.out.print(ele+" ");
      //  System.out.println();
        int[]cnt=new int[n];
        for(int i=n-1;i>=0;i--){
            if(suffix[i]==prefix[n-1]/3){
                cnt[i]=1;
            }
        }

      //  for(int ele:cnt) System.out.print(ele+" ");

        for(int i=n-2;i>=0;i--){
            cnt[i]+=cnt[i+1];
        }


        int count=0;

        for(int i=0;i<n;i++){
            if(prefix[i]==prefix[n-1]/3){
                if(i+2<n)
                count+=cnt[i+2];
               // System.out.println( i + " "+cn);
            }
        }

     //   System.out.println();
        return count;
    }
}