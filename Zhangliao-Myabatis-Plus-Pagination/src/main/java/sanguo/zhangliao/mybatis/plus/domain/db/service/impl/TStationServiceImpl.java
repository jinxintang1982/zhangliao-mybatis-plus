package sanguo.zhangliao.mybatis.plus.domain.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.mapper.TStationMapper;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;

import java.util.Map;

/**
 * <p>
 * 站点表 服务实现类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-10
 */
@Service
public class TStationServiceImpl extends ServiceImpl<TStationMapper, TStation> implements ITStationService {

    @Override
    public IPage<TStation> listPage(Map queryParam,Integer currentPage,Integer pageSize) {
        LambdaQueryWrapper<TStation> queryWrapper = Wrappers.lambdaQuery();
        //根据queryParam设置Wrapper的查询条件
        //......
        return page(new Page<>(currentPage,pageSize), queryWrapper);
    }
}
