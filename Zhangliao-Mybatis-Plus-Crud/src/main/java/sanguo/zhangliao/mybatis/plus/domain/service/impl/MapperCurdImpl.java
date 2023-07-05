package sanguo.zhangliao.mybatis.plus.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.mapper.TStationMapper;
import sanguo.zhangliao.mybatis.plus.domain.service.IMapperCurd;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-14 20:24
 **/
@Service
public class MapperCurdImpl implements IMapperCurd {

    @Resource
    TStationMapper tStationMapper;

    public void apply(){
        tStationMapper.selectList(Wrappers.<TStation>lambdaQuery()
                .eq(TStation::getNo, 1)
                .apply("name = 1") //and
                .apply("type = 2"));
    }
}
