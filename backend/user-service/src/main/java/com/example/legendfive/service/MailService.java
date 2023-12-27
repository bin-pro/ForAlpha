package com.example.legendfive.service;

import com.example.legendfive.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendMail(MailDto.MailSendDto mailSendDto);
}
