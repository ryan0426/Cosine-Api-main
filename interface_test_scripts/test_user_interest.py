import os
import sys
import requests
import json
import time
import calendar

from urllib import request

url = "http://124.70.141.11:9349/cosine/"


# url = "http://localhost:9339/cosine/"



# # 读取要上传的文件
# file_content = open('./test.jpg', 'rb').read()

#headers = {'User-Agent' : 'python-requests/2.4.3 CPython/3.4.0'}
# print( calendar.timegm(time.gmtime()) )

# 向服务器请求一个上传的url
data = {
    #"name" : "华为"
    #"id" : 1
    #"image" : ".png",
    #"name" : "default",
    #"description" : "default",
    #"month" : "2018-11",
    #"day" : "05",
    #"startTime" : "default",
    #"endTime" : "default",
    "location" : "北京",
    "month" : "不限"
    #"survey_url" : "www"
}

#ret = requests.post(url + "openday/add",data=json.dumps(data),headers={'Content-Type':'application/json'})
ret = requests.post(url + "openday/filter",data)



print(ret)
print(ret.text)



