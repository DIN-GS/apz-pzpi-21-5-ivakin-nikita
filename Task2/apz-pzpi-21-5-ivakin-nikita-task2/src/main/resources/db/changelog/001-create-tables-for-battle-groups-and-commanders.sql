CREATE TABLE project.brigade_commander (
                                           brigade_commander_id SERIAL PRIMARY KEY,
                                           first_name VARCHAR(255) NOT NULL ,
                                           last_name VARCHAR(255) NOT NULL ,
                                           second_name VARCHAR(255) NOT NULL ,
                                           rank VARCHAR(255) NOT NULL ,
                                           post VARCHAR(255) NOT NULL ,
                                           role VARCHAR(255) NOT NULL ,
                                           email VARCHAR(255) NOT NULL ,
                                           password VARCHAR(255) NOT NULL ,
                                           age INTEGER NOT NULL ,
                                           passport_number VARCHAR(255) NOT NULL ,
                                           brigade_group_id INTEGER UNIQUE

);

CREATE TABLE project.brigade_group (
                                       brigade_group_id SERIAL PRIMARY KEY,
                                       personnel_count INTEGER ,
                                       ammo_556x45ar_count INTEGER,
                                       ammo_545x39ak_rpk_count INTEGER,
                                       ammo_762x39ak_count INTEGER,
                                       ammo_145kpvt_count INTEGER,
                                       ammo_40mm_rpg_count INTEGER,
                                       ammo_40mm_gp_count INTEGER,
                                       ammo_762pkt INTEGER,
                                       defensive_grenades_count INTEGER,
                                       offensive_grenades_count INTEGER,
                                       body_armor_count INTEGER,
                                       helmets_count INTEGER,
                                       rifles_count INTEGER,
                                       machine_guns_count INTEGER,
                                       dry_rations_count INTEGER,
                                       food_count INTEGER,
                                       tank_count INTEGER,
                                       apc_count INTEGER,
                                       brigade_commander_id INTEGER UNIQUE NOT NULL
);

/*CREATE TABLE project.regiment_commander (
                                            regiment_commander_id SERIAL PRIMARY KEY,
                                            first_name VARCHAR(255) NOT NULL ,
                                            last_name VARCHAR(255) NOT NULL ,
                                            second_name VARCHAR(255) NOT NULL ,
                                            rank VARCHAR(255) NOT NULL ,
                                            post VARCHAR(255) NOT NULL ,
                                            role VARCHAR(255) NOT NULL ,
                                            email VARCHAR(255) NOT NULL ,
                                            password VARCHAR(255) NOT NULL ,
                                            age INTEGER NOT NULL ,
                                            passport_number INTEGER NOT NULL ,
                                            regiment_group_id INTEGER UNIQUE NOT NULL ,
                                            brigade_commander_id INTEGER UNIQUE NOT NULL ,
                                            brigade_group_id INTEGER NOT NULL
);

CREATE TABLE project.regiment_group (
                                        regiment_group_id SERIAL PRIMARY KEY,
                                        personnel_count INTEGER,
                                        ammo_556x45_ar_count INTEGER,
                                        ammo_545x39_ak_rpk_count INTEGER,
                                        ammo_762x39_ak_count INTEGER,
                                        ammo_145_kpvt_count INTEGER,
                                        ammo_40mm_rpg INTEGER,
                                        ammo_40mm_gp INTEGER,
                                        ammo_762_pkt INTEGER,
                                        defensive_grenades_count INTEGER,
                                        offensive_grenades_count INTEGER,
                                        body_armor_count INTEGER,
                                        helmets_count INTEGER,
                                        rifles_count INTEGER,
                                        machine_guns_count INTEGER,
                                        dry_rations_count INTEGER,
                                        food_count INTEGER,
                                        tank_count INTEGER,
                                        apc_count INTEGER,
                                        regiment_commander_id INTEGER UNIQUE  NOT NULL,
                                        brigade_group_id INTEGER  NOT NULL
);*/

CREATE TABLE project.battalion_commander (
                                             battalion_commander_id SERIAL PRIMARY KEY,
                                             first_name VARCHAR(255)NOT NULL,
                                             last_name VARCHAR(255) NOT NULL,
                                             second_name VARCHAR(255) NOT NULL,
                                             rank VARCHAR(255) NOT NULL,
                                             post VARCHAR(255) NOT NULL,
                                             role VARCHAR(255) NOT NULL ,
                                             email VARCHAR(255) NOT NULL ,
                                             password VARCHAR(255) NOT NULL ,
                                             age INTEGER NOT NULL,
                                             passport_number VARCHAR(255) NOT NULL,
                                             battalion_group_id INTEGER UNIQUE,
                                             brigade_commander_id INTEGER UNIQUE,
                                             brigade_group_id INTEGER
);

CREATE TABLE project.battalion_group (
                                         battalion_group_id SERIAL PRIMARY KEY,
                                         personnel_count INTEGER,
                                         ammo_556x45ar_count INTEGER,
                                         ammo_545x39ak_rpk_count INTEGER,
                                         ammo_762x39ak_count INTEGER,
                                         ammo_145kpvt_count INTEGER,
                                         ammo_40mm_rpg_count INTEGER,
                                         ammo_40mm_gp_count INTEGER,
                                         ammo_762pkt INTEGER,
                                         defensive_grenades_count INTEGER,
                                         offensive_grenades_count INTEGER,
                                         body_armor_count INTEGER,
                                         helmets_count INTEGER,
                                         rifles_count INTEGER,
                                         machine_guns_count INTEGER,
                                         dry_rations_count INTEGER,
                                         food_count INTEGER,
                                         tank_count INTEGER,
                                         apc_count INTEGER,
                                         battalion_commander_id INTEGER UNIQUE,
                                         brigade_group_id INTEGER  NOT NULL

);

