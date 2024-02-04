package br.com.dancehub.api.jobs;

import br.com.dancehub.api.contexts.company.Company;
import br.com.dancehub.api.contexts.company.CompanyRepository;
import br.com.dancehub.api.contexts.invite.Invite;
import br.com.dancehub.api.contexts.invite.InviteRepository;
import br.com.dancehub.api.contexts.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InviteJob {

    private final InviteRepository inviteRepository;
    private final CompanyRepository companyRepository;
    private final JavaMailSender sender;

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void sendEmails() {
        final Page<Invite> invites = this.inviteRepository.findAllBySentIsFalse(PageRequest.of(0, 10));
        invites.get().forEach(invite -> {
            final Optional<Company> companyOptional = this.companyRepository.findById(invite.getCompanyId());
            if (companyOptional.isPresent()) {
                final Company company = companyOptional.get();
                final User guest = invite.getGuest();

                final SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("DANCE-HUB Convite para Companhia " + company.getName());
                message.setTo(guest.getEmail());
                message.setText("Acesse o link de convite: %s".formatted("http://localhost:8091/v1/invite/confirm/" + invite.getKey()));

                try {
                    this.sender.send(message);
                    invite.setSent(true);
                    this.inviteRepository.save(invite);
                } catch (MailException e) {
                    this.inviteRepository.delete(invite);
                }
            } else this.inviteRepository.delete(invite);
        });
    }

}
