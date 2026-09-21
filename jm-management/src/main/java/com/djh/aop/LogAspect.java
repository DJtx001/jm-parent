package com.djh.aop;


import com.djh.entity.OperateLog;
import com.djh.mapper.OperateLogMapper;
import com.djh.utils.CurrentUserHoler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around(("@annotation(com.djh.anno.LogOperation)"))
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // 记录请求参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString( args);

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long costTime = System.currentTimeMillis() - startTime;

        // 保存日志
        OperateLog log = new OperateLog();
        log.setOperateUserId(CurrentUserHoler.getCurrentUser()); //操作用户
        log.setOperateTime(LocalDateTime.now()); // 操作时间
        log.setClassName(joinPoint.getTarget().getClass().getName()); // 类名
        log.setMethodName(joinPoint.getSignature().getName()); // 方法名
        log.setMethodParams(methodParams); // 参数
        log.setReturnValue(result.toString()); // 返回值
        log.setCostTime(costTime); // 耗时
            
        operateLogMapper.insert(log);
        return result;
    }
    
}