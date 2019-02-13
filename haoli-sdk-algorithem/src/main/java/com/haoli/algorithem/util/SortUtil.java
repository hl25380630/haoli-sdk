package com.haoli.algorithem.util;

/**
 * 排序工具包
 * @author 李昊
 */
public class SortUtil {
	
	public static void main(String[] args) {
		SortUtil su = new SortUtil();
		int[] array = {1, 4, 3, 5, 9, 8, 7, 2, 6};
		su.mergeSort(array);
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}
	
	public void mergeSort(int[] array) {
		this.sort(array, 0, array.length-1);
	}
	
	/**
	 * merge sort 归并排序
	 * @param array
	 */
	public void sort(int[] array, int start, int end) {
		if(start >= end) {
			return;
		}
		int mid = (end + start)/2;
		this.sort(array, start, mid);
		this.sort(array, mid+1, end);
		this.mergeArrays(array, start, mid, end);
	}
	
	public void mergeArrays(int[] array, int start, int mid, int end) {
		int[] tempArray = new int[array.length];
		int tempIndex = start;
		int index = start;
		int halfIndex = mid+1;
		while(start <= mid && halfIndex <= end) {
			if(array[start] <= array[halfIndex]) {
				tempArray[tempIndex] = array[start];
				tempIndex++;
				start++;
			}else {
				tempArray[tempIndex] = array[halfIndex];
				tempIndex++;
				halfIndex++;
			}
		}
		while(start <= mid) {
			tempArray[tempIndex] = array[start];
			tempIndex++;
			start++;
		}
		while(halfIndex <=end) {
			tempArray[tempIndex] = array[halfIndex];
			tempIndex++;
			halfIndex++;
		}
		while(index <= end) {
			array[index] = tempArray[index];
			index++;
		}
	}

}
