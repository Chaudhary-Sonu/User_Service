package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import com.dailycodebuffer.user.util.FeignServiceUtil;
import com.dailycodebuffer.user.vo.Department;
import com.dailycodebuffer.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeignServiceUtil feignServiceUtil;

    /*@Autowired
    private RestTemplate restTemplate;*/

    /*@Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private LoadBalancerClient loadBalancerClient;*/

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService");
        return userRepository.save(user);
    }

    /*public String getBaseUrl(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("DEPARTMENT-SERVICE");
        return serviceInstance.getUri().toString();
    }*/

    public ResponseTemplateVO getUserWithDepartment(Long userId) {

        log.info("Inside getUserWithDepartment method of UserService");

        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findById(userId).orElse(new User());

        //RestTemplate restTemplate = new RestTemplate();

        /*Department department =
                restTemplateBuilder.build().getForObject(getBaseUrl() + user.getDepartmentId()
                        , Department.class);*/

        Department department =
                feignServiceUtil.getDepartmentById(user.getDepartmentId());

        responseTemplateVO.setUser(user);
        responseTemplateVO.setDepartment(department);

        return responseTemplateVO;
    }
}