CREATE TABLE project.company_commander (
                                           company_commander_id SERIAL PRIMARY KEY,
                                           first_name VARCHAR(255)NOT NULL,
                                           last_name VARCHAR(255) NOT NULL,
                                           second_name VARCHAR(255) NOT NULL,
                                           rank VARCHAR(255) NOT NULL,
                                           post VARCHAR(255) NOT NULL,
                                           role VARCHAR(255) NOT NULL ,
                                           email VARCHAR(255) NOT NULL ,
                                           password VARCHAR(255) NOT NULL ,
                                           age INTEGER NOT NULL,
                                           passport_number VARCHAR(255) NOT NULL,
                                           company_group_id INTEGER UNIQUE,
                                           battalion_commander_id INTEGER UNIQUE ,
                                           battalion_group_id INTEGER
);

CREATE TABLE project.company_group (
                                       company_group_id SERIAL PRIMARY KEY,
                                       personnel_count INTEGER,
                                       ammo_556x45ar_count INTEGER,
                                       ammo_545x39ak_rpk_count INTEGER,
                                       ammo_762x39ak_count INTEGER,
                                       ammo_145kpvt_count INTEGER,
                                       ammo_40mm_rpg_count INTEGER,
                                       ammo_40mm_gp_count INTEGER,
                                       ammo_762pkt INTEGER,
                                       defensive_grenades_count INTEGER,
                                       offensive_grenades_count INTEGER,
                                       body_armor_count INTEGER,
                                       helmets_count INTEGER,
                                       rifles_count INTEGER,
                                       machine_guns_count INTEGER,
                                       dry_rations_count INTEGER,
                                       food_count INTEGER,
                                       tank_count INTEGER,
                                       apc_count INTEGER,
                                       company_commander_id INTEGER UNIQUE,
                                       battalion_group_id INTEGER NOT NULL
);

CREATE TABLE project.plat_commander (
                                        plat_commander_id SERIAL PRIMARY KEY,
                                        first_name VARCHAR(255)NOT NULL,
                                        last_name VARCHAR(255) NOT NULL,
                                        second_name VARCHAR(255) NOT NULL,
                                        rank VARCHAR(255) NOT NULL,
                                        post VARCHAR(255) NOT NULL,
                                        role VARCHAR(255) NOT NULL ,
                                        email VARCHAR(255) NOT NULL ,
                                        password VARCHAR(255) NOT NULL ,
                                        age INTEGER NOT NULL,
                                        passport_number VARCHAR(255) NOT NULL,
                                        plat_group_id INTEGER UNIQUE,
                                        company_commander_id INTEGER UNIQUE ,
                                        company_group_id INTEGER
);

CREATE TABLE project.plat_group (
                                    plat_group_id SERIAL PRIMARY KEY,
                                    personnel_count INTEGER,
                                    ammo_556x45ar_count INTEGER,
                                    ammo_545x39ak_rpk_count INTEGER,
                                    ammo_762x39ak_count INTEGER,
                                    ammo_145kpvt_count INTEGER,
                                    ammo_40mm_rpg_count INTEGER,
                                    ammo_40mm_gp_count INTEGER,
                                    ammo_762pkt INTEGER,
                                    defensive_grenades_count INTEGER,
                                    offensive_grenades_count INTEGER,
                                    body_armor_count INTEGER,
                                    helmets_count INTEGER,
                                    rifles_count INTEGER,
                                    machine_guns_count INTEGER,
                                    dry_rations_count INTEGER,
                                    food_count INTEGER,
                                    tank_count INTEGER,
                                    apc_count INTEGER,
                                    plat_commander_id INTEGER UNIQUE,
                                    company_group_id INTEGER NOT NULL
);

CREATE TABLE project.logistic_commander (
                                            logistic_commander_id SERIAL PRIMARY KEY,
                                            first_name VARCHAR(255)NOT NULL,
                                            last_name VARCHAR(255) NOT NULL,
                                            second_name VARCHAR(255) NOT NULL,
                                            rank VARCHAR(255) NOT NULL,
                                            post VARCHAR(255) NOT NULL,
                                            role VARCHAR(255) NOT NULL ,
                                            email VARCHAR(255) NOT NULL ,
                                            password VARCHAR(255) NOT NULL ,
                                            age INTEGER NOT NULL,
                                            passport_number VARCHAR(255) NOT NULL,
                                            logistic_company_id INTEGER UNIQUE,
                                            brigade_commander_id INTEGER UNIQUE ,
                                            brigade_group_id INTEGER
);


CREATE TABLE project.logistic_company (
                                          logistic_company_id SERIAL PRIMARY KEY,
                                          personnel_count INTEGER ,
                                          ammo_556x45ar_count INTEGER,
                                          ammo_545x39ak_rpk_count INTEGER,
                                          ammo_762x39ak_count INTEGER,
                                          ammo_145kpvt_count INTEGER,
                                          ammo_40mm_rpg_count INTEGER,
                                          ammo_40mm_gp_count INTEGER,
                                          ammo_762pkt INTEGER,
                                          defensive_grenades_count INTEGER,
                                          offensive_grenades_count INTEGER,
                                          body_armor_count INTEGER,
                                          helmets_count INTEGER,
                                          rifles_count INTEGER,
                                          machine_guns_count INTEGER,
                                          dry_rations_count INTEGER,
                                          food_count INTEGER,
                                          tank_count INTEGER,
                                          apc_count INTEGER,
                                          logistic_commander_id INTEGER UNIQUE,
                                          brigade_group_id INTEGER  NOT NULL
);