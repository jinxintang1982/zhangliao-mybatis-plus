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
            itStationService.updateById(new TStation().setNo("11111").setId(stationId));
            log.info("线程-1-更新完成");
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
            log.info("线程-2-在线程1更新后，提交事务前查询  station = {}", station.getNo());
            Thread.sleep(2000L);
            station = itStationService.getById(stationId);
            log.info("线程-2-在线程1，提交事务后查询 station = {}", station.getNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createWrongTrans() {
        updateAndThrow();
    }

    @Transactional
    public void updateAndThrow() {
        itStationService.updateById(new TStation().setNo("11111").setId(stationId));
        throw new RuntimeException("我要回滚；");
    }

    @Override
    @Transactional
    public void selectTestTransStart() {
        try {
            itFrameService.updateById(new TFrame().setId(1L).setName("aa"));
            TStation station;
            log.info("进入Select函数，已更新完Frame表");
            Thread.sleep(3000L);
            log.info("开始查询TStation表");
            station = itStationService.getById(stationId);
            log.info(station.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateSleep(Long id, String no, Long sleepBefore, Long sleepAfter) {
        try {
            Thread.sleep(sleepBefore * 1000);
            log.info("----更新Station表前，设置值为 = {}", no);
            itStationService.updateById(new TStation().setNo(no).setId(id));
            log.info("----更新Station表完成，设置值为 = {}", no);
            Thread.sleep(sleepAfter * 1000);
            log.info("----提交事务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateThrowException(Long id, String no) {
        try {
            itStationService.getById(id);
            //itStationService.updateById(new TStation().setNo(no).setId(id));
            log.info("A----更新Station表完成，设置值为 = {}", no);
            Thread.sleep(3000L);
            throw new RuntimeException("A---- throw Exception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateBySqlSleep(Long sleepSecond) {
        log.info("begin " + itStationService.getById(1L).toString());
        try {
            Thread.sleep(sleepSecond * 1000);
            itStationService.updateBySql("1#");
            log.info("end" + itStationService.getById(1L).toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkValid(Long orderId, String cellNo) {
        try {
            Thread.sleep(1000);
            log.info("begin updateStatusByOrderCellId");
            itFrameService.updateStatusByOrderCellId(orderId, cellNo, 7);
            Thread.sleep(1000);
            log.info("end updateStatusByOrderCellId");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void cellDownFinish(Long cellId, String cellNo) {
        try {
            log.info("begin updateById");
            itFrameService.updateById(new TFrame().setId(cellId).setReceiveTime("1123"));
            log.info("end updateById");
            Thread.sleep(2000);
            log.info("begin updateStatusDown");
            itFrameService.updateStatusDown(cellNo);
            log.info("end updateStatusDown");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
