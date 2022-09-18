package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.send.Company;
import cn.globalyouth.cosineapi.model.bean.send.Job;
import cn.globalyouth.cosineapi.model.bean.send.JobView;
import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.dao.send.CompanyDao;
import cn.globalyouth.cosineapi.dao.send.JobDao;
import cn.globalyouth.cosineapi.dao.send.JobViewDao;
import cn.globalyouth.cosineapi.dao.send.SendDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.service.IJobService;
import cn.globalyouth.cosineapi.common.utils.StringUtils;
import cn.globalyouth.cosineapi.model.vo.req.JobReqVo;
import cn.globalyouth.cosineapi.model.vo.req.SimpleListReqVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * @author liuyufeng, Rujun Yan 职位服务
 */
@Service
@Slf4j
public class JobServiceImpl implements IJobService {

    @Resource
    private CompanyDao companyDao;
    @Resource
    private JobDao jobDao;
    @Resource
    private JobViewDao jobViewDao;
    @Resource
    private SendDao sendDao;

    @Value("${companyFilePath}")
    private String filePath;
    @Value("${baseurl}")
    private String baseUrl;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public ApiResponse<Object> addCompany(Company company) {
        try {
            if (1 == companyDao.addCompany(company)) {
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
    public ApiResponse<Object> addJob(Job job) {
        try {
            if (1 == jobDao.addJob(job)) {
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
    public ApiResponse<Company> selectCompany(int id) {
        try {
            return ApiResponse.success(companyDao.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Job> selectJob(int id, int userId) {
        log.info("selectJob({},{})", id, userId);
        try {
            Job job = jobDao.selectById(id);
            // 浏览量+1
            jobDao.incViewCnt(id);
            // 添加浏览数据
            JobView jobView = new JobView();
            User user = new User();
            user.setId(userId);
            jobView.setUser(user);
            jobView.setCompany(job.getCompany());
            jobView.setJob(job);
            jobView.setAddTime(new Timestamp(System.currentTimeMillis()));
            jobViewDao.addView(jobView);
            log.info("查询成功");
            return ApiResponse.success(job);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();

            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Job>> listJobs(SimpleListReqVo ids) {
        log.info("listJobs({})", ids);
        try {
            List<Object> list = ids.getList();
            StringBuilder idStr = new StringBuilder();
            idStr.append("(");
            list.forEach(item -> {
                idStr.append((int) item);
                idStr.append(",");
            });
            idStr.replace(idStr.length() - 1, idStr.length(), ")");
            List<Job> data = jobDao.listByIds(idStr.toString());
            log.info("查询成功");
            return ApiResponse.success(data);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();

            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Job>> listRecommendJobs(int userId, int changeCnt) {
        log.info("listRecommendJobs({},{})", userId, changeCnt);
        try {
            List<Job> data = new ArrayList<>(10);
            List<Job> mostSend = jobDao.listBySend(0, 10);
            // 随机数
            Random random = new Random(System.currentTimeMillis() * changeCnt);
            // 查询投递和查看历史
            //List<Send> sendHistory = sendDao.listSendHistory(userId);
            List<JobView> viewHistory = jobViewDao.listJobViewHistory(userId);
            /*
            if (sendHistory.size() == 0 && viewHistory.size() == 0) {
                log.info("历史记录为空");
                // 历史数据为空，返回最多投递的
                data = mostSend;
            */
            if (viewHistory.size() == 0) {
                log.info("历史记录为空");
                // 历史数据为空，返回最多投递的
                data = mostSend;
            } else {
                List<String> locations = new ArrayList<>();
                List<String> industries = new ArrayList<>();
                List<String> jobNatures = new ArrayList<>();
                /*
                sendHistory.forEach(send -> {
                    locations.add(send.getJob().getLocation());
                    industries.add(send.getCompany().getIndustry());
                    jobNatures.add(send.getJob().getNature());
                });
                */
                viewHistory.forEach(view -> {
                    locations.add(view.getJob().getLocation());
                    industries.add(view.getCompany().getIndustry());
                    jobNatures.add(view.getJob().getNature());
                });
                // 统计出每种数据出现的频率
                Map<String, Long> locationMap = locations.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                Map<String, Long> industryMap = industries.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                Map<String, Long> jobNatureMap = jobNatures.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                log.info("历史工作城市: {}", locationMap);
                log.info("历史目标行业: {}", industryMap);
                log.info("历史工作性质: {}", jobNatureMap);
                // 筛选出出现次数最多的 作为查询条件
                List<String> locationQuery = locationMap.entrySet().stream()
                        .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).limit(2).map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                List<String> industryQuery = industryMap.entrySet().stream()
                        .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).limit(2).map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                List<String> jobNatureQuery = jobNatureMap.entrySet().stream()
                        .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue())).limit(1).map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                // 查询推荐的职位 5个
                List<Job> recommendJobs = jobDao.listByConditions2(locationQuery, industryQuery, jobNatureQuery, 15);
                // 随机打乱
                Collections.shuffle(recommendJobs, random);
                Collections.shuffle(mostSend, random);
                // 添加到data中
                if (recommendJobs.size() < 5) {
                    data.addAll(recommendJobs);
                } else {
                    for (Job job : recommendJobs) {
                        if (0 == sendDao.countSend(userId, job.getId()) && data.size() < 5) {
                            data.add(job);
                        }
                    }
                }
                for (Job job : data) {
                    log.info("推荐职位: {},{}", job.getId(), job.getTitle());
                }
                // 在最多投递中选5个加进来
                for (Job job : mostSend) {
                    if (!data.contains(job) && 0 == sendDao.countSend(userId, job.getId())) {
                        data.add(job);
                        if (data.size() == 10) {
                            break;
                        }
                    }
                }
            }
            // 随机打乱列表
            Collections.shuffle(data, random);
            log.info("查询成功");
            return ApiResponse.success(data);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();

            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Job>> listByName(String name, int page) {
        log.info("listByName({})", name);
        try {
            int offset = (page - 1) * 10;
            name = "%" + name + "%";
            List<Job> data = jobDao.listByName(name, offset, 10);
            log.info("查询成功");
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
    public ApiResponse<List<Job>> listByCondition(JobReqVo reqVo) {
        log.info("listByCondition({})", reqVo);
        try {
            int offset = (reqVo.getPage() - 1) * 10;
            if (StringUtils.notEmpty(reqVo.getName())) {
                reqVo.setName("%" + reqVo.getName() + "%");
            }
            List<Job> data = jobDao.listByConditions(reqVo.getLocations(), reqVo.getIndustries(), reqVo.getJobNatures(),
                    reqVo.getEducations(), reqVo.getSalaries(), reqVo.getCompanyNatures(), reqVo.getName(),
                    reqVo.isHaiTou(), offset, 10);
            log.info("查询成功");
            return ApiResponse.success(data);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Job>> listByCompany(int companyId, String name, int page) {
        log.info("listByCompany({}, {}, {})", companyId, name, page);
        try {
            int offset = (page - 1) * 10;
            name = "%" + name + "%";
            List<Job> data = jobDao.listByCompany(companyId, name, offset, 10);
            log.info("查询成功");
            return ApiResponse.success(data);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> incViewCnt(int id) {
        try {
            if (1 == jobDao.incViewCnt(id)) {
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
    public ApiResponse<Object> incSendCnt(int id) {
        try {
            if (1 == jobDao.incSendCnt(id)) {
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
    public ApiResponse<Map<String, Object>> batchImportJobs(String filePath) {
        log.info("batchImportJobs({})", filePath);
        try {
            int companyCount = companyDao.countCompany();
            int jobCnt = jobDao.countJob();
            Map<String, Object> data = readJobsFile(filePath, companyCount + 1);
            log.info("数据读取成功");
            return handleMapData(data, companyCount, jobCnt);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();

            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Map<String, Object>> batchImportJobs(MultipartFile file) {
        log.info("batchImportJobs({})", file);
        try {
            int companyCount = companyDao.countCompany();
            int jobCnt = jobDao.countJob();
            Map<String, Object> data = readJobsFile(file, companyCount + 1);
            log.info("数据读取成功");
            return handleMapData(data, companyCount, jobCnt);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Object> uploadCompanyLogo(MultipartFile file, int id) {
        log.info("uploadCompanyLogo({})", id);
        try {
            String oriName = file.getOriginalFilename();
            if (null == oriName) {
                return ApiResponse.error(403, "empty file");
            }
            String prefix = oriName.substring(oriName.lastIndexOf("."));
            // 文件存储名称 100000000.jpg
            String fileName = System.currentTimeMillis() + prefix;
            // 文件存储完整路径
            String filePath = this.filePath + File.separator + fileName;
            File file1 = new File(filePath);
            file.transferTo(file1);
            String logoUrl = baseUrl + "/" + fileName;
            log.info("文件保存成功: {}, {}", filePath, logoUrl);
            if (1 == companyDao.updateLogo(logoUrl, id)) {
                log.info("数据更新成功");
                return ApiResponse.success(null);
            }
            log.info("数据更新失败");
            return ApiResponse.error();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<Integer> updateWebsiteSendJob(int fromId, int toId, String website) {
        log.info("updateWebsiteSendJob({}, {}, {})", fromId, toId, website);
        int count = 0;
        try {
            for (int i = fromId; i <= toId; i++) {
                if (1 == jobDao.updateWebsiteSendJob(i, website)) {
                    count++;
                }
            }
            log.info("数据修改成功{}条", count);
            return ApiResponse.success(count);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    @Override
    public ApiResponse<List<Company>> listAllCompany() {
        log.info("listAllCompany()");
        try {
            return ApiResponse.success(companyDao.listAll());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    private ApiResponse<Map<String, Object>> handleMapData(Map<String, Object> data, int companyCount, int jobCount) {
        try {
            Map<String, Object> res = new HashMap<>(2);
            long now = System.currentTimeMillis();
            // 添加公司
            Company company = (Company) data.get("company");
            company.setAddTime(new Timestamp(now));
            if (1 == companyDao.addCompany(company)) {
                res.put("company", companyCount + 1);
            } else {
                return ApiResponse.error(403, "company add fail.");
            }
            // 添加职位
            List jobs = (List) data.get("jobs");
            List<Integer> jobIds = new ArrayList<>(jobs.size());
            for (Object item : jobs) {
                Job job = (Job) item;
                job.setId(++jobCount);
                job.setAddTime(new Timestamp(now));
                job.setShow(1);
                job.setViewCnt(0);
                job.setSendCnt(0);
                if (1 == jobDao.addJob(job)) {
                    jobIds.add(jobCount);
                }
            }
            res.put("jobs", jobIds);
            log.info("数据添加成功");
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 从xlsx中读取职位信息
     * 
     * @param filePath  文件存放路径
     * @param companyId 公司id
     * @return Map<String, Object>
     */
    private Map<String, Object> readJobsFile(String filePath, int companyId) throws IOException, ParseException {
        XSSFWorkbook wb = new XSSFWorkbook(filePath);
        return handleXlsx(wb, companyId);
    }

    private Map<String, Object> readJobsFile(MultipartFile file, int companyId) throws IOException, ParseException {
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        return handleXlsx(wb, companyId);
    }

    private Map<String, Object> handleXlsx(XSSFWorkbook workbook, int companyId) throws ParseException {
        Map<String, Object> data = new HashMap<>(2);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        // 跳过第一行
        rows.next();
        // 公司信息
        Company company = new Company();
        String suffix1 = "公司招聘总章";
        String suffix2 = "招聘总章";
        String companyName = ((XSSFRow) rows.next()).getCell(0).getStringCellValue();
        if (companyName.contains(suffix1)) {
            companyName = companyName.substring(0, companyName.indexOf(suffix1));
        } else if (companyName.contains(suffix2)) {
            companyName = companyName.substring(0, companyName.indexOf(suffix2));
        }
        company.setName(companyName);
        rows.next();
        company.setContact(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setEmail(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setNature(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setIndustry(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setDescription(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setWelfare(((XSSFRow) rows.next()).getCell(1).getStringCellValue());
        company.setId(companyId);
        // 公司信息放入数据
        data.put("company", company);
        // 职位信息
        List<Job> jobs = new ArrayList<>();
        int rowNum = sheet.getLastRowNum();
        int rowGroupNum = (rowNum - 9) / 11;
        int cellNum = 5;
        // 11行为一组 每组中有5个
        for (int i = 0; i < rowGroupNum; i++) {
            // 跳过两行
            rows.next();
            rows.next();
            XSSFRow titleRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != titleRow.getCell(j + 1) && null != titleRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(titleRow.getCell(j + 1).getStringCellValue())) {
                    jobs.add(new Job());
                    Job job = jobs.get(i * 5 + j);
                    job.setTitle(titleRow.getCell(j + 1).getStringCellValue());
                    job.setCompanyId(companyId);
                }
            }
            XSSFRow natureRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != natureRow.getCell(j + 1) && null != natureRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(natureRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setNature(natureRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow salaryRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != salaryRow.getCell(j + 1) && null != salaryRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(salaryRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setSalary(salaryRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow educationRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != educationRow.getCell(j + 1) && null != educationRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(educationRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setEducation(educationRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow descriptionRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != descriptionRow.getCell(j + 1) && null != descriptionRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(descriptionRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setDescription(descriptionRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow demandRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != demandRow.getCell(j + 1) && null != demandRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(demandRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setDemand(demandRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow locationRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != locationRow.getCell(j + 1) && null != locationRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(locationRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setLocation(locationRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow addressRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != addressRow.getCell(j + 1) && null != addressRow.getCell(j + 1).getStringCellValue()
                        && !"".equals(addressRow.getCell(j + 1).getStringCellValue())) {
                    Job job = jobs.get(i * 5 + j);
                    job.setAddress(addressRow.getCell(j + 1).getStringCellValue());
                }
            }
            XSSFRow deadlineRow = (XSSFRow) rows.next();
            for (int j = 0; j < cellNum; j++) {
                if (null != deadlineRow.getCell(j + 1)) {
                    XSSFCell deadLine = deadlineRow.getCell(j + 1);
                    if (deadLine.getCellType() == Cell.CELL_TYPE_NUMERIC && null != deadLine.getDateCellValue()) {
                        Job job = jobs.get(i * 5 + j);
                        job.setDeadline(deadLine.getDateCellValue());
                    } else if (deadLine.getCellType() == Cell.CELL_TYPE_STRING
                            && null != deadLine.getStringCellValue()) {
                        Job job = jobs.get(i * 5 + j);
                        job.setDeadline(FORMAT.parse(deadLine.getStringCellValue()));
                    }
                }
            }
        }
        // 职位信息放入数据
        data.put("jobs", jobs);
        return data;
    }

}
