# Endpoints Documentation

**/api** - general prefix

**/cities/sources** `GET params["_page={number}", "_limit={number}"]` - sources cities

    Return type: Page<String>

    HTTP statuses: 200

**/cities/destinations** `GET params["_page={number}", "_limit={number}"]` - destinations cities

    Return type: Page<String>

    HTTP statuses: 200

**/cities** `GET params["_page={number}", "_limit={number}"]` - all cities

    Return type: Page<String>

    HTTP statuses: 200

**/cities/sources/airports** `GET params["_page={number}", "_limit={number}"]` - sources cities airports

    Return type: Page<AirportDTO>

    HTTP statuses: 200

**/cities/destinations/airports** `GET params["_page={number}", "_limit={number}"]` - destinations cities airports

    Return type: Page<AirportDTO>

    HTTP statuses: 200

**/cities/{city_id}/airports** `GET params["_page={number}", "_limit={number}"]` - all airports by city

    Return type: Page<AirportDTO>

    HTTP statuses: 200, 400

**/airports** `GET params["_page={number}", "_limit={number}"]` - all airports

    Return type: Page<AirportDTO>

    HTTP statuses: 200

**/airports/{airport_id}/inbound** `GET` - inbound schedule for airport

    Return type: ScheduleDTO

    HTTP statuses: 200, 204, 400 

**/airports/{airport_id}/outbound** `GET` - inbound schedule for airport

    Return type: ScheduleDTO

    HTTP statuses: 200, 204, 400

**/routes/filter** `POST` - routes by filter (filter - object that encapsulates all search criteria)

    Return type: Page<RouteDTO>

    HTTP statuses: 200

**/routes/booking/{passenger_id}** `POST {RouteDTO}` - create booking for route (route - object that represents route)

    Return type: none

    HTTP statuses: 200, 400

### DTO Description 

    AirportDTO {
        code : String
        name : String
        city : String
        timezone : Timezone
    }

    ScheduleDTO {
        days : number[]
        departure : Timestamp
        flight_no : string
        origin : AirportDTO
        destination : AirportDTO
    }

    TicketInfoDTO{
        seat : string
        board_id : string
    }

    TicketDTO{
        id : string
        departure : Timestamp
        price : number
        arrival: Timestamp
        info : TicketInfoDTO
        origin : AirportDTO
        destination : AirportDTO
    }

    RouteDTO {
        id : number
        List<TicketDTO>
    }
