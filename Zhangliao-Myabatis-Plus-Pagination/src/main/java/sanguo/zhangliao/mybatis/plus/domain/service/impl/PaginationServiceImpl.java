package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IPaginationService;

import java.util.Map;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-14 20:24
 **/
@Service
public class PaginationServiceImpl implements IPaginationService {
    @Autowired
    ITStationService itStationService;

    @Override
    public IPage<TStation> listPage(Map param, Integer currentPage, Integer pageSize) {
        return itStationService.listPage(param, currentPage, pageSize);
    }
}
