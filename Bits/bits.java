package Bits;
import java.util.*;
import java.io.*;

public class bits{
	public static long nCr(long n,long r){
		if(r>n) return 0;
		if(r==n || r==0)  return (long)1;
        long n_fact=fact(n);
        long r_fact=fact(r);
        long nmr_fact=fact(n-r);
        
        return n_fact / (r_fact*nmr_fact) ;
    }
	
	public static long nCr_(long n,long r) {
		if(r>n) return 0;
		if(r==n)  return (long)1;
		
		long res=1;
		
		for(long i=0;i<r;i++) {
			res*=(n-i);
			res/=(i+1);
		}
		
		return res;
	}
    
    public static  long fact(long n){
        if(n<=1) return (long)1;
        return n*fact(n-1);
    }

    public static long count(long n)
    {
        // Code Here
        int set_bit=countSetBits(n);
        return helper(n,63,set_bit);
        
    }
    
    public static long helper(long n,int pos,int sb){
        if(pos==0 ){
            return 0;
        }
        
        long mask= ((long)1 <<  pos) ;
        
        long rsb= n&mask;
        
        if(rsb==0) {
        	return helper(n,pos-1,sb);
        }
        long zero=nCr_(pos,sb);
        long one=helper(n,pos-1,sb-1);
        
        return zero+one;
    }
    
    public static int countSetBits(long n){
        int count=0;
        
        while(n!=0){
            n-=(n&(-n));
            count++;
        }
        
        return count;
    }
	
    
	public static void  ArrayDifferentiation(int[]a) {
		
		
		int n=a.length;
		int[]narr=new int[n+1];
		for(int i=1;i<=n;i++)narr[i]=a[i-1];
		
		int limit=(int)(Math.pow(3,n));
		
		for(int i=1;i<limit;i++) {
			int temp=i;
			int sum=0;
			for(int j=1;j<=a.length;j++) {
				int rem=temp%3;
				temp=temp/3;
				
				if(rem==1) {
					sum+=narr[j];
				}else if(rem==2) {
					sum-=narr[j];
				}
				
			}
			
			if(sum==0) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
		return;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner(System.in);
		int t=scn.nextInt();
		while(t-->0) {
			int n=scn.nextInt();
			int[]a=new int[n];
			for(int i=0;i<n;i++)a[i]=scn.nextInt();
			ArrayDifferentiation(a);
			
		}

	}

    static class RPDandRapSheet{
        static StringBuilder sb;
        static dsu dsu;
        static long fact[];
        static long mod = (int) (1e9);
     
        static void solve() {
            int n = i();
            int k = i();
            System.out.println(0);
            System.out.flush();
            int temp = i();
            if(temp == 1){
                return;
            }
            
            int i = 0;
            int j = 1;
            while(true){
                int guess = i^j;
                System.out.println(guess);
                System.out.flush();
                temp = i();
                if(temp == 1){
                    return;
                }
                
                i++;
                j++;
                
            }	
        }
     
        public void  solution() {
            sb = new StringBuilder();
            int test = 1;
            test = i();
            while (test-- > 0) {
                solve();
            }
            
            // System.out.println(sb);
     
        }
     
        /*
         * fact=new long[(int)1e6+10]; fact[0]=fact[1]=1; for(int i=2;i<fact.length;i++)
         * { fact[i]=((long)(i%mod)1L(long)(fact[i-1]%mod))%mod; }
         */
        
    //**************NCR%P******************	 
        long ncr(int n, int r) {
            if (r > n)
                return (long) 0;
     
            long res = fact[n] % mod;
            // System.out.println(res);
            res = ((long) (res % mod) * (long) (p(fact[r], mod - 2) % mod)) % mod;
            res = ((long) (res % mod) * (long) (p(fact[n - r], mod - 2) % mod)) % mod;
            // System.out.println(res);
            return res;
     
        }
     
        static long p(long x, long y)// POWER FXN //
        {
            if (y == 0)
                return 1;
     
            long res = 1;
            while (y > 0) {
                if (y % 2 == 1) {
                    res = (res * x) % mod;
                    y--;
                }
     
                x = (x * x) % mod;
                y = y / 2;
     
            }
            return res;
        }
     
    //**************END******************
     
        // *************Disjoint set
        // union*********//
        static class dsu {
            int parent[];
     
            dsu(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++)
                    parent[i] = -1;
            }
     
            int find(int a) {
                if (parent[a] < 0)
                    return a;
                else {
                    int x = find(parent[a]);
                    parent[a] = x;
                    return x;
                }
            }
     
            void merge(int a, int b) {
                a = find(a);
                b = find(b);
                if (a == b)
                    return;
                parent[b] = a;
            }
        }
     
    //**************PRIME FACTORIZE **********************************//
        static TreeMap<Integer, Integer> prime(long n) {
            TreeMap<Integer, Integer> h = new TreeMap<>();
            long num = n;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (n % i == 0) {
                    int nt = 0;
                    while (n % i == 0) {
                        n = n / i;
                        nt++;
                    }
                    h.put(i, nt);
                }
            }
            if (n != 1)
                h.put((int) n, 1);
            return h;
     
        }
     
