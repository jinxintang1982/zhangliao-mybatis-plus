package sanguo.zhangliao.mybatis.plus.domain.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TFrame;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.mapper.TFrameMapper;
import sanguo.zhangliao.mybatis.plus.domain.db.mapper.TStationMapper;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITFrameService;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;

/**
 * <p>
 * 站点表 服务实现类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-17
 */
@Service
public class TFrameServiceImpl extends ServiceImpl<TFrameMapper, TFrame> implements ITFrameService {

    @Override
    public boolean updateStatusByCellNo(String cellNo, int status) {
        LambdaUpdateWrapper<TFrame> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TFrame::getNo,cellNo)
                .set(TFrame::getName,status);
        return update(updateWrapper);
    }

    @Override
    public boolean updateStatusByCellNo2(String cellNo) {
        LambdaUpdateWrapper<TFrame> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TFrame::getNo, cellNo)
                .set(TFrame::getName, 2);
        return update(updateWrapper);
    }
}
