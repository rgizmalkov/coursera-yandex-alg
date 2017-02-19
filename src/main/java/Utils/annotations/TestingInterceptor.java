package Utils.annotations;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by romanizmalkov on 18.02.17.
 */
@Aspect
public class TestingInterceptor {
    private final static Logger LOGGER = Logger.getLogger(TestingInterceptor.class);

    @Pointcut("@annotation(annotation)")
    public void annotationPointCutDefinition(TestingAlgorithms annotation){
    }


    @Around(value = "annotationPointCutDefinition(annotation)", argNames = "joinPoint,annotation")
    public Object aroundInvoke(ProceedingJoinPoint joinPoint, TestingAlgorithms annotation){
        String spath = annotation.path();
        Stream<Path> paths = null;
        try {
            LOGGER.info("Start reading files from path ["  + spath + "]");
            paths = Files.walk(Paths.get(spath));
        } catch (IOException e) {
            LOGGER.error("Unsuccessful reading files from path["  + spath + "]");
            e.printStackTrace();
        }
        if(paths != null){
            paths.forEach(path ->
                    {
                        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                            reader.lines().forEach(System.err::println);
                            System.err.flush();
                            String result = (String) joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
            );
        }
        
    }
}
