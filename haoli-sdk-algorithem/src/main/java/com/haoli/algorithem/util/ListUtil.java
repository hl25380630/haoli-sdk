package com.haoli.algorithem.util;

public class ListUtil {
	
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
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { 
			val = x; 
		}
	}

}
