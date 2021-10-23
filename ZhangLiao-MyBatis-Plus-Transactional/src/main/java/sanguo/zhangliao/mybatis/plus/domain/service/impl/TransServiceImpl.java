package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.ITransService;
import sanguo.zhangliao.mybatis.plus.domain.service.IWrapperService;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-10-23 10:50
 **/
@Service
@Slf4j
public class TransServiceImpl implements ITransService {

    /**
     RR-Repeatable Read
     RC-Read Committed
     **/

    @Autowired
    IWrapperService wrapperService;

    @Autowired
    ITStationService itStationService;

    public void testRR()  {
        new Thread(() -> wrapperService.selectAndUpdate()).start();
        new Thread(() -> wrapperService.select()).start();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程结束~~!!~~~");
    }

    public void devTrans() {
        wrapperService.createWrongTrans();
    }


    public void beginTrans() throws InterruptedException {
        new Thread(() -> wrapperService.updateSleep
                (1L, "aaa", 1L, 4L)).start();
        wrapperService.updateSleep(1L,"bbb",2L,1L);
        Thread.sleep(10000L);
    }

    public void updateBySql() throws InterruptedException{
        new Thread(() -> wrapperService.updateBySqlSleep(1L)).start();
        wrapperService.updateBySqlSleep(2L);

        Thread.sleep(5000L);
        System.out.println(itStationService.getById(1L));
    }


    public void selectTowRow()throws InterruptedException{
        new Thread(() ->wrapperService.selectToRow()).start();
        new Thread(() ->wrapperService.updateSleep(2L,"222",1L,0L)).start();
        Thread.sleep(5000L);
        log.info("selectTowRow end ");
    }

    public void deadLock() throws InterruptedException{
        new Thread(() -> wrapperService.thread1Exc("1")).start();
        new Thread(() -> wrapperService.thread2Exc(2L,"2")).start();
        Thread.sleep(3000L);
        log.info("deadLock end ");
    }
}

