package sample.jersey;

import me.yuanqingfei.swmm.swmm5;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by aaron on 16-5-15.
 */

@Component
public class SwmmService {

    public static final String DATA_DIR = System.getProperty("user.home") + "/";

    public static final String REPORT_FILENAME = "report.txt";

    public static final String OUTPUT_FILENAME = "output";

    static {
        try {
            System.loadLibrary("swmm5");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private String inputFileName;

    public synchronized void run(){
        Assert.notNull(inputFileName);
        swmm5.swmm_run(DATA_DIR + inputFileName, DATA_DIR + REPORT_FILENAME, DATA_DIR + OUTPUT_FILENAME);
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }
}
