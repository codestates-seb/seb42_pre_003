package com.jmc.stackoverflowbe.qa.service;

import com.jmc.stackoverflowbe.qa.entity.QA;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface QAService {
    QA createQA(QA qa);
    QA updateQA(QA qa);
    QA getQA(Long id);
    void deleteQA(Long id);
    QA findExistId(Long id);

}
