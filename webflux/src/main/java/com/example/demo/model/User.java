// model/User.java
package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
@Schema(description = "User entity representing a user in the system", title = "User")
public class User {
    @Id
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Schema(description = "Email address of the user", example = "john.doe@test.com")
    private String email;
}
