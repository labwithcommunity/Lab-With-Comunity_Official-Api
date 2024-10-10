package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

class InMemoryProjectRepository implements ProjectRepository {

    ConcurrentHashMap<Long, ProjectEntity> database = new ConcurrentHashMap<>();

    @Override
    public Optional<List<ProjectEntity>> findAllByCreatorid(UserQueryDto creatorId) {
        List<ProjectEntity> projects = new ArrayList<>();
        for (ProjectEntity project : database.values()) {
            if (project.getCreatorid().equals(creatorId)) {
                projects.add(project);
            }
        }
        return Optional.of(projects);
    }

    @Override
    public Optional<List<ProjectEntity>> findProjectsByParticipant(UserQueryDto user) {
        List<ProjectEntity> projects = new ArrayList<>();
        for (ProjectEntity project : database.values()) {
            if (project.getParticipants().contains(user)) {
                projects.add(project);
            }
        }
        return Optional.of(projects);
    }

    @Override
    public boolean existsByName(String title) {
        return database.values().stream()
                .anyMatch(project -> project.getName().equals(title));
    }

    @Override
    public boolean existsByParticipantsContainingAndId(UserQueryDto user, Long id) {
        return database.values().stream()
                .filter(project -> project.getId().equals(id))
                .anyMatch(project -> project.getParticipants().contains(user));
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public void delete(ProjectEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProjectEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<ProjectEntity> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public <S extends ProjectEntity> S save(S entity) {
        ProjectEntity newEntity = new ProjectEntity();
        long projectId = idGenerator.getAndIncrement();
        entity.setId(projectId);
        newEntity.setCreatorid(entity.getCreatorid());
        newEntity.setName(entity.getName());
        newEntity.setDescription(entity.getDescription());
        newEntity.setWebsite(entity.getWebsite());
        database.put(projectId, newEntity);
        return (S) entity;
    }

    @Override
    public <S extends ProjectEntity> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<ProjectEntity> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public List<ProjectEntity> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ProjectEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProjectEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ProjectEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProjectEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public ProjectEntity getById(Long aLong) {
        return null;
    }

    @Override
    public ProjectEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ProjectEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProjectEntity> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ProjectEntity> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ProjectEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProjectEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProjectEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProjectEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<ProjectEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ProjectEntity> findAll(Pageable pageable) {
        return null;
    }
}
