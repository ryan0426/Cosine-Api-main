import os
import sys
import requests
import json

from urllib import request

# url = "http://124.70.141.11:9339/cosine/"
url = "http://localhost:9339/cosine/"



# 读取要上传的文件
file_content = open('./test.jpg', 'rb').read()




# 向服务器请求一个上传的url
data = {
    "type" : "avatar",
    "suffix" : ".jpg",
    "length" : len(file_content)
}
ret = requests.post(url + "upload/upload_oss", data)



print(ret)
print(ret.text)
upload_info = json.loads(json.loads(ret.text)["data"])
print(upload_info["url"])       # 上传路径
print(upload_info["headers"])   # 上传头
print(upload_info["key"])       # 上传的文件在OBS中的key





# 上传图片

requests.put(upload_info["url"], file_content, headers=upload_info["headers"])
print(ret)


# 通过key获取一个临时访问url

data = {
    "objectKey" : upload_info["key"],
}
ret = requests.post(url + "upload/query_image_url", data)
print(ret)
print(json.loads(ret.text)["data"]) #临时访问url



# 通过返回的url下载图片

request.urlretrieve(json.loads(ret.text)["data"], "output.jpg")







