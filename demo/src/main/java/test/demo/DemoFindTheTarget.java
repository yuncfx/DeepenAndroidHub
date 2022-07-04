package test.demo;

import structure._011_hash.map.HashMap;
import structure._011_hash.map.Map;

public class DemoFindTheTarget {
  public static void main(String[] args) {
    int[] num = new int[] { 1, 5, 2, 10, 8, 3, 7, 9, 4 };
    int target = 7;
    String str = findTarget(target, num);
    System.out.println(str);
  }

  static String findTarget(int target, int[] num) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
      if (map.containsKey(num[i])) {
        System.out.println("map.get(num[" + i + "])=" + map.get(num[i]) + ", num[" + i + "]=" + num[i]);
        String s =  num[i] + " + " + map.get(num[i]);
        System.out.println("sss: " + s);
      }
      int t = target - num[i];
      System.out.println("target-num[" + i + "]=" + (t) + ", num[" + i + "]=" + num[i]);
      map.put(target - num[i], num[i]);
    }

    return "null";
  }
}


