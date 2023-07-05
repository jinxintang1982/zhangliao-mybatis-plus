package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IInvalidService;

@Service
@Slf4j
public class InvalidServiceImpl implements IInvalidService {

    @Autowired
    ITStationService itStationService;

    @Override
    @Transactional
    public void createRightTrans() {
        subPrivateMethod();
    }

    private void subPrivateMethod() {
        itStationService.updateById(new TStation().setId(1L).setNo("222"));
        throw new RuntimeException("roll back");
    }

    @Override
    public void createWrongTrans() {
        subTransMethod();
    }

    @Transactional
    public void subTransMethod() {
        itStationService.updateById(new TStation().setId(1L).setNo("222"));
        throw new RuntimeException("roll back");
    }
}
