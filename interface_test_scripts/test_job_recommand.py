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


# print( calendar.timegm(time.gmtime()) )

# 向服务器请求一个上传的url
data = {
    #"userId":"23",
    #"changeCnt":"0",
    "locations" : [],
    "industries" : [],
    "jobNatures" : [],
    "educations" : [],
    "salaries" : None,
    "companyNatures" : [],
    "name" : "",
    "haiTou" : False,
    "page" : 1
}
#ret = requests.post(url + "job/condition", data)
ret = requests.post(url + "job/condition",data=json.dumps(data),headers={'Content-Type':'application/json'})



print(ret)
print(ret.text)



