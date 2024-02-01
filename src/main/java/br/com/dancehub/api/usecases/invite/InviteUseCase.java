package br.com.dancehub.api.usecases.invite;


import br.com.dancehub.api.contexts.company.Company;
import br.com.dancehub.api.contexts.company.CompanyRepository;
import br.com.dancehub.api.contexts.invite.Invite;
import br.com.dancehub.api.contexts.invite.InviteRepository;
import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InviteUseCase {

    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateKey(int size) {
        final StringBuilder key = new StringBuilder(size);
        final SecureRandom random = new SecureRandom();

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            key.append(CHARACTERS.charAt(randomIndex));
        }

        return key.toString();
    }

    public void execute(CreateInviteRequest request) {
        final Company company = companyRepository.findById(UUIDUtils.getFromString(request.companyId())).orElseThrow(() -> new NotFoundEntityException(Company.class, request.companyId()));
        final User guest = userRepository.findById(UUIDUtils.getFromString(request.guestId())).orElseThrow(() -> new NotFoundEntityException(User.class, request.guestId()));

        if (guest.getId() == null) {
            throw new RuntimeException("Não pode convidar esse usuário");
        }

        final Invite invite = Invite.builder()
                .guest(guest)
                .companyId(company.getId())
                .key(generateKey(50))
                .sent(false)
                .build();

        this.inviteRepository.save(invite);
    }
}
