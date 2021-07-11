package sanguo.zhangliao.mybatis.plus.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.infrastructure.common.PageResult;

import java.util.Map;

public interface IPaginationService {
    IPage<TStation> listPage(Map param, Integer currentPage, Integer pageSize);
}
