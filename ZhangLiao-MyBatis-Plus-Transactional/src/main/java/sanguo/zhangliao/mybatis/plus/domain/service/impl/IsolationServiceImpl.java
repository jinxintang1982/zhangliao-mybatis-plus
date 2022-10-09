package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IIsolationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IWrapperService;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-10-23 10:50
 **/
@Service
@Slf4j
public class IsolationServiceImpl implements IIsolationService {

    /**
     RR-Repeatable Read
     RC-Read Committed
     **/

    @Autowired
    IWrapperService wrapperService;

    @Autowired
    ITStationService itStationService;

    //事务的隔离级别
    @Override
    public void defaultIsolation()  {
        //Mysql默认的事务隔离级别为可重复读（read repeat）
        //H2的事务隔离级别为读已提交(read committed)
        new Thread(() -> wrapperService.selectAndUpdate()).start();
        new Thread(() -> wrapperService.select()).start();
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程结束~~!!~~~");
    }

}

