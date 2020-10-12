package com.deepen.android.hub.codelib.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.*

class TaskExecutor {
    private val mLock = Any()

    @Volatile
    private var mThreadPoolExecutor: ThreadPoolExecutor? = null

    @Volatile
    private var mScheduledService: ScheduledThreadPoolExecutor? = null

    @Volatile
    private var mMainHandler: Handler? = null
    fun executeOnScheduleTask(runnable: Runnable?, delay: Long) {
        scheduledService!!.schedule(runnable, delay, TimeUnit.MILLISECONDS)
    }

    fun executeOnScheduleTask(runnable: Runnable?, delay: Long, unit: TimeUnit?): ScheduledFuture<*> {
        return scheduledService!!.schedule(runnable, delay, unit)
    }

    fun executeOnThread(runnable: Runnable) {
        threadService!!.submit(runnable)
    }

    fun postToMainThread(runnable: Runnable) {
        if (mMainHandler == null) {
            synchronized(mLock) {
                if (mMainHandler == null) {
                    mMainHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        mMainHandler!!.post(runnable)
    }

    fun delayPostToMainThread(runnable: Runnable, delayMillis: Long) {
        if (mMainHandler == null) {
            synchronized(mLock) {
                if (mMainHandler == null) {
                    mMainHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        mMainHandler!!.postDelayed(runnable, delayMillis)
    }

    val isMainThread: Boolean
        get() {
            val isMainThread = Looper.getMainLooper().thread === Thread.currentThread()
            Log.d(TAG, "TaskExecutor isMainThread:$isMainThread")
            return isMainThread
        }

    private val isScheduledServiceEnable: Boolean
        private get() = !((mScheduledService == null
                ) || mScheduledService!!.isShutdown || mScheduledService!!.isTerminated)

    private val scheduledService: ScheduledExecutorService?
        private get() {
            synchronized(TaskExecutor::class.java) {
                if (!isScheduledServiceEnable) {
                    mScheduledService = ScheduledThreadPoolExecutor(2, ThreadFactory { r -> Thread(r, "TaskExecutor-111-") })
                    mScheduledService!!.allowCoreThreadTimeOut(true)
                }
                return mScheduledService
            }
        }

    private val isThreadServiceEnable: Boolean
        private get() = !((mThreadPoolExecutor == null
                ) || mThreadPoolExecutor!!.isShutdown || mThreadPoolExecutor!!.isTerminated)

    private val threadService: ThreadPoolExecutor?
        private get() {
            synchronized(TaskExecutor::class.java) {
                if (!isThreadServiceEnable) {
                    mThreadPoolExecutor = ThreadPoolExecutor(
                        NORMAL_CORE_POOL_SIZE, NORMAL_MAX_POOL_SIZE,
                            NORMAL_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                            LinkedBlockingQueue(), object : ThreadFactory {
                        override fun newThread(r: Runnable): Thread {
                            return Thread(r, "TaskExecutor-222-")
                        }
                    })
                    mThreadPoolExecutor!!.allowCoreThreadTimeOut(true)
                }
                return mThreadPoolExecutor
            }
        }

    companion object {
        private val TAG = TaskExecutor::class.java.simpleName
        private val NORMAL_CORE_POOL_SIZE = Math.max(Runtime.getRuntime().availableProcessors(), 5)
        private val NORMAL_MAX_POOL_SIZE = NORMAL_CORE_POOL_SIZE * 2 + 1
        private const val NORMAL_KEEP_ALIVE_TIME = 5L

        @Volatile
        private var sInstance: TaskExecutor? = null
        val instance: TaskExecutor?
            get() {
                if (sInstance != null) {
                    return sInstance
                }
                synchronized(TaskExecutor::class.java) {
                    if (sInstance == null) {
                        sInstance = TaskExecutor()
                    }
                }
                return sInstance
            }
    }
}