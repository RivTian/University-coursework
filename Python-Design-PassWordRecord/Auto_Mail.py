#coding:utf-8
#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# taget:使用Python实现自动化邮件发送
# Python有两个内置库：
#   smtplib和email，能够实现邮件功能，
#   smtplib库负责发送邮件，email库负责构造邮件格式和内容。
# 邮件发送需要遵守SMTP协议，
# Python内置对SMTP的支持，可以发送纯文本邮件、
# HTML邮件以及带附件的邮件。
#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# 1、先导入相关的库和方法
#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
import smtplib
import email
# 负责构造文本
from email.mime.text import MIMEText
# 负责构造图片
from email.mime.image import MIMEImage
# 负责将多个对象集合起来
from email.mime.multipart import MIMEMultipart
from email.header import Header

#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# step2、设置邮箱域名、发件人邮箱、邮箱授权码、收件人邮箱
#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# SMTP服务器，这里使用qq邮箱服务器
mail_host = "smtp.qq.com"
# 发件人邮箱,这里使用了我的qq邮箱
mail_sender = "641292434@qq.com"
# 邮箱授权码,注意这里不是邮箱密码,如何获取授权码只需要查询一下即可
mail_license = "kkipoczrwrhsbegd"
# 收件人邮箱，可以为多个收件人
mail_receivers = ["kannajiahe@gmail.com"]


# 收件人，邮件文字内容
def mail(mail_receiver="kannajiahe@gmail.com", mail_content="Happy New Year"):
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step3、构建MIMEMultipart对象代表邮件本身，可以往里面添加文本、图片、附件等
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    mm = MIMEMultipart('related')

    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step4、设置邮件头部内容
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    # 邮件主题
    subject_content = """Python邮件测试"""
    # 设置发送者,注意严格遵守格式,里面邮箱为发件人邮箱
    mm["From"] = "sender_name<641292434@qq.com>"
    # 设置接受者,注意严格遵守格式,里面邮箱为接受者邮箱
    # mm["To"] = "receiver_1_name<kannajiahe@gmail.com>"
    mm["To"] = "receiver_1_name<{mail_receiver}>"
    # 设置邮件主题
    mm["Subject"] = Header(subject_content, 'utf-8')

    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step5、添加正文文本
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    # 邮件正文内容
    # body_content = """Happy New Year"""
    body_content = mail_content
    # body_content = """{mail_content}"""
    # 构造文本,参数1：正文内容，参数2：文本格式，参数3：编码方式
    message_text = MIMEText(body_content, "plain", "utf-8")
    # 向MIMEMultipart对象中添加文本对象
    mm.attach(message_text)

    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step6、添加图片
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    # 二进制读取jpg图片
    image_data = open('HappyNY.jpg', 'rb')
    # 设置读取获取的二进制数据
    message_image = MIMEImage(image_data.read())
    # 关闭刚才打开的文件
    image_data.close()
    # 添加图片文件到邮件信息当中去
    mm.attach(message_image)

    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step7、添加附件(excel表格)
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    # 构造附件
    atta = MIMEText(open('data.db', 'rb').read(), 'base64', 'utf-8')
    # 设置附件信息
    atta["Content-Disposition"] = 'attachment; filename="data.db"'
    # 添加附件到邮件信息当中去
    mm.attach(atta)

    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    # step8、发送邮件
    #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    # 创建SMTP对象
    stp = smtplib.SMTP()
    # 设置发件人邮箱的域名和端口，端口地址为25
    stp.connect(mail_host, 25)
    # set_debuglevel(1)可以打印出和SMTP服务器交互的所有信息
    stp.set_debuglevel(1)
    # 登录邮箱，传递参数1：邮箱地址，参数2：邮箱授权码
    stp.login(mail_sender, mail_license)
    # 发送邮件，传递参数1：发件人邮箱地址，参数2：收件人邮箱地址，参数3：把邮件内容格式改为str
    stp.sendmail(mail_sender, mail_receivers, mm.as_string())
    print("邮件发送成功")
    # 关闭SMTP对象
    stp.quit()


# 测试代码运行效果
if __name__ == "__main__":
    mail("kannajiahe@gmail.com", "demo")
