package my.user_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import my.user_service.dto.JoinDTO;
import my.user_service.entity.UserEntity;
import my.user_service.repository.UserRepository;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String phone = joinDTO.getPhone();
//        String useraddress = joinDTO.getUseraddress();

//        Boolean isExist = userRepository.findByName(username);
//
//        if (isExist) {
//
//            return;
//        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
//        data.setRole("ROLE_USER");
//        data.setPhone(phone);
//        data.setUseraddress(useraddress);

        userRepository.save(data);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public Member findOne(Long memberId) {
//        return userRepository.findOne(memberId);
//    }
}
