DROP TABLE IF EXISTS name_clean_up CASCADE;
DROP TABLE IF EXISTS measurement_clean_up CASCADE;
DROP TABLE IF EXISTS ship CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE name_clean_up (
	item_id serial PRIMARY KEY,
	original_item_name varchar(100),
	clean_item_name varchar(100),
	category varchar(50),
	case_weight numeric(5,2)

	CONSTRAINT pk_name_clean_up PRIMARY KEY (item_id)
);

CREATE TABLE measurement_clean_up (
	measurement_id serial PRIMARY KEY,
	original_measurement_name varchar(100),
	clean_measurement_name varchar(100)

	CONSTRAINT pk_measurement_clean_up PRIMARY KEY (measurement_id)
);

CREATE TABLE ship(
    ship_id serial PRIMARY KEY,
    ship_name varchar(100)

    CONSTRAINT pk_ship PRIMARY KEY (ship_id)
);


CREATE TABLE orders (
	order_id serial PRIMARY KEY,
	order_ship_id int NOT NULL,
	quantity numeric(5,2),
    order_measurement_id int NOT NULL,
    order_item_id int NOT NULL,

    CONSTRAINT pk_orders PRIMARY KEY (order_id)
    CONSTRAINT fk_order_ship FOREIGN KEY (order_ship_id) REFERENCES ship (ship_id)
    CONSTRAINT fk_order_measurement FOREIGN KEY (order_measurement_id) REFERENCES measurement_clean_up (measurement_id)
    CONSTRAINT fk_order_item FOREIGN KEY (order_item_id) REFERENCES name_clean_up (item_id)
);
