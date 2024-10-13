package com.labwithcommunity.domain.user;

class InMemoryUserRepository implements UserRepository {

//    ConcurrentHashMap<String, UserEntity> dataBase = new ConcurrentHashMap<>();
//
//    @Override
//    public boolean existsByUsername(String nickname) {
//        return dataBase.containsKey(nickname);
//    }
//
//    @Override
//    public Optional<UserEntity> findByUsername(String nickname) {
//        return Optional.ofNullable(dataBase.get(nickname));
//    }
//
//     @Override
//     public Optional<GetLoggedUserDto> findUsernameAndPasswordByNickname(String nickname) {
//         UserEntity userEntity = dataBase.get(nickname);
//         return Optional.of(new GetLoggedUserDto(
//                 userEntity.getUsername(),
//                 userEntity.getPassword()
//         ));
//     }
//
//     @Override
//     public boolean existsByEmail(String email) {
//         return false;
//     }
//
//     @Override
//     public boolean existsByNickname(String username) {
//         return false;
//     }
//
//     @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends UserEntity> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return List.of();
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<UserEntity> entities) {
//
//    }
//
//     @Override
//     public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//     }
//
//     @Override
//    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//     @Override
//     public UserEntity getOne(Long aLong) {
//         return null;
//     }
//
//     @Override
//     public UserEntity getById(Long aLong) {
//         return null;
//     }
//
//     @Override
//     public UserEntity getReferenceById(Long aLong) {
//         return null;
//     }
//
//     @Override
//    public UserEntity getOne(UUID uuid) {
//        return null;
//    }
//
//    @Override
//    public UserEntity getById(UUID uuid) {
//        return null;
//    }
//
//    @Override
//    public UserEntity getReferenceById(UUID uuid) {
//        return null;
//    }
//
//    @Override
//    public <S extends UserEntity> List<S> findAll(Example<S> example) {
//        return List.of();
//    }
//
//    @Override
//    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
//        return List.of();
//    }
//
//    @Override
//    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
//        return List.of();
//    }
//
//     @Override
//     public Optional<UserEntity> findById(Long aLong) {
//         return Optional.empty();
//     }
//
//     @Override
//     public boolean existsById(Long aLong) {
//         return false;
//     }
//
//     @Override
//    public List<UserEntity> findAll() {
//        return List.of();
//    }
//
//     @Override
//     public List<UserEntity> findAllById(Iterable<Long> longs) {
//         return List.of();
//     }
//
//     @Override
//    public List<UserEntity> findAllById(Iterable<UUID> uuids) {
//        return List.of();
//    }
//
//    @Override
//    public <S extends UserEntity> S save(S entity) {
////        UUID uuid = UUID.randomUUID();
//        UserEntity userEntity = new UserEntity();
////        userEntity.setId(uuid);
//        userEntity.setNickname(entity.getNickname());
//        userEntity.setUsername(entity.getNickname());
//        userEntity.setEmail(entity.getEmail());
//        userEntity.setPassword(entity.getPassword());
////        userEntity.setRole("USER");
////        userEntity.setTechnologies(entity.getTechnologies());
//        dataBase.put(entity.getNickname(), userEntity);
//        return (S) userEntity;
//    }
//
//    @Override
//    public Optional<UserEntity> findById(UUID uuid) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(UUID uuid) {
//        return false;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//     @Override
//     public void deleteById(Long aLong) {
//
//     }
//
//     @Override
//    public void deleteById(UUID uuid) {
//
//    }
//
//    @Override
//    public void delete(UserEntity entity) {
//
//    }
//
//     @Override
//     public void deleteAllById(Iterable<? extends Long> longs) {
//
//     }
//
//     @Override
//    public void deleteAllById(Iterable<? extends UUID> uuids) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends UserEntity> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<UserEntity> findAll(Sort sort) {
//        return List.of();
//    }
//
//    @Override
//    public Page<UserEntity> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends UserEntity> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends UserEntity> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
}
