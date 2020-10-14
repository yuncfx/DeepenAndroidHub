// IService.aidl
package deepen.android.hub;

import java.util.List;

// Declare any non-default types here with import statements

interface IService {
    void test() = 100;

    void test1(inout List list) = 101;

    oneway void test2() = 102;
}
