package demo.nicolas.yepes.tdd_con_ia.profile.services;

import demo.nicolas.yepes.tdd_con_ia.profile.dtos.ProfileResponseDTO;
import demo.nicolas.yepes.tdd_con_ia.profile.entities.ProfileEntity;
import demo.nicolas.yepes.tdd_con_ia.profile.repositories.ProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void findAllProfiles(){
        //Given
        final List<ProfileEntity> expected = List.of(new ProfileEntity("Sebastián Romero", "sebastianromero@gmail.com"));
        final List<ProfileEntity> saved = List.of(new ProfileEntity("Sebastián Romero", "sebastianromero@gmail.com"));
        //When
        Mockito.when(profileRepository.findAll()).thenReturn(saved);
        List<ProfileResponseDTO> result = profileService.getAll();
        //Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.getFirst().getName()).isEqualTo("Sebastián Romero");
        Assertions.assertThat(result.getFirst().getEmail()).isEqualTo("sebastianromero@gmail.com");


    }

}
