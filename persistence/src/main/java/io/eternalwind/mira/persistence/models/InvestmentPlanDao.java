package io.eternalwind.mira.persistence.models;

import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collectionName = "investment_plan")
public class InvestmentPlanDao {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SectorWeightDao {
        String sector;
        Double weight;
    }

    @DocumentId
    String id;
    Timestamp createdTime;
    Timestamp lastUpdatedTime;

    String name;
    List<SectorWeightDao> sectorWeights;
}
