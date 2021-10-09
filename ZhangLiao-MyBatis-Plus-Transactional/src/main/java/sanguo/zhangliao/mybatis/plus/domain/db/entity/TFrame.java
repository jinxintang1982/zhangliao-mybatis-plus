package sanguo.zhangliao.mybatis.plus.domain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 站点表
 * </p>
 *
 * @author zhangchangzhi
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 站点编码
     */
    private String no;
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 记录插入时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    private Long orderId;
    private String cellNo;
    private Integer status;
}
