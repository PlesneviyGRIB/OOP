select tf.ticket_no, tf.fare_conditions, tf.amount, bp.boarding_no, bp.seat_no
from bookings.ticket_flights as tf
join bookings.boarding_passes as bp on bp.ticket_no = tf.ticket_no and bp.flight_id = tf.flight_id
where tf.flight_id = 2 and bp.flight_id = 2
order by amount DESC, seat_no;
-- информация о цене на каждое места для конкретного перелета. (Для разных перелетов прослеживается закономерность, что эконом. класс содержит ценовое разделение)

select flight_id, avg(amount)
into tmp_flight_avg_for_economy
from ticket_flights as tf
where tf.fare_conditions = 'Economy'
group by flight_id;
-- средняя цена на билеты эконом. класса для каждого перелета

select tf.flight_id, tf.amount, bp.seat_no, a.aircraft_code
into tmp_flight_amount_seat_aircraft
from ticket_flights as tf
join boarding_passes bp on tf.ticket_no = bp.ticket_no and tf.flight_id = bp.flight_id
join flights f on f.flight_id = tf.flight_id
join aircrafts a on a.aircraft_code = f.aircraft_code
where tf.fare_conditions = 'Economy';
-- цены для кождого места для всех перелетов

select distinct tfasa.aircraft_code, tfasa.seat_no
into overpayment_fare_conditions_above_economy
from tmp_flight_amount_seat_aircraft as tfasa
join bookings.tmp_flight_avg_for_economy as tfafe on tfasa.flight_id = tfafe.flight_id
where tfasa.amount > tfafe.avg;
-- таблица, содержащая конкретные места для для каждого из самолетов, стоимость билетов на которые превышает стоимость для эконом. класса (дополнительный уровень комфорта: "Эконом_+")

select distinct f.flight_id, f.aircraft_code
from flights as f
order by f.aircraft_code, flight_id
offset 100;
-- для проверки

select tf.ticket_no, tf.fare_conditions, tf.amount, bp.boarding_no, bp.seat_no
from bookings.ticket_flights as tf
join bookings.boarding_passes as bp on bp.ticket_no = tf.ticket_no and bp.flight_id = tf.flight_id
join flights f on f.flight_id = tf.flight_id
where f.aircraft_code = '319' and f.flight_id = 8292
order by amount DESC, seat_no;
-- для проверки

select *
into seats1
from seats;
-- чтобы не затереть таблицу 'seats'

UPDATE seats1 as s1
set fare_conditions = 'Economy_+'
from overpayment_fare_conditions_above_economy as ofcae
where ofcae.seat_no = s1.seat_no and  ofcae.aircraft_code = s1.aircraft_code;
-- обновление таблицы seats1: внедрение нового класса 'Economy_+'

select count(*)
from seats
where fare_conditions = 'Economy';
-- для проверки

select distinct a2.city, a2.airport_code, a.aircraft_code, s1.seat_no, s1.fare_conditions, tf.amount
into price_info
from flights as f
join ticket_flights tf on f.flight_id = tf.flight_id
join tickets t on t.ticket_no = tf.ticket_no
join boarding_passes bp on f.flight_id = bp.flight_id and tf.ticket_no = bp.ticket_no
join aircrafts a on f.aircraft_code = a.aircraft_code
join seats1 s1 on a.aircraft_code = s1.aircraft_code and bp.seat_no = s1.seat_no
join airports a2 on f.arrival_airport = a2.airport_code
order by tf.amount;
-- построение основной таблицы, из которой можно достать информацию о цене перелета по различным параметрам
