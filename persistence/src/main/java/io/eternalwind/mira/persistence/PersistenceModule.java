package io.eternalwind.mira.persistence;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import com.google.cloud.spring.autoconfigure.firestore.FirestoreRepositoriesAutoConfiguration;

@AutoConfigurationPackage
@AutoConfigureBefore(value = FirestoreRepositoriesAutoConfiguration.class)
public class PersistenceModule {
}
