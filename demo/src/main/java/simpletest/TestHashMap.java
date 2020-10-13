package simpletest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * ��֪һ�� HashMap<Integer��User>���ϣ� User �� name��String���� age��int�����ԡ���дһ������ʵ�� ��
 * HashMap �������ܣ� �÷������� HashMap<Integer�� User>Ϊ�βΣ� ��������Ϊ HashMap<Integer�� User>��
 * Ҫ��� HashMap �е� User �� age ���������������ʱ key=value ��ֵ�Բ��ò�ɢ
 */

class TestHashMap {
	public static void main(String[] args) {
		HashMap<Integer, User> map = new HashMap<>();

		map.put(1, new User("zhangsan", 19));
		map.put(2, new User("lisi", 37));
		map.put(3, new User("wangwu", 8));

		System.out.println("before " + map);
		for (Entry<Integer, User> entry : map.entrySet()) {
			System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
		}

		map = new TestHashMap().t(map);

		System.out.println(" after " + map);
		for (Entry<Integer, User> entry : map.entrySet()) {
			System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
		}

	}

	public HashMap<Integer, User> t(HashMap<Integer, User> map) {

		Set<Entry<Integer, User>> entrySet = map.entrySet();
		ArrayList<Entry<Integer, User>> list = new ArrayList<>(entrySet);

		Collections.sort(list, new Comparator<Entry<Integer, User>>() {

			@Override
			public int compare(Entry<Integer, User> o1, Entry<Integer, User> o2) {
				return o2.getValue().age - o1.getValue().age;
			}

		});

		LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<Integer, User>();
		for (Entry<Integer, User> entry : list) {
			linkedHashMap.put(entry.getKey(), entry.getValue());
		}

		return linkedHashMap;

	}
}

class User {
	String name;
	int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return name + " " + age;
	}

}
