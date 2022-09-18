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
    "name" : "华为"
    #"id" : 3
    #"image" : "https://www.google.com.hk/url?sa=i&url=http%3A%2F%2Ft0.gstatic.com%2Fimages%3Fq%3Dtbn%3AANd9GcRnOwv1cg0ecoaMA5yDI5pbf4jOW_Jhjv3BZeONrztC2Y0v_Wcm&psig=AOvVaw0f0uOQI1l53Ow7yp1tvf9O&ust=1624762839337000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMCX5LCntPECFQAAAAAdAAAAABAD",
    #"name" : "华为数据通信部留学生线上宣讲会",
    #"description" : "此处添加华为数据通信部门介绍。此处添加华为数据通信部门介绍。此处添加华为数据通信部门介绍。此处添加华为数据通信部门介绍。此处添加华为数......",
    #"date" : "2021-05-10",
    #"startTime" : "10:00",
    #"endTime" : "11:00"
}

#ret = requests.post(url + "orientation/add",data=json.dumps(data),headers={'Content-Type':'application/json'})
ret = requests.post(url + "orientation/search",data)



print(ret)
print(ret.text)