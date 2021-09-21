package structure._010_map.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings({"unchecked", "unused"})
public class TreeMap<K, V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private int size;
	private Node<K, V> root;
	private Comparator<K> comparator;
	
	public TreeMap() {
		this(null);
	}
	
	public TreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		keyNotNullCheck(key);
		
		// ��ӵ�һ���ڵ�
		if (root == null) {
			root = new Node<>(key, value, null);
			size++;

			// ����ӽڵ�֮��Ĵ���
			afterPut(root);
			return null;
		}
		
		// ��ӵĲ��ǵ�һ���ڵ�
		// �ҵ����ڵ�
		Node<K, V> parent = root;
		Node<K, V> node = root;
		int cmp = 0;
		do {
			cmp = compare(key, node.key);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // ���
				node.key = key;
				V oldValue = node.value;
				node.value = value;
				return oldValue;
			}
		} while (node != null);

		// �������뵽���ڵ���ĸ�λ��
		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		
		// ����ӽڵ�֮��Ĵ���
		afterPut(newNode);
		return null;
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node != null ? node.value : null;
	}

	@Override
	public V remove(K key) {
		return remove(node(key));
	}

	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if (root == null) return false;
		
		Queue<Node<K, V>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<K, V> node = queue.poll();
			if (valEquals(value, node.value)) return true;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (visitor == null) return;
		traversal(root, visitor);
	}
	
	private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
		if (node == null || visitor.stop) return;
		
		traversal(node.left, visitor);
		if (visitor.stop) return;
		visitor.visit(node.key, node.value);
		traversal(node.right, visitor);
	}
	
	private boolean valEquals(V v1, V v2) {
		return v1 == null ? v2 == null : v1.equals(v2);
	}
	
	private V remove(Node<K, V> node) {
		if (node == null) return null;
		
		size--;
		
		V oldValue = node.value;
		
		if (node.hasTwoChildren()) { // ��Ϊ2�Ľڵ�
			// �ҵ���̽ڵ�
			Node<K, V> s = successor(node);
			// �ú�̽ڵ��ֵ���Ƕ�Ϊ2�Ľڵ��ֵ
			node.key = s.key;
			node.value = s.value;
			// ɾ����̽ڵ�
			node = s;
		}
		
		// ɾ��node�ڵ㣨node�Ķȱ�Ȼ��1����0��
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node�Ƕ�Ϊ1�Ľڵ�
			// ����parent
			replacement.parent = node.parent;
			// ����parent��left��right��ָ��
			if (node.parent == null) { // node�Ƕ�Ϊ1�Ľڵ㲢���Ǹ��ڵ�
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
			
			// ɾ���ڵ�֮��Ĵ���
			afterRemove(replacement);
		} else if (node.parent == null) { // node��Ҷ�ӽڵ㲢���Ǹ��ڵ�
			root = null;
		} else { // node��Ҷ�ӽڵ㣬�����Ǹ��ڵ�
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}
			
			// ɾ���ڵ�֮��Ĵ���
			afterRemove(node);
		}
		
		return oldValue;
	}
	
	private void afterRemove(Node<K, V> node) {
		// ���ɾ���Ľڵ��Ǻ�ɫ
		// ���� ����ȡ��ɾ���ڵ���ӽڵ��Ǻ�ɫ
		if (isRed(node)) {
			black(node);
			return;
		}
		
		Node<K, V> parent = node.parent;
		if (parent == null) return;
		
		// ɾ�����Ǻ�ɫҶ�ӽڵ㡾���硿
		// �жϱ�ɾ����node��������
		boolean left = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = left ? parent.right : parent.left;
		if (left) { // ��ɾ���Ľڵ�����ߣ��ֵܽڵ����ұ�
			if (isRed(sibling)) { // �ֵܽڵ��Ǻ�ɫ
				black(sibling);
				red(parent);
				rotateLeft(parent);
				// �����ֵ�
				sibling = parent.right;
			}
			
			// �ֵܽڵ��Ȼ�Ǻ�ɫ
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// �ֵܽڵ�û��1����ɫ�ӽڵ㣬���ڵ�Ҫ���¸��ֵܽڵ�ϲ�
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent);
				}
			} else { // �ֵܽڵ�������1����ɫ�ӽڵ㣬���ֵܽڵ��Ԫ��
				// �ֵܽڵ������Ǻ�ɫ���ֵ�Ҫ����ת
				if (isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
		} else { // ��ɾ���Ľڵ����ұߣ��ֵܽڵ������
			if (isRed(sibling)) { // �ֵܽڵ��Ǻ�ɫ
				black(sibling);
				red(parent);
				rotateRight(parent);
				// �����ֵ�
				sibling = parent.left;
			}
			
			// �ֵܽڵ��Ȼ�Ǻ�ɫ
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// �ֵܽڵ�û��1����ɫ�ӽڵ㣬���ڵ�Ҫ���¸��ֵܽڵ�ϲ�
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent);
				}
			} else { // �ֵܽڵ�������1����ɫ�ӽڵ㣬���ֵܽڵ��Ԫ��
				// �ֵܽڵ������Ǻ�ɫ���ֵ�Ҫ����ת
				if (isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}
		}
	}

	private Node<K, V> predecessor(Node<K, V> node) {
		if (node == null) return null;
		
		// ǰ���ڵ������������У�left.right.right.right....��
		Node<K, V> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		// �Ӹ��ڵ㡢�游�ڵ���Ѱ��ǰ���ڵ�
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}

		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
	
	private Node<K, V> successor(Node<K, V> node) {
		if (node == null) return null;
		
		// ǰ���ڵ������������У�right.left.left.left....��
		Node<K, V> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		// �Ӹ��ڵ㡢�游�ڵ���Ѱ��ǰ���ڵ�
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}

		return node.parent;
	}
	
	private Node<K, V> node(K key) {
		Node<K, V> node = root;
		while (node != null) {
			int cmp = compare(key, node.key);
			if (cmp == 0) return node;
			if (cmp > 0) {
				node = node.right;
			} else { // cmp < 0
				node = node.left;
			}
		}
		return null;
	}
	
	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		
		// ��ӵ��Ǹ��ڵ� ���� ���絽���˸��ڵ�
		if (parent == null) {
			black(node);
			return;
		}
		
		// ������ڵ��Ǻ�ɫ��ֱ�ӷ���
		if (isBlack(parent)) return;
		
		// �常�ڵ�
		Node<K, V> uncle = parent.sibling();
		// �游�ڵ�
		Node<K, V> grand = red(parent.parent);
		if (isRed(uncle)) { // �常�ڵ��Ǻ�ɫ��B���ڵ����硿
			black(parent);
			black(uncle);
			// ���游�ڵ㵱��������ӵĽڵ�
			afterPut(grand);
			return;
		}
		
		// �常�ڵ㲻�Ǻ�ɫ
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
			} else { // LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else { // R
			if (node.isLeftChild()) { // RL
				black(node);
				rotateRight(parent);
			} else { // RR
				black(parent);
			}
			rotateLeft(grand);
		}
	}
	
	private void rotateLeft(Node<K, V> grand) {
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}
	
	private void rotateRight(Node<K, V> grand) {
		Node<K, V> parent = grand.left;
		Node<K, V> child = parent.right;
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
		// ��parent��Ϊ�����ĸ��ڵ�
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // grand��root�ڵ�
			root = parent;
		}
		
		// ����child��parent
		if (child != null) {
			child.parent = grand;
		}
		
		// ����grand��parent
		grand.parent = parent;
	}

	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node == null) return node;
		node.color = color;
		return node;
	}
	
	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}
	
	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}
	
	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}
	
	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}
	
	private int compare(K e1, K e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<K>)e1).compareTo(e2);
	}
	
	private void keyNotNullCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key must not be null");
		}
	}

	private static class Node<K, V> {
		K key;
		V value;
		boolean color = RED;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;
		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
		
		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			
			if (isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
	}
}
