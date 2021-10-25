package main.dao;

import com.sunyard.frame.base.base.dao.CommonDao;
import main.pojo.Student;
import org.springframework.stereotype.Repository;

/**
 * @author ：chengg.sun
 * @version: v1.0
 * @date ：Created in 2021/10/20 11:09
 * @description： 写明作用，调用方式，使用场景，以及特殊情况
 */
@Repository("studentDao")
public interface StudentDao extends CommonDao<Student> {
}
