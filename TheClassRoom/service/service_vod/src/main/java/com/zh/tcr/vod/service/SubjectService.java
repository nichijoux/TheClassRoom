package com.zh.tcr.vod.service;

import com.zh.tcr.model.entity.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
public interface SubjectService extends IService<Subject> {
    // 根据id获取下层的学科列表
    List<Subject> getChildSubject(Long id);

    // 导出学科为excel表格
    void exportSubject(HttpServletResponse response);

    // 导入excel表格为学科
    void importSubject(MultipartFile file);
}
