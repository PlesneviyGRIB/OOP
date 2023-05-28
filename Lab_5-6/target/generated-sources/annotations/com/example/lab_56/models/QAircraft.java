package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAircraft is a Querydsl query type for Aircraft
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QAircraft extends com.querydsl.sql.RelationalPathBase<Aircraft> {

    private static final long serialVersionUID = -994979856;

    public static final QAircraft aircraft = new QAircraft("Aircraft");

    public final StringPath code = createString("code");

    public final StringPath model = createString("model");

    public final NumberPath<Integer> range = createNumber("range", Integer.class);

    public QAircraft(String variable) {
        super(Aircraft.class, forVariable(variable), null, "aircrafts");
        addMetadata();
    }

    public QAircraft(String variable, String schema, String table) {
        super(Aircraft.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAircraft(String variable, String schema) {
        super(Aircraft.class, forVariable(variable), schema, "aircrafts");
        addMetadata();
    }

    public QAircraft(Path<? extends Aircraft> path) {
        super(path.getType(), path.getMetadata(), null, "aircrafts");
        addMetadata();
    }

    public QAircraft(PathMetadata metadata) {
        super(Aircraft.class, metadata, null, "aircrafts");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(code, ColumnMetadata.named("aircraft_code").withIndex(0));
        addMetadata(model, ColumnMetadata.named("model").withIndex(1));
        addMetadata(range, ColumnMetadata.named("range").withIndex(2));
    }

}