    //****CLASS PAIR ************************************************
        static class Pair implements Comparable<Pair> {
            int x;
            int y;
     
            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
     
            public int compareTo(Pair o) {
                return (int) (this.y - o.y);
     
            }
     
        }
    //****CLASS PAIR **************************************************
     
        static class InputReader {
            private InputStream stream;
            private byte[] buf = new byte[1024];
            private int curChar;
            private int numChars;
            private SpaceCharFilter filter;
     
            public InputReader(InputStream stream) {
                this.stream = stream;
            }
     
            public int read() {
                if (numChars == -1)
                    throw new InputMismatchException();
                if (curChar >= numChars) {
                    curChar = 0;
                    try {
                        numChars = stream.read(buf);
                    } catch (IOException e) {
                        throw new InputMismatchException();
                    }
                    if (numChars <= 0)
                        return -1;
                }
                return buf[curChar++];
            }
     
            public int Int() {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                int sgn = 1;
                if (c == '-') {
                    sgn = -1;
                    c = read();
                }
                int res = 0;
                do {
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    res *= 10;
                    res += c - '0';
                    c = read();
                } while (!isSpaceChar(c));
                return res * sgn;
            }
     
            public String String() {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                StringBuilder res = new StringBuilder();
                do {
                    res.appendCodePoint(c);
                    c = read();
                } while (!isSpaceChar(c));
                return res.toString();
            }
     
            public boolean isSpaceChar(int c) {
                if (filter != null)
                    return filter.isSpaceChar(c);
                return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
            }
     
            public String next() {
                return String();
            }
     
            public interface SpaceCharFilter {
                public boolean isSpaceChar(int ch);
            }
        }
     
        static class OutputWriter {
            private final PrintWriter writer;
     
            public OutputWriter(OutputStream outputStream) {
                writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
            }
     
            public OutputWriter(Writer writer) {
                this.writer = new PrintWriter(writer);
            }
     
            public void print(Object... objects) {
                for (int i = 0; i < objects.length; i++) {
                    if (i != 0)
                        writer.print(' ');
                    writer.print(objects[i]);
                }
            }
     
            public void printLine(Object... objects) {
                print(objects);
                writer.println();
            }
     
            public void close() {
                writer.close();
            }
     
            public void flush() {
                writer.flush();
            }
        }
     
        static InputReader in = new InputReader(System.in);
        static OutputWriter out = new OutputWriter(System.out);
     
        public static long[] sort(long[] a2) {
            int n = a2.length;
            ArrayList<Long> l = new ArrayList<>();
            for (long i : a2)
                l.add(i);
            Collections.sort(l);
            for (int i = 0; i < l.size(); i++)
                a2[i] = l.get(i);
            return a2;
        }
     
        public static int[] sort(int[] a2) {
            int n = a2.length;
            ArrayList<Integer> l = new ArrayList<>();
            for (int i : a2)
                l.add(i);
            Collections.sort(l);
            for (int i = 0; i < l.size(); i++)
                a2[i] = l.get(i);
            return a2;
        }
     
        public static long pow(long x, long y) {
            long res = 1;
            while (y > 0) {
                if (y % 2 != 0) {
                    res = (res * x);// % modulus;
                    y--;
     
                }
                x = (x * x);// % modulus;
                y = y / 2;
            }
            return res;
        }
     
    //GCD___+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public static long gcd(long x, long y) {
            if (x == 0)
                return y;
            else
                return gcd(y % x, x);
        }
        // ******LOWEST COMMON MULTIPLE
        // *********************************************
     
        public static long lcm(long x, long y) {
            return (x * (y / gcd(x, y)));
        }
     
    //INPUT PATTERN********************************************************
        public static int i() {
            return in.Int();
        }
     
        public static long l() {
            String s = in.String();
            return Long.parseLong(s);
        }
     
        public static String s() {
            return in.String();
        }
     
        public static int[] readArrayi(int n) {
            int A[] = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = i();
            }
            return A;
        }
     
        public static long[] readArray(long n) {
            long A[] = new long[(int) n];
            for (int i = 0; i < n; i++) {
                A[i] = l();
            }
            return A;
        }
    }

}