package com.labwithcommunity.infrastructure.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
@Slf4j
class MailCommunicationService implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailSender;

    @Value("${register.url.approve}")
    private String registerUrlApprove;

    @Value("${smtp.queue.time}")
    private long smtpQueueTime;

    @Value("${change.password.url}")
    private String changePasswordUrl;

    private final Queue<SimpleMailMessage> emailQueue = new LinkedList<>();

    public MailCommunicationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        startEmailQueueProcessor();
    }
    @Override
    public void sendRegisterEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(emailSender);
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration by clicking the following link: "+ registerUrlApprove + token);
        enqueueEmail(message);
    }

    @Override
    public void sendPasswordResetEmail(String email, String resetToken)  {
        log.info("Starting to send password reset email to: {}", email);

        String changePasswordUrlWithParams = changePasswordUrl + resetToken + "&email=" + email;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(emailSender);
        message.setSubject("Password Reset Request");
        message.setText("Please confirm your password change request by clicking the link below: "+ changePasswordUrlWithParams);
        enqueueEmail(message);
    }

    private void enqueueEmail(SimpleMailMessage message) {
        synchronized (emailQueue) {
            emailQueue.add(message);
            emailQueue.notify();
        }
    }

    private void startEmailQueueProcessor() {
        Thread emailProcessorThread = new Thread(() -> {
            while (true) {
                try {
                    SimpleMailMessage message;
                    synchronized (emailQueue) {
                        while (emailQueue.isEmpty()) {
                            emailQueue.wait();
                        }
                        message = emailQueue.poll();
                    }
                    if (message != null) {
                        mailSender.send(message);
                        Thread.sleep(smtpQueueTime * smtpQueueTime * 1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        emailProcessorThread.setDaemon(true);
        emailProcessorThread.start();
    }


}