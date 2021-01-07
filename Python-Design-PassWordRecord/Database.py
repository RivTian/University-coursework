# -*- coding=utf-8 -*-
"""
Author: RioTian
Create time: 2021.01.04
Function: 该模块为数据库模块，主要的功能是为其他库提供数据库的更简易操作的接口
"""
import time
import sqlite3


# 为登录界面所提供数据库操作的类
class Database:
    def __init__(self, db):
        self._database = db
        self.create_table()

    @property
    def database(self):
        return self._database

    @database.setter
    def database(self, db):
        self._database = db

    # 创建一个数据库
    def create_table(self):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = "CREATE TABLE IF NOT EXISTS data(username TEXT, password TEXT, created_time TEXT)"
        cursor.execute(sql)
        if not self.is_has('admin'):  # 管理员的用户名一定为 admin
            created_time = self.get_time()
            default = "INSERT INTO data(username, password, created_time) VALUES('admin', 'admin123', ?)"  # 设置初始的账号密码
            cursor.execute(default, (created_time, ))
        connect.commit()
        connect.close()

    # 向数据库中插入元素
    def insert_table(self, username, password):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        if self.is_has(username):
            # print("Already exits username {}".format(username))  # 测试使用
            return True  # 已经有该元素的时候返回一个 True 提供外界接口
        else:
            created_time = self.get_time()
            sql = 'INSERT INTO data (username, password, created_time) VALUES(?,?,?)'
            cursor.execute(sql, (username, password, created_time))
            connect.commit()
        connect.close()

    # 读取数据库中的所有元素
    def read_table(self):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = 'SELECT * FROM data ORDER BY username'
        result = cursor.execute(sql)
        data = result.fetchall()
        connect.commit()
        connect.close()
        return data

    # 更新数据库中的数据
    def update_table(self, username, password):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = 'UPDATE data SET password =? WHERE username=? '
        cursor.execute(sql, (password, username))
        connect.commit()
        connect.close()

    # 根据用户名来查找用户的密码
    def find_password_by_username(self, username):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = 'SELECT password FROM data WHERE username=?'
        result = cursor.execute(sql, (username, ))
        connect.commit()
        found_data = result.fetchall()
        connect.close()
        return found_data

    # 通过用户名称删除数据
    def delete_table_by_username(self, username):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = 'DELETE FROM data WHERE  username=?'
        cursor.execute(sql, (username, ))
        connect.commit()
        connect.close()

    # 判断数据库中是否包含用户名信息
    def is_has(self, username):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = 'SELECT * FROM data WHERE username=?'
        result = cursor.execute(sql, (username, ))
        connect.commit()
        all_data = result.fetchall()
        connect.close()
        if all_data:
            return True
        else:
            return False

    # 清空所有的数据
    def clear(self):
        connect = sqlite3.connect(self._database)
        cursor = connect.cursor()
        sql = "DELETE FROM data"
        cursor.execute(sql)
        connect.commit()
        connect.close()

    @staticmethod
    def get_time():
        date = time.localtime()
        created_time = "{}-{}-{}-{}:{}:{}".format(date.tm_year, date.tm_mon,
                                                  date.tm_mday, date.tm_hour,
                                                  date.tm_min, date.tm_sec)
        return created_time


if __name__ == '__main__':
    # 打开数据库文件
    data = Database('./data.db')
    # data.insert_table('admin', 'password')

    # 利用循环快速创建账户用于测试
    # for i in range(23):
    #     data.insert_table(chr(i + 65) * 5, chr(i + 65) + chr(i + 66) * 5)

    # 按格式显示数据库中存储的Password
    data_ = data.read_table()
    print(data_)
