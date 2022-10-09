package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TFrame;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITFrameService;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IWrapperService;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-15 23:04
 **/
@Service
@Slf4j
public class WrapperServiceImpl implements IWrapperService {

    @Autowired
    ITStationService itStationService;
    Long stationId = 1L;
    @Autowired
    ITFrameService itFrameService;

    @Override
    @Transactional
    public void selectAndUpdate() {

        log.info("线程-1-开启事务");
        try {
            TStation station = itStationService.getById(stationId);
            log.info("线程-1-更新前查询 station = {}", station.getNo());
            Thread.sleep(1000L);
            itStationService.updateById(new TStation().setNo("2#").setId(stationId));
            log.info("线程-1-更新完成 2#");
            station = itStationService.getById(stationId);
            log.info("线程-1-更新后查询 station = {}", station.getNo());
            Thread.sleep(4000L);
            log.info("线程-1-提交事务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void select() {
        log.info("线程-2-开启事务");
        try {
            TStation station = itStationService.getById(stationId);
            log.info("线程-2-更新前查询 station = {}", station.getNo());
            Thread.sleep(2000L);
            station = itStationService.getById(stationId);
            log.info("线程-2-在线程1更新后，提交事务前查询 station = {}", station.getNo());
            Thread.sleep(4000L);
            station = itStationService.getById(stationId);
            log.info("线程-2-在线程1更新后，提交事务后查询 station = {}", station.getNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
