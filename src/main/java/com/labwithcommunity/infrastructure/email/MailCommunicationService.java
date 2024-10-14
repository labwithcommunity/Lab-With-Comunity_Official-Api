package com.labwithcommunity.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
class MailCommunicationService implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${email.sender}")
    private String emailSender;

    @Value("${register.url.approve}")
    private String registerUrlApprove;

    @Value("${smtp.queue.time}")
    private long smtpQueueTime;

    private final Queue<SimpleMailMessage> emailQueue = new LinkedList<>();

    public MailCommunicationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        startEmailQueueProcessor();
    }
    @Override
    public void sendRegisterEmail(String recipient, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setFrom(emailSender);
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration by clicking the following link: "+ registerUrlApprove + token);
        mailSender.send(message);
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