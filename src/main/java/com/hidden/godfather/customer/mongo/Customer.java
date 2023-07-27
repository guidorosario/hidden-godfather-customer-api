package com.hidden.godfather.customer.mongo;

import com.hidden.godfather.customer.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    private String name;
    @Indexed
    private String documentNumber;
    private DocumentType documentType;
    private String email;
    private String phone;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
