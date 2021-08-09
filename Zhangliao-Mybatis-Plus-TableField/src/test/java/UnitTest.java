import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-07-12 19:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MegviiApplication.class)
@Slf4j
public class UnitTest {

    @Autowired
    ITStationService itStationService;

    @Test
    public void TestInsert() throws InterruptedException {
        itStationService.save(new TStation().setId(100L).setNo("001"));
        Thread.sleep(2000L);
        itStationService.updateById(new TStation().setId(100L).setNo("002"));
        System.out.println(itStationService.getById(100L));
    }

}
