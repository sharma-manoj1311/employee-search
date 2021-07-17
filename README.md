# employee-search

Hi Sir ,
Please find the below details-


1- Create Data Base Script -
CREATE DATABASE "employee-search"

2-Sql Script to create Table -
CREATE TABLE public.employee
(
    employee_id integer NOT NULL,
    first_name character varying(500) ,
    last_name character varying(500) ,
    job_tittle character varying(500) ,
    age integer,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
)

3- Insert Script -
INSERT INTO public.employee(
	employee_id, first_name, last_name, job_tittle, age, start_date, end_date)
	VALUES (1, 'Manoj', 'Sharma', 'Sr Softwate Developer', '30', to_date('2021-07-06','yyy-mm-dd'), to_date('2021-08-06','yyy-mm-dd'));
  INSERT INTO public.employee(
	employee_id, first_name, last_name, job_tittle, age, start_date, end_date)
	VALUES (2, 'Manoj', 'Kumar', 'Sr Softwate Developer', '30', to_date('2021-09-06','yyy-mm-dd'), to_date('2021-11-06','yyy-mm-dd'));  
	
4- URL to Run The Project-
http://localhost:8080/employee-search/employee-search

5- In Java DataBase connectivity configuration-
spring.datasource.url=jdbc:postgresql://localhost:5434/employee-search
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true



