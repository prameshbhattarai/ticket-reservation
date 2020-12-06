INSERT INTO reservation_user(id, username, password) values
('1', 'adam', '$2a$10$valhjlaMsYXf9aiAXpVctufTT9rLRnXpTuB93StgUsQM5EVEUYTaS'),
('2', 'johnson', '$2a$10$valhjlaMsYXf9aiAXpVctufTT9rLRnXpTuB93StgUsQM5EVEUYTaS'),
('3', 'patrick', '$2a$10$valhjlaMsYXf9aiAXpVctufTT9rLRnXpTuB93StgUsQM5EVEUYTaS');


INSERT INTO reservation(id, reservation_from, reservation_to, amount, reserved_by) values
('1', 'kathmandu', 'pokhara', 1000, '1'),
('2', 'pokhara', 'jomson', 1500, '1'),
('3', 'jomson', 'mustang', 2000, '1'),

('4', 'kathmandu', 'solukhumbu', 1000, '2'),
('5', 'solukhumbu', 'namche', 1500, '2'),

('6', 'kathmandu', 'pokhara', 1000, '3'),
('7', 'pokhara', 'annapurna', 1500, '3');

INSERT INTO reservation_payment(id, payment_amount, reservation) values
('1', 1000, '1'),
('2', 1500, '2'),
('3', 2000, '3'),

('4', 5000, '4'),
('5', 6000, '5'),

('6', 1000, '6'),
('7', 1500, '7');


