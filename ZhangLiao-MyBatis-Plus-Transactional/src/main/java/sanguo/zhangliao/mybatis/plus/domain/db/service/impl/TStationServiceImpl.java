package sanguo.zhangliao.mybatis.plus.domain.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.mapper.TStationMapper;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 站点表 服务实现类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-17
 */
@Service
@Slf4j
public class TStationServiceImpl extends ServiceImpl<TStationMapper, TStation> implements ITStationService {

    @Override
    @Transactional
    public void updateBySql(String shelfNo) {
        LambdaUpdateWrapper<TStation> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TStation::getNo, shelfNo)
                .setSql("type = type +1");
        if (update(updateWrapper)) {
            log.info("updateBySql");

        }
    }

    @Override
    public boolean clearArrangeTaskIdByNo(String cellNo) {
        LambdaUpdateWrapper<TStation> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TStation::getNo, cellNo)
                .set(TStation::getArrangeTaskId, -1);
        return update(updateWrapper);
    }

}
