//package com.example.msuser.service.fake;
//
//import com.example.msuser.dao.entity.ConsumerEntity;
//import com.example.msuser.dao.entity.RoleEntity;
//import com.example.msuser.service.ConsumerService;
//import com.example.msuser.service.RoleService;
//import com.example.msuser.service.UserRoleService;
//import jakarta.annotation.PostConstruct;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class AutoSaveConsumerData {
//    ConsumerService consumerService;
//    UserRoleService userRoleService;
//    RoleService roleService;
//
////    @PostConstruct
//    public void saveConsumers(){
//        ConsumerEntity consumer1 = ConsumerEntity.builder()
//                .name("Akif")
//                .build();
//        ConsumerEntity consumer2 = ConsumerEntity.builder()
//                .name("Murad")
//                .build();
//        ConsumerEntity consumer3 = ConsumerEntity.builder()
//                .name("Qasim")
//                .build();
//        consumerService.saveConsumer(consumer1);
//        consumerService.saveConsumer(consumer2);
//        consumerService.saveConsumer(consumer3);
//    }
//
////    @PostConstruct
//    private void saveRoles() {
//        RoleEntity userRole = RoleEntity.builder()
//                .name("USER")
//                .build();
//
//        RoleEntity adminRole = RoleEntity.builder()
//                .name("ADMIN")
//                .build();
//
//        RoleEntity managerRole = RoleEntity.builder()
//                .name("MANAGER")
//                .build();
//
//        roleService.saveRoleList(Set.of(userRole, adminRole, managerRole));
//    }
////    @PostConstruct
//    public void saveUsersRoles() {
//        userRoleService.saveUserWithRole("murad@gmail.com", "USER");
//        userRoleService.saveUserWithRole("cavid@gmail.com", "USER");
//    }
//}
