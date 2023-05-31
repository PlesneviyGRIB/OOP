package com.example.lab_56.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QPriceInfo is a Querydsl query type for PriceInfo
 */
@Generated("com.infobip.spring.data.jdbc.annotation.processor.CustomMetaDataSerializer")
public class QPriceInfo extends com.querydsl.sql.RelationalPathBase<PriceInfo> {

    private static final long serialVersionUID = 1268461565;

    public static final QPriceInfo priceInfo = new QPriceInfo("PriceInfo");

    public final StringPath origin = createString("origin");

    public final StringPath destination = createString("destination");

    public final StringPath aircraftCode = createString("aircraftCode");

    public final StringPath seatNo = createString("seatNo");

    public final StringPath fareCondition = createString("fareCondition");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public QPriceInfo(String variable) {
        super(PriceInfo.class, forVariable(variable), null, "price_info");
        addMetadata();
    }

    public QPriceInfo(String variable, String schema, String table) {
        super(PriceInfo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPriceInfo(String variable, String schema) {
        super(PriceInfo.class, forVariable(variable), schema, "price_info");
        addMetadata();
    }

    public QPriceInfo(Path<? extends PriceInfo> path) {
        super(path.getType(), path.getMetadata(), null, "price_info");
        addMetadata();
    }

    public QPriceInfo(PathMetadata metadata) {
        super(PriceInfo.class, metadata, null, "price_info");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(origin, ColumnMetadata.named("origin").withIndex(0));
        addMetadata(destination, ColumnMetadata.named("destination").withIndex(1));
        addMetadata(aircraftCode, ColumnMetadata.named("aircraft_code").withIndex(2));
        addMetadata(seatNo, ColumnMetadata.named("seat_no").withIndex(3));
        addMetadata(fareCondition, ColumnMetadata.named("fare_conditions").withIndex(4));
        addMetadata(amount, ColumnMetadata.named("amount").withIndex(5));
    }

}

