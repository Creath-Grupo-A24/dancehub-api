package br.com.dancehub.api.contexts.invite;

import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/invite")
public interface InviteAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> invite(@RequestBody CreateInviteRequest request);

    @GetMapping(
            value = "/confirm/{key}",
            produces = MediaType.TEXT_HTML_VALUE
    )
    String confirm(@PathVariable String key);
}
