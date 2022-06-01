package com.example.mongodbtask.servicies.impl;

import com.example.mongodbtask.entities.DatabaseSequence;
import com.example.mongodbtask.servicies.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    private static final long START_WITH_CONSTANT = 1L;
    private static final long INCREMENT_BY_CONSTANT = 1L;

    private final MongoOperations mongoOperations;

    @Override
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", INCREMENT_BY_CONSTANT),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return Objects.nonNull(counter) ? counter.getSeq() : START_WITH_CONSTANT;
    }
}
