import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sanguo.zhangliao.mybatis.plus.MegviiApplication;
import sanguo.zhangliao.mybatis.plus.domain.db.entity.TStation;
import sanguo.zhangliao.mybatis.plus.domain.db.service.ITStationService;
import sanguo.zhangliao.mybatis.plus.domain.service.IServiceCurd;


/**
 * @description:
 * @author: zhangchangzhi
 * @create: 2021-06-14 23:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MegviiApplication.class)
public class DevTest {
    @Autowired
    IServiceCurd iServiceCurd;
    @Autowired
    ITStationService itStationService;

    @Test
    public void getObjTest() {
        System.out.println(iServiceCurd.getObjDemo());
    }

    @Test
    public void getOneTest(){
        System.out.println(iServiceCurd.getOneDemo());
    }

    @Test
    public void saveBatch(){
        System.out.println(iServiceCurd.saveBatch());
        // INSERT INTO t_station ( name, frame_no ) VALUES ( {nnn1,zzz1}, ,{nnn2},{zz2} )
    }

    @Test
    public void save(){
        itStationService.save(new TStation().setFrameNo("zzz1").setName("nnn1"));
        itStationService.save(new TStation().setFrameNo("zzz2").setName("nnn2"));
        itStationService.save(new TStation().setFrameNo("zzz3").setName("nnn3"));
        // INSERT INTO t_station ( name, frame_no ) VALUES ( ?, ? )
        // INSERT INTO t_station ( name, frame_no ) VALUES ( ?, ? )
        // INSERT INTO t_station ( name, frame_no ) VALUES ( ?, ? )
    }
}
