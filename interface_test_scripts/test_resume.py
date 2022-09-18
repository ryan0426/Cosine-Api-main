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
    "id" : 16
    #"userId" : 16,
    #"title" : "测试",
    #"recordValues" : "import",
    #"key" : "video",
    #"isDefault" : False,
    #"addTime" : 1625108675295
}

#ret = requests.post(url + "resume/add",data=json.dumps(data),headers={'Content-Type':'application/json'})
ret = requests.get(url + "videoresume/id",data)



print(ret)
print(ret.text)