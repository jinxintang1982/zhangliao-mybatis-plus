package sanguo.zhangliao.mybatis.plus.infrastructure.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anning
 * 2019-08-31 23:56
 **/
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -1824744679569359404L;

    private long total = 0;

    private List<T> data = new ArrayList<>(0);
}
