package com.zh.tcr.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.model.entity.vod.Subject;
import com.zh.tcr.model.vo.vod.SubjectExcelVO;
import com.zh.tcr.vod.listener.SubjectListener;
import com.zh.tcr.vod.mapper.SubjectMapper;
import com.zh.tcr.vod.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    private SubjectListener subjectListener;

    @Autowired
    public void setSubjectListener(SubjectListener subjectListener) {
        this.subjectListener = subjectListener;
    }

    @Override
    public List<Subject> getChildSubject(Long id) {
        // SELECT * FROM SUBJECT WHERE parent_id = id
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);
        // subjectList遍历，得到每个subject对象，判断是否有下一层数据，有hasChildren = true
        for (Subject subject : subjectList) {
            // 获取subject的id值
            Long subjectId = subject.getId();
            // 查询subjectId是否有子节点
            subject.setHasChildren(haveChild(subjectId));
        }
        return subjectList;
    }

    @Override
    public void exportSubject(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和 easyexcel 没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);

            //List<Subject> ---  List<SubjectExcelVO>
            List<SubjectExcelVO> SubjectExcelVOList = new ArrayList<>();
            for (Subject subject : subjectList) {
                SubjectExcelVO SubjectExcelVO = new SubjectExcelVO();
                BeanUtils.copyProperties(subject, SubjectExcelVO);
                SubjectExcelVOList.add(SubjectExcelVO);
            }

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectExcelVO.class)
                    .sheet("课程分类")
                    .doWrite(SubjectExcelVOList);
        } catch (Exception e) {
            throw new TCRException(20001, "导出失败");
        }
    }

    @Override
    public void importSubject(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectExcelVO.class,
                    subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new TCRException(20001, "导入失败");
        }
    }

    // 判断是否有下一层数据
    private boolean haveChild(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
