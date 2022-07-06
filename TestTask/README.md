### DataBase:
There is a PostgreSql database dump in the folder. All connection settings can be viewed in resources/HibernateConfiguration.properties
To restore a database from a dump, use the command:
	
	psql database_name < postgredb.dump     (user admin, password 1234)


### Available endpoints:
	http://localhost:8080/bid                      - view banners page
	http://localhost:8080/bid/logs                 - view banners page

	http://localhost:8080/bid/banners              - view all banners
	http://localhost:8080/bid/banners/new          - create banner
	http://localhost:8080/bid/banners/{id}         - view banner
	http://localhost:8080/bid/banners/{id}/edit    - edit banner

	http://localhost:8080/bid/categories           - view all categories
	http://localhost:8080/bid/categories/new       - create category
	http://localhost:8080/bid/categories/{id}      - view category
	http://localhost:8080/bid/categories/{id}/edit - edit category


### Security:
Only http://localhost:8080/bid available without authentication.
For other endpoints u need to be authenticated (login admin, password admin).


### Usage:
Send a request to an open address with parameters, example (available request parameters are indicated on the left)
	
	http://localhost:8080/bid?cat=humor&cat=city


### Additional information:
Information about the user viewing a particular banner is taken from the logs.
For the convenience of checking, logs can be deleted.
Server rises on Apache TomCat 127.0.0.1