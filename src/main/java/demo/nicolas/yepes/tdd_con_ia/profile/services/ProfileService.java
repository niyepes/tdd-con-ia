package demo.nicolas.yepes.tdd_con_ia.profile.services;

import demo.nicolas.yepes.tdd_con_ia.profile.dtos.ProfileRequestDTO;
import demo.nicolas.yepes.tdd_con_ia.profile.dtos.ProfileResponseDTO;
import demo.nicolas.yepes.tdd_con_ia.profile.entities.ProfileEntity;
import demo.nicolas.yepes.tdd_con_ia.profile.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    
    @Transactional
    public ProfileResponseDTO createProfile(ProfileRequestDTO requestDTO) {
        ProfileEntity profile = new ProfileEntity(requestDTO.getName(), requestDTO.getEmail());
        ProfileEntity savedProfile = profileRepository.save(profile);
        return mapToResponseDTO(savedProfile);
    }
    
    @Transactional(readOnly = true)
    public ProfileResponseDTO getProfileById(Long id) {
        ProfileEntity profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        return mapToResponseDTO(profile);
    }
    
    private ProfileResponseDTO mapToResponseDTO(ProfileEntity profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getEmail()
        );
    }
}
