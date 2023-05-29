package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBoardingPass is a Querydsl query type for BoardingPass
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QBoardingPass extends com.querydsl.sql.RelationalPathBase<BoardingPass> {

    private static final long serialVersionUID = 2022636871;

    public static final QBoardingPass boardingPass = new QBoardingPass("BoardingPass");

    public final StringPath ticketNo = createString("ticketNo");

    public final NumberPath<Long> flightId = createNumber("flightId", Long.class);

    public final NumberPath<Integer> boardingNo = createNumber("boardingNo", Integer.class);

    public final StringPath seatNo = createString("seatNo");

    public QBoardingPass(String variable) {
        super(BoardingPass.class, forVariable(variable), null, "boarding_passes");
        addMetadata();
    }

    public QBoardingPass(String variable, String schema, String table) {
        super(BoardingPass.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBoardingPass(String variable, String schema) {
        super(BoardingPass.class, forVariable(variable), schema, "boarding_passes");
        addMetadata();
    }

    public QBoardingPass(Path<? extends BoardingPass> path) {
        super(path.getType(), path.getMetadata(), null, "boarding_passes");
        addMetadata();
    }

    public QBoardingPass(PathMetadata metadata) {
        super(BoardingPass.class, metadata, null, "boarding_passes");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(ticketNo, ColumnMetadata.named("ticket_no").withIndex(0));
        addMetadata(flightId, ColumnMetadata.named("flight_id").withIndex(1));
        addMetadata(boardingNo, ColumnMetadata.named("boarding_no").withIndex(2));
        addMetadata(seatNo, ColumnMetadata.named("seat_no").withIndex(3));
    }

}

