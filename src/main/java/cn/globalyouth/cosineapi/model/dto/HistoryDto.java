package cn.globalyouth.cosineapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyufeng, Rujun Yan
 * 搜索历史dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

    private int id;

    private String word;
}
