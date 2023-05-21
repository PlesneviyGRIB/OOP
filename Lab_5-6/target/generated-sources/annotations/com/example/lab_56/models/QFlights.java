package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QFlights is a Querydsl query type for Flights
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QFlights extends com.querydsl.sql.RelationalPathBase<Flights> {

    private static final long serialVersionUID = 326688233;

    public static final QFlights flights = new QFlights("Flights");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath flightNo = createString("flightNo");

    public final DateTimePath<java.sql.Timestamp> scheduledDeparture = createDateTime("scheduledDeparture", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> scheduledArrival = createDateTime("scheduledArrival", java.sql.Timestamp.class);

    public final StringPath departureAirport = createString("departureAirport");

    public final StringPath arrivalAirport = createString("arrivalAirport");

    public final StringPath status = createString("status");

    public final StringPath aircraftCode = createString("aircraftCode");

    public final StringPath actualDeparture = createString("actualDeparture");

    public final StringPath actualArrival = createString("actualArrival");

    public QFlights(String variable) {
        super(Flights.class, forVariable(variable), null, "flights");
        addMetadata();
    }

    public QFlights(String variable, String schema, String table) {
        super(Flights.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QFlights(String variable, String schema) {
        super(Flights.class, forVariable(variable), schema, "flights");
        addMetadata();
    }

    public QFlights(Path<? extends Flights> path) {
        super(path.getType(), path.getMetadata(), null, "flights");
        addMetadata();
    }

    public QFlights(PathMetadata metadata) {
        super(Flights.class, metadata, null, "flights");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("flight_id").withIndex(0));
        addMetadata(flightNo, ColumnMetadata.named("flight_no").withIndex(1));
        addMetadata(scheduledDeparture, ColumnMetadata.named("scheduled_departure").withIndex(2));
        addMetadata(scheduledArrival, ColumnMetadata.named("scheduled_arrival").withIndex(3));
        addMetadata(departureAirport, ColumnMetadata.named("departure_airport").withIndex(4));
        addMetadata(arrivalAirport, ColumnMetadata.named("arrival_airport").withIndex(5));
        addMetadata(status, ColumnMetadata.named("status").withIndex(6));
        addMetadata(aircraftCode, ColumnMetadata.named("aircraft_code").withIndex(7));
        addMetadata(actualDeparture, ColumnMetadata.named("actual_departure").withIndex(8));
        addMetadata(actualArrival, ColumnMetadata.named("actual_arrival").withIndex(9));
    }

}

