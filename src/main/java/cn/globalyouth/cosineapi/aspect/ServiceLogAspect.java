package cn.globalyouth.cosineapi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aop  每个service调用记录日志
 */
@Aspect
@Component
public class ServiceLogAspect {
    private final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 1. 前置通知  在方法调用之前
     * 2. 后置通知  在方法正常调用之后执行
     * 3. 环绕通知  在方法调用之前和之后，都分别可以执行的通知
     * 4. 异常通过  如果在方法调用过程中发生异常  则通知
     * 5. 最终通知  在方法调用之后执行
     */

    /**
     * 切面表达式
     * execution 代表所要执行的表达式主体
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* cn.globalyouth.cosineapi.service..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("===== 开始执行  {}.{} =====",
                proceedingJoinPoint.getTarget().getClass(),
                proceedingJoinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();


        Object object = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long takeTime = end - begin;
        if (takeTime > 3000) {
            logger.error("======= 执行结束,耗时: {} 毫秒 ======", takeTime);
        } else if (takeTime > 2000) {
            logger.warn("======= 执行结束,耗时: {} 毫秒 ======", takeTime);
        } else {
            logger.info("======= 执行结束,耗时: {} 毫秒 ======", takeTime);
        }
        return object;
    }


}
