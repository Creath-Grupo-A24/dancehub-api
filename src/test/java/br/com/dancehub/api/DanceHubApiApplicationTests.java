package br.com.dancehub.api;

import br.com.dancehub.api.contexts.company.CompanyResponse;
import br.com.dancehub.api.contexts.company.models.CreateCompanyRequest;
import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.contexts.user.models.AuthResponse;
import br.com.dancehub.api.contexts.user.models.SignUpRequest;
import br.com.dancehub.api.contexts.user.models.UserResponse;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
@AutoConfigureMockMvc
class DanceHubApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSignUp() throws Exception {
        SignUpRequest request = new SignUpRequest("teste123novo", "emailteste@gmail.com", "novasenha123", "Roberto alves", "12/10/2001", 2, "21512607045", null);

        MvcResult result = mockMvc.perform(post("/v1/sign/up").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsBytes(request))).andExpect(status().isOk()).andExpect(jsonPath("$.user.name", is("Roberto alves"))).andReturn();

        AuthResponse authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponse.class);

        this.userRepository.deleteById(UUIDUtils.getFromString(authResponse.user().id()));
    }

    @Test
    void testGetUser() throws Exception {
        this.userRepository.deleteById(UUIDUtils.getFromString("27d048ae-e417-4219-a87d-21c761ff059c"));
        SignUpRequest request = new SignUpRequest("teste123novo", "emailteste@gmail.com", "novasenha123", "Roberto alves", "12/10/2001", 2, "21512607045", null);

        MvcResult result = mockMvc.perform(post("/v1/sign/up").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(request))).andExpect(status().isOk()).andExpect(jsonPath("$.user.name", is("Roberto alves"))).andReturn();

        AuthResponse authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponse.class);

        mockMvc.perform(get("/v1/sign/" + authResponse.user().id())).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Roberto alves")));


        this.userRepository.deleteById(UUIDUtils.getFromString(authResponse.user().id()));
    }

    @Test
    void testCreateCompany() throws Exception {
        SignUpRequest request = new SignUpRequest("teste123novo", "emailteste@gmail.com", "novasenha123", "Roberto alves", "12/10/2001", 2, "21512607045", null);
        MvcResult result = mockMvc.perform(post("/v1/sign/up").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(request))).andExpect(status().isOk()).andExpect(jsonPath("$.user.name", is("Roberto alves"))).andReturn();
        AuthResponse authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponse.class);


        // teste criar companhia

        String userId = authResponse.user().id();

        CreateCompanyRequest companyRequest = new CreateCompanyRequest(userId, "NOVA COMPANHIA VIP");
        mockMvc.perform(post("/v1/companies/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(companyRequest))).andExpect(status().isCreated());

    }

    @Test
    void testGetCompany() throws Exception{
        mockMvc.perform(get("/v1/companies/82432f9b-b204-405f-8181-d69977d00774"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("NOVA COMPANHIA VIP")));
    }

    @Test
    void CreateInvite() throws Exception {
        CreateInviteRequest inviteRequest = new CreateInviteRequest("95b12a64-ab75-4950-9c57-26222467de04", "716fe419-6437-4cbe-a6fb-32ec1d781e5f");
        MvcResult result = (MvcResult) mockMvc.perform(post("/v1/invite").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(inviteRequest))).andExpect(status().isCreated());
    }
}



