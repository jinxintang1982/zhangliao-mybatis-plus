package sanguo.zhangliao.mybatis.plus.domain.service;

import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;

public interface IServiceCurd {
    String getObjDemo();
    TStation getOneDemo();
    boolean saveBatch();
}
