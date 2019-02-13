package com.haoli.algorithem.util;

/**
 * 排序工具包
 * @author 李昊
 */
public class SortUtil {
	
	public static void main(String[] args) {
		int[] array = {1, 5, 2, 4, 3,6};
		SortUtil su = new SortUtil();
		su.quickSort(array);
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
	/**
	 * 摆动排序
	 */
	public void wiggleSort(int[] array) {
		
	}
	
	/**
	 *快速排序 
	 *时间复杂度:最坏O(n2), 最优O(nlogn)
	 */
	public void quickSort(int[] array) {
		this.quickSort(array, 0, array.length-1);
	}
	
	public void quickSort(int[] array, int low, int high) {
        if(low > high){
            return;
        }
        int start = low; //左哨兵
        int sindex = start;
        int end = high; //右哨兵
        int standard = array[low]; //基准值
        while(start < end) {
        	while(standard <= array[end] && start < end) {
        		end--;
        	}
        	while(standard >= array[start] && start < end) {
        		start++;
        	}
        	if(start < end) {
        		int startValue = array[start];
        		int endValue = array[end];
        		int tempValue = startValue;
        		array[start] = endValue;
        		array[end] = tempValue;
        	}
        }
        int value = array[start];
        array[sindex] = value;
        array[start] = standard;
        
        this.quickSort(array, low, end-1);
        this.quickSort(array, end+1, high);
	}
	
	/**
	 * merge sort 归并排序
	 * 时间复杂度O(nlogn), 空间复杂度O(n)
	 * @param array
	 */
	public int[] mergeSort(int[] array) {
		int[] result = this.sort(array, 0, array.length-1);
		return result;
	}

	public int[] sort(int[] array, int start, int end) {
		if(start >= end) {
			int[] temp = new int[1];
			temp[0] = array[start];
			return temp;
		}
		int mid = (end + start)/2;
		int[] left = this.sort(array, start, mid);
		int[] right = this.sort(array, mid+1, end);
		int[] result = this.mergeArrays(left, right);
		return result;
	}
	
	public int[] mergeArrays(int[] left, int[] right) {
		int leftIndex = 0;
		int rightIndex = 0;
		int leftEnd = left.length-1;
		int rightEnd = right.length-1;
		int[] temp = new int[left.length + right.length];
		int index = 0;
		while(leftIndex <= leftEnd && rightIndex <= rightEnd) {
			if(left[leftIndex] <= right[rightIndex]) {
				temp[index++] = left[leftIndex++];
			}else {
				temp[index++] = right[rightIndex++];
			}
		}
		if(leftIndex > leftEnd) {
			while(rightIndex<=rightEnd) {
				temp[index++] = right[rightIndex++];
			}
		}else {
			while(leftIndex<=leftEnd) {
				temp[index++] = left[leftIndex++];
			}
		}
		return temp;
	}

}
