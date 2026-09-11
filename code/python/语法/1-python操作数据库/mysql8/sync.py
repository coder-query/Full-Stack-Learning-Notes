
def query_data(cursor):
    # 查询单条
    cursor.execute("SELECT * FROM `clazz` WHERE id = %s", (1,))
    user = cursor.fetchone()
    print("查询单条 user")
    print(user)
    print("------------------------------------")

    # 查询所有
    cursor.execute("SELECT * FROM `clazz`")
    all_users = cursor.fetchall()
    print("查询所有 all_users")
    for user in all_users:
        print(user)
    print("------------------------------------")

def update_data(cursor):
    cursor.execute("UPDATE `clazz` SET `name` = %s WHERE `id` = %s", ('Python爬虫突击班', 1))
    conn.commit()
    print(f"更新了 {cursor.rowcount} 行")


def insert_data(cursor):
    # 插入单条
    cursor.execute("INSERT INTO `clazz` (`name`) VALUES (%s)", ('Python爬虫突击班666', ))
    # 插入多条
    d = [
        ('Python爬虫突击班777', ),
        ('Python爬虫突击班888', )
    ]
    cursor.executemany("INSERT INTO `clazz` (`name`) VALUES (%s)", d)

    conn.commit()
    print(f"插入了 {cursor.rowcount} 行")

if __name__ == "__main__":
    import pymysql

    conn = pymysql.connect(
        host='127.0.0.1',
        port=3306,
        user='root',
        password='123456',
        database='test',
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor # 返回字典格式,默认是元组格式
    )
    cursor = conn.cursor()
    insert_data(cursor)
    update_data(cursor)
    query_data(cursor)
    conn.close()
