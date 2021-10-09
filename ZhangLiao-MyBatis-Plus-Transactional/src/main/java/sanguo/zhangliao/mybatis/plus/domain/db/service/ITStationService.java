package sanguo.zhangliao.mybatis.plus.domain.db.service;

import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 站点表 服务类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-17
 */
public interface ITStationService extends IService<TStation> {
    void updateBySql(String shelfNo);
    boolean clearArrangeTaskIdByNo(String cellNo);
}
