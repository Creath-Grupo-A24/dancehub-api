package br.com.dancehub.api.controllers;

import br.com.dancehub.api.contexts.invite.InviteAPI;
import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import br.com.dancehub.api.usecases.invite.InviteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InviteController implements InviteAPI {

    private final InviteUseCase useCase;

    @Override
    public ResponseEntity<?> invite(CreateInviteRequest request) {
        this.useCase.execute(request);
        return ResponseEntity.noContent().build();
    }
}
