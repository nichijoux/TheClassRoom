package com.zh.tcr.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zh.tcr.model.entity.vod.Subject;
import com.zh.tcr.model.vo.vod.SubjectExcelVO;
import com.zh.tcr.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectListener extends AnalysisEventListener<SubjectExcelVO> {
    //注入mapper
    private SubjectMapper subjectMapper;

    @Autowired
    public void setSubjectMapper(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    //一行一行，从第二行
    @Override
    public void invoke(SubjectExcelVO SubjectExcelVO, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        //  SubjectExcelVO -- Subject
        BeanUtils.copyProperties(SubjectExcelVO, subject);
        //添加
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}