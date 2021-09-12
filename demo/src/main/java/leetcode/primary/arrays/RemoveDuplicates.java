package leetcode.primary.arrays;

import bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ����һ���������飬����Ҫ��ԭ��ɾ���ظ����ֵ�Ԫ�أ�ʹ��ÿ��Ԫ��ֻ����һ�Σ������Ƴ���������³��ȡ�
 *
 * ��Ҫʹ�ö��������ռ䣬�������ԭ���޸��������鲢��ʹ�� O(1) ����ռ����������ɡ�
 * 
 * @author shane
 *
 */

public class RemoveDuplicates {

	/**
	 * ����һ��index����¼�����������Чλ��
	 * ����һ��i�������������飬��Ϊ����������ģ�����ֻҪ������һ��nums[i]��ֵ������nums[index], �ͽ�index��ֵ��1�� ����nums[i]��ֵ��ֵ��nums[index].
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return nums.length;
		}

		int index = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[index]) {
				index++;
				nums[index] = nums[i];
			}
		}

		return index + 1;
	}

	private void removeArray() {
		List<Person> musics = new ArrayList<>();
		musics.add(new Person("zhangsan", "haidian", 30, 0, 100));
		musics.add(new Person("lisi", "changping", 30, 0, 101));
		musics.add(new Person("wangwu", "chaoyang", 30, 0, 102));
		musics.add(new Person("zhangsan", "haidian", 30, 0, 100));
		musics.add(new Person("zhangsan", "haidian", 30, 0, 100));
		musics.add(new Person("zhangsan", "haidian", 30, 0, 100));
		musics.add(new Person("lisi", "changping", 30, 0, 101));
		musics.add(new Person("wangwu", "chaoyang", 30, 0, 102));
		musics.add(new Person("zhaoliu", "xicheng", 30, 0, 103));

		List<Person> newMusics = new ArrayList<>(musics);
		for (int i = 0; i < musics.size() - 1; i++) {
			Person info1 = musics.get(i);
			for (int j = i+1; j < newMusics.size(); j++) {
				Person info2 = newMusics.get(j);
				if (info1.getId() == info2.getId() && Objects.equals(info1.getAddress(), info2.getAddress())) {
					musics.remove(info2);
				}
			}
		}

		for (Person p: newMusics) {
			System.out.println("p:" + p);
		}

		System.out.println("...........");

		for (Person p: musics) {
			System.out.println("p:" + p);
		}
	}

	public static void main(String[] args) {
		new RemoveDuplicates().removeArray();
	}
}
