package math.wondo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Stores information to indetify the user.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity(name = "User")
public final class User {
    
    @Id
    @GeneratedValue
    @Column(name = "UserId")
    private long id;
    
    @Column(name = "Alis")
    private final String alias;

    // Empty constructor for JSON deserialization
    protected User() { 
        alias = null;
    }
}
