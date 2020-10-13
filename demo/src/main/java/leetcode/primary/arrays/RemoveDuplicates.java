package leetcode.primary.arrays;

import bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * @author shane
 *
 */

public class RemoveDuplicates {

	/**
	 * 定义一个index，记录返回数组的有效位置
	 * 定义一个i，遍历整个数组，因为数组是有序的，所以只要碰到第一个nums[i]的值不等于nums[index], 就讲index的值加1， 并将nums[i]的值赋值给nums[index].
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
