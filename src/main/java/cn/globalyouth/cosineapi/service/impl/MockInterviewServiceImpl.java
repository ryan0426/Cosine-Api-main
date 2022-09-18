package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.mock.JoinMock;
import cn.globalyouth.cosineapi.model.bean.mock.MockInterview;
import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.dao.mock.JoinMockDao;
import cn.globalyouth.cosineapi.dao.mock.MockInterviewDao;
import cn.globalyouth.cosineapi.dao.user.UserDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.MockInterviewDto;
import cn.globalyouth.cosineapi.service.IMockInterviewService;
import cn.globalyouth.cosineapi.common.utils.StringUtils;
import cn.globalyouth.cosineapi.model.vo.req.JoinMockReqVo;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;




import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * @author liuyufeng
 * 模拟面试服务
 */
@Service
@Slf4j
public class MockInterviewServiceImpl implements IMockInterviewService {

    @Resource
    private MockInterviewDao mockInterviewDao;

    @Resource
    private JoinMockDao joinMockDao;

    @Resource
    private UserDao userDao;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询模拟面试
     *
     * @param industryId 行业id
     * @param time       时间列表
     * @return ApiResponse<List < MockInterviewDto>>
     */
    @Override
    public ApiResponse<List<MockInterviewDto>> listMockInterviews(Integer industryId, List<String> time) {
        try {
            List<MockInterviewDto> res = new ArrayList<>();
            // 查询模拟面试信息
            List<MockInterview> interviews;
            boolean allEmpty = null == industryId && (null == time || 0 == time.size());
            if (allEmpty) {
                interviews = mockInterviewDao.listAll();
            } else if (null == time || 0 == time.size()) {
                interviews = mockInterviewDao.listByIndustry(industryId);
            } else if (null == industryId) {
                interviews = new ArrayList<>();
                for (String timeStr : time) {
                    Date fromTime = DATE_FORMAT.parse(timeStr);
                    Date toTime = new Date(fromTime.getTime() + 24 * 60 * 60 * 1000);
                    interviews.addAll(mockInterviewDao.listByTime(fromTime, toTime));
                }
            } else {
                interviews = new ArrayList<>();
                for (String timeStr : time) {
                    Date fromTime = DATE_FORMAT.parse(timeStr);
                    Date toTime = new Date(fromTime.getTime() + 24 * 60 * 60 * 1000);
                    interviews.addAll(mockInterviewDao.listByIndustryAndTime(industryId, fromTime, toTime));
                }
            }
            interviews.forEach(interview -> {
                // 查询组织者信息
                User initiator = userDao.selectInitiator(interview.getInitiator());
                // 查询参与者信息
                List<JoinMock> joinUsers = joinMockDao.listMockUsers(interview.getId());
                List<User> participants;
                if (null != joinUsers && joinUsers.size() > 0) {
                    participants = userDao.selectParticipant(joinUsers.stream().map(JoinMock::getUser).collect(Collectors.toList()));
                } else {
                    participants = new ArrayList<>();
                }
                res.add(handleMockInterview(interview, initiator, participants));
            });
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
     * 添加参与模面用户
     *
     * @param data 添加的对象
     * @return ApiResponse
     */
    @Override
    public ApiResponse<Object> addJoinMock(JoinMockReqVo data) {
        try {
            boolean success = (0 == joinMockDao.countMockUser(data.getMock(), data.getUser()))
                    && (1 == joinMockDao.addMockUser(handleJoinMock(data)));
            if (success) {
                return ApiResponse.success(null);
            } else {
                return ApiResponse.error(403, "Data existed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 删除参与模面用户
     *
     * @param data 删除的对象
     * @return ApiResponse
     */
    @Override
    public ApiResponse<Object> delJoinMock(JoinMockReqVo data) {
        try {
            boolean success = (1 == joinMockDao.countMockUser(data.getMock(), data.getUser()))
                    && (1 == joinMockDao.delMockUser(handleJoinMock(data)));
            if (success) {
                return ApiResponse.success(null);
            } else {
                return ApiResponse.error(403, "Data absent.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error(sw.toString() + "\r\n\r\n");
            return ApiResponse.error();
        }
    }

    /**
     * 判断是否热门 少于2个名额，或少于2天开始，为热门
     */
    private boolean isHot(MockInterview mockInterview, int participantsCnt) {
        if (mockInterview.getContain() - participantsCnt <= 2) {
            return true;
        }
        Date now = new Date();
        long delay = mockInterview.getTime().getTime() - now.getTime();
        return delay <= 2 * 24 * 60 * 60 * 1000;
    }

    private MockInterviewDto handleMockInterview(MockInterview mockInterview, User initiator,
                                                 List<User> participants) {
        MockInterviewDto res = new MockInterviewDto();
        res.setId(mockInterview.getId());
        res.setTitle(mockInterview.getTitle());
        res.setDate(StringUtils.getDateStr(mockInterview.getTime()));
        res.setTime(StringUtils.getTimeStr(mockInterview.getTime()));
        res.setTimestamp(mockInterview.getTime().getTime());
        res.setHot(isHot(mockInterview, participants.size()));
        res.setContain(mockInterview.getContain());
        res.setRemaining(mockInterview.getContain() - participants.size());
        res.setLink(mockInterview.getLink());
        res.setContent(mockInterview.getContent());
        List<MockInterviewDto.Participant> innerList = new ArrayList<>();
        participants.forEach(user -> {
            MockInterviewDto.Participant p = new MockInterviewDto.Participant();
            p.setId(user.getId());
            p.setProfile(user.getProfile());
            innerList.add(p);
        });
        res.setParticipants(innerList);
        MockInterviewDto.Initiator inner = new MockInterviewDto.Initiator();
        inner.setId(inner.getId());
        inner.setProfile(initiator.getProfile());
        inner.setName(initiator.getName());
        inner.setIntroduction(initiator.getIntroduction());
        res.setInitiator(inner);
        return res;
    }

    private JoinMock handleJoinMock(JoinMockReqVo reqVo) {
        JoinMock res = new JoinMock();
        res.setUser(reqVo.getUser());
        res.setMock(reqVo.getMock());
        if(null != reqVo.getAddTime()) {
            res.setAddTime(new Timestamp(reqVo.getAddTime()));
        }
        return res;
    }
}
