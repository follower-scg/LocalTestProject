package main.test;

import com.github.junrar.Archive;
import com.github.junrar.VolumeManager;
import com.github.junrar.exception.RarException;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.IOException;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/7/13 16:19
 * @description： 测试继承类
 */
public class TestExtend extends UDF {

    VolumeManager volumeManager;
    Archive a;

    {
        try {
            a = new Archive(volumeManager);
        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
