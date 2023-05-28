package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTicketFlights is a Querydsl query type for TicketFlights
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QTicketFlights extends com.querydsl.sql.RelationalPathBase<TicketFlights> {

    private static final long serialVersionUID = 2097660093;

    public static final QTicketFlights ticketFlights = new QTicketFlights("TicketFlights");

    public final StringPath ticketNo = createString("ticketNo");

    public final NumberPath<Long> flightId = createNumber("flightId", Long.class);

    public final StringPath fareCondition = createString("fareCondition");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public QTicketFlights(String variable) {
        super(TicketFlights.class, forVariable(variable), null, "ticket_flights");
        addMetadata();
    }

    public QTicketFlights(String variable, String schema, String table) {
        super(TicketFlights.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTicketFlights(String variable, String schema) {
        super(TicketFlights.class, forVariable(variable), schema, "ticket_flights");
        addMetadata();
    }

    public QTicketFlights(Path<? extends TicketFlights> path) {
        super(path.getType(), path.getMetadata(), null, "ticket_flights");
        addMetadata();
    }

    public QTicketFlights(PathMetadata metadata) {
        super(TicketFlights.class, metadata, null, "ticket_flights");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(ticketNo, ColumnMetadata.named("ticket_no").withIndex(0));
        addMetadata(flightId, ColumnMetadata.named("flight_id").withIndex(1));
        addMetadata(fareCondition, ColumnMetadata.named("fare_conditions").withIndex(2));
        addMetadata(amount, ColumnMetadata.named("amount").withIndex(3));
    }

}

