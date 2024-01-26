package br.com.dancehub.api.user;

import br.com.dancehub.api.config.PasswordService;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultPasswordService implements PasswordService {

    private static final CharacterRule LOWERCASE = new CharacterRule(EnglishCharacterData.LowerCase, 4);
    private static final CharacterRule UPPERCASE = new CharacterRule(EnglishCharacterData.UpperCase, 1);
    private static final CharacterRule NUMBER = new CharacterRule(EnglishCharacterData.Digit, 1);
    private static final LengthRule LENGTH_RULE = new LengthRule(8, Integer.MAX_VALUE);

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(final String pass) {
        return this.passwordEncoder.encode(pass);
    }

    @Override
    public boolean validatePassword(final String pass) {
        final PasswordData passwordData = new PasswordData(pass);
        final org.passay.PasswordValidator validator = new org.passay.PasswordValidator(LENGTH_RULE, NUMBER, UPPERCASE, LOWERCASE);
        return validator.validate(passwordData).isValid();
    }

}
