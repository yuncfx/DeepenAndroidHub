package kot.relative;

import com.official.CallingKotlinKt;
import com.official.User;

import kotlin.collections.ArrayDeque;

public class CallingKotlinRelative {
}

/**
 * @see User
 */
class JavaClient {
    public String getID(User user) {
        return user.ID;
    }

    void test() {
        /*
            From Kotlin they will be accessible by the same name filterValid, but from Java it
            will be filterValid and filterValidInt.
         */
        CallingKotlinKt.filterValid(new ArrayDeque<>());
        CallingKotlinKt.filterValidInt(new ArrayDeque<>());

        CallingKotlinKt.y();
        CallingKotlinKt.changeY(20);
    }
}
