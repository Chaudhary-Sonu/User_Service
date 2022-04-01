package com.dailycodebuffer.user.util;

import com.dailycodebuffer.user.vo.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "FeignClientDemo", url = "http://localhost:9001/departments")
public interface FeignServiceUtil {

    @GetMapping("/{id}")
    Department getDepartmentById(Long departmentId);
}
