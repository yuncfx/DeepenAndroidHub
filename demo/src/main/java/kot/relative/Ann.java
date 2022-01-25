package kot.relative;

import com.ssy.chapter7.MyAnnotation;

/**
 *  @see AnnWithValue
 *  @see com.ssy.chapter7.C
 */
public @interface Ann {
    int intValue();

    String stringValue();
}

@MyAnnotation
@MyAnnotation.Container(@MyAnnotation)
class T {

}
