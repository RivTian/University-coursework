# -*- coding:utf-8 -*-
"""
Author: RioTian
Create time: 2021.01.04
Function: 主页面
"""
import sys
from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtGui import QIcon, QFont


class Main(QMainWindow):
    def __init__(self):
        super().__init__()
        self.set_ui()

    def set_ui(self):
        self.setWindowTitle('Main page')
        self.icon = QIcon("images/python-logo.png")
        self.setFixedSize(1200, 900)
        self.setFont(QFont('Consolas'))
        self.setStyleSheet(
            "background-image: url('images/welcome.jpg'); background-repeat: no repeat"
        )
        # self.setWindowOpacity(0.9)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Main()
    window.show()
    sys.exit(app.exec_())
