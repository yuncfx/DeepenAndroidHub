package com.ssy.coroutines5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
    code in the flow { ... } builder has to honor the context preservation property and is not
    allowed to emit from a different context.
 */
fun simple(): Flow<Int> = flow {
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100)
            emit(i)
        }
    }
}

/**
    Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
		Flow was collected in [BlockingCoroutine{Active}@506d469a, BlockingEventLoop@413d2b00],
		but emission happened in [DispatchedCoroutine{Active}@5368c0d0, Dispatchers.Default].
		Please refer to 'flow' documentation or use 'flowOn' instead

 * @see com.ssy.coroutines.samples.simple4
 */
fun main() = runBlocking {
    simple().collect { value -> println(value) }
}