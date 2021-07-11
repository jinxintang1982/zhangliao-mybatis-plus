package sanguo.zhangliao.mybatis.plus.domain.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;

import java.util.Map;

/**
 * <p>
 * 站点表 服务类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-10
 */
public interface ITStationService extends IService<TStation> {
    IPage<TStation> listPage(Map query,Integer currentPage,Integer pageSize);
}
