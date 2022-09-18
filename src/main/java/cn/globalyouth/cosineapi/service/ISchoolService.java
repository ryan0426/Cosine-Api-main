package cn.globalyouth.cosineapi.service;

import cn.globalyouth.cosineapi.common.utils.ApiResponse;

import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 学校服务
 */
public interface ISchoolService {

    /**
     * 通过xlsx批量添加学校
     *
     * @param filePath 文件路径
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> batchAddSchool(String filePath);

    /**
     * 通过网页批量添加专业
     *
     * @return ApiResponse<Integer>
     */
    ApiResponse<Integer> batchAddMajor();

    /**
     * 模糊查询学校
     *
     * @param name 模糊查询匹配值
     * @return ApiResponse<List < String>>
     */
    ApiResponse<List<String>> listSchool(String name);

    /**
     * 模糊查询专业
     *
     * @param name 模糊查询匹配值
     * @return ApiResponse<List < String>>
     */
    ApiResponse<List<String>> listMajor(String name);
}
