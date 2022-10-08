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

    }
}
