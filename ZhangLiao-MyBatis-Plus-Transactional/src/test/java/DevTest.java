import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
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
    IWrapperService wrapperService;

    @Test
    public void devTest() throws InterruptedException {
        //wrapperService.selectAndUpdate();
        new Thread(() -> wrapperService.selectAndUpdate()).start();
        //new Thread(() -> wrapperService.select()).start();
        Thread.sleep(10000L);
        log.info("主线程结束~~~~~");
    }

    @Test
    public void devTrans(){
        wrapperService.createWrongTrans();
    }
}
