package com.haoli.algorithem.util;

public class ArrayUtil {
	
	
	/**
	 *  删除一个排好序数组的重复元素 （Remove Duplicates from Sorted Array）
	 *  leetcode 26
	 *  @param nums sorted array
	 */
	public int removeDuplicateFromSortedArray(int[] nums) {
		int index1 = 0;
		int index2 = 1;
		int count = 1;
		while(index2 < nums.length) {
			if(nums[index2] != nums[index1]) {
				index2++;
				index1++;
				count++;
			}else {
				while(index2 < nums.length) {
					if(nums[index2] != nums[index1]) {
						int temp = nums[index2];
						nums[index1+1] = temp;
						index1++;
						count++;
						break;
					}
					index2++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 合并两个已经排序好的数组(Merge Sorted Array)
	 * leetcode 88 
	 * @param nums1 数组1
	 * @param m 数组1有效元素数量
	 * @param nums2 数组2
	 * @param n 数组2有效元素数量
	 */
	public void mergeTwoSortedArray(int[] nums1, int m, int[] nums2, int n) {
		int index1 = 0;
		int index2 = 0;
		int index = 0;
		int totalLength = m+n;
		int[] result = new int[totalLength];
		while(index1 < m && index2 < n) {
			if(index <= totalLength-1 && nums1[index1] < nums2[index2]) {
				result[index++] = nums1[index1++];
			}else {
				result[index++] = nums2[index2++];
			}
		}
		while(index1 < m) {
			result[index++] = nums1[index1++];
		}
		while(index2 < n) {
			result[index++] = nums2[index2++];
		}
		for(int i=0; i<result.length; i++) {
			nums1[i] = result[i];
		}
	}
}
