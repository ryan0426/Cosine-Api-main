package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.enums.YesOrNo;
import cn.globalyouth.cosineapi.common.exception.GraceException;
import cn.globalyouth.cosineapi.dao.mapper.TbResumeMapper;
import cn.globalyouth.cosineapi.model.bean.resume.Resume;
import cn.globalyouth.cosineapi.model.bean.resume.ResumeCase;
import cn.globalyouth.cosineapi.dao.resume.ResumeCaseDao;
import cn.globalyouth.cosineapi.dao.resume.ResumeDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseBriefDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeCaseDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeDto;
import cn.globalyouth.cosineapi.model.dto.resume.ResumeImportDto;
import cn.globalyouth.cosineapi.model.dto.resume.VideoResumeDto;
import cn.globalyouth.cosineapi.model.pojo.TbResume;
import cn.globalyouth.cosineapi.service.IResumeService;
import cn.globalyouth.cosineapi.common.utils.StringUtils;
import cn.globalyouth.cosineapi.model.vo.req.ResumeReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * @author liuyufeng 简历服务
 */
@Service
@Slf4j
public class ResumeServiceImpl implements IResumeService {
    @Autowired
    private TbResumeMapper resumeMapper;

    @Resource
    private ResumeCaseDao resumeCaseDao;
    @Resource
    private ResumeDao resumeDao;

    @Value("${resumeFilePath}")
    private String filePath;
    @Value("${baseurl}")
    private String baseUrl;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public List<TbResume> queryByUserIdList(Integer userId) {
        Example example = new Example(TbResume.class);
        example.orderBy("addTime").asc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user",userId);
        return resumeMapper.selectByExample(example);
    }

    @Override
    public TbResume selectTopResume(int userId) {
        Example example = new Example(TbResume.class);
        example.orderBy("addTime").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user",userId);
        return resumeMapper.selectOneByExample(example);
    }

    @Override
    public TbResume selectResume(int id) {
       return resumeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ApiResponse<Integer> countResume(int userId) {
        try {
            return ApiResponse.success(resumeDao.countResume(userId));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> setDefaultResume(int userId, int id) {
        try {
            resumeDao.setNotDefault(userId);
            if (1 == resumeDao.setDefault(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> updateTitle(int id, String title) {
        try {
            if (1 == resumeDao.updateTitle(id, title)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<ResumeImportDto> importResume(MultipartFile file) {
        try {
            ResumeImportDto res = new ResumeImportDto();
            // 存储pdf文件
            String oriName = file.getOriginalFilename();
            if (null == oriName) {
                return ApiResponse.error(403, "empty file");
            }
            String prefix = oriName.substring(oriName.lastIndexOf("."));
            // 文件存储名称 100000000.pdf
            String fileName = System.currentTimeMillis() + prefix;
            // 文件存储完整路径
            String filePath = this.filePath + File.separator + fileName;
            File pdfFile = new File(filePath);
            file.transferTo(pdfFile);
            res.setPdfUrl(baseUrl + "/" + fileName);
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> addResume(ResumeReqVo reqVo) {
        try {
            System.out.println(handleResume(reqVo));
            if (1 == resumeDao.addResume(handleResume(reqVo))) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteResume(int id) {
        try {
            if (1 == resumeDao.deleteResume(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<ResumeCaseBriefDto>> listHot() {
        try {
            List<ResumeCaseBriefDto> data = new ArrayList<>();
            List<ResumeCase> list = resumeCaseDao.listHot();
            list.forEach(resumeCase -> data.add(handleResumeCaseBrief(resumeCase)));
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<ResumeCaseBriefDto>> listByIndustry(int industryId) {
        try {
            List<ResumeCaseBriefDto> data = new ArrayList<>();
            List<ResumeCase> list = resumeCaseDao.listByIndustry(industryId);
            list.forEach(resumeCase -> data.add(handleResumeCaseBrief(resumeCase)));
            return ApiResponse.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<ResumeCaseDto> selectById(int id) {
        try {
            return ApiResponse.success(handleResumeCase(resumeCaseDao.selectById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }
    
    @Override
    public ApiResponse<Object> addVideoResume(int userId, String key) {
        try {
            System.out.println("addVideoResume");
            System.out.println(userId);
            System.out.println(key);

            if (1 == resumeDao.addVideoResume(userId, key)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> deleteVideoResume(int id) {
        try {
            if (1 == resumeDao.deleteVideoResume(id)) {
                return ApiResponse.success(null);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }
    
    @Override
    public ApiResponse<List<VideoResumeDto>> selectVideoResume(int id) {
        try {
            return ApiResponse.success(resumeDao.videoById(id));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    private ResumeDto handleResume(Resume resume) {
        if (null == resume) {
            return null;
        }
        ResumeDto res = new ResumeDto();
        res.setId(resume.getId());
        res.setTitle(resume.getTitle());
        //res.setLanguage(LanguageEnum.getValue(resume.getLanguage()));
        //res.setGrade(resume.getGrade());
        //res.setPercent(resume.getPercent());
        res.setAddTime(FORMAT.format(resume.getAddTime()));
        res.setKey(resume.getResumeKey());
        res.setDefault(resume.getIsDefault() == 1);
        return res;
    }

    
    private Resume handleResume(ResumeReqVo reqVo) {
        Resume res = new Resume();
        res.setUser(reqVo.getUserId());
        res.setTitle(reqVo.getTitle());
        res.setRecordValues(reqVo.getRecordValues());
        res.setResumeKey(reqVo.getKey());
        res.setIsDefault(reqVo.getIsDefault() ? 1 : 0);
        res.setAddTime(new Timestamp(reqVo.getAddTime()));
        return res;
    }

    private ResumeCaseDto handleResumeCase(ResumeCase resumeCase) {
        ResumeCaseDto res = new ResumeCaseDto();
        res.setId(resumeCase.getId());
        res.setTitle(resumeCase.getTitle());
        res.setIntroduction(resumeCase.getIntroduction());
        res.setImage(resumeCase.getImage());
        String tags = resumeCase.getTags();
        if (StringUtils.notEmpty(tags)) {
            res.setTags(Arrays.asList(tags.split(",")));
        } else {
            res.setTags(new ArrayList<>());
        }
        return res;
    }

    private ResumeCaseBriefDto handleResumeCaseBrief(ResumeCase resumeCase) {
        ResumeCaseBriefDto res = new ResumeCaseBriefDto();
        res.setId(resumeCase.getId());
        res.setIntroduction(resumeCase.getIntroduction());
        String tags = resumeCase.getTags();
        if (StringUtils.notEmpty(tags)) {
            res.setTags(Arrays.asList(tags.split(",")).subList(0, 2));
        } else {
            res.setTags(new ArrayList<>());
        }
        return res;
    }

    @Override
    public TbResume createResume(String pdfUrl, String jpgUrl, Integer userId, String title) {
        TbResume tbResume = new TbResume();
        tbResume.setPdfUrl(pdfUrl);
        tbResume.setJpgUrl(jpgUrl);
        tbResume.setUser(userId);
        tbResume.setTitle(title);
        tbResume.setIsDefault(YesOrNo.NO.type);
        tbResume.setRecordValues("import");
        tbResume.setAddTime(new Date());
        int res = resumeMapper.insert(tbResume);
        if(res != 1){
            GraceException.display(ResponseEnumState.INSERT_FAILED);
        }
        return tbResume;
    }
}
