package structure.leetcode.linkedlist;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author MJ Lee
 *
 */
public class _237_RemoveNodeInLinkedList {
	
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
    }
}
