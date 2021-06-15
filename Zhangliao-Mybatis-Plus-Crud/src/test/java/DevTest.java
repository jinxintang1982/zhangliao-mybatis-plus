import com.baomidou.mybatisplus.extension.service.IService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
import sanguo.zhangliao.mybatis.plus.domain.service.IServiceCurd;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-14 23:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MegviiApplication.class)
public class DevTest {
    @Autowired
    IServiceCurd IServiceCurd;

    @Test
    public void getObjTest() {
        System.out.println(IServiceCurd.getObjDemo());
    }

    @Test
    public void getOneTest(){
        System.out.println(IServiceCurd.getOneDemo());
    }
}
