INSERT INTO client_account (full_name, gender, status, create_dttm, modify_dttm) VALUES
('Иван Иванов', 'M', 'ACTIVE', NOW() - INTERVAL '10 days', NOW() - INTERVAL '10 days'),
('Мария Петрова', 'F', 'ACTIVE', NOW() - INTERVAL '7 days', NOW() - INTERVAL '7 days'),
('Алексей Смирнов', 'M', 'ACTIVE', NOW() - INTERVAL '5 days', NOW() - INTERVAL '3 days'),
('Екатерина Козлова', 'F', 'ACTIVE', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days'),
('Дмитрий Волков', 'M', 'INACTIVE', NOW() - INTERVAL '30 days', NOW() - INTERVAL '1 day');
