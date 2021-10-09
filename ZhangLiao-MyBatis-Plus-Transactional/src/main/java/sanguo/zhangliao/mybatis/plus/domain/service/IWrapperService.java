package sanguo.zhangliao.mybatis.plus.domain.service;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-15 23:03
 **/

public interface IWrapperService {
    void selectAndUpdate();
    void select();
    void createWrongTrans();
    void selectTestTransStart();
    void updateSleep(Long id,String no,Long sleepBefore,Long sleepAfter);
    void updateThrowException(Long id,String no);
    void updateBySqlSleep(Long sleepSecound);
    void checkValid( Long orderId,String cellNo);
}
