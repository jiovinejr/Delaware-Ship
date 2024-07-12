DROP TABLE IF EXISTS name_clean_up CASCADE;
DROP TABLE IF EXISTS measurement_clean_up CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE name_clean_up (
	item_id serial PRIMARY KEY,
	original_item_name varchar(100),
	clean_item_name varchar(100),
	category varchar(50),
	case_weight numeric(5,2)
);

CREATE TABLE measurement_clean_up (
	item_id serial PRIMARY KEY,
	original_measurement_name varchar(100),
	clean_measurement_name varchar(100)
);

CREATE TABLE orders (
	order_item_id serial PRIMARY KEY,
	ship_name varchar(100),
	quantity numeric(5,2),
	original_measurement_name varchar(100),
    clean_measurement_name varchar(100),
	original_item_name varchar(100),
	clean_item_name varchar(100),
	case_weight numeric(5,2)
);
