package server;

public class Quicksort {

	public Comparable[] sort(Comparable[] a)
	{
		quickSort(a, 0, a.length-1);
		return a;
	}
	private void quickSort(Comparable[] a, int lo, int hi)
	{
		if(lo<=hi)
		{
			int pivot = partition(a, lo, hi);
			quickSort(a,lo,pivot-1);
			quickSort(a, pivot+1,hi);
		}
	}
	private int partition(Comparable[] a, int lo, int hi)
	{
		int cur = lo+1;
		int lastlo = lo;
		while(cur<=hi)
		{
			if(a[cur].compareTo(a[lo])<0)
			{
				swap(a, cur, lastlo+1);
				lastlo++;
			}
			cur++;
		}
		swap(a, lastlo, lo);
		return lastlo;
	}
	private void swap(Comparable[] a, int b, int c) {
		Comparable temp = a[b];
		a[b]=a[c];
		a[c]=temp;
	}
}
