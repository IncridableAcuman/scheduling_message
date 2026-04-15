package com.scheduled.task;

import com.scheduled.task.scheduling.TaskScheduling;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


@SpringBootTest(properties = {
		"MAIL_USER=test@gmail.com",
		"MAIL_PASS=testpassword",
		"client.name=TestClient"
})
class TaskApplicationTests {


	@MockitoSpyBean
	TaskScheduling taskScheduling;

	@MockitoSpyBean
	JavaMailSender javaMailSender;

	@Test
	void contextLoads() {

	}



	@Test
	public void sendMessage(){
		await().atMost(Duration.ofSeconds(10)).untilAsserted(()-> {
			verify(taskScheduling,atLeast(1)).sendMessage();

			verify(javaMailSender,atLeast(1)).send(any(SimpleMailMessage.class));
		});
	}
}
