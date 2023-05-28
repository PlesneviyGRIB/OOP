package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBooking is a Querydsl query type for Booking
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QBooking extends com.querydsl.sql.RelationalPathBase<Booking> {

    private static final long serialVersionUID = 1163189311;

    public static final QBooking booking = new QBooking("Booking");

    public final StringPath bookRef = createString("bookRef");

    public final DatePath<java.sql.Date> bookDate = createDate("bookDate", java.sql.Date.class);

    public final NumberPath<Long> totalAmount = createNumber("totalAmount", Long.class);

    public QBooking(String variable) {
        super(Booking.class, forVariable(variable), null, "bookings");
        addMetadata();
    }

    public QBooking(String variable, String schema, String table) {
        super(Booking.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBooking(String variable, String schema) {
        super(Booking.class, forVariable(variable), schema, "bookings");
        addMetadata();
    }

    public QBooking(Path<? extends Booking> path) {
        super(path.getType(), path.getMetadata(), null, "bookings");
        addMetadata();
    }

    public QBooking(PathMetadata metadata) {
        super(Booking.class, metadata, null, "bookings");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bookRef, ColumnMetadata.named("book_ref").withIndex(0));
        addMetadata(bookDate, ColumnMetadata.named("book_date").withIndex(1));
        addMetadata(totalAmount, ColumnMetadata.named("total_amount").withIndex(2));
    }

}

