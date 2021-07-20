# main.py
import sys
import pygame
import traceback
import heroplane
import enemy
import bullet
import supply
import gmplus
import time

from pygame.locals import *
from random import *

pygame.init()
pygame.mixer.init()

# 基本变量
bg_size = width, height = 1440, 700
screen = pygame.display.set_mode(bg_size)  # set_mode(分辨率,标志位,色深)
pygame.display.set_caption(" Alien_Game -- RioTian")

# background = pygame.image.load("images/backgroundv.png").convert()
bg1 = pygame.image.load("images/backgroundv.png").convert()
bg2 = pygame.image.load("images/backgroundv.png").convert()

y1, y2 = 0, -700

BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
GREEN = (0, 255, 0)
RED = (255, 0, 0)

# 载入游戏音乐
pygame.mixer.music.load("sound/game_music.ogg")
pygame.mixer.music.set_volume(0.2)
bullet_sound = pygame.mixer.Sound("sound/bullet.wav")
bullet_sound.set_volume(0.2)
bomb_sound = pygame.mixer.Sound("sound/use_bomb.wav")
bomb_sound.set_volume(0.2)
supply_sound = pygame.mixer.Sound("sound/supply.wav")
supply_sound.set_volume(0.2)
get_bomb_sound = pygame.mixer.Sound("sound/get_bomb.wav")
get_bomb_sound.set_volume(0.2)
get_bullet_sound = pygame.mixer.Sound("sound/get_bullet.wav")
get_bullet_sound.set_volume(0.2)
upgrade_sound = pygame.mixer.Sound("sound/upgrade.wav")
upgrade_sound.set_volume(0.2)
enemy3_fly_sound = pygame.mixer.Sound("sound/enemy3_flying.wav")
enemy3_fly_sound.set_volume(0.2)
enemy1_down_sound = pygame.mixer.Sound("sound/enemy1_down.wav")
enemy1_down_sound.set_volume(0.2)
enemy2_down_sound = pygame.mixer.Sound("sound/enemy2_down.wav")
enemy2_down_sound.set_volume(0.2)
enemy3_down_sound = pygame.mixer.Sound("sound/enemy3_down.wav")
enemy3_down_sound.set_volume(0.5)
me_down_sound = pygame.mixer.Sound("sound/me_down.wav")
me_down_sound.set_volume(0.2)


# 增加各敌机类型的数量
def add_small_enemies(group1, group2, num):
    for i in range(num):
        e1 = enemy.SmallEnemy(bg_size)
        group1.add(e1)
        group2.add(e1)


def add_mid_enemies(group1, group2, num):
    for i in range(num):
        e2 = enemy.MidEnemy(bg_size)
        group1.add(e2)
        group2.add(e2)


def add_big_enemies(group1, group2, num):
    for i in range(num):
        e3 = enemy.BigEnemy(bg_size)
        group1.add(e3)
        group2.add(e3)


#速度增加
def inc_speed(target, inc):
    for each in target:
        each.speed += inc


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


# -----------------------------------
def Start():
    # 开始游戏页面设计
    # pass
    main()


# -----------------------------------


