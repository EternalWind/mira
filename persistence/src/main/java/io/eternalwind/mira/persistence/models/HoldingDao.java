package io.eternalwind.mira.persistence.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collectionName = "holding")
public class HoldingDao {
    @DocumentId
    String id;
    Timestamp createdTime;
    Timestamp lastUpdatedTime;

    String ticker;
    String name;
    Double amount;
    Double price;
    String sector;
}
