create schema IF NOT EXISTS public;
CREATE TABLE IF NOT EXISTS broker (
                                      id SERIAL,
                                      name VARCHAR(50),
                                      country VARCHAR(100),
                                      state boolean,
                                      primary key (id)
);
CREATE TABLE IF NOT EXISTS stock_exchange(
                                             id SERIAL,
                                             name VARCHAR(50),
                                             territory varchar(100),
                                             currency varchar(25),
                                             primary key (id)
);
CREATE TABLE IF NOT EXISTS rate(
                                   id SERIAL,
                                   name VARCHAR(50),
                                   commission real,
                                   cost integer,
                                   broker_id integer,
                                   primary key (id),
                                   FOREIGN KEY (broker_id)
                                       REFERENCES broker (id)
                                       ON DELETE CASCADE
                                       ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS account(
                                      id SERIAL,
                                      broker_id integer,
                                      cash decimal(10, 2),
                                      margin boolean,
                                      rate_id integer default 0,
                                      primary key (id),
                                      FOREIGN KEY (broker_id)
                                          REFERENCES broker (id)
                                          ON DELETE CASCADE
                                          ON UPDATE NO ACTION,
                                      FOREIGN KEY (rate_id)
                                          REFERENCES rate (id)
                                          ON DELETE SET DEFAULT
                                          ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS client(
                                     id SERIAL,
                                     account_id integer,
                                     name VARCHAR(50),
                                     surname varchar(50),
                                     birth_date date,
                                     primary key (id),
                                     FOREIGN KEY (account_id)
                                         REFERENCES account (id)
                                         ON DELETE CASCADE
                                         ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS share(
                                    id SERIAL,
                                    name VARCHAR(50),
                                    stock_exchange_id integer,
                                    cost decimal(10,2),
                                    count int,
                                    primary key (id),
                                    FOREIGN KEY (stock_exchange_id)
                                        REFERENCES public.stock_exchange (id)
                                        ON DELETE CASCADE
                                        ON UPDATE NO ACTION
);
CREATE TABLE IF NOT EXISTS operation(
                                        id SERIAL,
                                        client_id integer,
                                        share_id integer,
                                        count integer default 1,
                                        buy_sell char(1),
                                        primary key (id),
                                        FOREIGN KEY (client_id)
                                            REFERENCES client (id)
                                            ON DELETE NO ACTION
                                            ON UPDATE NO ACTION,
                                        FOREIGN KEY (share_id)
                                            REFERENCES share (id)
                                            ON DELETE NO ACTION
                                            ON UPDATE NO ACTION,
                                        Constraint bs Check(buy_sell In ('b','s'))
);
ALTER TABLE client DROP COLUMN account_id;
ALTER TABLE account ADD COLUMN client_id integer;
ALTER TABLE account ADD CONSTRAINT fk_grade_id FOREIGN KEY (client_id) REFERENCES client(id);

CREATE TABLE IF NOT EXISTS holiday(
                                      id SERIAL,
                                      day date,
                                      primary key (id)
);
ALTER TABLE stock_exchange ADD COLUMN day_start time default '10:00';
ALTER TABLE stock_exchange ADD COLUMN day_end time default '18:00';
CREATE TABLE IF NOT EXISTS holiday_stock_exchange(
                                                     holiday_id integer,
                                                     stock_exchange integer,
                                                     primary key (holiday_id, stock_exchange),
                                                     FOREIGN KEY (stock_exchange)
                                                         REFERENCES public.stock_exchange (id)
                                                         ON DELETE CASCADE
                                                         ON UPDATE NO ACTION,
                                                     FOREIGN KEY (holiday_id)
                                                         REFERENCES public.holiday (id)
                                                         ON DELETE CASCADE
                                                         ON UPDATE NO ACTION
);
INSERT INTO broker (name, country, state) VALUES ('Тинькофф', 'Россия', false);
INSERT INTO broker (name, country, state) VALUES ('Альфа-банк', 'Россия', false);
INSERT INTO broker (name, country, state) VALUES ('Сбербанк', 'Россия', true);
INSERT INTO broker (name, country, state) VALUES ('ВТБ', 'Россия', true);
INSERT INTO broker (name, country, state) VALUES ('Robinhood', 'USA', false);
INSERT INTO broker (name, country, state) VALUES ('СПБ', 'Россия', false);
INSERT INTO rate (name, commission, cost, broker_id) VALUES ('Инвестор', 0.3, 0, 1);
INSERT INTO rate (name, commission, cost, broker_id) VALUES ('Инвестор', 0.25, 0, 3);
INSERT INTO rate (name, commission, cost, broker_id) VALUES ('Трейдер', 0.05, 300, 1);
insert into rate (name, commission, cost, broker_id) VALUES ('Trader', 0.00, 0, 5);
INSERT INTO rate (name, commission, cost, broker_id) VALUES ('Investor', 0.05, 300, 1);
INSERT INTO stock_exchange (name, territory, currency) VALUES ('Московская биржа', 'Россия', 'RUB');
INSERT INTO stock_exchange (name, territory, currency) VALUES ('Санкт-петербургская биржа', 'Россия', 'DOLLAR');
INSERT INTO stock_exchange (name, territory, currency) VALUES ('NY', 'USA', 'DOLLAR');
INSERT INTO stock_exchange (name, territory, currency) VALUES ('Shanghai', 'CHINA', 'DOLLAR');
INSERT INTO share (name, stock_exchange_id, cost, count) VALUES ('Энел', 2, 0.85, 100);
INSERT INTO share (name, stock_exchange_id, cost, count) VALUES ('NY BANK', 3, 2500, 1);
INSERT INTO share (name, stock_exchange_id, cost, count) VALUES ('Bank of America', 3, 1700, 1);
INSERT INTO share (name, stock_exchange_id, cost, count) VALUES ('JD.com', 3, 7000, 1);
INSERT INTO client (name, surname, birth_date) VALUES ('Daniil', 'Tkachenko', '2001-04-24');
INSERT INTO client (name, surname, birth_date) VALUES ('Petr', 'the First', '1672-06-09');
INSERT INTO client (name, surname, birth_date) VALUES ('Gleb', 'Tkachenko', '2009-08-05');
INSERT INTO client (name, surname, birth_date) VALUES ('Kirill', 'Tkachenko', '2009-08-05');
INSERT INTO operation (client_id, share_id, count, buy_sell) VALUES (2, 1, 100, 'b');
INSERT INTO operation (client_id, share_id, count, buy_sell) VALUES (2, 1, 50, 's');
INSERT INTO operation (client_id, share_id, count, buy_sell) VALUES (3, 4, 1000, 'b');
INSERT INTO operation (client_id, share_id, count, buy_sell) VALUES (4, 3, 678, 'b');
INSERT INTO account (broker_id, cash, margin, rate_id, client_id) VALUES (1, 145675.20, true, 1, 2);
INSERT INTO account (broker_id, cash, margin, rate_id, client_id) VALUES (3, 0.78, false, 3, 3);
INSERT INTO account (broker_id, cash, margin, rate_id, client_id) VALUES (1, 23145675.34, true, 4, 3);
INSERT INTO account (broker_id, cash, margin, rate_id, client_id) VALUES (5, 67949585.67, true, 5, 4);
INSERT INTO holiday (day) VALUES ('2021-09-23');
INSERT INTO holiday (day) VALUES ('2021-02-12');
INSERT INTO holiday (day) VALUES ('2021-11-09');
INSERT INTO holiday (day) VALUES ('2021-08-25');
INSERT INTO holiday (day) VALUES ('2021-10-10');
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (1, 1);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (1, 2);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (2, 2);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (3, 4);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (4, 3);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (3, 3);
INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES (2, 3);






