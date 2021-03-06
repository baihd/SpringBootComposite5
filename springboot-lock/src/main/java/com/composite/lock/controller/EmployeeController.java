package com.composite.lock.controller;

import com.composite.lock.service.EmployeeService;
import com.composite.lock.utils.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TransactionUtil transactionUtil;

    /**
     * 未加锁
     */
    @RequestMapping(value = "increaseMoney")
    @ResponseBody
    public void increaseMoney() {
        LOGGER.info("start increaseMoney");
        int threadCount = 100;
        while (threadCount-- > 0) {
            new Thread(() -> employeeService.increaseMoney(1)).start();
        }
        LOGGER.info("end increaseMoney");
    }

    /**
     * 悲观锁
     */
    @RequestMapping(value = "increaseMoneyWithPessimisticLock")
    @ResponseBody
    public void increaseMoneyWithPessimisticLock() {
        LOGGER.info("start increaseMoneyWithPessimisticLock");
        int threadCount = 200;
        while (threadCount-- > 0) {
            //二者都可
            //在另一个线程执行
            //select * from employee where id =1 for update;会暂停当前线程
            //select * from employee where id =1;不会暂停当前线程
            //自动提交
            new Thread(() -> employeeService.increaseMoneyWithPessimisticLock(1)).start();
            //手动提交
            //new Thread(() -> transactionUtil.transact(s -> employeeService.increaseMoneyWithPessimisticLock(1))).start();
        }
        LOGGER.info("end increaseMoneyWithPessimisticLock");
    }

    @RequestMapping(value = "increaseMoneyWithOptimisticLock")
    @ResponseBody
    public void increaseMoneyWithOptimisticLock() {
        LOGGER.info("start increaseMoneyWithOptimisticLock");
        int threadCount = 100;
        while (threadCount-- > 0) {
            new Thread(() -> employeeService.increaseMoneyWithOptimisticLock(1)).start();
        }
        LOGGER.info("end increaseMoneyWithOptimisticLock");
    }


}
