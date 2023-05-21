package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAirport is a Querydsl query type for Airport
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QAirport extends com.querydsl.sql.RelationalPathBase<Airport> {

    private static final long serialVersionUID = 106836145;

    public static final QAirport airport = new QAirport("Airport");

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public final StringPath city = createString("city");

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath timezone = createString("timezone");

    public QAirport(String variable) {
        super(Airport.class, forVariable(variable), null, "airports");
        addMetadata();
    }

    public QAirport(String variable, String schema, String table) {
        super(Airport.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAirport(String variable, String schema) {
        super(Airport.class, forVariable(variable), schema, "airports");
        addMetadata();
    }

    public QAirport(Path<? extends Airport> path) {
        super(path.getType(), path.getMetadata(), null, "airports");
        addMetadata();
    }

    public QAirport(PathMetadata metadata) {
        super(Airport.class, metadata, null, "airports");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(code, ColumnMetadata.named("airport_code").withIndex(0));
        addMetadata(name, ColumnMetadata.named("airport_name").withIndex(1));
        addMetadata(city, ColumnMetadata.named("city").withIndex(2));
        addMetadata(longitude, ColumnMetadata.named("longitude").withIndex(3));
        addMetadata(latitude, ColumnMetadata.named("latitude").withIndex(4));
        addMetadata(timezone, ColumnMetadata.named("timezone").withIndex(5));
    }

}

