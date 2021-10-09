package sanguo.zhangliao.mybatis.plus.domain.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TFrame;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;

/**
 * <p>
 * 站点表 服务类
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-17
 */
public interface ITFrameService extends IService<TFrame> {
    boolean updateStatusByOrderCellId(Long orderId, String cellNo, int status);
    boolean updateStatusDown(String cellNo);
}
