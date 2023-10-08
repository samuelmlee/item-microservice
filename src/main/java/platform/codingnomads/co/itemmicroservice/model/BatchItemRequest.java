package platform.codingnomads.co.itemmicroservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchItemRequest {
    private List<Long> itemIds;
}
