package cn.globalyouth.cosineapi.service.enterprise.impl;

import cn.globalyouth.cosineapi.common.enums.ResponseEnumState;
import cn.globalyouth.cosineapi.common.enums.YesOrNo;
import cn.globalyouth.cosineapi.common.exception.GraceException;
import cn.globalyouth.cosineapi.common.utils.org.n3r.idworker.Sid;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.CreateJobBO;
import cn.globalyouth.cosineapi.model.bo.enterprise.jobmanagement.UpdateJobBO;
import cn.globalyouth.cosineapi.service.enterprise.JobManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class JobManageServiceImpl implements JobManageService {
    @Autowired
    private Sid sid;


}
