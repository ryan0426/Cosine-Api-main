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
job1 = {
    "companyId" : 1,
    "jobId" : 1
}
data = {
    "userId" : 19,
    "resumeId" : 33,
    "resumeTitle" : "test",
    "jobs" : [job1],
    #"companyId" : 3,
    #"jobId" : 3,
    "addTime" : 1617444064271
}

ret = requests.post(url + "send/batch",data=json.dumps(data),headers={'Content-Type':'application/json'})
#ret = requests.post(url + "send/batch",data)*



print(ret)
print(ret.text)