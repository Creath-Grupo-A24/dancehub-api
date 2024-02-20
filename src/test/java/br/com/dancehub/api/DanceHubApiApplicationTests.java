package br.com.dancehub.api;

import br.com.dancehub.api.contexts.company.CompanyResponse;
import br.com.dancehub.api.contexts.company.models.CreateCompanyRequest;
import br.com.dancehub.api.contexts.event.models.CreateEventRequest;
import br.com.dancehub.api.contexts.invite.models.CreateInviteRequest;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.contexts.user.models.AuthResponse;
import br.com.dancehub.api.contexts.user.models.SignInRequest;
import br.com.dancehub.api.contexts.user.models.SignUpRequest;
import br.com.dancehub.api.contexts.user.models.UserResponse;
import br.com.dancehub.api.shared.SearchQuery;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;


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
        SignUpRequest request = new SignUpRequest("dilma", "dilmao@gmail.com", "1234", "dilma", "03/09/2046", 1, "91185067043", null);

        MvcResult result = mockMvc.perform(post("/v1/sign/up").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsBytes(request))).andExpect(status().isOk()).andExpect(jsonPath("$.user.name", is("dilma"))).andReturn();

        AuthResponse authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponse.class);

        this.userRepository.deleteById(UUIDUtils.getFromString(authResponse.user().id()));
    }

    @Test
    void testGetUser() throws Exception {

        SignUpRequest request = new SignUpRequest("testando", "testando@gmail.com", "1234", "testee", "07/09/2046", 1, "45925104021", null);

        MvcResult result = mockMvc.perform(post("/v1/sign/up").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsBytes(request))).andExpect(status().isOk()).andExpect(jsonPath("$.user.name", is("testee"))).andReturn();

        AuthResponse authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AuthResponse.class);

        mockMvc.perform(get("/v1/sign/" + authResponse.user().id())).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("testee")));


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

        List<Integer> integers = List.of(1, 2, 3);

    }

    @Test
    void testGetCompany() throws Exception {
        mockMvc.perform(get("/v1/companies/82432f9b-b204-405f-8181-d69977d00774"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("NOVA COMPANHIA VIP")));

    }

    @Test
    void testCreateInvite() throws Exception {
        CreateInviteRequest inviteRequest = new CreateInviteRequest("82432f9b-b204-405f-8181-d69977d00774", "409733de-beb8-4090-8120-24aa7f92cdd8");
        MvcResult result = mockMvc.perform(post("/v1/invite").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(inviteRequest)))
                .andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void testCreateEvent() throws Exception {

        CreateEventRequest eventRequest = new CreateEventRequest(
                "Sapateando",
                "Evento para quem gosta de sapateado",
                "São Vicente-SP",
                LocalDateTime.of(2024, 7, 12, 5, 0),
                List.of(1, 2, 3, 4));

        mockMvc.perform(post("/v1/events/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(eventRequest)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void testGetEvent() throws Exception {
        mockMvc.perform(get("/v1/events/822ecce2-4b5b-4875-a7ea-e9d00dfdaebb"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testEventList() throws Exception {
        mockMvc.perform(get("/v1/events/list").queryParam(
                        "page", "0",
                        "perPage", "10",
                        "terms", "",
                        "sort", "time",
                        "direction", "ASC"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testEventCategories() throws Exception {
        mockMvc.perform(get("/v1/events/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andReturn();
    }

    @Test
    void testCreateSubscription() throws Exception {
        CreateSubscriptionRequest subscriptionRequest =
                new CreateSubscriptionRequest("NOVA COMPANHIA VIP",
                "descricao",
                LocalDateTime.of(2024, 7, 12, 5, 0),
                2,
                "822ecce2-4b5b-4875-a7ea-e9d00dfdaebb",
                List.of("409733de-beb8-4090-8120-24aa7f92cdd8"));
        mockMvc.perform(post("/v1/subscription/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(subscriptionRequest)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void testGetSubsciption() throws Exception {
        mockMvc.perform(get("/v1/subscription/7a0b6eaa-e2f9-40bd-aeba-efb68994ef26"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testSubscriptionList() throws Exception {
        mockMvc.perform(get("/v1/subscription/list").queryParam(
                        "page", "0",
                        "perPage", "10",
                        "terms", "",
                        "sort", "time",
                        "direction", "ASC"))
                .andExpect(status().isOk()).andReturn();
    }
    @Test
    void testGetSubsciptionEvent() throws Exception {
        mockMvc.perform(get("/v1/subscription/event/822ecce2-4b5b-4875-a7ea-e9d00dfdaebb"))
                .andExpect(status().isOk()).andReturn();
    }
}

