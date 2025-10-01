package demo.nicolas.yepes.tdd_con_ia.profile.controllers;

import demo.nicolas.yepes.tdd_con_ia.profile.entities.ProfileEntity;
import demo.nicolas.yepes.tdd_con_ia.profile.repositories.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureWebMvc
public class ProfileControllerTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProfileRepository profileRepository;

    private MockMvc mockMvc;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        profileRepository.deleteAll();
    }

    @Test
    void shouldCreateProfile() throws Exception {
        final String profileJson = """
                {
                    "name": "Nicolás Yepes",
                    "email": "nicolasyepes2004@gmail.com"
                }
                """;
        
        // When & Then
        mockMvc.perform(post("/api/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Nicolás Yepes")))
                .andExpect(jsonPath("$.email", is("nicolasyepes2004@gmail.com")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void shouldGetProfile() throws Exception {
        // Crear perfil de prueba para el test shouldGetProfile
        ProfileEntity testProfile = new ProfileEntity("Sebastián Romero", "sebastianromero@gmail.com");
        profileRepository.save(testProfile);

        mockMvc.perform(get("/api/profiles/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sebastián Romero")))
                .andExpect(jsonPath("$.email", is("sebastianromero@gmail.com")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }
    
    
}

