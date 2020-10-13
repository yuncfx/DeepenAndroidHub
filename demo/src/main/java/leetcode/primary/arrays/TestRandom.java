package leetcode.primary.arrays;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRandom {
    private Random random = new SecureRandom();

    private List<Integer> mIndexes;

    private TestRandom() {
        mIndexes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (random.nextBoolean()) {
                mIndexes.add(i);
            }
        }

        for (int i = 0; i < mIndexes.size(); i++) {
            System.out.println("index:" + i + ", value:" + mIndexes.get(i));
        }
    }

    private int getRandomNumberNew(List<Integer> indexes, int currentIndex) {
        int total = indexes.size();
        System.out.println("getRandomNumberNew total:" + total + ", currentIndex:" + currentIndex);
        if (total <= 1) {
            return indexes.get(0);
        }

        int randomNum = random.nextInt(total);
        while (randomNum == currentIndex) {
            randomNum = random.nextInt(total);
        }
        int index = indexes.get(randomNum);
        System.out.println("getRandomNumberNew randomNum:" + randomNum + ", value:" + index);
        if (isItemValid(index)) {
            return index;
        } else {
            mIndexes.remove(randomNum);
            return getRandomNumberNew(mIndexes, currentIndex);
        }
    }

    private boolean isItemValid(int index) {
        boolean flag = random.nextBoolean();
        System.out.println("isItemValid:" + flag);
        return flag;
    }

    public static void main(String[] args) {
        TestRandom testRandom = new TestRandom();
        if (testRandom.mIndexes.size() > 2) {
            testRandom.getRandomNumberNew(testRandom.mIndexes, testRandom.mIndexes.get(2));
        }
    }
}
