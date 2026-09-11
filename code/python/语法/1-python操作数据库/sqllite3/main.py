
def create_table(cursor):
    # 创建表
    cursor.execute(
        '''
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
           `name` varchar(255),
            age INTEGER,
            email varchar(255) UNIQUE,
            create_time DATETIME DEFAULT CURRENT_TIMESTAMP
        )
        '''
    )

def insert_data(cursor):
    # 单条插入
    cursor.execute(
        "INSERT INTO users (name, age, email) VALUES (?, ?, ?)",
        ('张三', 25, 'zhangsan@example.com')
    )
    # 批量插入
    users_data = [
        ('李四', 30, 'lisi@example.com'),
        ('王五', 28, 'wangwu@example.com'),
        ('赵六', 35, 'zhaoliu@example.com')
    ]
    cursor.executemany(
        "INSERT INTO users (name, age, email) VALUES (?, ?, ?)",
        users_data
    )
    conn.commit()


def query_data(cursor):
    # 查询所有
    cursor.execute("SELECT * FROM users")
    all_users = cursor.fetchall()
    print("查询所有 all_users")
    for user in all_users:
        print(user)
    print("------------------------------------")

    # 查询单条
    cursor.execute("SELECT * FROM users WHERE id = ?", (1,))
    user = cursor.fetchone()
    print("查询单条 user")
    print(user)
    print("------------------------------------")

    # 查询多条
    cursor.execute("SELECT * FROM users WHERE age > ?", (25,))
    users = cursor.fetchmany(2)  # 获取前2条
    print("查询多条 users")
    for user in users:
        print(user)
    print("------------------------------------")

    # 带条件的查询
    cursor.execute(
        "SELECT name, age FROM users WHERE name LIKE ?",
        ('%张%',)
    )
    results = cursor.fetchall()
    print("带条件的查询 results")
    print("------------------------------------")
    for result in results:
        print(result)


def update_data(cursor):
    cursor.execute(
        "UPDATE users SET age = ? WHERE name = ?",
        (26, '张三')
    )
    conn.commit()
    # 检查影响行数
    print(f"更新了 {cursor.rowcount} 行")

def delete_data(cursor):
    # 删除单条数据
    cursor.execute(
        "DELETE FROM users WHERE id = ?",
        (1,)
    )

    # 删除多条数据
    cursor.execute(
        "DELETE FROM users"
    )
    conn.commit()

    print(f"删除了 {cursor.rowcount} 行")

if __name__ == '__main__':
    import sqlite3
    # 连接到数据库（如果不存在会自动创建）
    conn = sqlite3.connect('test.db')
    # 创建游标对象
    cursor = conn.cursor()
    print("hello sqlite3")
    # create_table(cursor)
    # insert_data(cursor)
    query_data(cursor)
    # update_data(cursor)
    # delete_data(cursor)

    # 关闭连接
    conn.close()