def main():
    # -----------------------------------
    # 背景音乐持续播放
    pygame.mixer.music.play(-1)

    # 生成我方飞机
    me = heroplane.HeroPlane(bg_size)

    enemies = pygame.sprite.Group()

    # 生成敌方小型飞机
    small_enemies = pygame.sprite.Group()
    add_small_enemies(small_enemies, enemies, 10)

    # 生成敌方中型飞机
    mid_enemies = pygame.sprite.Group()
    add_mid_enemies(mid_enemies, enemies, 3)

    # 生成敌方大型飞机
    big_enemies = pygame.sprite.Group()
    add_big_enemies(big_enemies, enemies, 2)

    # 生成敌方特殊飞机
    plus_enemies = enemy.PlusEnemy(bg_size)

    # 生成普通子弹
    bullet1 = []
    bullet1_index = 0
    BULLET1_NUM = 4
    for i in range(BULLET1_NUM):
        bullet1.append(bullet.Bullet1(me.rect.midtop))

    # 生成超级子弹
    bullet2 = []
    bullet2_index = 0
    BULLET2_NUM = 8
    for i in range(BULLET2_NUM // 2):
        bullet2.append(bullet.Bullet2((me.rect.centerx - 33, me.rect.centery)))
        bullet2.append(bullet.Bullet2((me.rect.centerx + 30, me.rect.centery)))

    bullet3 = []
    bullet3_index = 0
    BULLET3_NUM = 21
    for i in range(BULLET3_NUM // 2):
        bullet3.append(bullet.Bullet2((me.rect.centerx - 30, me.rect.centery)))
        bullet3.append(bullet.Bullet2((me.rect.centerx - 00, me.rect.centery)))
        bullet3.append(bullet.Bullet2((me.rect.centerx + 30, me.rect.centery)))

    # 生成敌方 子弹
    bullet4 = []
    bullet4_index = 0
    BULLET4_NUM = 20
    for i in range(BULLET4_NUM):
        bullet4.append(bullet.Bullet_enemy(plus_enemies.rect.midbottom))

    clock = pygame.time.Clock()

    # 中弹图片索引
    e1_destroy_index = 0
    e2_destroy_index = 0
    e3_destroy_index = 0
    e4_destroy_index = 0
    me_destroy_index = 0

    # 统计得分
    score = 0
    score_font = pygame.font.Font("font/font.ttf", 36)

    # 标志是否暂停游戏
    paused = False
    pause_nor_image = pygame.image.load("images/pause_nor.png").convert_alpha()
    pause_pressed_image = pygame.image.load(
        "images/pause_pressed.png").convert_alpha()
    resume_nor_image = pygame.image.load(
        "images/resume_nor.png").convert_alpha()
    resume_pressed_image = pygame.image.load(
        "images/resume_pressed.png").convert_alpha()
    paused_rect = pause_nor_image.get_rect()
    paused_rect.left, paused_rect.top = width - paused_rect.width - 10, 10
    paused_image = pause_nor_image

    # 标志开始游戏
    # start_flag = False
    # start_flag_pressed_image = pygame.image.load(
    #     "images/resume_nor.png").convert_alpha()
    # start_rect = start_flag_pressed_image.get_rect()

    # 设置难度级别
    level = 1
    Level_MAX_Flag = False

    # 全屏炸弹
    bomb_image = pygame.image.load("images/bomb.png").convert_alpha()
    bomb_rect = bomb_image.get_rect()
    bomb_font = pygame.font.Font("font/font.ttf", 48)
    bomb_num = 1000
    MAX_BOMB_NUM = 1000

    # 初始每30秒发放一个补给包
    # 随着关卡难度上升补给时间间隔缩短
    # 基本单位设置为毫秒级别，不建议把时间设置的太低
    Invincible_supply = supply.Invincible_Supply(bg_size)
    bomb_supply = supply.Bomb_Supply(bg_size)
    supply_Inset_time = 30
    SUPPLY_TIME = USEREVENT
    pygame.time.set_timer(SUPPLY_TIME, supply_Inset_time * 1000)

    # 超级子弹定时器
    DOUBLE_BULLET_TIME = USEREVENT + 1

    # 标志是否使用超级子弹
    is_double_bullet = False
    is_thirh_bullet = False

    # 复活的无敌状态
    # 解除我方无敌状态定时器
    INVINCIBLE_TIME = USEREVENT + 2

    # 生命数量
    life_image = pygame.image.load("images/life.png").convert_alpha()
    life_rect = life_image.get_rect()
    life_num = 3
    MAX_LIFE_NUM = 3

    # 用于阻止重复打开记录文件
    recorded = False

    # 游戏结束画面
    gameover_flag = False
    gameover_font = pygame.font.Font("font/font.TTF", 48)
    again_image = pygame.image.load("images/again.png").convert_alpha()
    again_rect = again_image.get_rect()
    gameover_image = pygame.image.load("images/gameover.png").convert_alpha()
    gameover_rect = gameover_image.get_rect()

    # 用于切换图片
    switch_image = True

    # 游戏进行时间计时
    Time_Running_Count = time.time()

    # 用于延迟
    delay = 100

    running = True
    """
    Game Start
    """

    while running:
        y1, y2 = bg_update()

        # pygame.event.get() 从队列中获取事件
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()

            elif event.type == MOUSEBUTTONDOWN:
                if event.button == 1 and paused_rect.collidepoint(event.pos):
                    paused = not paused
                    if paused:  # 暂停状态
                        pygame.time.set_timer(SUPPLY_TIME, 0)
                        pygame.mixer.music.pause()
                        pygame.mixer.pause()
                    else:
                        pygame.time.set_timer(SUPPLY_TIME, 30 * 1000)
                        pygame.mixer.music.unpause()
                        pygame.mixer.unpause()

            elif event.type == MOUSEMOTION:
                if paused_rect.collidepoint(event.pos):
                    if paused:
                        paused_image = resume_pressed_image
                    else:
                        paused_image = pause_pressed_image
                else:
                    if paused:
                        paused_image = resume_nor_image
                    else:
                        paused_image = pause_nor_image

            elif event.type == KEYDOWN:
                if event.key == K_SPACE:
                    if bomb_num:
                        bomb_num -= 1
                        bomb_sound.play()
                        for each in enemies:
                            if each.rect.bottom > 0:
                                each.active = False

                # b键 自爆清屏
                elif event.key == K_b:
                    me.active = False
                    if me.active:
                        if switch_image:
                            screen.blit(me.image1, me.rect)
                        else:
                            screen.blit(me.image2, me.rect)
                    else:
                        # 毁灭
                        if not (delay % 3):
                            if me_destroy_index == 0:
                                me_down_sound.play()
                            screen.blit(me.destroy_images[me_destroy_index],
                                        me.rect)
                            me_destroy_index = (me_destroy_index + 1) % 4
                            if me_destroy_index == 0:
                                life_num -= 1
                                me.reset()
                                pygame.time.set_timer(INVINCIBLE_TIME,
                                                      3 * 1000)
                        bomb_sound.play()
                        for each in enemies:
                            if each.rect.bottom > 0:
                                each.active = False

                # Q键 暂停
                elif event.key == K_q:
                    if paused:
                        paused_image = resume_pressed_image
                    else:
                        paused_image = pause_pressed_image
                    paused = not paused
                    if paused:
                        pygame.time.set_timer(SUPPLY_TIME, 0)
                        pygame.mixer.music.pause()
                        pygame.mixer.pause()
                    else:
                        pygame.time.set_timer(SUPPLY_TIME, 30 * 1000)
                        pygame.mixer.music.unpause()
                        pygame.mixer.unpause()
                elif event.key == K_1:
                    life_num = 0

            elif event.type == SUPPLY_TIME:
                supply_sound.play()
                if choice([True, False]):
                    bomb_supply.reset()
                else:
                    Invincible_supply.reset()
                # Invincible_supply.reset()

            elif event.type == DOUBLE_BULLET_TIME:
                is_double_bullet = False
                pygame.time.set_timer(DOUBLE_BULLET_TIME, 0)

            elif event.type == INVINCIBLE_TIME:
                me.invincible = False
                pygame.time.set_timer(INVINCIBLE_TIME, 0)

        # 根据用户的得分增加难度
        # 选加项: 难度增加，生命值增加
        if level == 1 and score < 50000 and score >= 10000:
            level = 2
            upgrade_sound.play()
            # 增加3架小型敌机、2架中型敌机和1架大型敌机
            add_small_enemies(small_enemies, enemies, 3)
            add_mid_enemies(mid_enemies, enemies, 2)
            add_big_enemies(big_enemies, enemies, 1)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            life_num = gmplus.Life_plus(life_num, MAX_LIFE_NUM)
            supply_Inset_time -= 3

        elif level == 2 and score < 100000 and score >= 50000:
            level = 3
            upgrade_sound.play()
            # 增加5架小型敌机、3架中型敌机和2架大型敌机
            add_small_enemies(small_enemies, enemies, 4)
            add_mid_enemies(mid_enemies, enemies, 2)
            add_big_enemies(big_enemies, enemies, 1)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
            life_num = gmplus.Life_plus(life_num, MAX_LIFE_NUM)
            supply_Inset_time -= 3

        elif level == 3 and score < 200000 and score >= 100000:
            level = 4
            upgrade_sound.play()
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
            life_num = gmplus.Life_plus(life_num, MAX_LIFE_NUM)
            supply_Inset_time -= 3

        elif level == 4 and (not Level_MAX_Flag) and score > 200000:
            level = 5
            Level_MAX_Flag = True
            upgrade_sound.play()
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
            life_num = gmplus.Life_plus(life_num, MAX_LIFE_NUM)
            supply_Inset_time -= 3

        # screen.blit(background, (0, 0))

        if life_num and not paused:
            # 检测用户的键盘操作
            key_pressed = pygame.key.get_pressed()

            if key_pressed[K_w] or key_pressed[K_UP]:
                me.moveUp()
            if key_pressed[K_s] or key_pressed[K_DOWN]:
                me.moveDown()
            if key_pressed[K_a] or key_pressed[K_LEFT]:
                me.moveLeft()
            if key_pressed[K_d] or key_pressed[K_RIGHT]:
                me.moveRight()

            # 绘制全屏炸弹补给并检测是否获得
            if bomb_supply.active:
                bomb_supply.move()
                screen.blit(bomb_supply.image, bomb_supply.rect)
                if pygame.sprite.collide_mask(bomb_supply, me):
                    get_bomb_sound.play()
                    if bomb_num < MAX_BOMB_NUM:
                        bomb_num += 2
                        if bomb_num > MAX_BOMB_NUM:
                            bomb_num = MAX_BOMB_NUM
                    bomb_supply.active = False

            # 无敌状态补给检测

            # 绘制无敌补给并检测是否获得
            if Invincible_supply.active:
                Invincible_supply.move()
                screen.blit(Invincible_supply.image, Invincible_supply.rect)
                if pygame.sprite.collide_mask(Invincible_supply, me):
                    get_bullet_sound.play()
                    # is_double_bullet = True
                    me.invincible = True
                    pygame.time.set_timer(INVINCIBLE_TIME, 3 * 1000)
                    Invincible_supply.active = False

            # 我方发射子弹
            # 发射子弹的类型变更判断
            if level < 5 and level >= 3:
                is_double_bullet = True
            elif level >= 5:
                is_double_bullet = False
                is_thirh_bullet = True

            # 发射子弹
            if not (delay % 10):
                bullet_sound.play()
                if is_thirh_bullet:
                    bullets = bullet3
                    bullets[bullet3_index].reset(
                        (me.rect.centerx - 33, me.rect.centery))
                    bullets[bullet3_index + 1].reset(
                        (me.rect.centerx - 00, me.rect.centery))
                    bullets[bullet3_index + 2].reset(
                        (me.rect.centerx + 30, me.rect.centery))
                    bullet3_index = (bullet3_index + 3) % BULLET3_NUM
                elif is_double_bullet:
                    bullets = bullet2
                    bullets[bullet2_index].reset(
                        (me.rect.centerx - 33, me.rect.centery))
                    bullets[bullet2_index + 1].reset(
                        (me.rect.centerx + 30, me.rect.centery))
                    bullet2_index = (bullet2_index + 2) % BULLET2_NUM
                else:
                    bullets = bullet1
                    bullets[bullet1_index].reset(me.rect.midtop)
                    bullet1_index = (bullet1_index + 1) % BULLET1_NUM

            # 检测子弹是否击中敌机
            for b in bullets:
                if b.active:
                    b.move()
                    screen.blit(b.image, b.rect)
                    enemy_hit = pygame.sprite.spritecollide(
                        b, enemies, False, pygame.sprite.collide_mask)

                    enemy_bullets_hit = pygame.sprite.collide_rect(
                        b, plus_enemies)
                    if enemy_bullets_hit:
                        b.active = False
                        plus_enemies.active = False

                    if enemy_hit:
                        b.active = False
                        for e in enemy_hit:
                            if e in mid_enemies or e in big_enemies:
                                e.hit = True
                                e.energy -= 1
                                if e.energy == 0:
                                    e.active = False
                            else:
                                e.active = False

            # 敌方发射子弹
            if not (delay % 100):
                bullet_sound.play()
                bullets_enemy = bullet4
                bullets_enemy[bullet4_index].reset(plus_enemies.rect.midbottom)
                bullet4_index = (bullet4_index + 1) % BULLET4_NUM
                print("敌方子弹发射了")

            # 绘制大型敌机
            for each in big_enemies:
                if each.active:
                    each.move()

                    if each.hit:
                        screen.blit(each.image_hit, each.rect)
                        each.hit = False
                    else:
                        if switch_image:
                            screen.blit(each.image1, each.rect)
                        else:
                            screen.blit(each.image2, each.rect)

                    # 绘制血槽
                    pygame.draw.line(screen, BLACK,
                                     (each.rect.left, each.rect.top - 5),
                                     (each.rect.right, each.rect.top - 5), 2)
                    # 当生命大于20%显示绿色，否则显示红色
                    energy_remain = each.energy / enemy.BigEnemy.energy
                    if energy_remain > 0.2:
                        energy_color = GREEN
                    else:
                        energy_color = RED
                    pygame.draw.line(
                        screen, energy_color,
                        (each.rect.left, each.rect.top - 5),
                        (each.rect.left + each.rect.width * energy_remain,
                         each.rect.top - 5), 2)

                    # 即将出现在画面中，播放音效
                    if each.rect.bottom == -50:
                        enemy3_fly_sound.play(-1)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e3_destroy_index == 0:
                            enemy3_down_sound.play()
                        screen.blit(each.destroy_images[e3_destroy_index],
                                    each.rect)
                        e3_destroy_index = (e3_destroy_index + 1) % 6
                        if e3_destroy_index == 0:
                            enemy3_fly_sound.stop()
                            score += 10000
                            each.reset()

            # 绘制中型敌机：
            for each in mid_enemies:
                if each.active:
                    each.move()

                    if delay > 50:
                        each.moveRight()
                    else:
                        each.moveLeft()

                    if each.hit:
                        screen.blit(each.image_hit, each.rect)
                        each.hit = False
                    else:
                        screen.blit(each.image, each.rect)

                    # 绘制血槽
                    pygame.draw.line(screen, BLACK,
                                     (each.rect.left, each.rect.top - 5),
                                     (each.rect.right, each.rect.top - 5), 2)
                    # 当生命大于20%显示绿色，否则显示红色
                    energy_remain = each.energy / enemy.MidEnemy.energy
                    if energy_remain > 0.2:
                        energy_color = GREEN
                    else:
                        energy_color = RED
                    pygame.draw.line(
                        screen, energy_color,
                        (each.rect.left, each.rect.top - 5),
                        (each.rect.left + each.rect.width * energy_remain,
                         each.rect.top - 5), 2)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e2_destroy_index == 0:
                            enemy2_down_sound.play()
                        screen.blit(each.destroy_images[e2_destroy_index],
                                    each.rect)
                        e2_destroy_index = (e2_destroy_index + 1) % 4
                        if e2_destroy_index == 0:
                            score += 6000
                            each.reset()

            # 绘制小型敌机：
            for each in small_enemies:
                if each.active:
                    each.move()

                    if delay > 50:
                        each.moveLeft()
                    else:
                        each.moveRight()

                    screen.blit(each.image, each.rect)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e1_destroy_index == 0:
                            enemy1_down_sound.play()
                        screen.blit(each.destroy_images[e1_destroy_index],
                                    each.rect)
                        e1_destroy_index = (e1_destroy_index + 1) % 4
                        if e1_destroy_index == 0:
                            score += 1000
                            each.reset()

            # 绘制特殊敌机：
            if plus_enemies.active:
                plus_enemies.move()
                screen.blit(plus_enemies.image, plus_enemies.rect)
            else:
                # 毁灭
                print("消灭了")
                if not (delay % 3):
                    if e4_destroy_index == 0:
                        enemy1_down_sound.play()
                    screen.blit(plus_enemies.destroy_images[e4_destroy_index],
                                plus_enemies.rect)
                    e4_destroy_index = (e4_destroy_index + 1) % 4
                    if e4_destroy_index == 0:
                        print("加分了")
                        score += 3000
                        plus_enemies.reset()

            # 检测敌军子弹是否击中友军飞机
            for b in bullets_enemy:
                if b.active:
                    b.move()
                    screen.blit(b.image, b.rect)
                    enemy_bullets_hit = pygame.sprite.collide_rect(b, me)
                    if enemy_bullets_hit and not me.invincible:
                        me.active = False
                        b.active = False

            # 敌机间的碰撞检测
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

            # 检测我方飞机是否被撞
            enemies_down = pygame.sprite.spritecollide(
                me, enemies, False, pygame.sprite.collide_mask)
            enemies_plus_enemies = pygame.sprite.collide_rect(me, plus_enemies)

            if enemies_plus_enemies and not me.invincible:
                me.active = False
                plus_enemies.active = False

            if enemies_down and not me.invincible:
                me.active = False
                for e in enemies_down:
                    e.active = False

            # 绘制我方飞机
            if me.active:
                if switch_image:
                    screen.blit(me.image1, me.rect)
                else:
                    screen.blit(me.image2, me.rect)
            else:
                # 毁灭
                if not (delay % 3):
                    if me_destroy_index == 0:
                        me_down_sound.play()
                    screen.blit(me.destroy_images[me_destroy_index], me.rect)
                    me_destroy_index = (me_destroy_index + 1) % 4
                    if me_destroy_index == 0:
                        life_num -= 1
                        me.reset()
                        pygame.time.set_timer(INVINCIBLE_TIME, 3 * 1000)

            # 绘制全屏炸弹数量
            bomb_text = bomb_font.render("× %d" % bomb_num, True, WHITE)
            text_rect = bomb_text.get_rect()
            screen.blit(bomb_image, (10, height - 10 - bomb_rect.height))
            screen.blit(bomb_text,
                        (20 + bomb_rect.width, height - 5 - text_rect.height))

            # 绘制剩余生命数量
            if life_num:
                for i in range(life_num):
                    screen.blit(life_image,
                                (width - 10 - (i + 1) * life_rect.width,
                                 height - 10 - life_rect.height))

            # 绘制得分
            score_text = score_font.render("Score : %s" % str(score), True,
                                           WHITE)
            level_text = score_font.render("level : %s" % str(level), True,
                                           WHITE)
            small_size_text = score_font.render(
                "Small_Size : %s" % str(len(small_enemies)), True, WHITE)
            mid_size_text = score_font.render(
                "Mid_Size : %s" % str(len(mid_enemies)), True, WHITE)
            big_size_text = score_font.render(
                "Big_Size : %s" % str(len(big_enemies)), True, WHITE)
            screen.blit(score_text, (10, 5))
            screen.blit(level_text, (10, 45))
            screen.blit(small_size_text, (10, 85))
            screen.blit(mid_size_text, (10, 125))
            screen.blit(big_size_text, (10, 165))

        # 绘制游戏结束画面
        elif life_num == 0:
            # Time_Ending_Count = time.time()

            # 部分变量初始化
            level = 1
            supply_Inset_time = 30

            gameover_flag = True
            # 背景音乐停止
            pygame.mixer.music.stop()

            # 停止全部音效
            pygame.mixer.stop()

            # 停止发放补给
            pygame.time.set_timer(SUPPLY_TIME, 0)

            if not recorded:
                recorded = True
                # 读取历史最高得分
                with open("record.txt", "r") as f:
                    record_score = int(f.read())

                # 如果玩家得分高于历史最高得分，则存档
                if score > record_score:
                    with open("record.txt", "w") as f:
                        f.write(str(score))

            # 绘制结束画面
            record_score_text = score_font.render(
                "Best : %d" % max(record_score, score), True, (255, 255, 255))
            screen.blit(record_score_text, (50, 50))

            gameover_text1 = gameover_font.render("Your Score", True,
                                                  (255, 255, 255))
            gameover_text1_rect = gameover_text1.get_rect()
            gameover_text1_rect.left, gameover_text1_rect.top = \
                (width - gameover_text1_rect.width) // 2, height // 3
            screen.blit(gameover_text1, gameover_text1_rect)

            gameover_text2 = gameover_font.render(str(score), True,
                                                  (255, 255, 255))
            gameover_text2_rect = gameover_text2.get_rect()
            gameover_text2_rect.left, gameover_text2_rect.top = \
                (width - gameover_text2_rect.width) // 2, \
                gameover_text1_rect.bottom + 10
            screen.blit(gameover_text2, gameover_text2_rect)

            again_rect.left, again_rect.top = \
                (width - again_rect.width) // 2, \
                gameover_text2_rect.bottom + 50
            screen.blit(again_image, again_rect)

            gameover_rect.left, gameover_rect.top = \
                (width - again_rect.width) // 2, \
                again_rect.bottom + 10
            screen.blit(gameover_image, gameover_rect)

            # 检测用户的鼠标操作
            # 如果用户按下鼠标左键
            if pygame.mouse.get_pressed()[0]:
                # 获取鼠标坐标
                pos = pygame.mouse.get_pos()
                # 如果用户点击“重新开始”
                if again_rect.left < pos[0] < again_rect.right and \
                        again_rect.top < pos[1] < again_rect.bottom:
                    # 调用main函数，重新开始游戏
                    # gameover_flag = False
                    main()
                # 如果用户点击“结束游戏”
                elif gameover_rect.left < pos[0] < gameover_rect.right and \
                        gameover_rect.top < pos[1] < gameover_rect.bottom:
                    # 退出游戏
                    pygame.quit()
                    sys.exit()

        # 绘制暂停按钮
        if (not gameover_flag):
            screen.blit(paused_image, paused_rect)

        # 切换图片
        if not (delay % 5):
            switch_image = not switch_image

        delay -= 1
        if not delay:
            delay = 100

        pygame.display.flip()
        clock.tick(60)


if __name__ == "__main__":
    # Start()
    try:
        main()
    except SystemExit:
        pass
    except:
        traceback.print_exc()
        pygame.quit()
        input()
