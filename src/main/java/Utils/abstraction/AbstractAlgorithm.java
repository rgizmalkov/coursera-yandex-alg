package Utils.abstraction;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by elion on 19.02.2017.
 */
public abstract class AbstractAlgorithm implements Algorithm {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public Object run() {
        read();
        prepare();
        return alg();
    }

    @Override
    public abstract void read();

    @Override
    public abstract void prepare();

    @Override
    public abstract Object alg();

}
