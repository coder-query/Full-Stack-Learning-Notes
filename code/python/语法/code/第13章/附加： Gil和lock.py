import threading
import time

# 这是一个全局共享的业务数据
shared_counter = 0
# 这是我们手动创建的“业务锁”
manual_lock = threading.Lock()


def cpu_bound_task():
    """CPU密集型任务，展示GIL的单核限制"""
    start = time.time()
    num = 0
    # 循环1亿次，纯计算。GIL会频繁地在字节码间释放和获取，导致线程切换开销巨大
    for _ in range(100_000_000):
        num += 1
    print(f"CPU任务完成，耗时 {time.time() - start:.2f} 秒")


def io_bound_task():
    """IO密集型任务，展示GIL的自动释放"""
    print(f"线程 {threading.current_thread().name} 开始I/O")
    # sleep会释放GIL，让其他线程有机会运行
    time.sleep(2)
    print(f"线程 {threading.current_thread().name} I/O结束")


def thread_safe_business_increment():
    """线程安全的业务操作，展示手动 Lock 的作用"""
    global shared_counter
    for _ in range(1000000):
        # 线程在此协作，请求访问共享资源的权限
        with manual_lock:
            # 手动锁保证了这三步操作的原子性：
            # 1. 读取 shared_counter
            # 2. 计算 shared_counter + 1
            # 3. 将新值写回 shared_counter
            shared_counter += 1


# --- 测试场景 ---
if __name__ == "__main__":
    print("=" * 40)
    print("场景1: 证明GIL在CPU密集型任务下导致单核限制")
    start = time.time()
    # 创建两个线程执行纯计算，你会发现总耗时约等于一个线程耗时的两倍
    # 因为GIL让他们几乎无法并行，还增加了切换开销
    t1 = threading.Thread(target=cpu_bound_task)
    t2 = threading.Thread(target=cpu_bound_task)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print(f"两个CPU任务总耗时 {time.time() - start:.2f} 秒")
    print("结论: 两个CPU线程并未利用多核，反而更慢。\n")

    # print("=" * 40)
    # print("场景2: 证明GIL在I/O操作时自动释放，允许并发")
    # start = time.time()
    # t1 = threading.Thread(target=io_bound_task, name="A")
    # t2 = threading.Thread(target=io_bound_task, name="B")
    # t1.start()
    # t2.start()
    # t1.join()
    # t2.join()
    # print(f"两个I/O任务总耗时 {time.time() - start:.2f} 秒 (近似2秒)")
    # print("结论: 线程A sleep时释放GIL，线程B得以运行，实现了I/O并发。\n")

    # print("=" * 40)
    # print("场景3: 展示手动Lock保护业务数据")
    # # 如果去掉 with manual_lock，shared_counter 的最终结果将远小于 2000000
    # t1 = threading.Thread(target=thread_safe_business_increment)
    # t2 = threading.Thread(target=thread_safe_business_increment)
    # t1.start()
    # t2.start()
    # t1.join()
    # t2.join()
    # print(f"手动Lock保护后的计数器结果: {shared_counter} (预期 2000000)")
    # print("结论: 手动Lock保证了业务数据的线程安全。")
