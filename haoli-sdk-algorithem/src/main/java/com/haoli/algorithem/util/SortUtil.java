package com.haoli.algorithem.util;

import java.util.Arrays;

/**
 * 排序工具包
 * @author 李昊
 */
public class SortUtil {
	
	public static void main(String[] args) {
		int[] array = {1, 3, 2, 2, 3, 1};
		SortUtil su = new SortUtil();
		su.wiggleSort2(array);
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
	/**
	 * 摆动排序
	 */
	public int[] wiggleSort(int[] array) {
		Arrays.sort(array);
		int startIndex = 0;
		int lastIndex = array.length-1;
		int midIndex = (lastIndex + startIndex)/2;
		int leftIndex = startIndex;
		int rightIndex = midIndex+1;
		int index = 0;
		int[] result = new int[array.length];
		while(leftIndex <= midIndex || rightIndex <= lastIndex) {
			if(index%2 == 0) {
				if(leftIndex > midIndex) {
					continue;
				}
				result[index++] = array[leftIndex++];
			}else {
				if(rightIndex > lastIndex) {
					continue;
				}
				result[index++] = array[rightIndex++];
			}
		}
		return result;
	}
	
	/**
	 * 摆动排序2
	 * leet code 324
	 */
	public void wiggleSort2(int[] array) {
		if(array.length <=1) {
			return;
		}
		int index1 = 0;
		int index2 = 1;
		int current = 0;
		while(index2 <= array.length-1 && current <= array.length-1) {
			if(current % 2 ==0) {
				if(array[index2] > array[index1]) {
					current ++;
					index1++;
					index2++;
				}else {
					int indexTemp = index2;
					while(indexTemp <= array.length-1 && array[indexTemp] <= array[index1]) {
						indexTemp++;
						continue;
					}
					if(indexTemp > array.length-1) {
						break;
					}
					int value = array[indexTemp];
					int temp = array[index2];
					array[index2] = value;
					array[indexTemp] = temp;
					current++;
					index1++;
					index2++;
				}
			}else {
				if(array[index2] < array[index1]) {
					current++;
					index1++;
					index2++;
				}else {
					int indexTemp = index2;
					while(array[indexTemp] >= array[index1]) {
						indexTemp++;
						continue;
					}
					int value = array[indexTemp];
					int temp = array[index2];
					array[index2] = value;
					array[indexTemp] = temp;
					current++;
					index1++;
					index2++;
				}
			}
		}
	}
	
	/**
	 *快速排序 （空间不稳定）
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
        	//从后往前找，如果碰到小于基准值的数，则停止
        	while(standard <= array[end] && start < end) {
        		end--;
        	}
        	//从前往后找，如果碰到大于基准值的数，则停止
        	while(standard >= array[start] && start < end) {
        		start++;
        	}
        	//如果此时左哨兵位置仍小于右哨兵的位置，则左哨兵与右哨兵所在位置的值交换
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
	 * merge sort 归并排序（空间稳定）
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
