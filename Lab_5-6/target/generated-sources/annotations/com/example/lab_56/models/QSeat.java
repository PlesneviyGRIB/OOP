package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSeat is a Querydsl query type for Seat
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QSeat extends com.querydsl.sql.RelationalPathBase<Seat> {

    private static final long serialVersionUID = 1041298399;

    public static final QSeat seat = new QSeat("Seat");

    public final StringPath aircraftCode = createString("aircraftCode");

    public final StringPath seatNo = createString("seatNo");

    public final StringPath fareCondition = createString("fareCondition");

    public QSeat(String variable) {
        super(Seat.class, forVariable(variable), null, "seats");
        addMetadata();
    }

    public QSeat(String variable, String schema, String table) {
        super(Seat.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSeat(String variable, String schema) {
        super(Seat.class, forVariable(variable), schema, "seats");
        addMetadata();
    }

    public QSeat(Path<? extends Seat> path) {
        super(path.getType(), path.getMetadata(), null, "seats");
        addMetadata();
    }

    public QSeat(PathMetadata metadata) {
        super(Seat.class, metadata, null, "seats");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(aircraftCode, ColumnMetadata.named("aircraft_code").withIndex(0));
        addMetadata(seatNo, ColumnMetadata.named("seat_no").withIndex(1));
        addMetadata(fareCondition, ColumnMetadata.named("fare_conditions").withIndex(2));
    }

}

