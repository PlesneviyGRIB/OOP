package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QTicket extends com.querydsl.sql.RelationalPathBase<Ticket> {

    private static final long serialVersionUID = -7241114;

    public static final QTicket ticket = new QTicket("Ticket");

    public final StringPath ticketNo = createString("ticketNo");

    public final StringPath bookRef = createString("bookRef");

    public final StringPath passengerId = createString("passengerId");

    public final StringPath passengerName = createString("passengerName");

    public final StringPath contactData = createString("contactData");

    public QTicket(String variable) {
        super(Ticket.class, forVariable(variable), null, "tickets");
        addMetadata();
    }

    public QTicket(String variable, String schema, String table) {
        super(Ticket.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTicket(String variable, String schema) {
        super(Ticket.class, forVariable(variable), schema, "tickets");
        addMetadata();
    }

    public QTicket(Path<? extends Ticket> path) {
        super(path.getType(), path.getMetadata(), null, "tickets");
        addMetadata();
    }

    public QTicket(PathMetadata metadata) {
        super(Ticket.class, metadata, null, "tickets");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(ticketNo, ColumnMetadata.named("ticket_no").withIndex(0));
        addMetadata(bookRef, ColumnMetadata.named("book_ref").withIndex(1));
        addMetadata(passengerId, ColumnMetadata.named("passenger_id").withIndex(2));
        addMetadata(passengerName, ColumnMetadata.named("passenger_name").withIndex(3));
        addMetadata(contactData, ColumnMetadata.named("contact_data").withIndex(4));
    }

}

