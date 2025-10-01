package demo.nicolas.yepes.tdd_con_ia.profile.controllers;

import demo.nicolas.yepes.tdd_con_ia.profile.dtos.ProfileRequestDTO;
import demo.nicolas.yepes.tdd_con_ia.profile.dtos.ProfileResponseDTO;
import demo.nicolas.yepes.tdd_con_ia.profile.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO requestDTO) {
        ProfileResponseDTO responseDTO = profileService.createProfile(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long id) {
        ProfileResponseDTO responseDTO = profileService.getProfileById(id);
        return ResponseEntity.ok(responseDTO);
    }
}
