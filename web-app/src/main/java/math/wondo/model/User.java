package math.wondo.model;

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
public final class User {
    
    private final String alias;

    // Empty constructor for JSON deserialization
    protected User() { 
        alias = null;
    }
}
