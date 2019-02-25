package com.haoli.algorithem.util;

import java.util.Arrays;

/**
 * 排序工具包
 * @author 李昊
 */
public class SortUtil {
	
	public static void main(String[] args) {
		SortUtil su = new SortUtil();
		int[] nums1 = {1,2,3,0,0,0};
		int[] nums2 = {2,5,6};
		su.mergeTwoSortedArray(nums1, 3, nums2, 3);
		for(int i=0; i<nums1.length; i++) {
			System.out.print(nums1[i] + " ");
		}
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
	
	/**
	 * 合并两个排好序的链表（merge two sorted Lists）
	 * leetcode 21
	 * @param l1
	 * @param l2
	 */
	public ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
		ListNode index1 = l1;
		ListNode index2 = l2;
		ListNode index = new ListNode(0);
		ListNode result = index;
		while(index1 != null && index2 != null) {
			if(index1.val < index2.val) {
				ListNode node = new ListNode(index1.val);
				index.next = node;
				index = index.next;
				index1 = index1.next;
			}else{
				ListNode node = new ListNode(index2.val);
				index.next = node;
				index = index.next;
				index2 = index2.next;
			}
		}
		while(index1 != null) {
			ListNode node = new ListNode(index1.val);
			index.next = node;
			index = index.next;
			index1 = index1.next;
		}
		while(index2 != null) {
			ListNode node = new ListNode(index2.val);
			index.next = node;
			index = index.next;
			index2 = index2.next;
		}
		return result.next;
	}
	
	/**
	 * 摆动排序2
	 * leet code 324
	 */
	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		int[] newnums = new int[nums.length];//定义新数组将原数组隔位插入
		for (int i = nums.length - 1, j = 1; i >= 0 && j < nums.length ; i--, j = j + 2) {
			//将数组从大到小开始从newnums[1]隔行插入进新数组，偶数数组到nums.length-1结束，奇数数组到nums.length-1-1处结束。
			//举例 newnums｛0，6，0，5，0，4｝j的值分别为 1，3，5
			newnums[j] = nums[i];		
		}
		//继续将数组从大到小开始从newnums[0]隔行插入进新数组，偶数数组到nums.length-1-1结束，奇数数组到nums.length-1处结束。保证数组中每个位置都有值。
		//举例 newnums｛3，6，2，5，1，4｝j的值分别为 0，2，4
		for (int i = (nums.length - 1) / 2, j = 0; i >= 0 && j < nums.length; i--, j = j + 2) {
			newnums[j] = nums[i];		}
		//将排列好的数组数据赋给原数组
		for (int i = 0; i < nums.length; i++) {
			nums[i] = newnums[i];
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
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { 
			val = x; 
		}
	}

}
