package Utils.annotations;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by romanizmalkov on 18.02.17.
 */
@Aspect
public class TestingInterceptor {
    private final static Logger LOGGER = Logger.getLogger(TestingInterceptor.class);

    @Pointcut("@annotation(annotation)")
    public void annotationPointCutDefinition(TestingAlgorithms annotation){
        System.out.println("one");
    }

//    @Pointcut("@annotation(timeLimitAnnotation)")
//    public void timeLimitAnnotation(TimeLimit timeLimitAnnotation) {
//        System.out.println("two");
//    }

    @Around("annotationPointCutDefinition(annotation)&& execution(* *(..))")
    public void aroundInvoke(ProceedingJoinPoint joinPoint, TestingAlgorithms annotation/*, TimeLimit timeLimitAnnotation*/) throws Throwable{
        List<Review> reviews = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();

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
                        Future<String> future = null;
                        Review review = null;
                        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                            reader.lines().forEach(System.out::println);
                            System.out.flush();
                            future = executor.submit(new Task(joinPoint));
                            String sreview = future.get(/*timeLimitAnnotation.limit()*/1, TimeUnit.SECONDS);
                            review = new Review(path.getFileName().toString(), sreview, CheckBox.TEST_PASSED);
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            review = new Review(path.getFileName().toString(), e.getMessage(),CheckBox.TEST_NOT_PASSED);
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            review = new Review(path.getFileName().toString(), "Time Limit!",CheckBox.TEST_NOT_PASSED);
                            future.cancel(Boolean.TRUE);
                            e.printStackTrace();
                        }finally {
                            reviews.add(review);
                        }
                    }
            );
        }
        executor.shutdown();

    }
}

class Task implements Callable<String> {
    private ProceedingJoinPoint joinPoint;

    Task(ProceedingJoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }

    @Override
    public String call() throws Exception {
        try {
            return String.valueOf(joinPoint.proceed());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "";
    }
}
