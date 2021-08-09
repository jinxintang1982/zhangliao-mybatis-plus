import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
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

    @Autowired
    ITStationService itStationService;

    @Test
    public void devTest() throws InterruptedException {
        //wrapperService.selectAndUpdate();
        new Thread(() -> wrapperService.selectAndUpdate()).start();
        //new Thread(() -> wrapperService.select()).start();
        Thread.sleep(10000L);
        log.info("主线程结束~~~~~");
    }

    @Test
    public void devTrans() {
        wrapperService.createWrongTrans();
    }

    @Test
    public void beginTrans() throws InterruptedException {
        new Thread(() -> wrapperService.updateSleep
                (1L, "aaa", 1L, 4L)).start();
        wrapperService.updateSleep(1L,"bbb",2L,1L);
        Thread.sleep(10000L);
    }

    @Test
    public void updateBySql() throws InterruptedException{
        new Thread(() -> wrapperService.updateBySqlSleep(1L)).start();
        wrapperService.updateBySqlSleep(2L);

        Thread.sleep(5000L);
        System.out.println(itStationService.getById(1L));
    }
}
