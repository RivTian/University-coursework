# 暑期实训程序文档 V1.0
- 题目：外星人入侵（飞机大战）
- 作者:   RioTian
- 时间：21/07/14

## 实验环境
- 操作系统：Windows 10，Marjoram
- python 版本：Python 3.8
- 主要用到的包：Pygame，sys

## 程序结构
![程序结构](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/20210714130111.png)

## 功能模块

游戏的基本设定:

- 敌方共有大中小3款飞机，分为高中低三种速度;

- 子弹的射程并非全屏,而大概是屏幕长度的80%;

- 消灭小飞机需要1发子弹,中飞机需要8发,大飞机需要20发子弹;

- 每消灭一架小飞机得1000分,中飞机6000分,大飞机10000分;

- 每隔30秒有一个随机的道具补给,分为两种道具，全屏炸弹和无敌状态;

  道具补给间隔时间随关卡难度增加而减少

- 全屏炸弹最多只能存放1000枚,无敌状态以维持5秒钟的效果;

- 游戏将根据分数来逐步提高难度,难度的提高表现为飞机数量的增多以及速度的加快。

- 游戏背景交替滚动

另外还对游戏做了一些改进,比如为中飞机和大飞机增加了血槽的显示,这样玩家可以直观地知道敌机快被消灭了没有;我方有三次机会,每次被敌人消灭,新诞生的飞机会有5秒钟的安全期;游戏结束后会显示和更新游戏历史最高分数。

---

Update：基本飞机大战基础上增添功能点

- [x] 用户支持飞机上下移动 
- [x] 特种外星人，需命中5次才会消失 
- [x] 敌机也可开火 
- [x] 无敌道具，吃下可无敌5秒 
- [x] 外星人随机移动，相互不能碰撞（只能下左右方向） 



## 算法说明
**01.背景交替滚动的思路确定**

* 游戏启动后，背景图像会连续不断的向下方移动

* 在视觉上产生英雄的飞机不断向上方飞行的错觉，在很多跑酷类游戏中常用的套路

    游戏的背景 不断变化

    游戏的主角 位置保持不变

1.实现思路分析

![02_游戏背景图](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/20210714130707.png)

2.代码实现

```python
y1, y2 = 0, -700 # Global

# 背景图滚动
def bg_update():
    global y1, y2
    y1 += 5
    y2 += 5
    screen.blit(bg1, (0, y1))
    screen.blit(bg2, (0, y2))
    if y1 > 700:
        y1 = -700
    if y2 > 700:
        y2 = -700
    return y1, y2
```

**02.敌机随机移动与碰撞检测**

```python
# 敌机类设计
class Small/Mid/Big_Enemy(pygame.sprite.Sprite):
    ...
    def move(self):
        if self.rect.top < self.height:
            self.rect.top += self.speed
        else:
            self.reset()

    def moveLeft(self):
        if self.rect.left - self.speed > 0:
            self.rect.left -= self.speed
        else:
            self.rect.left = 0

    def moveRight(self):
        if self.rect.right + self.speed < self.width:
            self.rect.left += self.speed
        else:
            self.rect.right = self.width
    ...
```

**随机移动设计:**

保持最基本的向下运行，如果每一帧都随机向左向右移动的话容易产生画面抖动感，所以利用delay控制敌机移动左右方向

```python
for each in small/mid_enemies:
    if each.active:
        each.move()
        if delay > 50:
            each.moveRight()
        else:
            each.moveLeft()
		...
```

**敌机间碰撞检测**

由于本游戏设定过程中敌军飞机较小，所以采用遍历列表的方法检测是否坐标重叠

方法：使用Pygame提供的 `pygame.sprite.collide_rect` 方法

```python
# 敌机间碰撞检测
for each_a in enemies:
    for each_b in enemies:
        if each_a != each_b:
            enemies_Collide = pygame.sprite.collide_rect(
                each_a, each_b)
            if enemies_Collide:
                if each_a.rect.left < each_b.rect.left:
                    each_a.rect.left -= 10
                    each_b.rect.left += 10
                else:
                    each_a.rect.left += 10
                    each_b.rect.left -= 10
```

## 程序使用说明

```markdown
cd Aircraft_Warfare
python main.py
```

## 程序运行示意图

![程序运行图](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/20210714130156.png)

![Game_Over](https://cdn.jsdelivr.net/gh/RivTian/Blogimg/img/20210714164804.png)