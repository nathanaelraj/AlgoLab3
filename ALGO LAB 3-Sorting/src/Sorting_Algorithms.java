import java.io.BufferedWriter;
import java.io.FileWriter;

public class Sorting_Algorithms {
	private static int count;
	
	public static void main(String[] args){
		for (int n = 2000; n <=10000; n += 2000){
			sorting(n);
		}
	}
	
	public static int[] randArrayGen(int n){
		int i;
		int[] array = new int[n];
		for(i = 0; i < n; i++){
			array[i] = (int) (Math.random() * 10000);	
		}	
		return array;
	}
	public static int[] ascArrayGen(int n){
		int i;
		int[] array = new int[n];
		for(i = 0; i < n; i++){
			array[i] = i;	
		}	
		return array;
	}
	public static int[] dscArrayGen(int n){
		int i;
		int[] array = new int[n];
		for(i = 0; i < n; i++){
			array[i] = (n - i);	
		}	
		return array;
	}
	
	public static void quickSort(int s, int e, int[] ar){
		if (e-s <= 0) return;
		int piv = partition(s, e, ar);
		quickSort(s, (piv-1), ar);
		quickSort((piv+1), e, ar);
	}
	public static int partition(int s, int e, int[] ar){
		int last_small = s;
		int tmp;
		int pivot = ar[s]; 
		int mid = (s+e)/2;
		tmp = ar[mid];
		ar[mid] = ar[s];
		ar[s] = tmp;
		for (int i = s+1; i<=e; i++){
			if (ar[i] < pivot){
				tmp = ar[++last_small];
				ar[last_small] = ar[i];
				ar[i] = tmp;
				
			}
			count++;
		}
		tmp = ar[last_small];
		ar[last_small] = ar[s];
		ar[s] = tmp;
		return last_small;		
	}
	
	
	public static void mergeSort(int s, int e, int[] ar){
		if (s == e) return;
		else{
			int mid = (s+e)/2;
			mergeSort(s, mid, ar);
			mergeSort(mid+1, e, ar);
			merge(s, e, ar);
			return;
		}
		
	}
	public static void merge(int s, int e, int[] ar){
		if ((e - s)<= 1){
			if (ar[s] <= ar[e]){
				count++;
				return;
			}
			else{
				count++;
				int tmp;
				tmp = ar[e];
				ar[e] = ar[s];
				ar[s] = tmp;
				return;
			}
		}
		else {
			int mid = (s+e)/2;
			int [] temp = new int[ar.length];
			int a, b, i;
			for (i = 0; i < ar.length; i++){
				temp[i] = ar[i];				
			}
			a = s;
			b = mid + 1;
			for (i = s; i <= e; i++){
				if (temp[a] <= temp[b]){
					count++;
					ar[i] = temp[a++];
					}
				else {
					count++;
					ar[i] = temp[b++];
				}
				if(a > mid) break;
				if(b > e){
					while (i < e){
						ar[++i] = temp[a++];					
					}
					break;
				}			
			}
	
			return;
		}		
	}

	public static void sorting(int n) {
		int[] randQuick = randArrayGen(n);
		int[] ascQuick = ascArrayGen(n);
		int[] dscQuick = dscArrayGen(n);
		int[] randMerge = randArrayGen(n);
		int[] ascMerge = ascArrayGen(n);
		int[] dscMerge = dscArrayGen(n);
		int[] test = dscArrayGen(n);
		
		count = 0;
		long starttime = System.nanoTime();
		mergeSort(0, n-1, randMerge);
		long endtime = System.nanoTime();
		System.out.println("MergeSorting random array of size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		
		count = 0;
		starttime = System.nanoTime();
		mergeSort(0, n-1, ascMerge);
		endtime = System.nanoTime();
		System.out.println("MergeSorting ascending array of size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		
		
		count = 0;
		starttime = System.nanoTime();
		mergeSort(0, n-1, dscMerge);
		endtime = System.nanoTime();
		System.out.println("MergeSorting descending array of size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		System.out.println();
		
		count = 0;
		starttime = System.nanoTime();
		quickSort(0, n-1, randQuick);
		endtime = System.nanoTime();
		System.out.println("QuickSorting random array of size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		
		count = 0;
		starttime = System.nanoTime();
		quickSort(0, n-1, ascQuick);
		endtime = System.nanoTime();
		System.out.println("QuickSorting ascending array size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		
		
		count = 0;
		starttime = System.nanoTime();
		quickSort(0, n-1, dscQuick);
		endtime = System.nanoTime();
		System.out.println("QuickSorting descending array of size = " + n +" takes: "+((endtime - starttime) +" nanoseconds"));
		System.out.println("Comparisons: " + count);
		System.out.println();
		
		try{
		    FileWriter fstream = new FileWriter(System.currentTimeMillis() +".Sort." +n +".txt");
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write("Unordered array:" );
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(randMerge[k]) +" ");
		    }
		    out.newLine();
		    out.write("Random array MergeSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(randMerge[k]) +" ");
		    }
		    out.newLine();
		    out.write("Ascending array MergeSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(ascMerge[k]) +" ");
		    }
		    out.newLine();
		    out.write("Descending array MergeSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(dscMerge[k]) +" ");
		    }
		    out.newLine();
		    out.write("Random array QuickSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(randQuick[k]) +" ");
		    }
		    out.newLine();
		    out.write("Ascending array QuickSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(ascQuick[k]) +" ");
		    }
		    out.newLine();
		    out.write("Descending array QuickSorted:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(dscQuick[k]) +" ");
		    }
		    
		    out.close();
		    }catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
		    }
	}
}
