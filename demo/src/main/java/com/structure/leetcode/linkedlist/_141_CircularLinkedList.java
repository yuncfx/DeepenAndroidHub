package com.structure.leetcode.linkedlist;

public class _141_CircularLinkedList {
	
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) return true;
		}
		
		return false;
    }
	
}
