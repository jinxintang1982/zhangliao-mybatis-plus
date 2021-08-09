package sanguo.zhangliao.mybatis.plus;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;

/**
 * @author anning
 * 2019-06-28 10:56
 **/
@Component
@Slf4j
public class InitRunner implements CommandLineRunner {
    @Autowired
    ITStationService itStationService;

    @Override
    public void run(String... args) {
        itStationService.saveOrUpdate(new TStation().setId(100L).setNo("001"));

//        LambdaUpdateWrapper<TStation> updateWrapper = Wrappers.lambdaUpdate();
//        updateWrapper.eq(TStation::getId,100L)
//                .set(TStation::getNo,"002");
//        itStationService.update(updateWrapper);

        //itStationService.updateById(new TStation().setNo("002"));

        TStation tStation = itStationService.getById(100L);
        itStationService.updateById(tStation.setNo("002").setVersion(4));

        System.out.println(itStationService.getById(100L));
    }
}
