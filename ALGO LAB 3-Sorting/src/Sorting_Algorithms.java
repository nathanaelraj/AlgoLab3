import java.io.BufferedWriter;
import java.io.FileWriter;

public class Sorting_Algorithms {
	private static int count;
	
	public static void main(String[] args){
		for (int n = 2000; n <=10000; n += 2000){
			sorting(n);
		}
	}
	
	public static int[] arrayGen(int n){
		int i;
		int[] array = new int[n];
		for(i = 0; i < n; i++){
			array[i] = (int) (Math.random() * 10000);	
		}	
		return array;
	}
	
	public static void quickSortAsc(int s, int e, int[] ar){
		if (e-s <= 0) return;
		int piv = partitionAsc(s, e, ar);
		quickSortAsc(s, (piv-1), ar);
		quickSortAsc((piv+1), e, ar);
	}
	public static int partitionAsc(int s, int e, int[] ar){
		int last_small = s;
		int tmp;
		int pivot = ar[s]; 
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
	
	public static void quickSortDsc(int s, int e, int[] ar){
		if (e-s <= 0) return;
		int piv = partitionDsc(s, e, ar);
		quickSortDsc(s, (piv-1), ar);
		quickSortDsc((piv+1), e, ar);
	}
	public static int partitionDsc(int s, int e, int[] ar){
		int last_small = s;
		int tmp;
		int pivot = ar[s]; 
		for (int i = s+1; i<=e; i++){
			if (ar[i] > pivot){
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
	
	public static void MergeSortAsc(int s, int e, int[] ar){
		if (s == e) return;
		else{
			int mid = (s+e)/2;
			MergeSortAsc(s, mid, ar);
			MergeSortAsc(mid+1, e, ar);
			MergeAsc(s, e, ar);
			return;
		}
		
	}
	public static void MergeAsc(int s, int e, int[] ar){
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
	
	public static void MergeSortDsc(int s, int e, int[] ar){
		if (s == e) return;
		else{
			int mid = (s+e)/2;
			MergeSortDsc(s, mid, ar);
			MergeSortDsc(mid+1, e, ar);
			MergeDcs(s, e, ar);
			return;
		}
		
	}
	public static void MergeDcs(int s, int e, int[] ar){
		if ((e - s)<= 1){
			if (ar[s] >= ar[e]){
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
				if (temp[a] >= temp[b]){
					ar[i] = temp[a++];
					count++;
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
		int[] toSort = arrayGen(n);
		int[] toMergeDsc = new int[n];
		int[] toMergeAsc = new int[n];
		int[] toQuickDsc = new int[n];
		int[] toQuickAsc = new int[n];
		for (int i = 0; i<n; i++){
			toMergeDsc[i] = toSort[i];			
		}
		for (int i = 0; i<n; i++){
			toMergeAsc[i] = toSort[i];			
		}
		for (int i = 0; i<n; i++){
			toQuickDsc[i] = toSort[i];			
		}
		for (int i = 0; i<n; i++){
			toQuickAsc[i] = toSort[i];			
		}
		
		count = 0;
		long starttime = System.nanoTime();
		MergeSortDsc(0, n-1, toMergeDsc);
		long endtime = System.nanoTime();
		System.out.println("Descending MergeSort runtime for size = " + n +": "+((endtime - starttime) +" nanoseconds"));
		System.out.println("No. of comparisons made: " + count);
		
		count = 0;
		starttime = System.nanoTime();
		MergeSortAsc(0, n-1, toMergeAsc);
		endtime = System.nanoTime();
		System.out.println("Ascending MergeSort runtime for size = " + n +": "+((endtime - starttime) +" nanoseconds"));
		System.out.println("No. of comparisons made: " + count);
		System.out.println();
		
		count = 0;
		starttime = System.nanoTime();
		quickSortDsc(0, n-1, toQuickDsc);
		endtime = System.nanoTime();
		System.out.println("Descending QuickSort runtime for size = " + n +": "+((endtime - starttime) +" nanoseconds"));
		System.out.println("No. of comparisons made: " + count);
		
		count = 0;
		starttime = System.nanoTime();
		quickSortAsc(0, n-1, toQuickAsc);
		endtime = System.nanoTime();
		System.out.println("Ascending QuickSort runtime for size = " + n +": "+((endtime - starttime) +" nanoseconds"));
		System.out.println("No. of comparisons made: " + count);
		System.out.println();
		
		try{
		    FileWriter fstream = new FileWriter(System.currentTimeMillis() +".Sort." +n +".txt");
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write("Unordered array:" );
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(toSort[k]) +" ");
		    }
		    out.newLine();
		    out.write("Merged Sorted in Ascending order:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(toMergeAsc[k]) +" ");
		    }
		    out.newLine();
		    out.write("Merge Sorted in Descending order:\n");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(toMergeDsc[k]) +" ");
		    }
		    out.newLine();
		    out.write("Quick Sorted in Ascending order:");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(toQuickAsc[k]) +" ");
		    }
		    out.newLine();
		    out.write("Quick Sorted in Descending order:\n");
		    out.newLine();
		    for(int k = 0; k < n; k++){
		        out.write(String.valueOf(toQuickDsc[k]) +" ");
		    }
		    out.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
}
