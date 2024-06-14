package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.ChatEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Repository
public class ChatRepository implements JpaRepository<ChatEntity, Long> {

    @Override
    public List<ChatEntity> findAll() {
        return List.of();
    }

    @Override
    public List<ChatEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ChatEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ChatEntity> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ChatEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChatEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ChatEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ChatEntity> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ChatEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ChatEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ChatEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ChatEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public ChatEntity getById(Long aLong) {
        return null;
    }

    @Override
    public ChatEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ChatEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ChatEntity> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ChatEntity> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ChatEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ChatEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ChatEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ChatEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
