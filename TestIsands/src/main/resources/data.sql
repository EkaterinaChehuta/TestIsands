INSERT INTO bank(id, bank_name, bik, city, street, house_number)
VALUES(1, 'МДМ', '1234', 'Томск', 'Ленина', '2'), (2, 'СБЕР', '4321', 'Томск', 'Ленина', '1');

INSERT INTO post(id, post_name, is_archived)
VALUES(1, 'программист', 0), (2, 'тестировщик', 0);

INSERT INTO employee(id, last_name, first_name, patronymic, gender, date_of_birth,
                                post_id, date_of_employment, salary,mobile_phone_number, bank_id, is_archived)
VALUES(1, 'Иванов', 'Иван', 'Иванович', 'М', '1988-12-12', 1, '2020-02-02', 30000, '81234567890', 1, 0),
      (2, 'Петрова', 'Ирина', 'Ивановна', 'Ж', '1988-12-12', 2, '2020-02-02', 30000, '81234567890', 1, 0),
	  (3, 'Кулаков', 'Петр', 'Иванович', 'М', '1988-12-12', 2, '2020-02-02', 30000, '81234567890', 2, 0),
	  (4, 'Логинов', 'Александр', 'Иванович', 'М', '1988-12-12', 1, '2020-02-02', 30000, '81234567890', 2, 0);
