CREATE TABLE employee ( 

user_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 

name varchar(255) NOT NULL, 

email varchar(255) UNIQUE NOT NULL, 

password varchar(255) NOT NULL, 

created_at timestamp DEFAULT CURRENT_TIMESTAMP 

); 

CREATE TABLE personal_info ( 

user_id bigint PRIMARY KEY REFERENCES employee(user_id) ON DELETE CASCADE, 

full_name varchar(255) NOT NULL, 

phone_number varchar(20) NOT NULL, 

birth_date date NOT NULL, 

emergency_contact_name varchar(255) NOT NULL, 

emergency_contact_number varchar(20) NOT NULL, 

address text NOT NULL, 

updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP 

); 

CREATE TABLE onboarding_progress ( 

user_id bigint PRIMARY KEY REFERENCES employee(user_id) ON DELETE CASCADE, 

personal_information_completed boolean NOT NULL DEFAULT FALSE, 

read_company_introduction boolean NOT NULL DEFAULT FALSE, 

acknowledged_company_policy boolean NOT NULL DEFAULT FALSE, 

checklist_joining_date boolean NOT NULL DEFAULT FALSE, 

checklist_first_day boolean NOT NULL DEFAULT FALSE, 

checklist_orientation boolean NOT NULL DEFAULT FALSE, 

joining_checklist_completed boolean NOT NULL DEFAULT FALSE, 

final_confirmation boolean NOT NULL DEFAULT FALSE,  

updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 

completed_at timestamp 

);