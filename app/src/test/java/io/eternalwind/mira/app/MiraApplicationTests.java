package io.eternalwind.mira.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class MiraApplicationTests {
	public FirestoreEmulatorContainer emulator = new FirestoreEmulatorContainer(
			DockerImageName.parse("gcr.io/google.com/cloudsdktool/google-cloud-cli:380.0.0-emulators"));

	@Test
	void contextLoads() {
	}

}
