package br.com.dancehub.api.usecases.invite;

import br.com.dancehub.api.contexts.company.Company;
import br.com.dancehub.api.contexts.company.CompanyRepository;
import br.com.dancehub.api.contexts.invite.Invite;
import br.com.dancehub.api.contexts.invite.InviteRepository;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.contexts.user.role.RoleType;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfirmInviteUseCase {

    private final InviteRepository inviteRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;


    public String execute(final String key) {
        try {
            final Invite invite = this.inviteRepository.findByKey(key).orElseThrow(() -> new NotFoundEntityException(Invite.class, key));
            final User guest = invite.getGuest();
            if (guest.getCompanyId() != null || guest.getRoles().stream().anyMatch(role -> role.getType() == RoleType.MANAGER)) {
                return this.buildErrorPage();
            }
            final Company company = this.companyRepository.findById(invite.getCompanyId()).orElseThrow(() -> new NotFoundEntityException(Company.class, invite.getCompanyId().toString()));
            guest.setCompanyId(company.getId());
            this.userRepository.save(guest);
            return this.buildSuccessPage(company);
        } catch (NotFoundEntityException ignored) {
            return this.buildErrorPage();
        }
    }

    private String buildErrorPage() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Convite Não Aceitável</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            height: 100vh;
                        }
                                
                        .container {
                            text-align: center;
                            padding: 20px;
                            border-radius: 8px;
                            background-color: #fff;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                                
                        h1 {
                            color: #333;
                        }
                                
                        p {
                            color: #666;
                        }
                                
                        .btn {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 10px 20px;
                            text-decoration: none;
                            background-color: #3498db;
                            color: #fff;
                            border-radius: 4px;
                            transition: background-color 0.3s;
                        }
                                
                        .btn:hover {
                            background-color: #2980b9;
                        }
                    </style>
                </head>
                              
                <body>
                    <div class="container">
                        <h1>Convite Não Aceitável</h1>
                        <p>Você não pode mais aceitar este convite ou esta key é inválida.</p>
                    </div>
                </body>
                                
                </html>       \s
                """;
    }

    private String buildSuccessPage(Company company) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                                
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Convite Não Aceitável</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            height: 100vh;
                        }
                                
                        .container {
                            text-align: center;
                            padding: 20px;
                            border-radius: 8px;
                            background-color: #fff;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                                
                        h1 {
                            color: #333;
                        }
                                
                        p {
                            color: #666;
                        }
                                
                        .btn {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 10px 20px;
                            text-decoration: none;
                            background-color: #3498db;
                            color: #fff;
                            border-radius: 4px;
                            transition: background-color 0.3s;
                        }
                                
                        .btn:hover {
                            background-color: #2980b9;
                        }
                    </style>
                </head>
                              
                <body>
                    <div class="container">
                        <h1>Convite Aceito!</h1>
                        <p>Agora você faz parte da companhia %s.</p>
                    </div>
                </body>
                                
                </html>       \s
                """.formatted(company.getName());
    }

}
