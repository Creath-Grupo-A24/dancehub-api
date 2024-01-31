package br.com.dancehub.api.invite.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateInviteRequest(
        @JsonProperty("company_id")
        String companyId,
        @JsonProperty("guest_id")
        String guestId
) {

}
