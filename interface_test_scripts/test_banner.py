import os
import sys
import requests
import json
import time
import calendar

from urllib import request

url = "http://124.70.141.11:9339/cosine/"
# url = "http://localhost:9339/cosine/"



# # 读取要上传的文件
# file_content = open('./test.jpg', 'rb').read()


# print( calendar.timegm(time.gmtime()) )

# 向服务器请求一个上传的url
data = {
    "position" : 0,
}
ret = requests.get(url + "banner/list", data)



print(ret)
print(ret.text)



