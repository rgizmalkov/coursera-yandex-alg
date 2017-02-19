package Utils.annotations;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by romanizmalkov on 18.02.17.
 */
@Aspect
public class TimingInterceptor {
    private final static Logger LOGGER = Logger.getLogger(TimingInterceptor.class);

    @Around("@annotation(Utils.annotations.Timing)  &&  execution(* *(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        Object returnObject = null;
        try {
            LOGGER.info("Invoke method: <" + joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName() + ">");
            long from = System.nanoTime();
            returnObject = joinPoint.proceed();
            long to = System.nanoTime();
            LOGGER.info("Processed time: " + (to - from)*(Math.pow(10, -9)));
        } catch (Throwable throwable) {
            LOGGER.error("Invoking of method: <" + joinPoint.getSignature().getName() + "> UNSUCCESSFUL " + throwable.getMessage());
            throw  throwable;
        } finally {
        }
        return returnObject;
    }

}
