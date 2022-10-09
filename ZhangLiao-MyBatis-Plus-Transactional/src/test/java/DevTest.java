import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
import sanguo.zhangliao.mybatis.plus.domain.service.IInvalidService;
import sanguo.zhangliao.mybatis.plus.domain.service.IIsolationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IWrapperService;


/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-14 23:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MegviiApplication.class)
@Slf4j
public class DevTest {
    @Autowired
    IIsolationService transService;

    @Autowired
    IInvalidService iInvalidService;

    @Test
    public void test()  {
        //iInvalidService.createWrongTrans();
        iInvalidService.createRightTrans();
    }

}
