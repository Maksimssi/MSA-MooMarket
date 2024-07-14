package my.user_service.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import my.user_service.entity.UserEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public void save(UserEntity users) {
        em.persist(users);
    }

    public UserEntity findOne(Integer id){
        return em.find(UserEntity.class, id);
    }

    public UserEntity findByUsername(String username) {
        return em.createQuery("select u from UserEntity u where u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult(); // 결과가 하나만 예상되는 경우 사용
    }

    public List<UserEntity> findAll(){

        return em.createQuery("select m from Member m", UserEntity.class)
                .getResultList();
    }

//    public List<UserEntity> findByName(String name) {
//        return em.createQuery("select m from Member m where m.name = :name", UserEntity.class)
//                .setParameter("name", name)
//                .getResultList();
//    }

//    Boolean existsByUsername(String username);
//
//    UserEntity findByUsername(String username);
}
