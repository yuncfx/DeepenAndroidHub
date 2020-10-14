package test.refactor;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author shane
 *         并发集合库 CopyOnWriteArrayList
 */
public class CollectionModifyExceptionTest {

    public static void main(String[] args) {

        Collection<String> user = new CopyOnWriteArrayList<>();

        user.add("zhangsan");
        user.add("lisi");
        user.add("wangwu");

        for (String s : user) {
            if ("zhangsan".equals(user)) {
                user.remove(s);
            }
            System.out.println(s);

        }

    }

}