// IService.aidl
package scut.carson_ho.service_server;

import java.util.List;

// Declare any non-default types here with import statements

interface IService {
    void test() = 100;

    void test1(inout List<String> list) = 101;

    oneway void test2() = 102;
}
