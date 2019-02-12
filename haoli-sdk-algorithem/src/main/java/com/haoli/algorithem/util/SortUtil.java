package com.haoli.algorithem.util;

/**
 * 排序工具包
 * @author 李昊
 */
public class SortUtil {
	
	/**
	 * merge sort 归并排序
	 * @param array
	 */
	public int[] mergeSort(int[] array) {
		int start = 0;
		int end = array.length-1;
		int mid = (end -start)/2;
		int[] array1 = this.sort(array, start, mid);
		int[] array2 = this.sort(array, mid+1, end);
		int[] result = this.mergeArrays(array1, array2);
		return result;
	}
	
	public int[] sort(int[] array, int start, int end) {
		int[] tempArray = new int[array.length];
		if(start >= end) {
			tempArray[start] = array[start];
			return tempArray;
		}
		
		return null;
	}
	
	public int[] mergeArrays(int[] array1, int[] array2) {
		return null;
	}

}
