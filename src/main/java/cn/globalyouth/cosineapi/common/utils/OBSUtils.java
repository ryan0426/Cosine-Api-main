package cn.globalyouth.cosineapi.common.utils;

import cn.globalyouth.cosineapi.common.utils.extend.HuaWeiYunConfig;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OBSUtils {

    public static String upload(String filename, String bucketName, String directory, InputStream inputStream) {
// 创建ObsClient实例
        ObsClient obsClient = null;
        try {
            obsClient = new ObsClient(HuaWeiYunConfig.ak, HuaWeiYunConfig.sk, HuaWeiYunConfig.endPoint);

            final String keySuffixWithSlash = directory + "/";
            obsClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));

// 在文件夹下创建对象
            PutObjectResult putObjectResult = obsClient.putObject(bucketName, keySuffixWithSlash + filename, inputStream);
            return "https://" + putObjectResult.getBucketName() + "." + HuaWeiYunConfig.url + "/" + putObjectResult.getObjectKey();
        } finally {
            try {
                if (obsClient != null) {
                    obsClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 不指定 bocketname  则默认为 	central-storage
     * @param filename
     * @param directory   --规范--   文件夹名称  他会自动创建  必须指定
     * @param inputStream
     * @return
     */
    public static String upload(String filename, String directory, InputStream inputStream) {
// 创建ObsClient实例
        ObsClient obsClient = null;
        try {
            obsClient = new ObsClient(HuaWeiYunConfig.ak, HuaWeiYunConfig.sk, HuaWeiYunConfig.endPoint);

            final String keySuffixWithSlash = directory + "/";
            obsClient.putObject(HuaWeiYunConfig.defaultBucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));

// 在文件夹下创建对象
            PutObjectResult putObjectResult = obsClient.putObject(HuaWeiYunConfig.defaultBucketName, keySuffixWithSlash + filename, inputStream);
            return "https://"+putObjectResult.getBucketName()+"."+HuaWeiYunConfig.url+"/"+putObjectResult.getObjectKey();
        } finally {
            try {
                if(obsClient != null){
                    obsClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
