package br.com.dancehub.api.contexts.invite;

import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/invites")
public interface InviteAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> invite(@RequestBody CreateInviteRequest request);
}
