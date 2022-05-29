package io.github.gcdd1993.java.study.springboot.bizlog.logrecord;

import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import io.github.gcdd1993.java.study.springboot.bizlog.mapper.LogRecordMapper;
import io.github.gcdd1993.java.study.springboot.bizlog.model.po.LogRecordPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@Slf4j
@Service
public class LogRecordServiceImpl implements ILogRecordService {

    @Resource
    private LogRecordMapper logRecordMapper;

    /**
     * 异步写入操作日志
     *
     * @param logRecord 日志
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void record(LogRecord logRecord) {
//        log.info("record: {}", logRecord);
        LogRecordPO po = new LogRecordPO();
        BeanUtils.copyProperties(logRecord, po);
        po.setCreateTime(Instant.ofEpochMilli(logRecord.getCreateTime().getTime()));
        logRecordMapper.insert(po);
    }

    @Override
    public void batchRecord(List<LogRecord> records) {
        records.forEach(this::record);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return null;
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }
}
