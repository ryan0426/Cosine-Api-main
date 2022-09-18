package cn.globalyouth.cosineapi.service.impl;

import cn.globalyouth.cosineapi.model.bean.teachin.TeachIn;
import cn.globalyouth.cosineapi.model.bean.user.User;
import cn.globalyouth.cosineapi.dao.teachin.TeachInDao;
import cn.globalyouth.cosineapi.dao.user.UserDao;
import cn.globalyouth.cosineapi.common.utils.ApiResponse;
import cn.globalyouth.cosineapi.model.dto.TeachInDto;
import cn.globalyouth.cosineapi.service.ITeachInService;
import cn.globalyouth.cosineapi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyufeng, Rujun Yan
 * 宣讲会面试服务
 */
@Service
public class TeachInServiceImpl implements ITeachInService {

    @Resource
    private TeachInDao teachInDao;

    @Resource
    private UserDao userDao;

    /**
     * 查询模拟面试
     *
     * @param name 名称
     * @param vip  是否VIP 0全部 1VIP
     * @return ApiResponse<List < TeachInDto>>
     */
    @Override
    public ApiResponse<List<TeachInDto>> listMockInterviews(String name, int vip) {
        try {
            List<TeachInDto> res = new ArrayList<>();
            List<TeachIn> teachIns;
            // 查询宣讲会信息
            if (!StringUtils.notEmpty(name) && vip == 0) {
                teachIns = teachInDao.listAll();
            } else if (!StringUtils.notEmpty(name) && vip == 1) {
                teachIns = teachInDao.listVip();
            } else if (StringUtils.notEmpty(name) && vip == 0) {
                teachIns = teachInDao.listByName(name);
            } else {
                teachIns = teachInDao.listVipByName(name);
            }
            teachIns.forEach(teachIn -> {
                // 查询组织者信息
                User initiator = userDao.selectInitiator(teachIn.getInitiator());
                res.add(handleTeachIn(teachIn, initiator));
            });
            return ApiResponse.success(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    private TeachInDto handleTeachIn(TeachIn teachIn, User initiator) {
        TeachInDto res = new TeachInDto();
        res.setId(teachIn.getId());
        res.setTitle(teachIn.getTitle());
        res.setImage(teachIn.getImage());
        res.setTimestamp(teachIn.getTime().getTime());
        res.setFullDate(StringUtils.getFullDateStr(teachIn.getTime()));
        res.setDate(StringUtils.getDateStr(teachIn.getTime()));
        res.setTime(StringUtils.getTimeStr(teachIn.getTime()));
        res.setHot(isHot(teachIn));
        res.setVip(teachIn.getIsVip() == 1);
        res.setGuest(teachIn.getGuest());
        res.setPeople(teachIn.getPeople());
        res.setLink(teachIn.getLink());
        res.setContent(teachIn.getContent());
        TeachInDto.Initiator inner = new TeachInDto.Initiator();
        inner.setId(initiator.getId());
        inner.setProfile(initiator.getProfile());
        inner.setName(initiator.getName());
        inner.setIntroduction(initiator.getIntroduction());
        res.setInitiator(inner);
        return res;
    }

    private boolean isHot(TeachIn teachIn) {
        if (teachIn.getPeople() > 100) {
            return true;
        }
        Date now = new Date();
        long delay = teachIn.getTime().getTime() - now.getTime();
        return delay <= 2 * 24 * 60 * 60 * 1000;
    }
}
